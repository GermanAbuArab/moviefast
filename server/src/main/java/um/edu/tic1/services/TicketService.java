package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import um.edu.tic1.commons.DTO.TicketDTO;
import um.edu.tic1.entities.Ticket;
import um.edu.tic1.repositories.TicketRepository;

import java.util.List;

public class TicketService {

    @Autowired
    private TicketRepository tr;

    @PostMapping("/save")
    public void save(@RequestBody Ticket cine) {
        tr.save(cine);
    }

    @GetMapping("/findAll")
    public List<Ticket> findAll() {
        return tr.findAll();
    }

    @GetMapping("/findById/{id}")
    public TicketDTO findById(@PathVariable("id") String id){
        Ticket mov = tr.findById(Long.parseLong(id)).get();
        return mov.toDTO();
    }

    @DeleteMapping(value = "/{id}")
    public void eliminar(@PathVariable Long id){
        tr.deleteById(id);
    }

}
