/*package um.edu.tic1.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.edu.tic1.Tic1Application;
import um.edu.tic1.entities.Funcion;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.services.FuncionService;
import um.edu.tic1.services.MovieService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


@Component
public class ViewFilmsController {


    @FXML
    private ScrollPane scrollPane;
    @FXML
    private HBox hbox;
    @FXML
    private GridPane grid;
    @FXML
    ImageView pic, imagenInicio, imagenRecomendados, imagenTeatros, imagenMap, imagenLogin, imagenMovieFast;
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
    private TextField buscar;
    private TableView<Movie> tabla = new TableView<>();

    private Image[] images = new Image[150];
    private Movie movieAux;
    private Funcion funcionAux;
    @Autowired
    private FuncionService funcionService;


    @Autowired
    private MovieService ms;

    public void initialize() {

        setUpIconosDec();
        int m = 0;
        addImagesToArray(getMovie());
        setUpGrid();

        for (m = 0; m < getMovie().size(); m++) {
            int finalM = m;
            grid.getChildren().get(m).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    grid.getChildren().get(finalM).setStyle("-fx-scale-x: 1.1; -fx-scale-y: 1.1;");
                }
            });

            grid.getChildren().get(m).setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    grid.getChildren().get(finalM).setStyle("-fx-scale-x: 1; -fx-scale-y: 1;");
                }
            });
        }

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToHeight(true);
    }

    private void setUpGrid() {
        grid.getChildren().clear();
        grid.setPadding(new Insets(90, 7, 80, 7));
        grid.setHgap(50);
        grid.setVgap(170);
        int rows = 3;
        int columns = 4;
        int imageIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (imageIndex < images.length) {
                    try {

                        addImage(imageIndex, j, i);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    imageIndex++;
                }
            }
        }


    }

    private void addImagesToArray(ObservableList<Movie> movie) {
        Image[] imagesAux = new Image[150];
        for (int m = 0; m < movie.size(); m++) {
            byte[] img = movie.get(m).getMovieImage();
            ByteArrayInputStream bis = new ByteArrayInputStream(img);
            BufferedImage bImage = null;
            try {
                bImage = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("RE LOCO");
            }
            Image image = SwingFXUtils.toFXImage(bImage, null);
            imagesAux[m] = image;
        }
        images = imagesAux;
    }

    private void setUpIconosDec() {

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


    }


    private void addImage(int index, int colIndex, int rowIndex) throws Exception {

        pic = new ImageView();
        pic.setFitWidth(150);
        pic.setFitHeight(200);
        pic.setImage(images[index]);
        grid.add(pic, colIndex, rowIndex);


        pic.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                Movie movie = getMovie().get(index);


                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

                Parent inicio = null;
                try {
                    inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/Movie.fxml"));
                    inicio.getStylesheets().add("/templates/styles.css");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                MovieController movieController = fxmlLoader.getController();
                movieController.loadData(movie);
                try {
                    movieController.setImagenMovie(images[index]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Scene inicioScene = new Scene(inicio, 600, 400);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(inicioScene);
                window.show();//ramayama

            }
        });


    }


    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {  // vuelve a la scena
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/login.fxml"));
        inicio.getStylesheets().add("/templates/styles.css");
        Scene inicioScene = new Scene(inicio, 1000, 500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }


    private ObservableList<Movie> getMovie() {

        ObservableList<Movie> movie = FXCollections.observableArrayList();

        List<Funcion> lista = funcionService.findAll();

        for (int i = 0; i < lista.size(); i++) {
            if (!movie.contains(lista.get(i).getMovie())) {
                movie.add(lista.get(i).getMovie());
            }
        }

        return movie;
    }


    public void filtrar(ActionEvent event) {
        ObservableList<Movie> movies = getMovie();
        ObservableList<Movie> moviesFiltradas = FXCollections.observableArrayList();

        if (buscar.getText() != null) {

            for (Movie m : movies) {
                if (m.getName().toLowerCase().contains(buscar.getText().toLowerCase())) {
                    moviesFiltradas.add(m);
                }
                else if (m.getGenero().toLowerCase().contains(buscar.getText().toLowerCase())){
                    moviesFiltradas.add(m);
                }

            }

        } else {
            moviesFiltradas = getMovie();

        }
        addImagesToArray(moviesFiltradas);
        setUpGrid();
        for (int m = 0; m < moviesFiltradas.size(); m++) {
            int finalM = m;
            grid.getChildren().get(m).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    grid.getChildren().get(finalM).setStyle("-fx-scale-x: 1.1; -fx-scale-y: 1.1;");
                }
            });

            grid.getChildren().get(m).setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    grid.getChildren().get(finalM).setStyle("-fx-scale-x: 1; -fx-scale-y: 1;");
                }
            });

        }
    }
}
 */



