/**
 * Sample Skeleton for 'ParkingLotDirectorView.fxml' Controller Class
 */

package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ParkingLotDirectorController {

    @FXML // fx:id="fullSubscriptionHoursChangeButton"
    private Button fullSubscriptionHoursChangeButton; // Value injected by FXMLLoader

    @FXML // fx:id="welcomeBanner"
    private Label welcomeBanner; // Value injected by FXMLLoader

    @FXML // fx:id="routinelySubscriptionHoursTF"
    private TextField routinelySubscriptionHoursTF; // Value injected by FXMLLoader

    @FXML // fx:id="regularChangeButton"
    private Button regularChangeButton; // Value injected by FXMLLoader

    @FXML // fx:id="businessSubscriptionHoursChangeButton"
    private Button businessSubscriptionHoursChangeButton; // Value injected by FXMLLoader

    @FXML // fx:id="ChangePricesParkingLotDirectorBorderPane"
    private BorderPane ChangePricesParkingLotDirectorBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="fullSubscriptionHoursTF"
    private TextField fullSubscriptionHoursTF; // Value injected by FXMLLoader

    @FXML // fx:id="parkingLotDirectorReportsButton"
    private Button parkingLotDirectorReportsButton; // Value injected by FXMLLoader

    @FXML // fx:id="signOutButton"
    private Button signOutButton; // Value injected by FXMLLoader

    @FXML // fx:id="balanceOnTopOfLogIn"
    private Text balanceOnTopOfLogIn; // Value injected by FXMLLoader

    @FXML // fx:id="regularReservationPriceTF"
    private TextField regularReservationPriceTF; // Value injected by FXMLLoader

    @FXML // fx:id="businessSubscriptionHoursTF"
    private TextField businessSubscriptionHoursTF; // Value injected by FXMLLoader

    @FXML // fx:id="occasionalReservationPriceTF"
    private TextField occasionalReservationPriceTF; // Value injected by FXMLLoader

    @FXML // fx:id="parkingLotDirectorCahngePriceButton"
    private Button parkingLotDirectorCahngePriceButton; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResReserveParkingButton"
    private Button CusSerparkResReserveParkingButton; // Value injected by FXMLLoader

    @FXML // fx:id="routinelySubscriptionChangeButton"
    private Button routinelySubscriptionChangeButton; // Value injected by FXMLLoader

    @FXML // fx:id="ReportsParkingLotDirectorBorderPane"
    private BorderPane ReportsParkingLotDirectorBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="textInTopOfLogIn"
    private Text textInTopOfLogIn; // Value injected by FXMLLoader

    @FXML // fx:id="ParLotDirecReportComboBox"
    private ComboBox<String> ParLotDirecReportComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="occasionalChangeButton"
    private Button occasionalChangeButton; // Value injected by FXMLLoader
    
    private ObservableList<String> myComboBoxParLotDirecReport= FXCollections.observableArrayList();
    

    @FXML
    void signOut(ActionEvent event) {

    }

    @FXML
    void sendReportToAdministrator(ActionEvent event) {

    }

    @FXML
    void occasionalChange(ActionEvent event) {

    }

    @FXML
    void regularChange(ActionEvent event) {

    }

    @FXML
    void routinelySubscriptionChange(ActionEvent event) {

    }

    @FXML
    void businessSubscriptionHoursChange(ActionEvent event) {

    }

    @FXML
    void fullSubscriptionHoursChange(ActionEvent event) {

    }

    @FXML
    void loadReports(ActionEvent event) {
    	ReportsParkingLotDirectorBorderPane.setVisible(true);
    	ChangePricesParkingLotDirectorBorderPane.setVisible(false);
    	
    	parkingLotDirectorReportsButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	parkingLotDirectorReportsButton.getStyleClass().add("pressedButton");
    	parkingLotDirectorCahngePriceButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingLotDirectorCahngePriceButton.getStyleClass().add("loginView-buttons");
    	ArrayList<String> Reports = new ArrayList<String>();
    	Reports.add("Reservations");
    	Reports.add("Complaints");
    	Reports.add("Disabled Parking Spots");
    	Reports.add("Regular Routinely Subscriptions");
    	Reports.add("Business Routinely Subscriptions");
    	Reports.add("Full Subscriptions");
    	
    	myComboBoxParLotDirecReport.clear();     
    	for(int i = 0; i < Reports.size(); i++){
    		myComboBoxParLotDirecReport.add(Reports.get(i));
    	}
    	
    	ParLotDirecReportComboBox.setItems(myComboBoxParLotDirecReport);
    	
    	
    }

    @FXML
    void loadChangePrices(ActionEvent event) {
    	ReportsParkingLotDirectorBorderPane.setVisible(false);
    	ChangePricesParkingLotDirectorBorderPane.setVisible(true);
    	
    	parkingLotDirectorCahngePriceButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	parkingLotDirectorCahngePriceButton.getStyleClass().add("pressedButton");
    	parkingLotDirectorReportsButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingLotDirectorReportsButton.getStyleClass().add("loginView-buttons");
    	
    	
    	

    }

	public void setWelcome(String welcome) {
		// TODO Auto-generated method stub
		welcomeBanner.setText(welcome);
	}

	public void setTopOfParkingWorker(String _fullname) {
		// TODO Auto-generated method stub
		textInTopOfLogIn.setText(_fullname);
	}


}
