package um.edu.tic1.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.tic1.Tic1Application;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.services.MovieService;
import javafx.event.ActionEvent;

import java.awt.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



@Component
public class ViewFilmsController implements Initializable {


    ArrayList<File> fileList = new ArrayList<File>();
    HBox hb = new HBox();
    File imagen = new File("descarga.jpg");

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;
    @FXML
    ImageView pic,imagenInicio,imagenRecomendados,imagenTeatros,imagenMap,imagenLogin,imagenMovieFast;
    @FXML
    Image imagen1;
    @FXML
    Image ImagenInicio = new Image("assets/icono_inicio.png");
   @FXML
    Image ImagenRecomendados = new Image("assets/icono_fav.png");
    @FXML
    Image ImagenTeatros = new Image("assets/icono_teatro.png");
    @FXML
    Image ImagenMap = new Image("assets/icono_map.png");
    @FXML
    Image ImagenLogin = new Image("assets/icono_login.png");
    @FXML
    Image ImagenMovieFast = new Image("assets/icono_movieFast.png");

    @FXML
    private Button btn_nav, home_icon;



    @Autowired
    private MovieService ms;

    public void initialize(URL location, ResourceBundle resources) {

        imagenInicio.setFitWidth(25);
        imagenInicio.setFitHeight(25);
        imagenInicio.setImage(ImagenInicio);

        imagenLogin.setFitWidth(25);
        imagenLogin.setFitHeight(25);
        imagenLogin.setImage(ImagenLogin);

        imagenMovieFast.setFitWidth(30);
        imagenMovieFast.setFitHeight(30);
        imagenMovieFast.setImage(ImagenMovieFast);

        imagenRecomendados.setFitWidth(25);
        imagenRecomendados.setFitHeight(25);
        imagenRecomendados.setImage(ImagenRecomendados);

        imagenTeatros.setFitWidth(25);
        imagenTeatros.setFitHeight(25);
        imagenTeatros.setImage(ImagenTeatros);

        imagenMap.setFitWidth(25);
        imagenMap.setFitHeight(25);
        imagenMap.setImage(ImagenMap);


        int b=0;
        while (b<4){
            fileList.add(imagen);
            b++;
        }

        grid.setPadding(new Insets(50,7,7,7));
        // setting interior grid padding
        grid.setHgap(10);
        grid.setVgap(150);

        // grid.setGridLinesVisible(true);

        int rows = (fileList.size() / 2) + 1;
        int columns = 2;
        int imageIndex = 0;
        for (int i = 0 ; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (imageIndex < fileList.size()) {
                    addImage(imageIndex, j, i);
                    imageIndex++;
                }
            }
        }
        fileList.clear();


    }

    private void addImage(int index, int colIndex, int rowIndex) {

        String idToCut = fileList.get(index).getName();
        String id = idToCut.substring(0, (idToCut.length() - 4));
        imagen1 = new Image("descarga.jpg");
        pic = new ImageView();
        pic.setFitWidth(150);
        pic.setFitHeight(150);
        pic.setImage(imagen1);
        pic.setId(id);
        grid.add(pic,colIndex,rowIndex);

        pic.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

                Parent inicio = null;
                try {
                    inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/Movie.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene inicioScene = new Scene(inicio,600,500);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(inicioScene);
                window.show();

            }
        });
    }



    @FXML
    private void iniciarSesion(ActionEvent event)throws IOException {  // vuelve a la scena
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/inicio.fxml"));
        Scene inicioScene = new Scene(inicio,600,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }


    private ObservableList<Movie> getMovie() {

        ObservableList<Movie> movie = FXCollections.observableArrayList();

        List<Movie> lista = ms.findAll();

        for (int i = 0; i < lista.size(); i++) {
            movie.add(lista.get(i));
        }

        return movie;
    }

    public void SignIn(ActionEvent event)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/SignIn.fxml"));
        Scene inicioScene = new Scene(inicio,800,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }
}
