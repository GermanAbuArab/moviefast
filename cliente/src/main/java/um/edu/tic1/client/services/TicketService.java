package um.edu.tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Ticket;
import um.edu.tic1.commons.DTO.TicketDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    public void save(Ticket ticket) {
        RestTemplate restTemplate= new RestTemplate();
        HttpEntity<TicketDTO> body = new HttpEntity<>(
                ticket.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8081/ticket/saveTicket", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    public List<Ticket> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<TicketDTO>> response = restTemplate.exchange(
                "http://localhost:8081/ticket/findAll", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TicketDTO>>(){});
        List<TicketDTO> salas = response.getBody();
        return salas.stream().map(Ticket::new).collect(Collectors.toList());
    }

}
