package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.tic1.entities.Funcion;
import um.edu.tic1.repositories.FuncionRepository;

import java.util.List;

@Service
public class FuncionService {

    @Autowired
    private FuncionRepository fr;

    public void save(Funcion funcion) {
        fr.save(funcion);
    }

    public FuncionRepository getMovieRepository() {
        return fr;
    }
    public List<Funcion> findAll() {
        return fr.findAll();
    }


}
