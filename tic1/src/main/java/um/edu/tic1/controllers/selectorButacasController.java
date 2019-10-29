package um.edu.tic1.controllers;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import de.jensd.fx.glyphs.materialicons.utils.MaterialIconFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import um.edu.tic1.Tic1Application;
import um.edu.tic1.entities.Cine;
import um.edu.tic1.entities.Funcion;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.entities.Sala;
import um.edu.tic1.services.CineService;
import um.edu.tic1.services.FuncionService;
import um.edu.tic1.services.MovieService;
import um.edu.tic1.services.SalaService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@Component
public class selectorButacasController {



    @FXML
    private GridPane gridSeats;
    @FXML
    DatePicker datePicker;
    @FXML
    ComboBox CineDropDownList, horaDropDownList, salaDropDownList;
    @Autowired
    private SalaService salaService;

    @Autowired
    private FuncionService funcionService;

    @Autowired
    private CineService cineService;
    private Cine cine;
    private Movie movieAux;

    @FXML
    public void setMovie(Movie movie){
        movieAux = movie;


    }

    public void initialize(){


        datePicker.setValue(LocalDate.now());
        addSeats();

    }


    private ObservableList<String> getCines() {

        ObservableList<String> movie = FXCollections.observableArrayList();

        List<Cine> lista = cineService.findAll();

        for (int i = 0; i < lista.size(); i++) {

            if (funcionService.findAll().get(i).getMovie().equals(this.movieAux))
            movie.add(lista.get(i).getName());
        }

        return movie;
    }


    private void addSeats() {
        int x =3;
        int y = 3;
        int indiceButaca =0;
        for (int i=0;i<x;i++ ){
            for (int j =0;j<y;j++){
                Seat(indiceButaca,i,j);
            }
        }
    }

    private void Seat(int indiceButaca, int i, int j) {
        MaterialIconView icon = new MaterialIconView(MaterialIcon.EVENT_SEAT);
        icon.setStyle("-fx-fill:black; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;");
        icon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (((Node) event.getSource()).getStyle()
                                .equals("-fx-fill:#c9b3b3; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    "The seat " + ((Node) event.getSource()).getId() + " is already booked!", ButtonType.OK);
                            alert.showAndWait();
                            if (alert.getResult() == ButtonType.OK) {
                                alert.close();
                            }
                        }
                        else {
                            // turning seat back to black if it is red - unselecting it
                            if (((Node) event.getSource()).getStyle()
                                    .equals("-fx-fill:red; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;")) {
                                ((Node) event.getSource())
                                        .setStyle("-fx-fill:black; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;");
                                // Main.getSelectedSeats().remove(((Node) e.getSource()).getId());
                            }
                            // turning seat red if it is black - selecting it
                            else {
                                ((Node) event.getSource())
                                        .setStyle("-fx-fill:red; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;");
                                // Main.getSelectedSeats().add(((Node) e.getSource()).getId());
                            }
                        }

                    }
                });
        gridSeats.add(icon,i,j);

    }


    @FXML
    private void volver(ActionEvent event)throws IOException {  // vuelve a la scena

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/viewFilms.fxml"));
        inicio.getStylesheets().add("/templates/styles.css");
        Scene inicioScene = new Scene(inicio,600,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();

    }



    public ObservableList<Funcion> getFunciones(){

        ObservableList<Funcion> funciones = FXCollections.observableArrayList();

        List<Funcion> lista = funcionService.findAll();

        for (int i = 0; i < lista.size(); i++) {
            Funcion funcion = lista.get(i);

            if(funcion.getMovie().equals(movieAux)) {
                funciones.add(lista.get(i));
            }
        }

        return funciones;
    }




}
