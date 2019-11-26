package um.edu.tic1.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Funcion;

import java.io.IOException;
import java.util.List;

@Service
public class FuncionService {
    ObjectMapper mapper=new ObjectMapper();

    TypeFactory factory= mapper.getTypeFactory();

    public List<Funcion> findAll(){
        RestTemplate restTemplate=
                new RestTemplate();
        ResponseEntity<String> response=
                restTemplate.exchange("http://localhost:8090/cine/findAll", HttpMethod.GET,null, String.class);

        CollectionType listType=
                factory.constructCollectionType(List.class,Funcion.class);
        List<Funcion> list=null;

        try{
            list=mapper.readValue(response.getBody(),listType);
        }catch (JsonProcessingException e){

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }




}
