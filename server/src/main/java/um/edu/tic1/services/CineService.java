package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import um.edu.tic1.commons.DTO.CineDTO;
import um.edu.tic1.entities.Cine;
import um.edu.tic1.repositories.CineRepository;
import um.edu.tic1.repositories.SalasRepository;

import java.util.List;

@RestController
@RequestMapping("/cine")
public class CineService{

    @Autowired
    private CineRepository cr;

    @PostMapping("/save")
    public void save(@RequestBody Cine  cine) {
        cr.save(cine);
    }

    @GetMapping("/findAll")
    public List<Cine> findAll() {
        return cr.findAll();
    }

    @GetMapping("/findById/{id}")
    public CineDTO findById(@PathVariable("id") String id){
        Cine mov = cr.findById(Long.parseLong(id)).get();
        return mov.toDTO();
    }

    @DeleteMapping(value = "/{id}")
    public void eliminar(@PathVariable Long id){
        cr.deleteById(id);
    }

}
