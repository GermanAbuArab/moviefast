package um.edu.tic1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.edu.tic1.entities.Funcion;


import java.util.List;

@Repository
public interface FuncionRepository extends JpaRepository<Funcion,Long> {
    List<Funcion> findAllByName(String name);
    Funcion findByName(String name);
    List<Funcion> findAll();

}
