package um.edu.tic1.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
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

import java.io.IOException;
import java.util.List;

@Component
public class MostrarController {


    @Autowired
    private MovieService ms;


    public MostrarController() {
        System.out.println("Entro");

    }

    @FXML
    private TableView<Movie> tabla;

    @FXML
    private TableColumn<Movie, String> nombrePeli;

    @FXML
    private TableColumn<Movie, String> categoria;

    @FXML
    private TableColumn<Movie, String> descripcion;

    @FXML
    private TableColumn<Movie, String> genero;

    //@FXML
    //private TextField nombrePelicula, descripcion, estreno;

    @FXML
    private void volver(ActionEvent event)throws IOException {  // vuelve a la scena
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/addMovie.fxml"));
        Scene inicioScene = new Scene(inicio);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    private void eliminar(ActionEvent event)throws IOException {
        // todo hay que arreglar el tema de que si apretas eliminar sin apretar sobra nada tira una excepcion, hay que cathcearla y que salte un cartelito
       tabla.getItems().removeAll(tabla.getSelectionModel().getSelectedItem());
       ms.getMovieRepository().delete(getMovie().remove(tabla.getSelectionModel().getFocusedIndex()));


    }

    public ObservableList<Movie> getMovie() {

        ObservableList<Movie> movie = FXCollections.observableArrayList();

        List<Movie> lista = ms.findAll();

        for (int i = 0; i < lista.size(); i++) {
            movie.add(lista.get(i));
        }

        return movie;
    }

    public void initialize() {

        //set up the columns in the table
        genero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        nombrePeli.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("description"));


        //load dummy data
        tabla.setItems(getMovie());

        //Update the table to allow for the first and last name fields
        //to be editable
        tabla.setEditable(false); //
        nombrePeli.setCellFactory(TextFieldTableCell.forTableColumn());
        categoria.setCellFactory(TextFieldTableCell.forTableColumn());
        genero.setCellFactory(TextFieldTableCell.forTableColumn());


        //This will allow the table to select multiple rows at once
        //vamos a usar esto para poder marcar varias y eliminarlas
        tabla.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


}
