package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import model.ParkingLot;
import model.SharedData;

public class AdministratorController {

    @FXML
    private Button currentSituationButton;

    @FXML
    private Label welcomeBanner;

    @FXML
    private BorderPane changePricesRequestsBorderPane;

    @FXML
    private ComboBox<String> parkLotNameComboBox;

    @FXML
    private DatePicker toDateDP;

    @FXML
    private Button getReportButton;

    @FXML
    private Button signOutButton;

    @FXML
    private BorderPane reportsBorderPane;

    @FXML
    private DatePicker fromDateDP;

    @FXML
    private Button reportsButton;

    @FXML
    private Text textInTopOfLogIn;

    @FXML
    private ComboBox<String> ReportComboBox;

    @FXML
    private Button changePricesRequestsButton;
    private ObservableList<String> myComboBoxData = FXCollections.observableArrayList();
    private ObservableList<String> myComboxReport = FXCollections.observableArrayList();
 

    @FXML
    void signOut(ActionEvent event) {

    }

    @FXML
    void getCurrentSituation(ActionEvent event) {

    }

    @FXML
    void getReport(ActionEvent event) {

    }

    @FXML
    void loadReporsBorderPane(ActionEvent event) {
    	reportsBorderPane.setVisible(true);
    	changePricesRequestsBorderPane.setVisible(false); 
 

    	reportsButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	reportsButton.getStyleClass().add("pressedButton");
    	changePricesRequestsButton.getStyleClass().removeAll("pressedButton", "focus");
    	changePricesRequestsButton.getStyleClass().add("loginView-buttons");
    	
    	ArrayList<ParkingLot> parkingLotNames = SharedData.getInstance().getParkingLotsAL();
        
    	myComboBoxData.clear();
    	for(int i = 0; i < parkingLotNames.size(); i++){
    		myComboBoxData.add(parkingLotNames.get(i).get_name());
    	}
    	
    	parkLotNameComboBox.setItems(myComboBoxData);
    	ArrayList<String> Reports = new ArrayList<String>();
    	Reports.add("Reservations");
    	Reports.add("Complaints");
    	Reports.add("Disabled Parking Spots");
    	Reports.add("Regular Routinely Subscriptions");
    	Reports.add("Business Routinely Subscriptions");
    	Reports.add("Full Subscriptions");
    	myComboxReport.clear();     
    	for(int i = 0; i < Reports.size(); i++){
    		myComboxReport.add(Reports.get(i));
    	}
    	
    	ReportComboBox.setItems(myComboxReport);
    	
    }

    @FXML
    void loadChangePricesRequestsBorderPane(ActionEvent event) {
    	reportsBorderPane.setVisible(false);
    	changePricesRequestsBorderPane.setVisible(true); 
 

    	changePricesRequestsButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	changePricesRequestsButton.getStyleClass().add("pressedButton");
    	reportsButton.getStyleClass().removeAll("pressedButton", "focus");
    	reportsButton.getStyleClass().add("loginView-buttons");

    }

 

}
