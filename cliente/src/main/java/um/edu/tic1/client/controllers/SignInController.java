package um.edu.tic1.client.controllers;


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
import um.edu.tic1.client.ClientApplication;
import um.edu.tic1.client.models.ClienteFinal;
import um.edu.tic1.client.services.UsuarioService;
import um.edu.tic1.commons.DTO.UsuarioDTO;


import java.io.IOException;

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
    Image ImagenMovieFast = new Image("assets/icono_movieFast.png");
    @FXML
    private ImageView imagenMovieFast;

    @Autowired
    private UsuarioService us;

    public void initialize() {

        imagenMovieFast.setFitHeight(30);
        imagenMovieFast.setFitWidth(30);
        imagenMovieFast.setImage(ImagenMovieFast);


    }

    @FXML
    void signIn(ActionEvent event)throws IOException {

        String nombre = Nombre.getText();
        String userName = nombreUsuario.getText();
        String contra = password.getText();
        // TODO: 25/11/2019  
        ClienteFinal usuario = new ClienteFinal(userName,nombre,contra);

        UsuarioDTO userdto = us.findByUserName(userName);


        if (userdto!=null) {
            if (userdto.getUserName().equals(userName)) {
                AlertBox.display("Registro","Lo siento,ese usuario ya exsite,porfavor elija otro");
                Nombre.clear();
                nombreUsuario.clear();
                password.clear();
            }
        }else {
            us.saveFinal(usuario);
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
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/login.fxml"));
        inicio.getStylesheets().add("/templates/styles.css");
        Scene inicioScene = new Scene(inicio,1000,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();

    }

}
