package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import um.edu.tic1.commons.DTO.SalaDTO;
import um.edu.tic1.entities.Sala;
import um.edu.tic1.repositories.CineRepository;
import um.edu.tic1.repositories.SalasRepository;

import java.util.List;

@RestController
@RequestMapping("/sala")
public class SalaService {
    @Autowired
    private SalasRepository sr;
    @Autowired
    private CineRepository cr;

    @PostMapping(path = "/saveMovie")//ACA HAY ALGO RARO
    public void save(@RequestBody SalaDTO sala) {
        Sala aux = new Sala(sala);
        aux.setCine(cr.findById(sala.getCineId()).get());

        sr.save(aux);
    }
    public SalasRepository getMovieRepository() {
        return sr;
    }
    @GetMapping
    public List<Sala> findAll() {
        return sr.findAll();
    }

    @GetMapping("/findById/{id}")
    public SalaDTO findById(@PathVariable("id") String id){
        Sala mov = sr.findById(Long.parseLong(id)).get();
        return mov.toDTO();
    }

    @DeleteMapping(value = "/{id}")
    public void eliminar(@PathVariable Long id){
        sr.deleteById(id);
    }

}
