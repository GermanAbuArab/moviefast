package um.edu.tic1.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.tic1.Tic1Application;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.services.MovieService;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class mostrarController {

    @Autowired
    private MovieService ms;


    public mostrarController(){
        System.out.println("Entro");

    }

    @FXML private TableView<Movie> tabla;

    @FXML private TableColumn<Movie,String> nombrePeli;

    @FXML
    private TextField nombrePelicula, descripcion, estreno;

    @FXML
    private void volver(ActionEvent event) throws IOException {  // vuelve a la scena
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);
        Parent inicio = fxmlLoader.load(getClass().getResource("/templates/addMovie.fxml"));
        Scene inicioScene = new Scene(inicio);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    public ObservableList<Movie> getMovie(){

        ObservableList<Movie> movie = FXCollections.observableArrayList();

        List<Movie> lista = ms.read();

        for(int i=0;i<lista.size();i++){
            movie.add(lista.get(i));
        }

        return movie;
    }

    public void initialize() {
        //set up the columns in the table
       nombrePeli.setCellValueFactory(new PropertyValueFactory<>("name"));

        //load dummy data
        tabla.setItems(getMovie());

        //Update the table to allow for the first and last name fields
        //to be editable
        tabla.setEditable(true);
        nombrePeli.setCellFactory(TextFieldTableCell.forTableColumn());

    }


}
