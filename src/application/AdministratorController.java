package application;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

import org.json.JSONArray;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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


	Alert informationAlert = new Alert(AlertType.INFORMATION);
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
    
	/**
	 * sign out from system
	 * @param event
	 */
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
    /**
     * aprove to update costs that Suggested by Parking Lot Director 
     * @param e
     * @param reqID
     */
    
    void aproveUpdateCost(ActionEvent e, int reqID){
    	
    	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
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
				loadChangePricesRequestsBorderPane(null);
				
			}else{
				
				System.out.println("ERROR @ Aproving the new Costs.");
				
			}
			
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
    	
    }
   
    /**
     * decline to update costs that Suggested by Parking Lot Director 
     * @param e
     * @param reqID
     */
    void declineUpdtae(ActionEvent e, int reqID){
    
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
				
				loadChangePricesRequestsBorderPane(null);
				
			}else{
				
				System.out.println("ERROR @ Declining the new Costs.");
				
			}
			
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
    	
    }

    /**
     * this method sends request to parking lot director and asks him  to send the lot's Current Situation.
     * The administrator will get a main that contains  pdf of parking lot current Situation.
     * @param event
     */
    @FXML
    void getCurrentSituation(ActionEvent event) {
    	
    	String _lotName = parkLotNameComboBox.getValue();
    	if(_lotName == null){
    		informationAlert.setTitle("Warning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("You must choose a parking lot");
			informationAlert.showAndWait();
			return;
    	}
    	
    	else{
    		JSONObject json = new JSONObject();
    		JSONObject ret = new JSONObject();

    		try {
    				json.put("lotName", _lotName);
    				json.put("requestType", "lotCurrentSituation");
    				json.put("cmd", "managementRequest");
    				ret = request(json, "SystemUserServices");
    				System.out.println(ret);
    				if (ret.getBoolean("result")) {
    					informationAlert.setTitle("Your request was sent successfully");
    					informationAlert.setHeaderText(null);
    					informationAlert.setContentText(
    							"Your request was sent successfully.\nPlease wait for the parking lot "
    							+ "manager to send response in a mail");
    					informationAlert.showAndWait();
    				}else{
    					if(ret.getString("info").equals("Request already exist")){
        					informationAlert.setTitle("Pending request");
        					informationAlert.setHeaderText(null);
        					informationAlert.setContentText(
        							"Previous request was send earlier.\nPlease wait for the parking lot "
        							+ "manager to send response in a mail");
        					informationAlert.showAndWait();
    					}
    				}
    			
    		} catch(JSONException e1) {
    			e1.printStackTrace();
    		}

    	}
		
    }
 
    
  
  
    
    @FXML
    void getReport(ActionEvent event) {

    }

   
   /**
    * view Reports page  
    * @param event
    */
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

    /**
     * view Change PricesREquests page  
     * @param event
     */  
    @FXML
    void loadChangePricesRequestsBorderPane(ActionEvent event) {
    	reportsBorderPane.setVisible(false);
    	changePricesRequestsBorderPane.setVisible(true); 
 

    	changePricesRequestsButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	changePricesRequestsButton.getStyleClass().add("pressedButton");
    	reportsButton.getStyleClass().removeAll("pressedButton", "focus");
    	reportsButton.getStyleClass().add("loginView-buttons");
    	
    	int length = changePricesRequestVbox.getChildren().size();
    	changePricesRequestVbox.getChildren().remove(0, length);
		
    	
    	
    	//TODO: to get all the price change costs
    	
    	JSONObject json = new JSONObject();
    	
    	try {
			
    		json.put("cmd", "getCostChangeRequests");
			JSONObject ret = request(json, "SystemUserServices");
			System.out.println(ret);
			if(ret.getBoolean("result")){
				System.out.println("SUCCESS @ get cost change requests");
				

					
				JSONObject updateCost = request(null, "SystemQueries");
			
				if (updateCost.getBoolean("result")) {
					System.out.println(updateCost);

					JSONArray costs = updateCost.getJSONArray("Costs");
					
					SharedData.getInstance().setOccasionalCost(((JSONObject) costs.get(0)).getDouble("cost"));
					SharedData.getInstance().setReservationCost(((JSONObject) costs.get(1)).getDouble("cost"));
					SharedData.getInstance().setRoutineCost(((JSONObject) costs.get(2)).getDouble("cost"));
					SharedData.getInstance().setBusinessCost(((JSONObject) costs.get(3)).getDouble("cost"));
					SharedData.getInstance().setFullCost(((JSONObject) costs.get(4)).getDouble("cost"));
				}
				
				
				
				Label  occasionalReservationPrice= new Label(SharedData.getInstance().getOccasionalCost() + "");
		    	occasionalReservationPrice.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		 		Label RegularReservationPrice = new Label(SharedData.getInstance().getReservationCost() + "");
		 		RegularReservationPrice.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		 		Label  routinelySubscriptionHours = new Label(SharedData.getInstance().getRoutineCost() / SharedData.getInstance().getReservationCost() + "");
		 		routinelySubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		 		Label   businessSubscriptionHours = new Label(SharedData.getInstance().getBusinessCost() / SharedData.getInstance().getReservationCost() + "");
		 		businessSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		 		Label   fullSubscriptionHours = new Label(SharedData.getInstance().getFullCost() / SharedData.getInstance().getReservationCost() + "");
		 		fullSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		 		Label lotName = new Label("Current Price");
		 		lotName.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		 		
		 		HBox oldHbox = new HBox();
		    	oldHbox.getChildren().add(occasionalReservationPrice);
		    	oldHbox.getChildren().add(RegularReservationPrice);
		    	oldHbox.getChildren().add(routinelySubscriptionHours);
		    	oldHbox.getChildren().add(businessSubscriptionHours);
		    	oldHbox.getChildren().add(fullSubscriptionHours);
		    	oldHbox.getChildren().add(lotName);
		    	oldHbox.setStyle("-fx-background-color: red");
		    	oldHbox.getStyleClass().add("hbox");
		    	
				changePricesRequestVbox.getChildren().add(oldHbox);

				if(ret.getJSONArray("changeRequests").length() > 0){
					JSONArray loop = ret.getJSONArray("changeRequests");
					
					for(int i = 0; i < ret.getJSONArray("changeRequests").length(); i++){
						
						
				 		
				 		Label  NewOccasionalReservationPrice= new Label(((JSONObject)loop.get(i)).getInt("occasional") + "");
				 		NewOccasionalReservationPrice.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
						Label newRegularReservationPrice = new Label(((JSONObject)loop.get(i)).getInt("reserveAhead") + "");
						newRegularReservationPrice.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
						Label  newRoutinelySubscriptionHours = new Label(((JSONObject)loop.get(i)).getInt("routineHours") + "");
						newRoutinelySubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
						Label   newbusinessSubscriptionHours = new Label(((JSONObject)loop.get(i)).getInt("businessHours") + "");
						newbusinessSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
						Label   newFullSubscriptionHours = new Label(((JSONObject)loop.get(i)).getInt("fullHours") + "");
						newFullSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
						Label   newLotName = new Label(((JSONObject)loop.get(i)).getString("lotName"));
						newFullSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 0 3.5 0 0");
						
						Label   space = new Label("       ");
						newFullSubscriptionHours.setStyle("-fx-pref-width:90 ;");
						
				    
				    	Button approve=new Button("Approve");
						Button refuse=new Button("Decline");
								
						HBox newHbox = new HBox();
						newHbox.getChildren().add(NewOccasionalReservationPrice);
						newHbox.getChildren().add(newRegularReservationPrice);
						newHbox.getChildren().add(newRoutinelySubscriptionHours);
						newHbox.getChildren().add(newbusinessSubscriptionHours);
						newHbox.getChildren().add(newFullSubscriptionHours);
						newHbox.getChildren().add(newLotName);
						newHbox.getChildren().add(space);
						newHbox.getChildren().add(approve);
						newHbox.getChildren().add(refuse);
						oldHbox.setStyle("-fx-background-color: green");
						
						
						Label   id = new Label(((JSONObject)loop.get(i)).getInt("requestID") + "");
						
						
						approve.setId("approveButton" /*+ resId.getText()*/);
						String css = getClass().getResource("application.css").toExternalForm();
						approve.getStylesheets().clear();
						approve.getStylesheets().add(css);
						approve.setOnAction(e -> aproveUpdateCost(e, Integer.parseInt(id.getText())));
						approve.getStyleClass().add("approve-button");
						approve.setStyle("-fx-color: #d0e6f8;");
					
						refuse.setId("refuseButton" /*+ resId.getText()*/);
						refuse.getStylesheets().clear();
						refuse.getStylesheets().add(css);
						refuse.setOnAction(e -> declineUpdtae(e, Integer.parseInt(id.getText())));
						refuse.getStyleClass().add("approve-button");
						refuse.setStyle("-fx-color: #8d2626;");
					
						newHbox.setStyle("-fx-background-color:#98FB98;-fx-border-style: solid inside;-fx-pref-height: 30;-fx-border-width: 0 0 2 0;"
								+ "-fx-border-color: #d0e6f8; -fx-padding: 1.5 0 0 5;");
						oldHbox.setStyle("-fx-background-color: #FF4500;-fx-border-style: solid inside;-fx-pref-height: 30;-fx-border-width: 0 0 2 0;"
								+ "-fx-border-color: #d0e6f8; -fx-padding: 1.5 0 0 5;");
						changePricesRequestVbox.getChildren().add(newHbox);
						
					}
				
				
				}else{
					System.out.println("no requests to handle");
				}
			}else{
				System.out.println("ERROR while getting the cost change requests");
			}
			
			
		} catch (JSONException | NumberFormatException e) {
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
