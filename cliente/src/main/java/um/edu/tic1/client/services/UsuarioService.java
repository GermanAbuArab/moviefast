package um.edu.tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.ClienteFinal;
import um.edu.tic1.commons.DTO.UsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioService {

    public UsuarioDTO findByUserName(String user) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UsuarioDTO> response = restTemplate.exchange(
                "http://localhost:8080/user/"+user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UsuarioDTO>(){});
        UsuarioDTO usuario = response.getBody();

        return usuario;

    }


    public void saveFinal(ClienteFinal usuario) {
        usuario.toDTO();
    }
}
