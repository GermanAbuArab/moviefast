package um.edu.tic1.client.controllers;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import um.edu.tic1.Tic1Application;
import um.edu.tic1.client.ClientApplication;
import um.edu.tic1.client.models.Cine;
import um.edu.tic1.entities.Cine;
import um.edu.tic1.services.CineService;

import java.io.IOException;
import java.util.List;

@Controller
public class TablaCinesController {

    @FXML
    private Button botonAplicar;

    @FXML
    private TableView<Cine> tabla;

    @FXML
    private TableColumn<Cine, String> nombrePeli;

    public void initialize() {

        //set up the columns in the table
        nombrePeli.setCellValueFactory(new PropertyValueFactory<>("name"));

        //load dummy data
        tabla.setItems(getCine());

        //Update the table to allow for the first and last name fields
        //to be editable
        tabla.setEditable(true); //
        nombrePeli.setCellFactory(TextFieldTableCell.forTableColumn());



        //This will allow the table to select multiple rows at once
        //vamos a usar esto para poder marcar varias y eliminarlas
        //tabla.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    @Autowired
    private CineService cs;
    @Autowired
    private vistaCinesController vc;

    private ObservableList<Cine> getCine() {

        ObservableList<Cine> movie = FXCollections.observableArrayList();

        List<Cine> lista = cs.findAll();

        for (int i = 0; i < lista.size(); i++) {
            movie.add(lista.get(i));
        }

        return movie;
    }



    @FXML
    void agregar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/addCine.fxml"));
        Scene inicioScene = new Scene(inicio,600,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    @FXML
    void aplicar(ActionEvent event) {

    }

    @FXML
    void editNombreCommit(ActionEvent event) {

    }

    @FXML
    void eliminar(ActionEvent event) {

    }

    @FXML
    void volver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/viewFilms.fxml"));
        inicio.getStylesheets().add("/templates/styles.css");
        Scene inicioScene = new Scene(inicio,1000,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    public void peliculas(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/mostrar.fxml"));
        Scene inicioScene = new Scene(inicio,600,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();

    }

    public void entrar(ActionEvent event) throws IOException {

        Cine cine = tabla.getSelectionModel().getSelectedItem();
        vc.setCine(cine);
        if (cine == null){
            AlertBox.display("Error","Seleccione un cine");
        }else {


            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);
            Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/vistaFunciones.fxml"));
            Scene inicioScene = new Scene(inicio, 600, 500);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(inicioScene);
            window.show();
        }
    }
}
