package um.edu.tic1.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import um.edu.tic1.entities.Movie;
import um.edu.tic1.entities.Sala;
import um.edu.tic1.services.CineService;
import um.edu.tic1.services.SalaService;

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

    @FXML
    private TableView<Sala> tabla;

    @FXML
    private TableColumn<Sala, String> nombreSala;
    @FXML
    private TextField nombreAgregado;
    @Autowired
    private SalaService salaService;


    public void initialize() {


        //set up the columns in the table
        nombreSala.setCellValueFactory(new PropertyValueFactory<>("name"));

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

    public ObservableList<Sala> getSalas() {

        ObservableList<Sala> salas = FXCollections.observableArrayList();

        List<Sala> lista = salaService.findAll();

        for (int i = 0; i < lista.size(); i++) {
            salas.add(lista.get(i));
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
        sala.setName(nombre);
        sala.setCine(cine);
        salaService.save(sala);
        nombreAgregado.clear();
        initialize();
        // cineService.save(cine2);


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
