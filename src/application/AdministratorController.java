package application;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CpsMailBox;
import model.ParkingLot;
import model.ParkingSituation;
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
    
    @FXML // fx:id="changePricesRequestVbox"
    private VBox changePricesRequestVbox; // Value injected by FXMLLoader

    
    private ObservableList<String> myComboBoxData = FXCollections.observableArrayList();
    private ObservableList<String> myComboxReport = FXCollections.observableArrayList();
    private Stage popupwindow;

    
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
    
    void aproveUpdateCost(int reqID){
    	
    	JSONObject json = new JSONObject();
    	try {
			json.put("requestID", reqID);
			json.put("performChange", true);
			json.put("cmd","handleChangeRequest");
			
			JSONObject ret = request(json, "SystemUserServices");
			System.out.println(ret);
			if(ret.getBoolean("result")){
				
				System.out.println("Aproving the new Costs saved successfully.\nThe new Costs is");
				
				JSONObject upd = request(null, "SystemQueries");
				System.out.println(upd);
				System.out.println(upd.getJSONArray("Costs"));
				
			}else{
				
				System.out.println("ERROR @ Aproving the new Costs.");
				
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }
    
    void declineUpdtae(int reqID){
    
    	JSONObject json = new JSONObject();
    	try {
			json.put("requestID", reqID);
			json.put("performChange", false);
			json.put("cmd","handleChangeRequest");
			
			JSONObject ret = request(json, "SystemUserServices");
			System.out.println(ret);
			if(ret.getBoolean("result")){
				
				System.out.println("Decling the new Costs saved successfully.\nThe new Costs is");
				
				JSONObject upd = request(null, "SystemQueries");
				System.out.println(upd);
				System.out.println(upd.getJSONArray("Costs"));
				
			}else{
				
				System.out.println("ERROR @ Declining the new Costs.");
				
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void getCurrentSituation(ActionEvent event) {
    	
    	popupwindow=new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Current Situation");
		
		VBox vB=new VBox();
		vB.setPadding(new Insets(10, 10, 10, 10));
		
		vB.getChildren().clear();
		ParkingLot pl = SharedData.getInstance().getCurrentParkingLot();
		ParkingSituation ps = new ParkingSituation(pl.getHeight(), pl.getWidth());
		ps.getGridLayer(pl);
		
		
		Scene scene1= new Scene(vB, 520, 520);
		      
		popupwindow.setScene(scene1);
		
		String email = "cps.client4@gmail.com";
		CpsMailBox mail = new CpsMailBox(SharedData.getInstance().getCPSEmail(),
				 SharedData.getInstance().getCPSPassword(), email);
		mail.sendMailToAdministrator();
		

		popupwindow.showAndWait();
		
//
//    	currentSituationCallBack(event);
    	
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
    	
    	//int length = changePricesRequestVbox.getChildren().size();
    	//changePricesRequestVbox.getChildren().remove(0, length);
		
    	 Label  occasionalReservationPrice= new Label("10");
    	 occasionalReservationPrice.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
 		Label RegularReservationPrice = new Label("10");
 		RegularReservationPrice.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
 		Label  routinelySubscriptionHours = new Label("10");
 		routinelySubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
 		Label   businessSubscriptionHours = new Label("10");
 		businessSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
 		Label   fullSubscriptionHours = new Label("10");
 		fullSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
 		Label lotName = new Label("from Carmel");
 		lotName.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
 		
 		Label  NewOccasionalReservationPrice= new Label("15");
 		NewOccasionalReservationPrice.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		Label newRegularReservationPrice = new Label("15");
		newRegularReservationPrice.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		Label  newRoutinelySubscriptionHours = new Label("15");
		newRoutinelySubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		Label   newbusinessSubscriptionHours = new Label("15");
		newbusinessSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		Label   newFullSubscriptionHours = new Label("15");
		newFullSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		
    	HBox oldHbox = new HBox();
    	oldHbox.getChildren().add(occasionalReservationPrice);
    	oldHbox.getChildren().add(RegularReservationPrice);
    	oldHbox.getChildren().add(routinelySubscriptionHours);
    	oldHbox.getChildren().add(businessSubscriptionHours);
    	oldHbox.getChildren().add(fullSubscriptionHours);
    	oldHbox.getChildren().add(lotName);
    	oldHbox.setStyle("-fx-background-color: red");
    	oldHbox.getStyleClass().add("hbox");
    
    	Button approve=new Button("Approve");
		Button refuse=new Button("Refuse");
				
		HBox newHbox = new HBox();
		newHbox.getChildren().add(NewOccasionalReservationPrice);
		newHbox.getChildren().add(newRegularReservationPrice);
		newHbox.getChildren().add(newRoutinelySubscriptionHours);
		newHbox.getChildren().add(newFullSubscriptionHours);
		newHbox.getChildren().add(newbusinessSubscriptionHours);
		newHbox.getChildren().add(approve);
		newHbox.getChildren().add(refuse);
		oldHbox.setStyle("-fx-background-color: green");
		
		
		
		approve.setId("approveButton" /*+ resId.getText()*/);
		String css = getClass().getResource("application.css").toExternalForm();
		approve.getStylesheets().clear();
		approve.getStylesheets().add(css);
		//approve.setOnAction(e -> approve(e,requestId.getText());
		approve.getStyleClass().add("approve-button");
		approve.setStyle("-fx-color: #d0e6f8;");
	
		refuse.setId("refuseButton" /*+ resId.getText()*/);
		refuse.getStylesheets().clear();
		refuse.getStylesheets().add(css);
		//approve.setOnAction(e -> refuse(e, requestId.getText());
		refuse.getStyleClass().add("approve-button");
		refuse.setStyle("-fx-color: #8d2626;");
	
		newHbox.setStyle("-fx-background-color:#98FB98;-fx-border-style: solid inside;-fx-pref-height: 30;-fx-border-width: 0 0 2 0;"
				+ "-fx-border-color: #d0e6f8; -fx-padding: 1.5 0 0 5;");
		oldHbox.setStyle("-fx-background-color: #FF4500;-fx-border-style: solid inside;-fx-pref-height: 30;-fx-border-width: 0 0 2 0;"
				+ "-fx-border-color: #d0e6f8; -fx-padding: 1.5 0 0 5;");
		changePricesRequestVbox.getChildren().add(oldHbox);
		changePricesRequestVbox.getChildren().add(newHbox);
    	
    	//TODO: to get all the price change costs
    	
    	JSONObject json = new JSONObject();
    	
    	try {
			
    		json.put("cmd", "getCostChangeRequests");
			JSONObject ret = request(json, "SystemUserServices");
			System.out.println(ret);
			if(ret.getBoolean("result")){
				System.out.println("SUCCESS @ get cost change requests");
				if(ret.getJSONArray("changeRequests").length() > 0){
					System.out.println("handle this requests");
				}else{
					System.out.println("no requests to handle");
				}
			}else{
				System.out.println("ERROR while getting the cost change requests");
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	

    }
    

	public void setWelcome(String welcome) {
		welcomeBanner.setText(welcome);
	}

	public void setTopOfParkingWorker(String _fullname) {
		textInTopOfLogIn.setText(_fullname);
	}
	
	JSONObject request(JSONObject json, String servletName) {
		HttpURLConnection connection = null;
		try {
			// Create connection
			URL url = new URL("http://" + SharedData.getInstance().getIP() + ":" + SharedData.getInstance().getPORT()
					+ "/server/" + servletName);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);

			// Send request
			DataOutputStream sentData = new DataOutputStream(connection.getOutputStream());

		
			if (json != null) {
				sentData.writeBytes(json.toString());

				sentData.close();
			}
			JSONObject ret;

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if
															// Java version 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				// response.append('\r');
			}

			rd.close();
			// System.out.println(response.toString() +
			// "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			ret = new JSONObject(response.toString());

			return ret;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (connection != null) {
				connection.disconnect();
			}

		}

		return null;

	}

}
