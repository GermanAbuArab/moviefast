package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import um.edu.tic1.entities.Funcion;
import um.edu.tic1.repositories.FuncionRepository;

import java.util.List;

@RestController
@RequestMapping("/funcion")
public class  FuncionService {

    @Autowired
    private FuncionRepository fr;
    @PostMapping
    public void save(@RequestBody Funcion funcion) {
        fr.save(funcion);
    }

    public FuncionRepository getMovieRepository() {
        return fr;
    }
    @GetMapping
    public List<Funcion> findAll() {
        return fr.findAll();
    }


}
