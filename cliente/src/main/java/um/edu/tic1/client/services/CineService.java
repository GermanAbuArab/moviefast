package um.edu.tic1.client.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Cine;
import um.edu.tic1.commons.DTO.CineDTO;
import um.edu.tic1.commons.DTO.MovieDTO;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CineService {

    public void save(Cine cine) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<CineDTO> body = new HttpEntity<>(
                cine.toDTO());
        ResponseEntity<String> response =  restTemplate.exchange("http://localhost:8081/cine/saveCine", HttpMethod.POST, body, String.class);

        System.out.println("RestTemplate response : " + response.getBody());
    }


    public List<Cine> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CineDTO>> response = restTemplate.exchange(
                "http://localhost:8081/cine/findAll", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<CineDTO>>() {
                });
        List<CineDTO> movies = response.getBody();
        return movies.stream().map(Cine::new).collect(Collectors.toList());
    }

    public Cine findById(Long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CineDTO> response = restTemplate.exchange(
                "http://localhost:8081/movie/findByID/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<CineDTO>(){});
        CineDTO user = response.getBody();

        return new Cine(user);
    }



}
