package um.edu.tic1.client.controllers;

//import de.jensd.fx.glyphs.materialicons.MaterialIcon;
//import de.jensd.fx.glyphs.materialicons.MaterialIconView;
//import de.jensd.fx.glyphs.materialicons.utils.MaterialIconFactory;

import com.sun.xml.internal.bind.v2.TODO;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import um.edu.tic1.client.ClientApplication;
import um.edu.tic1.client.models.*;
import um.edu.tic1.client.services.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
public class selectorButacasController {


    private Ticket ticket;
    @FXML
    private GridPane gridSeats;
    @FXML
    DatePicker datePicker;
    @FXML
    Button comprar;
    @FXML
    ComboBox CineDropDownList, horaDropDownList, salaDropDownList;
    @Autowired
    private SalaService salaService;

    @Autowired
    private MovieService movieService;


    @Autowired
    private FuncionService funcionService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ViewFilmsController vfc;

    @Autowired
    private CineService cineService;
    private Cine cine;
    private Sala sala;
    private Movie movieAux;
    private Funcion funcionAux;
    private Sala salaFuncion;

    @FXML
    public void setMovie(Movie movie){

        movieAux = movie;
        init();
    }

    public void init() {
        gridSeats.setHgap(10);
        gridSeats.setVgap(10);
        gridSeats.setPadding(new Insets(10, 10, 10, 10));
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
            datePicker.setValue(null);
            List<Cine> list = null;
            list = cineService.findAll();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getName().equals(CineDropDownList.getValue())) {
                    this.cine = list.get(i);
                    datePicker.setVisible(true);
                    datePicker.setOnAction(event1 -> {
                        horas.clear();
                        for (int o =0 ;o<funciones.size();o++) {
                            String str = funciones.get(o).getHoraInicio();
                            if ((str.substring(0,str.length()-5).equals(datePicker.getValue().format(formatter))) && funciones.get(o).getCineId().equals(this.cine.getId())){
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
                                            funcionAux = funciones.get(t);
                                            salaDropDownList.setVisible(true);
                                            salas.add(salaService.findById(funciones.get(t).getSalaId()));
                                            salaDropDownList.setItems(salas);
                                            int finalT = t;
                                            salaDropDownList.setOnAction((e) -> {
                                                gridSeats.getChildren().clear();
                                                sala = (Sala) salaDropDownList.getValue();

                                                for (int j =0;j<funciones.size();j++){
                                                    if (sala.getName().equals(salaService.findById(funciones.get(finalT).getSalaId()).getName()) && funciones.get(finalT).getHoraInicio().equals(funcionAux.getHoraInicio())){
                                                        salaFuncion = salaService.findById(getFunciones().get(finalT).getSalaId());
                                                        funcionAux = getFunciones().get(finalT);
                                                        addSeats(salaFuncion.getX(),salaFuncion.getY());
                                                        ShowButacas();
                                                        System.out.println(funcionAux.getId());
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

    private ObservableList<String> getCines() {

        ObservableList<String> cines = FXCollections.observableArrayList();

        List<Cine> lista  = cineService.findAll();

        for (int i = 0; i < getFunciones().size(); i++) {
            for (int j = 0; j < lista.size(); j++) {
                if (getFunciones().get(i).getCineId().equals(lista.get(j).getId()))
                    if (!cines.contains(lista.get(j).getName())) {
                        cines.add(lista.get(j).getName());
                    }
            }
        }
            return cines;

    }
    private ObservableList<Sala> getSalas() {

        ObservableList<Sala> salas = FXCollections.observableArrayList();

        List<Funcion> lista = funcionService.findAll();

        for (int i = 0; i < lista.size(); i++) {
            Sala sala = salaService.findById(lista.get(i).getSalaId());

            if(sala.getCineId().equals(this.cine.getId())) {
                salas.add(salaService.findById(lista.get(i).getSalaId()));

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
            indiceButaca++;
        }
    }

    private void Seat(int indiceButaca, int i, int j) {
        MaterialIconView icon = new MaterialIconView(MaterialIcon.EVENT_SEAT);

        if (funcionAux.getButacas()[i][j] == true) {
            icon.setStyle("-fx-fill:black; -fx-font-family: 'Material Icons'; -fx-font-size: 30.0;");
            gridSeats.add(icon, i, j);
        }
        else {
            icon.setStyle("-fx-fill:#c9b3b3; -fx-font-family: 'Material Icons'; -fx-font-size: 30.0;");
            gridSeats.add(icon, i, j);
        }

        icon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (((Node) event.getSource()).getStyle()
                                .equals("-fx-fill:#c9b3b3; -fx-font-family: 'Material Icons'; -fx-font-size: 30.0;")) {
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    "The seat is already booked!", ButtonType.OK);
                            alert.showAndWait();
                            if (alert.getResult() == ButtonType.OK) {
                                alert.close();
                            }
                        }
                        else {
                            // turning seat back to black if it is red - unselecting it
                            if (((Node) event.getSource()).getStyle()
                                    .equals("-fx-fill:red; -fx-font-family: 'Material Icons'; -fx-font-size: 30.0;")) {
                                ((Node) event.getSource())
                                        .setStyle("-fx-fill:black; -fx-font-family: 'Material Icons'; -fx-font-size: 30.0;");

                                // Main.getSelectedSeats().remove(((Node) e.getSource()).getId());
                            }
                            // turning seat red if it is black - selecting it
                            else {
                                ((Node) event.getSource())
                                        .setStyle("-fx-fill:red; -fx-font-family: 'Material Icons'; -fx-font-size: 30.0;");

                                // Main.getSelectedSeats().add(((Node) e.getSource()).getId());
                            }

                        }

                    }
                });



    }

    void ShowButacas(){
        for (int x= 0;x<salaFuncion.getX();x++) {
            for (int y = 0; y < salaFuncion.getY(); y++) {
                System.out.println(funcionAux.getButacas()[x][y]);
            }
        }
    }


    @FXML
    private void volver(ActionEvent event)throws IOException {  // vuelve a la scena

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/viewFilms.fxml"));
        inicio.getStylesheets().add("/templates/styles.css");
        Scene inicioScene = new Scene(inicio,1000,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();

    }
    @FXML
    private void comprar(ActionEvent event) throws IOException{
        ClienteFinal clienteFinal=  vfc.getClienteFinal();
        ticket = new Ticket();
        for (int x= 0;x<salaFuncion.getX();x++){
            for (int y= 0;y<salaFuncion.getY();y++){
                if (getNodeByRowColumnIndex(x,y,gridSeats).getStyle().equals("-fx-fill:red; -fx-font-family: 'Material Icons'; -fx-font-size: 30.0;")){
                    getNodeByRowColumnIndex(x,y,gridSeats).setStyle("-fx-fill:#c9b3b3; -fx-font-family: 'Material Icons'; -fx-font-size: 30.0;");
                    funcionAux.reservaButaca(x,y);
                    System.out.println(funcionAux.getId() + funcionAux.getHoraInicio() + salaService.findById(funcionAux.getSalaId()) );
                    ShowButacas();
                    ticket.addAsiento(y,x);
                    ticket.setAsientoCol(x);
                    ticket.setAsientosFila(y);
                    //ticket.setFuncionId(funcionAux.getId());
                    //ticket.setClienteId(clienteFinal.getUserName());


                }

            }
        }
        ticket.setFuncionId(funcionAux.getId());
        ticket.setClienteId(clienteFinal.getUserName());
        funcionService.update(funcionAux); // se esta creando una nueva funcion en la base no se esta updateando
        ticketService.save(ticket);
        AlertBox.display("Compra Exitosa","Compraste el asiento : "  + ticket.imprimirAsientos()+ " \n Fecha : " + funcionAux.getHoraInicio() + " \n Pelicula : " +movieService.findById(funcionAux.getMovieId())+ " \n Sala : " + salaService.findById(funcionAux.getSalaId())+ " \n Cliente :" + clienteFinal.getName());

    }
    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }



    public ObservableList<Funcion> getFunciones(){

        ObservableList<Funcion> funciones = FXCollections.observableArrayList();

        List<Funcion> lista = funcionService.findAll();

        for (int i = 0; i < lista.size(); i++) {
            Funcion funcion = lista.get(i);

            if(movieService.findById(funcion.getMovieId()).getName().equals(movieAux.getName()) ) {
                funciones.add(lista.get(i));
            }
        }

        return funciones;
    }




}


// TODO LAS FUNCIONES SE REGISTRAN EN TODAS LOS HORARIOS
