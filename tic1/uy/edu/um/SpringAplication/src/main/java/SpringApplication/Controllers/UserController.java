package SpringApplication.Controllers;


import SpringApplication.Entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import SpringApplication.Repositories.*;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController{


    @Autowired
    UserRepo repository;

    @PostMapping("/SpringApplication/Entities/Usuario")

    @RequestMapping(path="/SpringApplication/Entities/Usuario",method= RequestMethod.POST)
    public void save(@RequestBody Usuario usuario){
        repository.save(usuario);
    }
}
