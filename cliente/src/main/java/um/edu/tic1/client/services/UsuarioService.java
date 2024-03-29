package um.edu.tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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
                "http://localhost:8081/usuario/Final/" + user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<UsuarioDTO>() {
                });

        return response.getBody();

    }

    public ClienteFinal findByUserNameFinal(String user) {
        UsuarioDTO dto= findByUserName(user);
        if (dto != null) {
            return new ClienteFinal(dto);
        } else {
            return null;
        }
    }


    public void saveFinal(ClienteFinal usuario) {
        System.out.println("Nombre: "+usuario.getName());
        System.out.println("Pass: "+usuario.getPassword());
        System.out.println("Username: "+usuario.getUserName());
        RestTemplate restTemplate =
                new RestTemplate();
        HttpEntity<UsuarioDTO> body = new HttpEntity<>(
                usuario.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8081/usuario/saveFinal", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());

    }
}
