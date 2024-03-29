package um.edu.tic1.client.controllers;

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

import um.edu.tic1.client.ClientApplication;
import um.edu.tic1.client.models.*;
import um.edu.tic1.client.services.*;


import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class vistaUsuarioCineController {

    private Cine cine;

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    @Autowired
    private CineService cineService;
    @Autowired
    private TicketService ticketService;

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
    private TableColumn<Ticket,String> asientosTicket;


    @FXML
    private TableView<Ticket> tablaTickets;

    @FXML
    private TableView<Sala> tabla;

    @FXML
    private TableView<Funcion> tablaFunciones;

    @FXML
    private TableColumn<Funcion,String> salaFuncion;

    @FXML
    private TableColumn<Funcion,String> funcionTicket;

    @FXML
    private TableColumn<Funcion,String> fechaFuncion;
    @FXML
    private TableColumn<Funcion,String> finFuncion;

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
    private TextField nombreAgregado;

    @FXML
    private TextField capacidadAgregada;

    @FXML
    private ComboBox comboSala;

    @FXML
    private ComboBox comboDimension;

    @FXML
    private ComboBox comboPeli;

    @Autowired
    private SalaService salaService;

    @Autowired
    private FuncionService funcionService;

    @Autowired
    private MovieService movieService;

    @FXML
    private TableColumn<Sala, Boolean> tresDCol;

    @FXML
    private TableColumn<Sala, Boolean> cuatroDCol;

    @FXML
    private ComboBox horaInicio;

    @FXML
    private TextField duracionMovie;

    @FXML
    private DatePicker fechaInicio;

    @FXML
    private DatePicker fechaFin;

    @FXML
    private CheckBox todosDias;

    @FXML
    private CheckMenuItem lunes;

    @FXML
    private CheckMenuItem martes;

    @FXML
    private CheckMenuItem miercoles;

    @FXML
    private CheckMenuItem jueves;

    @FXML
    private CheckMenuItem viernes;

    @FXML
    private CheckMenuItem sabado;

    @FXML
    private CheckMenuItem domingo;


    public void initialize() {

        inicializarSalas();
        inicializarFunciones();
        inicializarTickets();
        inicializarCombos();

    }

    public void inicializarCombos(){

        ObservableList<Sala> salas = getSalas();
        ObservableList<Movie> pelis = getMovie();
        ObservableList<String> horas = FXCollections.observableArrayList();
        horas.setAll("08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00");

        comboSala.setItems(salas);
        comboPeli.setItems(pelis);
        horaInicio.setItems(horas);

    }

    private ObservableList<Ticket> getTickets() {

        ObservableList<Ticket> ticketsLista = FXCollections.observableArrayList();

        List<Ticket> lista = ticketService.findAll();

        for (int i = 0; i < lista.size(); i++) {
            Ticket ticket = lista.get(i);

            if(funcionService.findById(ticket.getFuncionId()).getCineId().equals(this.cine.getId())) {
                lista.get(i).setMovie(movieService.findById(funcionService.findById(lista.get(i).getFuncionId()).getMovieId()).getName());
                lista.get(i).setSala(salaService.findById(funcionService.findById(lista.get(i).getFuncionId()).getSalaId()).getName());

                String aux = "Asientos: ";
                for(int k=0;k<lista.get(i).getAsientos().size();k=k+2){
                    aux = aux + " columna "+lista.get(i).getAsientos().get(k);
                    aux = aux + " fila "+lista.get(i).getAsientos().get(k+1);
                }

                lista.get(i).setAsientosString(aux);

                ticketsLista.add(lista.get(i));
            }
        }

        return ticketsLista;
    }


    public void comboSalaActualizado(){

        Sala sala = (Sala) comboSala.getSelectionModel().getSelectedItem();

        //segun esto se pone la dimension
        ObservableList<String> dimensiones = FXCollections.observableArrayList();
        dimensiones.add("2D");

        if(sala.isTresD()){
            dimensiones.add("3D");
        }

        if(sala.isCuatroD()){
            dimensiones.add("4D");
        }

        comboDimension.setItems(dimensiones);

    }

    public void inicializarFunciones(){

        codigoFuncion.setCellValueFactory(new PropertyValueFactory<>("id"));
        salaFuncion.setCellValueFactory(new PropertyValueFactory<>("sala"));
        peliculaFuncion.setCellValueFactory(new PropertyValueFactory<>("movie"));
        dimensionFuncion.setCellValueFactory(new PropertyValueFactory<>("dimension"));
        fechaFuncion.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        finFuncion.setCellValueFactory(new PropertyValueFactory<>("duracion"));



        tablaFunciones.setItems((ObservableList<Funcion>) getFunciones());

    }
    public void inicializarTickets(){
        clienteTicket.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
        peliculaTicket.setCellValueFactory(new PropertyValueFactory<>("movie"));
        salaTicket.setCellValueFactory(new PropertyValueFactory<>("sala"));
        funcionTicket.setCellValueFactory(new PropertyValueFactory<>("funcionId"));
        asientosTicket.setCellValueFactory(new PropertyValueFactory<>("asientosString"));

        tablaTickets.setItems((ObservableList<Ticket>) getTickets());

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

            if(funcion.getCineId().equals(this.cine.getId())) {
                lista.get(i).setMovie(movieService.findById(lista.get(i).getMovieid()));
                //System.out.println("SalaID "+lista.get(i).getSalaId());//podemos ver que llega el salaID
                lista.get(i).setSala(salaService.findById(lista.get(i).getSalaId()));
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

            if(sala.getCineId().equals(this.cine.getId())) {
                salas.add(lista.get(i));
            }
        }

        return salas;
    }

    public ObservableList<Movie> getMovie() {

        ObservableList<Movie> movie = FXCollections.observableArrayList();

        List<Movie> lista = movieService.findAll();

        for (int i = 0; i < lista.size(); i++) {
            movie.add(lista.get(i));
        }

        return movie;
    }

    @FXML
    void editNombreCommit(ActionEvent event) {

    }

    public void addFun2(){

        System.out.println("Entro aca MAN");

        Funcion funcion = new Funcion();

        Sala sala = (Sala) comboSala.getSelectionModel().getSelectedItem();
        Movie peli = (Movie) comboPeli.getSelectionModel().getSelectedItem();
        //String nombre = nombreAgregado.getText();
        String dimension = (String) comboDimension.getSelectionModel().getSelectedItem();
        LocalDate fechainicio = fechaInicio.getValue();
        LocalDate fechafin = fechaFin.getValue();
        String horainicio = (String)horaInicio.getSelectionModel().getSelectedItem();
        String fechatotalInicio = fechainicio +"T"+ horainicio + ":00.00";
        LocalDateTime fechatotalinicio = LocalDateTime.parse(fechatotalInicio);
        LocalDateTime fechatotalFin = fechatotalinicio.plusMinutes(( Long.valueOf(duracionMovie.getText())));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss");
        long id = 1;



        //Date fecha = new Date(String.valueOf(fechaFin.getChronology()));
        //System.out.println(fecha.toString());

        funcion.setId(id);
        funcion.setSalaId(sala.getId());
        funcion.setMovieId(peli.getId());
        //funcion.setName(nombre);
        funcion.setDimension(dimension);
        funcion.setHoraInicio(fechatotalinicio.format(formatter));
        funcion.setHora(horainicio);
        funcion.setHoraFin(fechatotalFin.format(formatter));



        funcionService.save(funcion);



        inicializarFunciones();
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        this.cine = null;
        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/login.fxml")); //todo tira un error
        inicio.getStylesheets().add("/templates/styles.css");
        Scene inicioScene = new Scene(inicio, 1000, 500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    public void addFun()throws datoNoSeleccionado{

        this.agregado = false;

        LocalDate fechainicio = fechaInicio.getValue();
        LocalDate fechafin = fechaFin.getValue();

        if(fechainicio.isBefore(LocalDateTime.now().toLocalDate())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("Alerta");
            alert.setContentText("La fecha inicial seleccionada es anterior a la fecha actual");
            alert.showAndWait();
            throw new datoNoSeleccionado();
        }

        if(fechaFin.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("Alerta");
            alert.setContentText("Fecha final no seleccionada");
            alert.showAndWait();
            throw new datoNoSeleccionado();
        }

        if(fechaInicio.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("Alerta");
            alert.setContentText("Fecha inicial no seleccionada");
            alert.showAndWait();
            throw new datoNoSeleccionado();
        }

        if(fechainicio.isAfter(fechafin)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("Alerta");
            alert.setContentText("La fecha inicial es posterior a la fecha final");
            alert.showAndWait();
            throw new datoNoSeleccionado();
        }

        if(fechainicio.equals(fechafin)){
            if (fechainicio.getDayOfWeek().equals(DayOfWeek.MONDAY) && lunes.isSelected()) {
                agregarFunIndividual(fechainicio);
            } else if (fechainicio.getDayOfWeek().equals(DayOfWeek.TUESDAY) && martes.isSelected()) {
                agregarFunIndividual(fechainicio);
            } else if (fechainicio.getDayOfWeek().equals(DayOfWeek.WEDNESDAY) && miercoles.isSelected()) {
                agregarFunIndividual(fechainicio);
            } else if (fechainicio.getDayOfWeek().equals(DayOfWeek.THURSDAY) && jueves.isSelected()) {
                agregarFunIndividual(fechainicio);
            } else if (fechainicio.getDayOfWeek().equals(DayOfWeek.FRIDAY) && viernes.isSelected()) {
                agregarFunIndividual(fechainicio);
            } else if (fechainicio.getDayOfWeek().equals(DayOfWeek.SATURDAY) && sabado.isSelected()) {
                agregarFunIndividual(fechainicio);
            } else if (fechainicio.getDayOfWeek().equals(DayOfWeek.SUNDAY) && domingo.isSelected()) {
                agregarFunIndividual(fechainicio);
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alerta");
                alert.setHeaderText("Alerta");
                alert.setContentText("No se ha agregado ninguna funcion");
                alert.showAndWait();
            }

        }else {
            if (todosDias.isSelected()) {
                do {
                    agregarFunIndividual(fechainicio);
                    fechainicio = fechainicio.plusDays(1);
                } while (!fechainicio.equals(fechafin));

            } else {
                do {

                    if (fechainicio.getDayOfWeek().equals(DayOfWeek.MONDAY) && lunes.isSelected()) {
                        agregarFunIndividual(fechainicio);
                    } else if (fechainicio.getDayOfWeek().equals(DayOfWeek.TUESDAY) && martes.isSelected()) {
                        agregarFunIndividual(fechainicio);
                    } else if (fechainicio.getDayOfWeek().equals(DayOfWeek.THURSDAY) && miercoles.isSelected()) {
                        agregarFunIndividual(fechainicio);
                    } else if (fechainicio.getDayOfWeek().equals(DayOfWeek.WEDNESDAY) && jueves.isSelected()) {
                        agregarFunIndividual(fechainicio);
                    } else if (fechainicio.getDayOfWeek().equals(DayOfWeek.SUNDAY) && viernes.isSelected()) {
                        agregarFunIndividual(fechainicio);
                    } else if (fechainicio.getDayOfWeek().equals(DayOfWeek.SATURDAY) && sabado.isSelected()) {
                        agregarFunIndividual(fechainicio);
                    } else if (fechainicio.getDayOfWeek().equals(DayOfWeek.FRIDAY) && domingo.isSelected()) {
                        agregarFunIndividual(fechainicio);
                    }

                    fechainicio = fechainicio.plusDays(1);

                } while (!fechainicio.equals(fechafin));

                if(!agregado){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alerta");
                    alert.setHeaderText("Alerta");
                    alert.setContentText("No se ha agregado ninguna funcion");
                    alert.showAndWait();
                }
            }
        }

        inicializarFunciones();
    }

    private boolean agregado = false;

    public void agregarFunIndividual(LocalDate fecha){


        Funcion funcion = new Funcion();

        if(duracionMovie.getText().equals("")){
            AlertBox.display("Alerta","No ha introducido duracion");
        }

        Sala sala = (Sala) comboSala.getSelectionModel().getSelectedItem();
        Movie peli = (Movie) comboPeli.getSelectionModel().getSelectedItem();
        //String nombre = nombreAgregado.getText();
        String dimension = (String) comboDimension.getSelectionModel().getSelectedItem();
        LocalDate fechainicio = fecha;
        String horainicio = (String)horaInicio.getSelectionModel().getSelectedItem();
        String fechatotalInicio = fechainicio +"T"+ horainicio ;
        LocalDateTime fechatotalinicio = LocalDateTime.parse(fechatotalInicio);
        LocalDateTime fechatotalFin = fechatotalinicio.plusMinutes(( Long.valueOf(duracionMovie.getText())));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd/MM/yyyy HH:mm");
        long id = 1;


        funcion.setMovieId(peli.getId());
        funcion.setDuracion(Integer.parseInt(duracionMovie.getText()));
        //funcion.setName(nombre);
        funcion.setDimension(dimension);
        funcion.setHoraInicio(fechatotalinicio.format(formatter));
        funcion.setHora(horainicio);
        System.out.println("arranca a las " + horainicio);
        funcion.setHoraFin(fechatotalFin.format(formatter));
        funcion.setCineId(this.cine.getId());
        funcion.setSalaId(sala.getId());
        funcion.setButacasConSala(sala);

        funcionService.save(funcion);

        this.agregado = true;

    }

    public void eliminar(ActionEvent event) {
        Funcion funcion = tablaFunciones.getSelectionModel().getSelectedItem();
        funcion.clearAll();
        funcionService.deleteFuncion(funcion.getId());
        inicializarFunciones();
    }
}
