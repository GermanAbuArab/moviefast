package SpringApplication.Controllers2;

import SpringApplication.Entities.Pelicula;
import SpringApplication.Repositories.PeliculaRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class MovieAddController {

    private ConfigurableApplicationContext springContext;

    @Autowired
    PeliculaRepository peliculaRepository;


    @FXML
    private TextField PeliculaNombre, PeliculaDuracion, PeliculaDirector, PeliculaDescripcion, PeliculaTrailer;



    @FXML
    public void addMovie() throws Exception {

        String name = PeliculaNombre.getText();
        String duration = PeliculaDuracion.getText();
        String direct = PeliculaDirector.getText();
        String description = PeliculaDescripcion.getText();
        String trailer = PeliculaTrailer.getText();
        Pelicula pelicula = new Pelicula();

        pelicula.setNombre(name);
        pelicula.setDuracion(duration);
        pelicula.setDirector(direct);
        pelicula.setDescripcion(description);
        pelicula.setTrailer(trailer);



        peliculaRepository.save(pelicula);



    }

    @FXML
    private void addScene(ActionEvent event) throws IOException {
        Parent inicio = FXMLLoader.load(getClass().getResource("../../../../resources/AdminService.fxml"));

        Scene iniciarScene = new Scene(inicio);
        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(iniciarScene);
        window.show();
    }


}
