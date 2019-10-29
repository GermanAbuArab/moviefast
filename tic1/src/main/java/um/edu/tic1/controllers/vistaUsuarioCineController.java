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
import um.edu.tic1.entities.Cine;
import um.edu.tic1.entities.Funcion;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.entities.Sala;
import um.edu.tic1.services.CineService;
import um.edu.tic1.services.FuncionService;
import um.edu.tic1.services.MovieService;
import um.edu.tic1.services.SalaService;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class vistaUsuarioCineController {

    private Cine cine;

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    @Autowired
    private CineService cineService;

    @FXML
    private TableView<Sala> tabla;

    @FXML
    private TableView<Funcion> tablaFunciones;

    @FXML
    private TableColumn<Funcion,String> salaFuncion;

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
        funcion.setSala(sala);
        funcion.setMovie(peli);
        //funcion.setName(nombre);
        funcion.setDimension(dimension);
        funcion.setHoraInicio(fechatotalinicio.format(formatter));
        funcion.setHoraFin(fechatotalFin.format(formatter));



        funcionService.save(funcion);



        inicializarFunciones();
    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);
        this.cine = null;
        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/inicio.fxml"));
        Scene inicioScene = new Scene(inicio, 600, 500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    public void addFun(){

        LocalDate fechainicio = fechaInicio.getValue();
        LocalDate fechafin = fechaFin.getValue();


        if(todosDias.isSelected()){
            do {
                agregarFunIndividual(fechainicio);
                fechainicio = fechainicio.plusDays(1);
            }while(!fechainicio.equals(fechafin));
        }else {

            do {

                if (fechainicio.getDayOfWeek().equals(DayOfWeek.MONDAY) && lunes.isSelected()) {
                    agregarFunIndividual(fechainicio);
                }
                else if (fechainicio.getDayOfWeek().equals(DayOfWeek.TUESDAY) && martes.isSelected()) {
                    agregarFunIndividual(fechainicio);
                }
                else if (fechainicio.getDayOfWeek().equals(DayOfWeek.THURSDAY) && miercoles.isSelected()) {
                    agregarFunIndividual(fechainicio);
                }
                else if (fechainicio.getDayOfWeek().equals(DayOfWeek.WEDNESDAY) && jueves.isSelected()) {
                    agregarFunIndividual(fechainicio);
                }
                else if (fechainicio.getDayOfWeek().equals(DayOfWeek.SUNDAY) && viernes.isSelected()) {
                    agregarFunIndividual(fechainicio);
                }
                else if (fechainicio.getDayOfWeek().equals(DayOfWeek.SATURDAY) && sabado.isSelected()) {
                    agregarFunIndividual(fechainicio);
                }
                else if (fechainicio.getDayOfWeek().equals(DayOfWeek.FRIDAY) && domingo.isSelected()) {
                    agregarFunIndividual(fechainicio);
                }

                fechainicio = fechainicio.plusDays(1);

            } while (!fechainicio.equals(fechafin));
        }

        inicializarFunciones();
    }

    public void agregarFunIndividual(LocalDate fecha){

        System.out.println("Entro aca MAN");

        Funcion funcion = new Funcion();

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



        //Date fecha = new Date(String.valueOf(fechaFin.getChronology()));
        //System.out.println(fecha.toString());

        funcion.setId(id);
        funcion.setSala(sala);
        funcion.setMovie(peli);
        funcion.setDuracion(Integer.parseInt(duracionMovie.getText()));
        //funcion.setName(nombre);
        funcion.setDimension(dimension);
        funcion.setHoraInicio(fechatotalinicio.format(formatter));
        funcion.setHoraFin(fechatotalFin.format(formatter));



        funcionService.save(funcion);

    }

}
