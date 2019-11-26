package um.edu.tic1.client.services;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Funcion;
import um.edu.tic1.client.models.Sala;
import um.edu.tic1.commons.DTO.FuncionDTO;
import um.edu.tic1.commons.DTO.SalaDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaService {

    //@Deprecated
    public List<Sala> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<SalaDTO>> response = restTemplate.exchange(
                "http://localhost:8080/sala", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<SalaDTO>>(){});
        List<SalaDTO> salas = response.getBody();
        return salas.stream().map(Sala::new).collect(Collectors.toList());
    }


}
