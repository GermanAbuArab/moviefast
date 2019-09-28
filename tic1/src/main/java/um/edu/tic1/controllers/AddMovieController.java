package um.edu.tic1.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.tic1.Tic1Application;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.services.MovieService;


import java.io.IOException;
import java.util.List;


@Component
public class AddMovieController {

    public AddMovieController() {
        System.out.println( "SKERE");
    }

    @Autowired
    private MovieService ms;

    @FXML
    private TextField nombrePelicula, descripcion,genero,categoria;
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
    public void addMovie(){

        String nombre=nombrePelicula.getText();
        String descripcion1=descripcion.getText();
        String categoria1= categoria.getText();
        String genero1=genero.getText();
        Movie movie = new Movie(nombre,descripcion1,genero1,categoria1);

        //try {
        System.out.println("hasta aca llega , SI DEBUGEAMOS VEMOS QUE EL REPOSITORIO ES NULO");
            ms.save(movie);
            System.out.println("Creaste la peli " + nombre + " cuya descripcion es " + descripcion1);


        //} catch (PeliculaAlreadyExistsException e) {
          //  e.printStackTrace();
        //} catch (InvalidPeliculaInformationException e) {
        //  e.printStackTrace();
        //}
        nombrePelicula.clear();
        descripcion.clear();

    }

    @FXML
    private void mostrarPelis(ActionEvent event) throws IOException {  // vuelve a la scena
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);
        Parent inicio = fxmlLoader.load(getClass().getResource("/templates/addMovie.fxml"));
        Scene inicioScene = new Scene(inicio);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }





}
