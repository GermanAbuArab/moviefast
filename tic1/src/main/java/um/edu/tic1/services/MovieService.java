package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.repositories.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository mr;

    public MovieRepository getMovieRepository() {
        return mr;
    }


    public void save(Movie movie) {
        mr.save(movie);
    }

    public List<Movie> findAll() {
        return mr.findAll();
    }

}
