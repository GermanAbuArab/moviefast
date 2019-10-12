package um.edu.tic1.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.tic1.entities.Cine;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.services.CineService;
import um.edu.tic1.services.MovieService;

import java.io.IOException;

@Component
public class AddCineController {

    @Autowired
    private CineService cs;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField nombreCine;


    @FXML
    public void addMovie() throws IOException {

        String nombre=nombreCine.getText();


        Cine cine = new Cine(nombre);

        //try {
        System.out.println("hasta aca llega , SI DEBUGEAMOS VEMOS QUE EL REPOSITORIO ES NULO");
        cs.save(cine);
        System.out.println("Creaste el Cine " + nombre);


        //} catch (PeliculaAlreadyExistsException e) {
        //  e.printStackTrace();
        //} catch (InvalidPeliculaInformationException e) {
        //  e.printStackTrace();
        //}
        nombreCine.clear();
    }


}
