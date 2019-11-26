package um.edu.tic1.client.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Ticket;
import um.edu.tic1.commons.DTO.TicketDTO;

@Service
public class TicketService {

    public void save(Ticket ticket) {
        RestTemplate restTemplate= new RestTemplate();
        HttpEntity<TicketDTO> body = new HttpEntity<>(
                ticket.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/sala", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

}
