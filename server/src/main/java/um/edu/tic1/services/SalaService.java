package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import um.edu.tic1.entities.Sala;
import um.edu.tic1.repositories.SalasRepository;

import java.util.List;

@RestController
@RequestMapping("/sala")
public class SalaService {
    @Autowired
    private SalasRepository sr;

    public void save(@RequestBody Sala sala) {
        sr.save(sala);
    }
    public SalasRepository getMovieRepository() {
        return sr;
    }
    @GetMapping
    public List<Sala> findAll() {
        return sr.findAll();
    }
}
