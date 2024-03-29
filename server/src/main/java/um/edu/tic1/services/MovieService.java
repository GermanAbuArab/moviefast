package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import um.edu.tic1.commons.DTO.MovieDTO;
import um.edu.tic1.commons.DTO.UsuarioDTO;
import um.edu.tic1.entities.ClienteFinal;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.repositories.MovieRepository;

import java.security.acl.LastOwnerException;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieService {

    @Autowired
    private MovieRepository mr;

    @PostMapping(path = "/saveMovie")
    public void save(@RequestBody MovieDTO movie) {
        mr.save(new Movie(movie));
    }


    @GetMapping("/findAll")
    public List<Movie> findAll() {
        return mr.findAll();
    }

    @GetMapping("/findById/{id}")
    public MovieDTO findById(@PathVariable("id") long id){
        Movie mov = mr.findById(id).get();
        return mov.toDTO();
    }

    @DeleteMapping("delete/{id}")
    public void eliminar(@PathVariable Long id){
        mr.deleteById(id);
    }


}
