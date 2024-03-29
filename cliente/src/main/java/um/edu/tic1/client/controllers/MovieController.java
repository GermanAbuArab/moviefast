package um.edu.tic1.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import um.edu.tic1.client.ClientApplication;
import um.edu.tic1.client.models.Movie;
import um.edu.tic1.client.services.MovieService;


import java.io.IOException;
import java.util.List;

@Component
public class MovieController {
    @FXML
    private Text nombre,descripcion,genero,categoria;

    @Autowired
    private MovieService ms;
    private Movie movie;

    @FXML
    private ImageView imagenMovie;

    @FXML
    public void loadData(Movie movie){
        nombre.setText(movie.getName());
        descripcion.setText(movie.getDescription());
        genero.setText(movie.getGenero());
        categoria.setText(movie.getCategoria());
        this.movie= movie;
    }

    public void setImagenMovie(Image imagen){
        imagenMovie.setImage(imagen);
    }
    public void initialize() {


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

    }
    @FXML
    private void comprar(ActionEvent event)throws IOException {  // vuelve a la scena

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/selectorButacas.fxml"));
        inicio.getStylesheets().add("/templates/styles.css");
        selectorButacasController selectorButacasController =fxmlLoader.getController();
        selectorButacasController.setMovie(movie);
        Scene inicioScene = new Scene(inicio,700,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();

    }



    public ObservableList<Movie> getMovie() throws IOException {

        ObservableList<Movie> movie = FXCollections.observableArrayList();

        List<Movie> lista = ms.findAll();

        for (int i = 0; i < lista.size(); i++) {
            movie.add(lista.get(i));
        }

        return movie;
    }
}
