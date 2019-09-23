package SpringApplication.Repositories;

import SpringApplication.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface UserRepo extends JpaRepository<Usuario,String> {

}

