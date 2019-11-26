package um.edu.tic1.client.services;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import um.edu.tic1.client.models.Movie;
import um.edu.tic1.commons.DTO.MovieDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {


    public void save(Movie movie) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MovieDTO> body = new HttpEntity<>(
                movie.toDTO());
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8080/movie", HttpMethod.POST, body, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }

    //@Deprecated
    public List<Movie> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<MovieDTO>> response = restTemplate.exchange(
                "http://localhost:8080/movie", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<MovieDTO>>() {
                });
        List<MovieDTO> movies = response.getBody();
        return movies.stream().map(Movie::new).collect(Collectors.toList());
    }

    public Movie findById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MovieDTO> response = restTemplate.exchange(
                "http://localhost:8080/movie/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<MovieDTO>() {
                });
        MovieDTO user = response.getBody();

        return new Movie(user);
    }

    public void delete(Long id) {

        RestTemplate restTemplate =
                new RestTemplate();
        ResponseEntity<String> response =  restTemplate.exchange("http://localhost:8080/movie/"+id, HttpMethod.DELETE, null, String.class);
        System.out.println("RestTemplate response : " + response.getBody());
    }
}
