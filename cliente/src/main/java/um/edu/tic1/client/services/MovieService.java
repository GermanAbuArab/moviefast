package um.edu.tic1.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Cine;
import um.edu.tic1.client.models.Movie;

import java.io.IOException;
import java.util.List;

@Service
public class MovieService {

    ObjectMapper mapper=new ObjectMapper();

    TypeFactory factory= mapper.getTypeFactory();


    public List<Movie> findAll() throws IOException {
        RestTemplate restTemplate=
                new RestTemplate();
        ResponseEntity<String> response=
                restTemplate.exchange("http://localhost:8090/movie/findAll", HttpMethod.GET,null, String.class);

        CollectionType listType=
                factory.constructCollectionType(List.class,Movie.class);
        List<Movie> list=null;

        try{
            list=mapper.readValue(response.getBody(),listType);
        }catch (JsonProcessingException e){

        }

        return list;
    }


}
