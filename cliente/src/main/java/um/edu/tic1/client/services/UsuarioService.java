package um.edu.tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioService {

    public Object findByUserName(String user) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
                "http://localhost:8080/user/"+name,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDTO>>(){});
        List<UserDTO> users = response.getBody();

        return users.stream()
                .map(User::new)
                .collect(Collectors.toList());
    }




}
