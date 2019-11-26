package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.tic1.entities.Cine;
import um.edu.tic1.entities.Ticket;
import um.edu.tic1.repositories.CineRepository;
import um.edu.tic1.repositories.TicketRepository;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ts;

    public TicketRepository getTicketRepository() {
        return ts;
    }


    public void save(Ticket ticket) {

        ts.save(ticket);
    }


    public List<Ticket> findAll() {
        return ts.findAll();
    }


}
