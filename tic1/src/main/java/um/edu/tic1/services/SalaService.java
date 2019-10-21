package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.tic1.entities.Sala;
import um.edu.tic1.repositories.SalasRepository;

import java.util.List;

@Service
public class SalaService {
    @Autowired
    private SalasRepository sr;

    public void save(Sala sala) {
        sr.save(sala);
    }
    public SalasRepository getMovieRepository() {
        return sr;
    }
    public List<Sala> findAll() {
        return sr.findAll();
    }
}
