package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.tic1.entities.Cine;
import um.edu.tic1.repositories.CineRepository;
import um.edu.tic1.repositories.SalasRepository;

import java.util.List;

@Service
public class CineService{

    @Autowired
    private CineRepository cr;


    public CineRepository getMovieRepository() {
        return cr;
    }


    public void save(Cine cine) {
        cr.save(cine);
    }


    public List<Cine> findAll() {
        return cr.findAll();
    }

}
