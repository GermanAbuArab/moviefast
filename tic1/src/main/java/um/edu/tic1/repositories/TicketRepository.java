package um.edu.tic1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import um.edu.tic1.entities.Ticket;

import java.util.List;

@Repository
public interface TicketRepository  extends JpaRepository<Ticket,Long> {
        List<Ticket> findAllById(Long name);

}
