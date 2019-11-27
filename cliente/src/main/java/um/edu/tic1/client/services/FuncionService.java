package um.edu.tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Funcion;
import um.edu.tic1.commons.DTO.FuncionDTO;
import um.edu.tic1.commons.DTO.MovieDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionService {

    public void save(Funcion funcion) {
        RestTemplate restTemplate= new RestTemplate();
        HttpEntity<FuncionDTO> body = new HttpEntity<>(funcion.toDTO());
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8081/funcion/saveFuncion", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }


    //@Deprecated
    public List<Funcion> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<FuncionDTO>> response = restTemplate.exchange(
                "http://localhost:8081/funcion/findAll", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<FuncionDTO>>(){});
        List<FuncionDTO> funciones = response.getBody();
        return funciones.stream().map(Funcion::new).collect(Collectors.toList());
    }

    public void deleteFuncion(long id) {
        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8081/funcion/delete"+id, HttpMethod.DELETE, null, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    public Funcion findById(Long id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FuncionDTO> response = restTemplate.exchange(
                "http://localhost:8081/funcion/findById"+id, HttpMethod.GET, null, new ParameterizedTypeReference<FuncionDTO>(){});
        FuncionDTO func = response.getBody();

        return new Funcion(func);
    }


    public void update(Funcion funcionAux) {
        RestTemplate restTemplate= new RestTemplate();
        HttpEntity<FuncionDTO> body = new HttpEntity<>(funcionAux.toDTO());
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8081/funcion/updateFuncion", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }
}
