package um.edu.tic1.controllers;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import um.edu.tic1.entities.ClienteFinal;
import um.edu.tic1.repositories.UsuarioRepository;
import um.edu.tic1.services.UsuarioService;

import static org.junit.jupiter.api.Assertions.*;
class SingInTest {
    @Autowired
    private UsuarioService ur;

    @Test
    public void sing(){
        ClienteFinal usuariof = new ClienteFinal("PlatiniumPrime","German","1234");
        ur.save(usuariof);

    }

}