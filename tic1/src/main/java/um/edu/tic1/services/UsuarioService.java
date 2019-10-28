package um.edu.tic1.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.edu.tic1.entities.AdminEmpresa;
import um.edu.tic1.entities.ClienteFinal;
import um.edu.tic1.entities.Usuario;
import um.edu.tic1.entities.UsuarioCine;
import um.edu.tic1.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository ur;

    public UsuarioRepository getUr() {
        return ur;
    }

    public void save(AdminEmpresa usuario) {
        ur.save(usuario);
    }
    public void save(ClienteFinal usuario) {
        ur.save(usuario);
    }
    public void save(UsuarioCine usuario) {
        ur.save(usuario);
    }

    public void delete(Usuario usuario){
               ur.delete(usuario);
    }


    public List<Usuario> findAll() {
        return ur.findAll();
    }
}
