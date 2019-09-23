package SpringApplication.Repositories;

import SpringApplication.Entities.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
}
