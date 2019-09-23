package SpringApplication.Controllers;


import SpringApplication.Entities.Pelicula;
import SpringApplication.Repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PeliculaController {

    @Autowired
    private PeliculaRepository peliculaRepository;


//    public PeliculaController(PeliculaRepository peliculaRepository){
//        this.peliculaRepository = peliculaRepository;
//    }

    @PostMapping("/pelicula")
    @RequestMapping(path="/pelicula", method = RequestMethod.POST)
    public void save(@RequestBody Pelicula pelicula){
        peliculaRepository.save(pelicula);
    }
}
