package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import um.edu.tic1.commons.DTO.TicketDTO;
import um.edu.tic1.entities.ClienteFinal;
import um.edu.tic1.entities.Ticket;
import um.edu.tic1.repositories.FuncionRepository;
import um.edu.tic1.repositories.TicketRepository;
import um.edu.tic1.repositories.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ticket")
public class TicketService {

    @Autowired
    private UsuarioRepository ur;
    @Autowired
    private FuncionRepository fr;
    @Autowired
    private TicketRepository tr;

    @PostMapping("/saveTicket")
    public void save(@RequestBody TicketDTO cine) {
        Ticket tick = new Ticket(cine);
        tick.setCliente(ur.findByUserName(cine.getClienteId()));
        tick.setFuncion(fr.findById((cine.getFuncionId())).get());
        tr.save(tick);
    }

    @GetMapping("/findAll")
    public List<TicketDTO> findAll() {
        List<Ticket> lista = tr.findAll();
        return lista.stream().map(Ticket::toDTO).collect(Collectors.toList());
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
