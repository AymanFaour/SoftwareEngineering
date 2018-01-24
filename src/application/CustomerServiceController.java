/**
 * Sample Skeleton for 'CustomerServiceView.fxml' Controller Class
 */

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class CustomerServiceController {

    @FXML // fx:id="welcomeBanner"
    private Label welcomeBanner; // Value injected by FXMLLoader

    @FXML // fx:id="HandlingComplaintsCustomerServiceBorderPane"
    private BorderPane HandlingComplaintsCustomerServiceBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="ParkingReservationCustomerServiceBorderPane"
    private BorderPane ParkingReservationCustomerServiceBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResLeavingMinuteComboBox"
    private ComboBox<?> CusSerparkResLeavingMinuteComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResArrivingMinuteComboBox"
    private ComboBox<?> CusSerparkResArrivingMinuteComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="signOutButton"
    private Button signOutButton; // Value injected by FXMLLoader

    @FXML // fx:id="HandlingComplaintsButton"
    private Button HandlingComplaintsButton; // Value injected by FXMLLoader

    @FXML // fx:id="parkingReservationButton"
    private Button parkingReservationButton; // Value injected by FXMLLoader

    @FXML // fx:id="balanceOnTopOfLogIn"
    private Text balanceOnTopOfLogIn; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResArrivingHourComboBox"
    private ComboBox<?> CusSerparkResArrivingHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="ParkingReservationParkingLotWorkerBorderPane1"
    private BorderPane ParkingReservationParkingLotWorkerBorderPane1; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerParkingReservationCarNumberTF"
    private TextField CusSerParkingReservationCarNumberTF; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResLeavingDateDP"
    private DatePicker CusSerparkResLeavingDateDP; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResArrivingDateDP"
    private DatePicker CusSerparkResArrivingDateDP; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResComboBox"
    private ComboBox<?> CusSerparkResComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResReserveParkingButton"
    private Button CusSerparkResReserveParkingButton; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResLeavingHourComboBox"
    private ComboBox<?> CusSerparkResLeavingHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="textInTopOfLogIn"
    private Text textInTopOfLogIn; // Value injected by FXMLLoader

    @FXML
    void signOut(ActionEvent event) {

    }



    @FXML
    void CustomerServiceParkingReservation(ActionEvent event) {

    }

    @FXML
    void loadParkingReservation(ActionEvent event) {
    	HandlingComplaintsCustomerServiceBorderPane.setVisible(false);
    	ParkingReservationCustomerServiceBorderPane.setVisible(true);
    	
    
    
    	
    	
    	
    	parkingReservationButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	parkingReservationButton.getStyleClass().add("pressedButton");
    	HandlingComplaintsButton.getStyleClass().removeAll("pressedButton", "focus");
    	HandlingComplaintsButton.getStyleClass().add("loginView-buttons");

    }

    @FXML
    void loadHandlingComplaints(ActionEvent event) {
    	HandlingComplaintsCustomerServiceBorderPane.setVisible(true);
    	ParkingReservationCustomerServiceBorderPane.setVisible(false);
    	
    
    	
    	
    	
    	HandlingComplaintsButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	HandlingComplaintsButton.getStyleClass().add("pressedButton");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");

    }

   
}
