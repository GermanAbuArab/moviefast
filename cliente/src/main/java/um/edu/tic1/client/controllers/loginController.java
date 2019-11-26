package um.edu.tic1.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.tic1.client.ClientApplication;
import um.edu.tic1.client.models.Cine;
import um.edu.tic1.client.models.ClienteFinal;
import um.edu.tic1.client.services.CineService;
import um.edu.tic1.client.services.UsuarioService;
import um.edu.tic1.commons.DTO.UsuarioDTO;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class loginController implements Initializable {

    @FXML
    Image ImagenMovieFast = new Image("assets/icono_movieFast.png");
    @FXML
    private ImageView imagenMovieFast;

    @FXML
    private TextField usuarioIngresado;

    @FXML
    private PasswordField contraseñaIngresada;
    @Autowired
    private UsuarioService us;

    @Autowired
    private CineService cs;

    @Autowired
    private vistaCinesController vc;

    @Autowired
    private vistaUsuarioCineController vuc;
    @Autowired
    private ViewFilmsController vfc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imagenMovieFast.setFitWidth(30);
        imagenMovieFast.setFitHeight(30);
        imagenMovieFast.setImage(ImagenMovieFast);

    }


    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/viewFilms.fxml"));
        inicio.getStylesheets().add("/templates/styles.css");
        Scene inicioScene = new Scene(inicio, 1000, 500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    void ingresar(ActionEvent event) throws IOException {

        String user = usuarioIngresado.getText();
        String contra = contraseñaIngresada.getText();
        List<Cine> lista = cs.findAll();

        boolean cineEncontrado = false;

        int indiceCine = 0;

        for (int i = 0; i < lista.size(); i++) {

            String nombre = lista.get(i).getName();

            if (nombre.equals(user)) {
                System.out.println("CINE ENCONTRADO");
                indiceCine = i;
                cineEncontrado = true;
            }

        }

        if (cineEncontrado) {

            vuc.setCine(lista.get(indiceCine));

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
            Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/vistaUsuarioCine.fxml"));
            Scene inicioScene = new Scene(inicio, 800, 600);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(inicioScene);
            window.show();

        } else if (user.equals("root") || user.toUpperCase().equals("ADMIN")) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
            Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/mostrar.fxml"));
            Scene inicioScene = new Scene(inicio, 600, 500);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(inicioScene);
            window.show();
            ClienteFinal clienteFinal = new ClienteFinal();
            clienteFinal.setName("ADMIN");
            vfc.setUser(clienteFinal);



        } else {
            UsuarioDTO clienteDeBase1 = us.findByUserName(user);
            ClienteFinal clienteDeBase = new ClienteFinal(clienteDeBase1));
            if (clienteDeBase != null) {
                if (clienteDeBase.getPassword().equals(contra)) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
                    Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/viewFilms.fxml"));
                    inicio.getStylesheets().add("/templates/styles.css");
                    Scene inicioScene = new Scene(inicio, 1000, 500);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(inicioScene);
                    window.show();
                    vfc.setUser(clienteDeBase);
                }
                else {AlertBox.display("No se pudo iniciar secion","Contraseña incorrecta");}
            } else {
                AlertBox.display("No se pudo iniciar secion", "Usuario no encontrado");
            }

        }

    }

    public void registrar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/SignIn.fxml"));
        Scene inicioScene = new Scene(inicio, 600, 500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }
}
