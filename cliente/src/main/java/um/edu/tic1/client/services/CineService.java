package um.edu.tic1.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Cine;
import um.edu.tic1.client.rest.Rest;

import java.io.IOException;
import java.util.List;

@Service
public class CineService {

    ObjectMapper mapper=new ObjectMapper();

    TypeFactory factory= mapper.getTypeFactory();

    @Autowired
    Rest rest;

    public void save(Cine cine) {

        rest.saveCine(cine);
    }

    public List<Cine> findAll() throws IOException {
        RestTemplate restTemplate=
                new RestTemplate();
        ResponseEntity<String> response=
                restTemplate.exchange("http://localhost:8080/cine/findAll", HttpMethod.GET,null, String.class);

        CollectionType listType=
                factory.constructCollectionType(List.class,Cine.class);
        List<Cine> list=null;

        try{
            list=mapper.readValue(response.getBody(),listType);
        }catch (JsonProcessingException e){

        }

        return list;
    }


}
