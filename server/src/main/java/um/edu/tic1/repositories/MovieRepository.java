package um.edu.tic1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.tic1.entities.Movie;

import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findAllByName(String name);
    Movie findByName(String name);
}
