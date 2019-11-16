package um.edu.tic1.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.edu.tic1.Tic1Application;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

//import de.jensd.fx.glyphs.materialicons.MaterialIconView;

/**
 * The controller for the Bookings Scene.
 * 
 * @author Team 3: Filippos Zofakis and Lucio D'Alessandro
 * @since 12.12.2017
 */

@Component
public class ManageBookingsController implements Initializable {

    int bookedSeatsCount;

    @FXML
    static Stage stage;
    @FXML
    GridPane gridSeats;
    @FXML
    Button backButton, giveFeedback;
    @FXML
    DatePicker datePicker;
    @FXML
    ComboBox<String> filmDropDownList, timeDropDownList, customerDropDownList;
    @FXML
    Label bookedSeatsLabel, availableSeatsLabel, totalSeatsLabel;
    @FXML
    Text customer;
    @FXML
    //MaterialIconView A1, A2, A3, A4, A5, A6, B1, B2, B3, B4, B5, B6, C1, C2, C3, C4, C5, C6;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        // setting the date to the current one in the default time-zone of the system
        datePicker.setValue(LocalDate.now());
        try {
            populateFilmDropDownList(new ActionEvent());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        populateTimeDropDownList(new ActionEvent());
        populateUserDropDownList(new ActionEvent());

        // setting the total number of seats to a value of 18
        totalSeatsLabel.setText("Total seats: 18");
        bookedSeatsCount = 0;

        // resetting all seats to black every time the user selects a new screening time
        for (int i = 0; i < 18; i++) {
            gridSeats.getChildren().get(i)
                    .setStyle("-fx-fill:black; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;");
        }

        bookedSeatsLabel.setText("Booked seats: " + bookedSeatsCount);
        availableSeatsLabel.setText("Available seats: " + (18 - bookedSeatsCount));
    }
    @FXML
    private void selectSeat(MouseEvent e) {

        // firing a pop up message if user clicks on already booked seat
        if (((Node) e.getSource()).getStyle()
                .equals("-fx-fill:#c9b3b3; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;")) {
            Alert alert = new Alert(AlertType.WARNING,
                    "The seat " + ((Node) e.getSource()).getId() + " is already booked!", ButtonType.OK);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                alert.close();
            }
        } else {
            // turning seat back to black if it is red - unselecting it
            if (((Node) e.getSource()).getStyle()
                    .equals("-fx-fill:red; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;")) {
                ((Node) e.getSource())
                .setStyle("-fx-fill:black; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;");
            //    Main.getSelectedSeats().remove(((Node) e.getSource()).getId());
            }
            // turning seat red if it is black - selecting it
            else {
                ((Node) e.getSource())
                .setStyle("-fx-fill:red; -fx-font-family: 'Material Icons'; -fx-font-size: 40.0;");
              //  Main.getSelectedSeats().add(((Node) e.getSource()).getId());
            }
        }
    }

    /**
     * Method that gets called when a customer clicks on the book seat button to make a booking.
     * @param e Mouse event representing a click on the book seat button
     * @throws IOException Exception to be thrown if there is a problem with storing the booking in the JSON text files
     * @throws GeneralSecurityException Exception to be thrown if encryption fails
     */
    @FXML
    private void bookSeat(MouseEvent e) throws IOException, GeneralSecurityException {


        try {
            datePicker.getValue().equals(null);
            filmDropDownList.getValue().equals(null);
            timeDropDownList.getValue().equals(null);
        } catch(NullPointerException ex){
            throwAlert();
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION, "Do you want to proceed with the booking?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if(alert.getResult() == ButtonType.NO) {
            alert.close();
            return;
        }
        else {
            // getting the latest booking id and incrementing by one
            // System.out.println(newBookingId);
            //        System.out.println(customerDropDownList.getValue());



        }
    }

    static Stage getStage() {

        return stage;
    }

    @FXML
    private void showBookingHistoryOnClick(ActionEvent event) throws IOException {

        //SceneCreator.launchScene("/scenes/BookingHistoryScene.fxml");
    }

    @FXML
    private void giveFeedback(ActionEvent event) throws IOException {

        //SceneCreator.launchScene("/scenes/FeedbackScene.fxml");
    }

    @FXML
    private void backToPrevScene(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Tic1Application.getContext()::getBean);

        Parent inicio = fxmlLoader.load(getClass().getResourceAsStream("/templates/viewFilms.fxml"));
        inicio.getStylesheets().add("/templates/styles.css");
        Scene inicioScene = new Scene(inicio,1000,500);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(inicioScene);
        window.show();
    }

    /**
     * Method that gets called when a date is selected from the date picker.
     * @param event Action event representing a selection in the date picker
     * @throws ParseException Exception to be thrown if the parsing of film start and end dates to LacalDate objects fails
     */
    @FXML
    private void populateFilmDropDownList(ActionEvent event) throws ParseException {

        //Main.readJSONFile("filmsJSON.txt");
        try {
            //Main.setSelectedDate(datePicker.getValue().toString());

            ObservableList<String> filmTitles = FXCollections.observableArrayList();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


            filmDropDownList.setItems(filmTitles);
        }
        catch(NullPointerException e) {
            e.getMessage();
        }
    }

    /**
     * Method that gets called when a film is selected from the drop-down list.
     * @param event Action event representing a selection in the films' drop-down list
     */
    @FXML
    private void populateTimeDropDownList(ActionEvent event) {
    }

    @FXML
    private void populateUserDropDownList(ActionEvent event) {



    }

    @FXML
    private void throwAlert() {

        Alert alert = new Alert(AlertType.INFORMATION, "Please make sure all fields are selected!", ButtonType.OK);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK){
            alert.close();
        }
    }
}