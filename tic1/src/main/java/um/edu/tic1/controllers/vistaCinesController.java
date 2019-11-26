package um.edu.tic1.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import um.edu.tic1.Tic1Application;
import um.edu.tic1.entities.*;
import um.edu.tic1.services.CineService;
import um.edu.tic1.services.FuncionService;
import um.edu.tic1.services.SalaService;
import um.edu.tic1.services.TicketService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class vistaCinesController {

    private Cine cine;

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    @Autowired
    private CineService cineService;
    @Autowired
    private TicketService ticketService;

    @FXML
    private TableView<Ticket> tablaTickets;

    @FXML
    private TableColumn<Ticket,String> peliculaTicket;
    @FXML
    private TableColumn<Ticket,String> salaTicket;
    @FXML
    private TableColumn<Ticket,String> clienteTicket;
    @FXML
    private TableColumn<Ticket,String> FilaTicket;
    @FXML
    private TableColumn<Ticket,String> ColumnaTicket;
    @FXML
    private TableColumn<Ticket,String> horarioTicket;

    @FXML
    private TableView<Sala> tabla;

    @FXML
    private TableView<Funcion> tablaFunciones;

    @FXML
    private TableColumn<Funcion,String> salaFuncion;

    @FXML
    private TableColumn<Funcion,String> codigoFuncion;

    @FXML
    private TableColumn<Funcion,String> peliculaFuncion;

    @FXML
    private TableColumn<Funcion,String> dimensionFuncion;

    @FXML
    private TableColumn<Sala, String> nombreSala;

    @FXML
    private TableColumn<Sala, String> capacidad;

    @FXML
    private TableColumn<Sala, String> idSala;

    @FXML
    private TableColumn<Sala, Boolean> tresDCol;

    @FXML
    private TextField nombreAgregado;

    @FXML
    private TextField largo;
    @FXML
    private TextField ancho;

    @FXML
    private CheckBox select3D;

    @FXML
    private CheckBox select4D;

    @Autowired
    private SalaService salaService;

    @Autowired
    private FuncionService funcionService;

    @FXML
    private TableColumn<Sala, Boolean> cuatroDCol;


    public void initialize() {

        inicializarSalas();
        inicializarFunciones();

    }

    public void inicializarFunciones(){

        codigoFuncion.setCellValueFactory(new PropertyValueFactory<>("id"));
        salaFuncion.setCellValueFactory(new PropertyValueFactory<>("sala"));
        peliculaFuncion.setCellValueFactory(new PropertyValueFactory<>("movie"));
        dimensionFuncion.setCellValueFactory(new PropertyValueFactory<>("dimension"));

        tablaFunciones.setItems((ObservableList<Funcion>) getFunciones());

        peliculaTicket.setCellValueFactory(new PropertyValueFactory<>("name"));
        salaTicket.setCellValueFactory(new PropertyValueFactory<>("sala"));
        clienteTicket.setCellValueFactory(new PropertyValueFactory<>("clienteName"));
        horarioTicket.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        FilaTicket.setCellValueFactory(new PropertyValueFactory<>("asientosFila"));
        ColumnaTicket.setCellValueFactory(new PropertyValueFactory<>("asientoCol"));
        tablaTickets.setItems(getTickets());


    }

    private ObservableList<Ticket> getTickets() {

        ObservableList<Ticket> tickets = FXCollections.observableArrayList();

        List<Ticket> lista = ticketService.findAll();

        for (int i = 0; i < lista.size(); i++) {
            Ticket ticket = lista.get(i);

            if(ticket.getFuncion().getSala().getCine().equals(this.cine)) {
                tickets.add(lista.get(i));
            }
        }

        return tickets;
    }


    public void inicializarSalas(){
        //set up the columns in the table
        nombreSala.setCellValueFactory(new PropertyValueFactory<>("name"));
        capacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        idSala.setCellValueFactory(new PropertyValueFactory<>("id"));
        tresDCol.setCellValueFactory(new PropertyValueFactory<>("tresD"));
        cuatroDCol.setCellValueFactory(new PropertyValueFactory<>("cuatroD"));

        //load dummy data
        tabla.setItems((ObservableList<Sala>) getSalas());

        //Update the table to allow for the first and last name fields
        //to be editable
        tabla.setEditable(true); //
        nombreSala.setCellFactory(TextFieldTableCell.forTableColumn());


        //This will allow the table to select multiple rows at once
        //vamos a usar esto para poder marcar varias y eliminarlas
        //tabla.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public ObservableList<Funcion> getFunciones(){

        ObservableList<Funcion> funciones = FXCollections.observableArrayList();

        List<Funcion> lista = funcionService.findAll();

        for (int i = 0; i < lista.size(); i++) {
            Funcion funcion = lista.get(i);

            if(funcion.getSala().getCine().equals(this.cine)) {
                funciones.add(lista.get(i));
            }
        }

        return funciones;
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


    @FXML
    void aplicar(ActionEvent event) {

    }

    @FXML
    void editNombreCommit(ActionEvent event) {

    }

    @FXML
    void agregarSala(ActionEvent event) {
        String nombre = nombreAgregado.getText();
        Sala sala = new Sala();

        try {
            int capacidadIntLargo = Integer.parseInt(largo.getText());
            int capacidadIntAncho = Integer.parseInt(ancho.getText());
            sala.setX(capacidadIntAncho);
            sala.setY(capacidadIntLargo);
            sala.setCapacidad(capacidadIntAncho*capacidadIntLargo);
        } catch (NumberFormatException nfe) {

        }

        if(select3D.isSelected()){
            sala.setTresD(true);
        }

        if(select4D.isSelected()){
            sala.setCuatroD(true);
        }


        sala.setName(nombre);
        sala.setCine(cine);
        salaService.save(sala);
        nombreAgregado.clear();
        largo.clear();
        ancho.clear();
        //capacidad.clear();

        initialize();

        // cineService.save(cine2);


    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);
        this.cine = null;
        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/mostrar.fxml"));
        Scene inicioScene = new Scene(inicio, 600, 500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }


}
