package um.edu.tic1.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.tic1.client.models.Cine;
import um.edu.tic1.client.rest.Rest;

@Service
public class CineService {


    @Autowired
    Rest rest;

    public void save(Cine cine) {

        rest.saveCine(cine);
    }
}
