package um.edu.tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Funcion;
import um.edu.tic1.commons.DTO.FuncionDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionService {

    //@Deprecated
    public List<Funcion> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<FuncionDTO>> response = restTemplate.exchange(
                "http://localhost:8080/funcion", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<FuncionDTO>>(){});
        List<FuncionDTO> funciones = response.getBody();
        return funciones.stream().map(Funcion::new).collect(Collectors.toList());
    }

    public void deleteFuncion(long id) {
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/funcion/"+id, HttpMethod.DELETE, null, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }



}
