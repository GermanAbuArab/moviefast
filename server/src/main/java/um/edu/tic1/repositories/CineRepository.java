package um.edu.tic1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import um.edu.tic1.entities.Cine;
import um.edu.tic1.entities.Movie;


import java.util.List;

@Repository
public interface CineRepository extends JpaRepository<Cine,Long> {
    List<Cine> findAllByName(String name);
    Cine findByName(String name);

    @Override
    List<Cine> findAll();
}
