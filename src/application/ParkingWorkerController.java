/**
 * Sample Skeleton for 'ParkingWrokerView.fxml' Controller Class
 */

package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ParkingWorkerController {

    @FXML // fx:id="welcomeBanner"
    private Label welcomeBanner; // Value injected by FXMLLoader

    @FXML // fx:id="disabledParkingSpotButton"
    private Button disabledParkingSpotButton; // Value injected by FXMLLoader

    @FXML // fx:id="DisaParkSpotUntilHourComboBox"
    private ComboBox<String> DisaParkSpotUntilHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="ParkingReservationParkingLotWorkerBorderPane"
    private BorderPane ParkingReservationParkingLotWorkerBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="parkResLeavingMinuteComboBox"
    private ComboBox<String> parkResLeavingMinuteComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="parkResLeavingHourComboBox"
    private ComboBox<String> parkResLeavingHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="signOutButton"
    private Button signOutButton; // Value injected by FXMLLoader

    @FXML // fx:id="parkResLeavingDateDP"
    private DatePicker parkResLeavingDateDP; // Value injected by FXMLLoader

    @FXML // fx:id="parkingReservationButton"
    private Button parkingReservationButton; // Value injected by FXMLLoader

    @FXML // fx:id="balanceOnTopOfLogIn"
    private Text balanceOnTopOfLogIn; // Value injected by FXMLLoader

    @FXML // fx:id="DisaParkSpotUntilMinuteComboBox"
    private ComboBox<String> DisaParkSpotUntilMinuteComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="ParkingWorkerReserveParkingButton"
    private Button ParkingWorkerReserveParkingButton; // Value injected by FXMLLoader

    @FXML // fx:id="DiabledParkingSpotParkingBorderPane"
    private BorderPane DiabledParkingSpotParkingBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="textInTopOfLogIn"
    private Text textInTopOfLogIn; // Value injected by FXMLLoader

    @FXML // fx:id="DisaParkSpotSpotIdTF"
    private TextField DisaParkSpotSpotIdTF; // Value injected by FXMLLoader

    @FXML // fx:id="alternativeComboBox"
    private ComboBox<String> alternativeComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="parkResComboBox"
    private ComboBox<String> parkResComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="DisaParkSpotUntilDateDP"
    private DatePicker DisaParkSpotUntilDateDP; // Value injected by FXMLLoader

    @FXML // fx:id="IntitializationButton"
    private Button IntitializationButton; // Value injected by FXMLLoader

    @FXML // fx:id="alternativeParkingReToAlterParkButton"
    private Button alternativeParkingReToAlterParkButton; // Value injected by FXMLLoader

    @FXML // fx:id="parkResArrivingDateDP"
    private DatePicker parkResArrivingDateDP; // Value injected by FXMLLoader

    @FXML // fx:id="parkResArrivingMinuteComboBox"
    private ComboBox<String> parkResArrivingMinuteComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="AlternativeParkingParkingLotWorkerBorderPane"
    private BorderPane AlternativeParkingParkingLotWorkerBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="parkResReserveParkingButton"
    private Button parkResReserveParkingButton; // Value injected by FXMLLoader

    @FXML // fx:id="alternativeParkingReservationIdTF"
    private TextField alternativeParkingReservationIdTF; // Value injected by FXMLLoader

    @FXML // fx:id="parkResArrivingHourComboBox"
    private ComboBox<String> parkResArrivingHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="AlternativeParkingCarNumberTF"
    private TextField AlternativeParkingCarNumberTF; // Value injected by FXMLLoader

    @FXML // fx:id="IntitializationParkingLotWorkerBorderPane"
    private BorderPane IntitializationParkingLotWorkerBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="ParkingReservationCarNumberTF"
    private TextField ParkingReservationCarNumberTF; // Value injected by FXMLLoader

    @FXML // fx:id="alternativeParkButton"
    private Button alternativeParkButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="ParkingWorkerActivateParkingSpotButton"
    private Button ParkingWorkerActivateParkingSpotButton; // Value injected by FXMLLoader
   
    @FXML // fx:id="ParkingWorkerDisabledParkingSpotButton"
    private Button ParkingWorkerDisabledParkingSpotButton; // Value injected by FXMLLoader

    private ObservableList<String> myComboBoxHoursData = FXCollections.observableArrayList();
    private ObservableList<String> myComboBoxMinutesData = FXCollections.observableArrayList();
    //private ObservableList<String> myComboBoxComplaintParkingData = FXCollections.observableArrayList();
    
    
    @FXML
    void signOut(ActionEvent event) {

    }
    @FXML
    void disabledParkingSpot(ActionEvent event) {

    }
    @FXML
    void activateParkingSpot(ActionEvent event) {

    }

    @FXML
    void ReferenceToAlternativeParking(ActionEvent event) {

    }


    @FXML
    void parkingWorkerReserveParking(ActionEvent event) {
    	
    }



   

    @FXML
    void loadReferenceToAlternativeParking(ActionEvent event) {
    	AlternativeParkingParkingLotWorkerBorderPane.setVisible(true);
    	IntitializationParkingLotWorkerBorderPane.setVisible(false);
    	ParkingReservationParkingLotWorkerBorderPane.setVisible(false);
    	DiabledParkingSpotParkingBorderPane.setVisible(false);
    	
    	
    	alternativeParkButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	alternativeParkButton.getStyleClass().add("pressedButton");
    	IntitializationButton.getStyleClass().removeAll("pressedButton", "focus");
    	IntitializationButton.getStyleClass().add("loginView-buttons");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	disabledParkingSpotButton.getStyleClass().removeAll("pressedButton", "focus");
    	disabledParkingSpotButton.getStyleClass().add("loginView-buttons");
    
    }

    @FXML
    void loadIntitialization(ActionEvent event) {
    	AlternativeParkingParkingLotWorkerBorderPane.setVisible(false);
    	IntitializationParkingLotWorkerBorderPane.setVisible(true);
    	ParkingReservationParkingLotWorkerBorderPane.setVisible(false);
    	DiabledParkingSpotParkingBorderPane.setVisible(false);
    	
    	
    	IntitializationButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	IntitializationButton.getStyleClass().add("pressedButton");
    	alternativeParkButton.getStyleClass().removeAll("pressedButton", "focus");
    	alternativeParkButton.getStyleClass().add("loginView-buttons");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	disabledParkingSpotButton.getStyleClass().removeAll("pressedButton", "focus");
    	disabledParkingSpotButton.getStyleClass().add("loginView-buttons");

    }

    @FXML
    void loadParkingReservation(ActionEvent event) {
    	AlternativeParkingParkingLotWorkerBorderPane.setVisible(false);
    	IntitializationParkingLotWorkerBorderPane.setVisible(false);
    	ParkingReservationParkingLotWorkerBorderPane.setVisible(true);
    	DiabledParkingSpotParkingBorderPane.setVisible(false);
    	
    	
    	parkingReservationButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	parkingReservationButton.getStyleClass().add("pressedButton");
    	IntitializationButton.getStyleClass().removeAll("pressedButton", "focus");
    	IntitializationButton.getStyleClass().add("loginView-buttons");
    	alternativeParkButton.getStyleClass().removeAll("pressedButton", "focus");
    	alternativeParkButton.getStyleClass().add("loginView-buttons");
    	disabledParkingSpotButton.getStyleClass().removeAll("pressedButton", "focus");
    	disabledParkingSpotButton.getStyleClass().add("loginView-buttons");

    	myComboBoxHoursData.clear();
    	for(Integer i = 0; i < 24; i++){
    		if(i < 10 ){
    			myComboBoxHoursData.add("0" + i.toString());
    		}
    		else
    			myComboBoxHoursData.add(i.toString());
    	}
    	
    	myComboBoxMinutesData.clear();
    	for(Integer i = 0; i < 60; i++){
    		if(i < 10 ){
    			myComboBoxMinutesData.add("0" + i.toString());
    		}
    		else
    			myComboBoxMinutesData.add(i.toString());
    	}

    	parkResLeavingHourComboBox.setItems(myComboBoxHoursData);
    	parkResLeavingMinuteComboBox.setItems(myComboBoxMinutesData);
    	parkResArrivingHourComboBox.setItems(myComboBoxHoursData);
    	parkResArrivingMinuteComboBox.setItems(myComboBoxMinutesData);
    	
    }

    @FXML
    void loadDisabledParkingSpot(ActionEvent event) {
    	AlternativeParkingParkingLotWorkerBorderPane.setVisible(false);
    	IntitializationParkingLotWorkerBorderPane.setVisible(false);
    	ParkingReservationParkingLotWorkerBorderPane.setVisible(false);
    	DiabledParkingSpotParkingBorderPane.setVisible(true);
    	
    	
    	disabledParkingSpotButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	disabledParkingSpotButton.getStyleClass().add("pressedButton");
    	IntitializationButton.getStyleClass().removeAll("pressedButton", "focus");
    	IntitializationButton.getStyleClass().add("loginView-buttons");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	alternativeParkButton.getStyleClass().removeAll("pressedButton", "focus");
    	alternativeParkButton.getStyleClass().add("loginView-buttons");

    }

 

}
