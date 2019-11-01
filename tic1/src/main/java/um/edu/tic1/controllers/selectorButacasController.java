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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private Sala sala;
    private Movie movieAux;
    private Funcion funcionAux;

    @FXML
    public void setMovie(Movie movie){

        movieAux = movie;
        init();
    }

    public void init() {
        ObservableList<String> cines = getCines();
        ObservableList<String> horas = FXCollections.observableArrayList();
        ObservableList<Sala> salas = FXCollections.observableArrayList();
        datePicker.setVisible(false);
        salaDropDownList.setVisible(false);
        horaDropDownList.setVisible(false);
        ObservableList<Funcion> funciones = getFunciones();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd/MM/yyyy ");

        if (funciones.size() != 0) {
            CineDropDownList.setItems(cines);
        CineDropDownList.setOnAction((event -> {
            List<Cine> list = cineService.findAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equals(CineDropDownList.getValue())) {
                    this.cine = list.get(i);
                    datePicker.setVisible(true);
                    datePicker.setOnAction(event1 -> {
                        horas.clear();
                        for (int o =0 ;o<funciones.size();o++) {
                            String str = funciones.get(o).getHoraInicio();
                            if ((str.substring(0,str.length()-5).equals(datePicker.getValue().format(formatter)))){
                                horaDropDownList.setVisible(true);
                                horas.add(funciones.get(o).getHora());
                                horaDropDownList.setItems(horas);

                                horaDropDownList.setOnAction((event2) -> {
                                    salas.clear();
                                    String horainicio = (String)horaDropDownList.getSelectionModel().getSelectedItem();
                                    LocalDate fechainicio = datePicker.getValue();
                                    String fechatotalInicio = fechainicio +"T"+ horainicio + ":00.00";
                                    LocalDateTime fechatotalinicio = LocalDateTime.parse(fechatotalInicio);
                                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("E, dd/MM/yyyy HH:mm");
                                    for(int t=0 ;t<funciones.size();t++){
                                        if (funciones.get(t).getHoraInicio().equals(fechatotalinicio.format(formatter1))){
                                            salaDropDownList.setVisible(true);
                                            salas.add(funciones.get(t).getSala());
                                            salaDropDownList.setItems(salas);
                                            salaDropDownList.setOnAction((e) -> {
                                                gridSeats.getChildren().clear();
                                                List<Sala> listSala = getSalas();
                                                sala = (Sala) salaDropDownList.getValue();

                                                for (int j =0;j<listSala.size();j++){
                                                    if (sala.getName().equals(listSala.get(j).getName())){
                                                        addSeats(sala.getX(),sala.getY());
                                                    }
                                                }

                                            });
                                        }

                                    }

                                });
                            }
                        }
                    });

                }
            }

        }));
        }


    }

    public ObservableList<String> getCines() {

        ObservableList<String> cines = FXCollections.observableArrayList();

        List<Cine> lista = cineService.findAll();

        for (int i = 0; i < lista.size(); i++) {
            cines.add(lista.get(i).getName());
        }
            return cines;

    }
    public ObservableList<Sala> getSalas() {

        ObservableList<Sala> salas = FXCollections.observableArrayList();

        List<Sala> lista = salaService.findAll();

        for (int i = 0; i < lista.size(); i++) {
            Sala sala = lista.get(i);

            if(sala.getCine().equals(this.cine)) {
                salas.add(lista.get(i));

            }
        }

        return salas;
    }


    private void addSeats(int x,int y) {
        gridSeats.getChildren().clear();
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

            if(funcion.getMovie().getName().equals(movieAux.getName())) {
                funciones.add(lista.get(i));
            }
        }

        return funciones;
    }




}
