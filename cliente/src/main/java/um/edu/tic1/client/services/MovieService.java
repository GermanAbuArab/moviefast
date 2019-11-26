package um.edu.tic1.client.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Movie;
import um.edu.tic1.commons.DTO.MovieDTO;

@Service
public class MovieService {


    public void save(Movie movie)
    {
        RestTemplate restTemplate= new RestTemplate();
        HttpEntity<MovieDTO> body = new HttpEntity<>(
                movie.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/movie", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());


    }
}
