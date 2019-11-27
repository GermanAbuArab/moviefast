package um.edu.tic1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import um.edu.tic1.commons.DTO.CineDTO;
import um.edu.tic1.commons.DTO.FuncionDTO;
import um.edu.tic1.entities.Cine;
import um.edu.tic1.entities.Funcion;
import um.edu.tic1.repositories.FuncionRepository;
import um.edu.tic1.repositories.MovieRepository;
import um.edu.tic1.repositories.SalasRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/funcion")
public class  FuncionService {

    @Autowired
    private FuncionRepository fr;

    @Autowired
    private MovieRepository mr;

    @Autowired
    private SalasRepository sr;

    @PostMapping("/saveFuncion")
    public void save(@RequestBody FuncionDTO funcion) {
        Funcion dev = new Funcion(funcion);

        dev.setSala(sr.findById(funcion.getSalaId()).get());
        dev.setMovie(mr.findById(funcion.getMovieId()).get());

        fr.save(dev);
    }

    @GetMapping("/findAll")
    public List<FuncionDTO> findAll() {
        List<Funcion> lista = fr.findAll();
        return lista.stream().map(Funcion::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/findById/{id}")
    public FuncionDTO findById(@PathVariable("id") String id){
        Funcion mov = fr.findById(Long.parseLong(id)).get();
        return mov.toDTO();
    }

    @DeleteMapping(value = "/{id}")
    public void eliminar(@PathVariable Long id){
        fr.deleteById(id);
    }

    @PostMapping("/updateFuncion")
    public void update(@RequestBody FuncionDTO funcion) {
            Funcion base=fr.findById(funcion.getId()).get();
            base.setButacas(funcion.getButacas());
            fr.save(base);
    }


}
