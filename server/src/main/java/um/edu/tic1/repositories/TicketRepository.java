package um.edu.tic1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import um.edu.tic1.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
