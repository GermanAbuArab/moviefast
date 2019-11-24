package um.edu.tic1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.edu.tic1.entities.Sala;
@Repository
public interface SalasRepository extends JpaRepository<Sala,Long> {


}
