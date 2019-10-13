package um.edu.tic1.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.tic1.entities.Usuario;
import um.edu.tic1.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository ur;

    public UsuarioRepository getUr() {
        return ur;
    }

    public void save(Usuario usuario) {
        ur.save(usuario);
    }


    public List<Usuario> findAll() {
        return ur.findAll();
    }
}
