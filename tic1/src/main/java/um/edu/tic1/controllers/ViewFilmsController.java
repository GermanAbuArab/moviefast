package um.edu.tic1.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.services.MovieService;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



@Component
public class ViewFilmsController implements Initializable {

    HBox hb = new HBox();

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;
    @FXML
    private Button btn_nav, home_icon;


    @Autowired
    private MovieService ms;

    public void initialize(URL location, ResourceBundle resources) {



        /*
        try {

            ObservableList<Movie> movies = getMovie();

            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


            // gridpane settings
            // setting exterior grid padding
            grid.setPadding(new Insets(7,7,7,7));
            // setting interior grid padding
            grid.setHgap(10);
            grid.setVgap(10);
            // grid.setGridLinesVisible(true);

            int rows = (movies.size() / 4) + 1;
            int columns = 4;
            int indicePeli = 0;

            for (int i = 0 ; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (indicePeli < movies.size()) {
                        indicePeli++;
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }}

    private ObservableList<Movie> getMovie() {

        ObservableList<Movie> movie = FXCollections.observableArrayList();

        List<Movie> lista = ms.findAll();

        for (int i = 0; i < lista.size(); i++) {
            movie.add(lista.get(i));
        }

        return movie;
    }
    */
    }
}