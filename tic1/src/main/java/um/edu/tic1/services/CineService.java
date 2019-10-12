package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.tic1.entities.Cine;
import um.edu.tic1.repositories.CineRepository;

import java.util.List;

@Service
public class CineService{

    @Autowired
    private CineRepository cs;

    public CineRepository getMovieRepository() {
        return cs;
    }


    public void save(Cine cine) {
        cs.save(cine);
    }


    public List<Cine> findAll() {
        return cs.findAll();
    }

}
