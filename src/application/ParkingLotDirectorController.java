/**
 * Sample Skeleton for 'ParkingLotDirectorView.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SharedData;




public class ParkingLotDirectorController {
	
	Alert informationAlert = new Alert(AlertType.INFORMATION);
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	
	

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
		SharedData.getInstance().setCurrentSystemUser(null);

		Scene currentScene = signOutButton.getScene();
		Parent mainLayout = null;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("MainView.fxml"));
		try {
			mainLayout = loader.load();
		} catch (IOException | NullPointerException e) {

			e.printStackTrace();
		}

		Scene scene = new Scene(mainLayout);
		Stage stage = (Stage) currentScene.getWindow();
		stage.setScene(scene);

    }

    @FXML
    void sendReportToAdministrator(ActionEvent event) {
    	
    	String report = ParLotDirecReportComboBox.getValue();
    	
    	if (report == null) {

			informationAlert.setTitle("Report warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please choose report type to complete the report");
			informationAlert.showAndWait();
			return;

		} else {
			String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
			
			JSONObject json = new JSONObject();
			try {
				//TODO: synchronize with server
	
				json.put("report", report);
				json.put("lotName", lotName);
				json.put("cmd", "report");
	
				// send to reservation servlet
	//			JSONObject ret = request(json, "CustomerServiceReservationController");
	//
	////			System.out.println(ret.getBoolean("result"));
	//			if (ret.getBoolean("result")) {
	//				System.out.println("Old balance is: " + SharedData.getInstance().getCurrentUser().getBalance());
	//				
	////				updateBalance((-1) * cost);
	////				System.out.println("New balance is: " + SharedData.getInstance().getCurrentUser().getBalance());
	//			}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
    }
    
  

    @FXML
    void occasionalChange(ActionEvent event) {
    	String cost =occasionalReservationPriceTF.getText();
    	
    	if (cost.equals("")) {

			informationAlert.setTitle("change warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please enter the desired new cost");
			informationAlert.showAndWait();
			return;

		} else {
			double doubleCost=Double.parseDouble(cost);
			String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
			JSONObject json = new JSONObject();
			try {
				
				//TODO: synchronize with server
	
				json.put("cost", doubleCost);
				json.put("lotName", lotName);
				json.put("cmd", "report");
	
				// send to reservation servlet
	//			JSONObject ret = request(json, "CustomerServiceReservationController");
	//
	////			System.out.println(ret.getBoolean("result"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
    }

    @FXML
    void regularChange(ActionEvent event) {

    	String cost = regularReservationPriceTF.getText();
    	
    	if (cost.equals("")) {

			informationAlert.setTitle("change warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please enter the desired new cost");
			informationAlert.showAndWait();
			return;
			
		} else {
			double doubleCost=Double.parseDouble(cost);
			String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
			JSONObject json = new JSONObject();
			try {
				
				//TODO: synchronize with server
	
				json.put("cost", doubleCost);
				json.put("lotName", lotName);
				json.put("cmd", "report");
	
				// send to reservation servlet
	//			JSONObject ret = request(json, "CustomerServiceReservationController");
	//
	////			System.out.println(ret.getBoolean("result"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

    }

    @FXML
    void routinelySubscriptionChange(ActionEvent event) {
    	String hours = routinelySubscriptionHoursTF.getText();
    	
    	if (hours.equals("")) {

			informationAlert.setTitle("change warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please enter the desired new number of hours");
			informationAlert.showAndWait();
			return;
			
		} else {
			int intHours=Integer.parseInt(hours);
			String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
			JSONObject json = new JSONObject();
			try {
				
				//TODO: synchronize with server
	
				json.put("cost", intHours);
				json.put("lotName", lotName);
				json.put("cmd", "report");
	
				// send to reservation servlet
	//			JSONObject ret = request(json, "CustomerServiceReservationController");
	//
	////			System.out.println(ret.getBoolean("result"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
    }

    @FXML
    void businessSubscriptionHoursChange(ActionEvent event) {

    	String hours = businessSubscriptionHoursTF.getText();
    	
    	if (hours.equals("")) {

			informationAlert.setTitle("change warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please enter the desired new number of hours");
			informationAlert.showAndWait();
			return;
			
		} else {
			int intHours=Integer.parseInt(hours);
			String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
			JSONObject json = new JSONObject();
			try {
				
				//TODO: synchronize with server
	
				json.put("cost", intHours);
				json.put("lotName", lotName);
				json.put("cmd", "report");
	
				// send to reservation servlet
	//			JSONObject ret = request(json, "CustomerServiceReservationController");
	//
	////			System.out.println(ret.getBoolean("result"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
    

    }

    @FXML
    void fullSubscriptionHoursChange(ActionEvent event) {


    	String hours = fullSubscriptionHoursTF.getText();
    	
    	if (hours.equals("")) {

			informationAlert.setTitle("change warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please enter the desired new number of hours");
			informationAlert.showAndWait();
			return;
			
		} else {
			int intHours=Integer.parseInt(hours);
			String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
			JSONObject json = new JSONObject();
			try {
				
				//TODO: synchronize with server
	
				json.put("cost", intHours);
				json.put("lotName", lotName);
				json.put("cmd", "report");
	
				// send to reservation servlet
	//			JSONObject ret = request(json, "CustomerServiceReservationController");
	//
	////			System.out.println(ret.getBoolean("result"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

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
