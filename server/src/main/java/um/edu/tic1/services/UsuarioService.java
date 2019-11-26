package um.edu.tic1.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import um.edu.tic1.commons.DTO.UsuarioDTO;
import um.edu.tic1.entities.AdminEmpresa;
import um.edu.tic1.entities.ClienteFinal;
import um.edu.tic1.entities.Usuario;
import um.edu.tic1.entities.UsuarioCine;
import um.edu.tic1.repositories.UsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioService {

    @Autowired
    private UsuarioRepository ur;

    public UsuarioRepository getUr() {
        return ur;
    }
    @PostMapping(path = "/saveAdmin")
    public void save(@RequestBody AdminEmpresa usuario) {
        ur.save(usuario);
    }
    @PostMapping(path = "/saveClient")
    public void saveCliente(@RequestBody ClienteFinal usuario) {
        ur.save(usuario);
    }
    @PostMapping(path = "/saveFinal")
    public void saveUsuario(@RequestBody UsuarioDTO usuario) {
        ur.save(new ClienteFinal(usuario));
    }
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id){
               ur.deleteById(id);
    }

    @GetMapping
    public List<Usuario> findAll() {
        return ur.findAll();
    }

    @GetMapping("/Final/{id}" )
    public Usuario findByUserName(@PathVariable("id") String id){
        return ur.findByUserName(id);
    }
}
