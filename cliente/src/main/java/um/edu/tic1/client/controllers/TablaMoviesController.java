package um.edu.tic1.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.tic1.client.ClientApplication;
import um.edu.tic1.client.models.Movie;
import um.edu.tic1.client.services.MovieService;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

@Component
public class TablaMoviesController {


    @Autowired
    private MovieService ms;
    private Movie peli;

    @Autowired
    private ViewFilmsController vfc;

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
    private javafx.scene.control.TextField buscar;

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


        FilteredList filteredData = new FilteredList(getMovie(),e -> true);

        buscar.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super Movie>)(Movie movie) ->{

                if(newValue.isEmpty() || newValue==null){
                    return true;
                }else if (movie.getName().contains(newValue)){
                    return true;
                }else if (movie.getGenero().contains(newValue)){
                    return true;
                }
                return false;
            });
        }));
        SortedList sortedList = new SortedList(filteredData);
        sortedList.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(sortedList);


        //Update the table to allow for the first and last name fields
        //to be editable
        tabla.setEditable(true); //
        nombrePeli.setCellFactory(TextFieldTableCell.forTableColumn());
        categoria.setCellFactory(TextFieldTableCell.forTableColumn());
        genero.setCellFactory(TextFieldTableCell.forTableColumn());
        descripcion.setCellFactory(TextFieldTableCell.forTableColumn());


        //This will allow the table to select multiple rows at once
        //vamos a usar esto para poder marcar varias y eliminarlas
        //tabla.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    @FXML
    private void agregar(ActionEvent event)throws IOException {  // vuelve a la scena
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/addMovie.fxml"));
        Scene inicioScene = new Scene(inicio,600,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }
    @FXML
    private void volver(ActionEvent event)throws IOException {  // vuelve a la scena
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/viewFilms.fxml"));
        inicio.getStylesheets().add("/templates/styles.css");
        Scene inicioScene = new Scene(inicio,1000,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
        vfc.setUser(vfc.getClienteFinal());

    }

    @FXML
    private void eliminar(ActionEvent event)throws IOException {

        int selectedIndex = tabla.getSelectionModel().getSelectedIndex();
            if (getMovie().isEmpty()){
                AlertBox.display("Error","no hay peliculas que borrar");
            }else {
                if (tabla.getSelectionModel().getSelectedItem() == null) {
                    AlertBox.display("Error", "Porfavor seleccione la pelicula que quiere borrar");
                } else {
                    Movie product = tabla.getSelectionModel().getSelectedItem();
                    ms.getMovieRepository().delete(product);


                }
            }
            initialize();


    }

    public void cines(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/tablaCines.fxml"));
        Scene inicioScene = new Scene(inicio,600,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();

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

        //todo PROBLEMA DE EFICIENCIA CON ESTE METODO, EDITA TODA LA PELICULA PARA EDITAR UN CAMPO

        this.idEdit = tabla.getSelectionModel().getSelectedItem().getId();
        this.movie = tabla.getSelectionModel().getSelectedItem();
        movie.setName(movieStringCellEditEvent.getNewValue());

    }

    private Movie movie;
    private long idEdit;

    @FXML
    private void aplicar(ActionEvent event)throws IOException{
        // todo problema no se puede editar de a mas de a uno, podira solucionarse con un arraylist de movies en lugar de una sola.
            ms.getMovieRepository().deleteById(idEdit);
        ms.getMovieRepository().save(movie);
    }

    public TableView<Movie> getTabla(){

        return tabla;
    }


  /* @FXML
    private void search(KeyEvent event){

       FilteredList filteredData = new FilteredList(getMovie(),e -> true);

        buscar.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super Movie>)(Movie movie) ->{

                if(newValue.isEmpty() || newValue==null){
                    return true;
                }else if (movie.getName().contains(newValue)){
                    return true;
                }else if (movie.getGenero().contains(newValue)){
                    return true;
                }
                return false;
            });
        }));

        SortedList sortedList = new SortedList(filteredData);
        sortedList.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(sortedList);
    }*/


}
