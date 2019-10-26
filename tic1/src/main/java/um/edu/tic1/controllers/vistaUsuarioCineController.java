package um.edu.tic1.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

import java.io.IOException;
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


    public void initialize() {

        inicializarSalas();
        inicializarFunciones();
        inicializarCombos();

    }

    public void inicializarCombos(){

        ObservableList<Sala> salas = getSalas();
        ObservableList<Movie> pelis = getMovie();


        comboSala.setItems(salas);
        comboPeli.setItems(pelis);

    }

    public void comboSalaActualizado(){

        Sala sala = (Sala) comboSala.getSelectionModel().getSelectedItem();

        //segun esto se pone la dimension

    }

    public void inicializarFunciones(){

        codigoFuncion.setCellValueFactory(new PropertyValueFactory<>("id"));
        salaFuncion.setCellValueFactory(new PropertyValueFactory<>("sala"));
        peliculaFuncion.setCellValueFactory(new PropertyValueFactory<>("movie"));
        dimensionFuncion.setCellValueFactory(new PropertyValueFactory<>("dimension"));

        tablaFunciones.setItems((ObservableList<Funcion>) getFunciones());

    }


    public void inicializarSalas(){
        //set up the columns in the table
        nombreSala.setCellValueFactory(new PropertyValueFactory<>("name"));
        capacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        idSala.setCellValueFactory(new PropertyValueFactory<>("id"));

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
    void aplicar(ActionEvent event) {

    }

    @FXML
    void editNombreCommit(ActionEvent event) {

    }

    @FXML
    void agregarFuncion(ActionEvent event) {



    }

    public void addFun(){

        System.out.println("Entro aca MAN");

        Funcion funcion = new Funcion();

        Sala sala = (Sala) comboSala.getSelectionModel().getSelectedItem();
        Movie peli = (Movie) comboPeli.getSelectionModel().getSelectedItem();
        String nombre = nombreAgregado.getText();
        long id = 1;

        funcion.setId(id);
        funcion.setSala(sala);
        funcion.setMovie(peli);
        funcion.setName(nombre);
        funcion.setDimension("2D");

        funcionService.save(funcion);

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

}
