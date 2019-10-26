package um.edu.tic1.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.tic1.Tic1Application;
import um.edu.tic1.entities.Cine;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.services.CineService;
import um.edu.tic1.services.MovieService;

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


    @Autowired
    private CineService cs;

    @Autowired
    private vistaCinesController vc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imagenMovieFast.setFitWidth(30);
        imagenMovieFast.setFitHeight(30);
        imagenMovieFast.setImage(ImagenMovieFast);

    }


    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);
        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/viewFilms.fxml"));
        inicio.getStylesheets().add("/templates/styles.css");
        Scene inicioScene = new Scene(inicio,1000,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }
    @FXML
    void ingresar(ActionEvent event) throws IOException {

        String user = usuarioIngresado.getText();

        List<Cine> lista = cs.findAll();

        boolean cineEncontrado = false;

        int indiceCine = 0;

        for(int i=0;i<lista.size();i++){

            String nombre = lista.get(i).getName();

            if (nombre.equals(user)) {
                System.out.println("CINE ENCONTRADO");
                indiceCine = i;
                cineEncontrado = true;
            }

        }

        if(cineEncontrado){

            vc.setCine(lista.get(indiceCine));

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);
            Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/vistaFunciones.fxml"));
            Scene inicioScene = new Scene(inicio, 600, 500);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(inicioScene);
            window.show();

        }

        if(!cineEncontrado){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);
            Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/inicio.fxml"));
            Scene inicioScene = new Scene(inicio, 600, 500);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(inicioScene);
            window.show();
        }
    }
}
