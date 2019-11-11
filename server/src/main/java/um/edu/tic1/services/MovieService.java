package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.repositories.MovieRepository;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieService {

    @Autowired
    private MovieRepository mr;

    public MovieRepository getMovieRepository() {
        return mr;
    }

    @PostMapping
    public void save(@RequestBody Movie movie) {
        mr.save(movie);
    }

    @GetMapping
    public List<Movie> findAll() {
        return mr.findAll();
    }

}
