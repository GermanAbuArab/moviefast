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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.rmi.server.MarshalOutputStream;
import um.edu.tic1.Tic1Application;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.services.MovieService;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class MostrarController {


    @Autowired
    private MovieService ms;


    public MostrarController() {
        System.out.println("Entro");

    }
    private Movie peli;

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

    @FXML
    private TableColumn<Movie, String> image;

    @FXML
    private Button btn_nav, home_icon;
    //@FXML
    //private TextField nombrePelicula, descripcion, estreno;

    public void initialize() {

        //set up the columns in the table
        genero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        nombrePeli.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("peliuclas"));

        //load dummy data
        tabla.setItems(getMovie());

        //Update the table to allow for the first and last name fields
        //to be editable
        tabla.setEditable(true); //
        nombrePeli.setCellFactory(TextFieldTableCell.forTableColumn());
        categoria.setCellFactory(TextFieldTableCell.forTableColumn());
        genero.setCellFactory(TextFieldTableCell.forTableColumn());
        descripcion.setCellFactory(TextFieldTableCell.forTableColumn());
        image.setCellFactory(TextFieldTableCell.forTableColumn());

        //This will allow the table to select multiple rows at once
        //vamos a usar esto para poder marcar varias y eliminarlas
        //tabla.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    @FXML
    private void volver(ActionEvent event)throws IOException {  // vuelve a la scena
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/addMovie.fxml"));
        Scene inicioScene = new Scene(inicio);
        inicioScene.getStylesheets().add("templates/styles.css");
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    private void eliminar(ActionEvent event)throws IOException {

            if (getMovie().isEmpty()){
                AlertBox.display("Error","no hay peliculas que borrar");
            }else {
                if (tabla.getSelectionModel().getSelectedItem() == null) {
                    AlertBox.display("Error", "Porfavor seleccione la pelicula que quiere borrar");
                } else {
                    tabla.getItems().removeAll(tabla.getSelectionModel().getSelectedItem());


                    if (tabla.getSelectionModel().getSelectedIndex() != -1) {
                        ms.getMovieRepository().delete(getMovie().remove(tabla.getSelectionModel().getSelectedIndex() +1));

                    }
                    else {
                        ms.getMovieRepository().delete(getMovie().get(0));
                    }


                }
            }

      /*  if( tabla.getItems().isEmpty()){
            AlertBox.display("Error","No  hay peliculas para borrar");
        }
        if(tabla.getSelectionModel().getSelectedItem() == null && tabla.getItems().isEmpty()==false ){
            AlertBox.display("Error","Porfavor seleccione la pelicula que quiere borrar");

        }*/

    }


    public ObservableList<Movie> getMovie() {

        ObservableList<Movie> movie = FXCollections.observableArrayList();

        List<Movie> lista = ms.findAll();

        for (int i = 0; i < lista.size(); i++) {
            movie.add(lista.get(i));
        }

        return movie;
    }


    public void editNombreCommit(TableColumn.CellEditEvent<Movie, String> movieStringCellEditEvent) {

        //PROBLEMA DE EFICIENCIA CON ESTE METODO, EDITA TODA LA PELICULA PARA EDITAR UN CAMPO

        this.idEdit = tabla.getSelectionModel().getSelectedItem().getId();
        this.movie = tabla.getSelectionModel().getSelectedItem();
        movie.setName(movieStringCellEditEvent.getNewValue());

    }

    private Movie movie;
    private long idEdit;

    @FXML
    private void aplicar(ActionEvent event)throws IOException{
        //problema no se puede editar de a mas de a uno, podira solucionarse con un arraylist de movies en lugar de una sola.
        ms.getMovieRepository().deleteById(idEdit);
        ms.getMovieRepository().save(movie);
    }
}
