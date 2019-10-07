package um.edu.tic1.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
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

//
//        Image imgUsa = new Image ("http://gifimage.net/wp-content/uploads/2017/06/american-flag-gif-13.gif");
//        Image imgChina = new Image ("http://bestanimations.com/Flags/Asia/china/chinese-flag-waving-gif-animation-10.gif");
//
//        ImageView ivUsa = new ImageView(imgUsa);
//        ImageView ivChina = new ImageView(imgChina);
//
        Image image = new Image ("descarga.jpg");

        ImageView pic = new ImageView();
        ImageView pic1 = new ImageView();
        ImageView pic2 = new ImageView();
        pic.setFitWidth(175);
        pic.setFitHeight(150);
        pic.setImage(image);
        pic1.setFitWidth(175);
        pic1.setFitHeight(150);
        pic1.setImage(image);
        pic2.setFitWidth(175);
        pic2.setFitHeight(150);
        pic2.setImage(image);


        grid.add(pic,0,0);
        grid.add(pic1,1,0);
        grid.add(pic2,0,1);

    }




    private ObservableList<Movie> getMovie() {

        ObservableList<Movie> movie = FXCollections.observableArrayList();

        List<Movie> lista = ms.findAll();

        for (int i = 0; i < lista.size(); i++) {
            movie.add(lista.get(i));
        }

        return movie;
    }

    }
