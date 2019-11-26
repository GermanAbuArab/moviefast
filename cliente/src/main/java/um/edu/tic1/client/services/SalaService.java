package um.edu.tic1.client.services;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Funcion;
import um.edu.tic1.client.models.Movie;
import um.edu.tic1.client.models.Sala;
import um.edu.tic1.commons.DTO.FuncionDTO;
import um.edu.tic1.commons.DTO.MovieDTO;
import um.edu.tic1.commons.DTO.SalaDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaService {


    public void save(Sala sala) {
        RestTemplate restTemplate= new RestTemplate();
        HttpEntity<SalaDTO> body = new HttpEntity<>(
                sala.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/sala/saveMovie", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }


    //@Deprecated
    public List<Sala> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<SalaDTO>> response = restTemplate.exchange(
                "http://localhost:8080/sala/findAll", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<SalaDTO>>(){});
        List<SalaDTO> salas = response.getBody();
        return salas.stream().map(Sala::new).collect(Collectors.toList());
    }

    public Sala findById(Long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SalaDTO> response = restTemplate.exchange(
                "http://localhost:8080/movie/"+id, HttpMethod.GET, null, new ParameterizedTypeReference<SalaDTO>(){});
        SalaDTO user = response.getBody();

        return new Sala(user);
    }


}
