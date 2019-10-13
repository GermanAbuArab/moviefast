package um.edu.tic1.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.tic1.entities.Usuario;
import um.edu.tic1.services.UsuarioService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SignInController {

    @FXML
    private TextField Nombre;

    @FXML
    private TextField nombreUsuario;

    @FXML
    private TextField password;

    @FXML
    private Button signIn;

    @FXML
    private ImageView imagenFondo;

    @Autowired
    private UsuarioService us;

    public void initialize() {

        Image imagenCine = new Image("cinema.jpg");

        imagenFondo.setImage(imagenCine);
    }

    @FXML
    void signIn(ActionEvent event)throws IOException {

        String nombre = Nombre.getText();
        String userName = nombreUsuario.getText();
        String contra = password.getText();

        Usuario usuario = new Usuario(userName,nombre,contra);

        us.save(usuario);

        Nombre.clear();
        nombreUsuario.clear();
        password.clear();

        //todo al crear nuevo usuario habria que cambiar de escena

    }

}
