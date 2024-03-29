package um.edu.tic1.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.tic1.Tic1Application;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.services.MovieService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Component
public class AddMovieController {


    @Autowired
    private MovieService ms;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField nombrePelicula, descripcion, genero, categoria;
    @FXML
    private File imagen;

    //    @FXML
//    public void mostrarPelis2(){
//
//        List<Movie> lista = ms.findAll();
//
//        for(int i=0;i<lista.size();i++){
//            System.out.println("Nombre: " +lista.get(i).getName());
//        }
//
//    }
    @FXML
    public byte[] addImage() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elija su imagen");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*bmp", "*png", "*jpg", "*jfif"));
        imagen = fileChooser.showOpenDialog(null);
        File file = new File(imagen.getPath());
        byte[] picInBytes = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(picInBytes);
        fileInputStream.close();
        return picInBytes;
    }

    @FXML
    public void addMovie() throws IOException {

        String nombre = nombrePelicula.getText();
        String descripcion1 = descripcion.getText();
        String categoria1 = categoria.getText();
        String genero1 = genero.getText();
        byte[] movieImage = addImage();

        Movie movie = new Movie(nombre, descripcion1, genero1, categoria1);
        movie.setMovieImage(movieImage);

        ms.save(movie);
        nombrePelicula.clear();
        descripcion.clear();
        categoria.clear();
        genero.clear();

    }

    @FXML
    private void mostrarPelis(ActionEvent event) throws IOException {  // vuelve a la scena
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);
        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/mostrar.fxml"));
        Scene inicioScene = new Scene(inicio);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }


}
