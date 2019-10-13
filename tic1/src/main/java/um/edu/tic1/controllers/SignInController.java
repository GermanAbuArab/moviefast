package um.edu.tic1.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.tic1.Tic1Application;
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
    private PasswordField password;

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

        if (us.getUr().findByName(userName)!=null) {
            if (us.getUr().findByName(userName).getUserName().equals(userName)) {
                AlertBox.display("Registro","Lo siento,ese usuario ya exsite,porfavor elija otro");
                Nombre.clear();
                nombreUsuario.clear();
                password.clear();
            }
        }else {
            us.save(usuario);
            Nombre.clear();
            nombreUsuario.clear();
            password.clear();
            AlertBox.display("Registro", "Registro Completo");
        }

        //todo al crear nuevo usuario habria que cambiar de escena


    }

    @FXML
    private void volver(ActionEvent event)throws IOException {  // vuelve a la scena

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/viewFilms.fxml"));
        inicio.getStylesheets().add("/templates/styles.css");
        Scene inicioScene = new Scene(inicio,800,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();

    }

}
