package um.edu.tic1.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.edu.tic1.Tic1Application;

import java.io.IOException;

@Component
public class InicioController {

    @FXML
    public void initialize() {

    }

    @FXML
    public void adminEmpresa(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/mostrar.fxml"));
        Scene inicioScene = new Scene(inicio);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    public void usuarioFinal(ActionEvent actionEvent)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/viewFilms.fxml"));
        Scene inicioScene = new Scene(inicio);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }
}
