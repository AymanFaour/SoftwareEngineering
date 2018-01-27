/**
 * Sample Skeleton for 'ParkingLotDirectorView.fxml' Controller Class
 */

package application;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
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
import javafx.scene.layout.VBox;
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
    
    @FXML // fx:id="administratorRequestsButton"
    private Button administratorRequestsButton; // Value injected by FXMLLoader

    @FXML // fx:id="viewAdministratorReqeustBorderPane"
    private BorderPane viewAdministratorReqeustBorderPane; // Value injected by FXMLLoader
    
    @FXML // fx:id="administratorRequestsListVB"
    private VBox administratorRequestsListVB; // Value injected by FXMLLoader

    
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

			informationAlert.setTitle("Report warning");
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
    	String cost = occasionalReservationPriceTF.getText();
    	
    	if (cost.equals("")) {

    		informationAlert.setTitle("change warning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please enter the desired new cost");
			informationAlert.showAndWait();
			return;

		} else {
			
			JSONObject json = new JSONObject();
			try {
				
				
				String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
				
				//TODO: synchronize with server
	
				json.put("username", SharedData.getInstance().getCurrentSystemUser().get_username());
				json.put("lotName", lotName);
				
				json.put("occasional", Double.parseDouble(cost));
				json.put("reserveAhead", SharedData.getInstance().getReservationCost());
				json.put("routineHours", (int)SharedData.getInstance().getRoutineCost() / (int)SharedData.getInstance().getReservationCost());
				json.put("fullHours", (int)SharedData.getInstance().getFullCost() / (int)SharedData.getInstance().getReservationCost());
				json.put("businessHours", (int)SharedData.getInstance().getBusinessCost() / (int)SharedData.getInstance().getReservationCost());

				json.put("cmd", "requestCostChange");
	
				JSONObject ret = request(json, "SystemUserServices");
	
				System.out.println(ret);
				if(ret.getBoolean("result")){
					System.out.println("Changing occasional price SUCCEEDED!");
					
					informationAlert.setTitle("Request Success");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Your request has been sent to the adminstrator");
					informationAlert.showAndWait();
				
				}else{
					System.out.println("ERROR @ occasional change price");
					
					informationAlert.setTitle("Request Failed");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Something went wrong in sending the request.");
					informationAlert.showAndWait();
					
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}catch(NumberFormatException e){
				informationAlert.setTitle("change warning");
				informationAlert.setHeaderText(null);
				informationAlert.setContentText("new cast ONLY number value.");
				informationAlert.showAndWait();
			}
		}
    }

    
    @FXML
    void regularChange(ActionEvent event) {

    	String cost = regularReservationPriceTF.getText();
    	
    	if (cost.equals("")) {

			informationAlert.setTitle("change warning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please enter the desired new cost");
			informationAlert.showAndWait();
			return;
			
		} else {
			String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
			JSONObject json = new JSONObject();
			try {
				
				
				
				//TODO: synchronize with server
	
				json.put("username", SharedData.getInstance().getCurrentSystemUser().get_username());
				json.put("lotName", lotName);
				
				json.put("occasional", SharedData.getInstance().getOccasionalCost());
				json.put("reserveAhead", Double.parseDouble(cost));
				json.put("routineHours", (int)SharedData.getInstance().getRoutineCost() / (int)SharedData.getInstance().getReservationCost());
				json.put("fullHours", (int)SharedData.getInstance().getFullCost() / (int)SharedData.getInstance().getReservationCost());
				json.put("businessHours", (int)SharedData.getInstance().getBusinessCost() / (int)SharedData.getInstance().getReservationCost());

				json.put("cmd", "requestCostChange");
	
				JSONObject ret = request(json, "SystemUserServices");
	
				System.out.println(ret);
				if(ret.getBoolean("result")){
					System.out.println("Changing reserve price SUCCEEDED!");
					
					informationAlert.setTitle("Request Success");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Your request has been sent to the adminstrator");
					informationAlert.showAndWait();
				
				}else{
					System.out.println("ERROR @ reserve change price");
					
					informationAlert.setTitle("Request Failed");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Something went wrong in sending the request.");
					informationAlert.showAndWait();
					
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}catch(NumberFormatException e){
				informationAlert.setTitle("change warning");
				informationAlert.setHeaderText(null);
				informationAlert.setContentText("new cast ONLY number value.");
				informationAlert.showAndWait();
			}
		}

    }

    
    @FXML
    void routinelySubscriptionChange(ActionEvent event) {
    	String hours = routinelySubscriptionHoursTF.getText();
    	
    	if (hours.equals("")) {

			informationAlert.setTitle("change warning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please enter the desired new number of hours");
			informationAlert.showAndWait();
			return;
			
		} else {
			String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
			JSONObject json = new JSONObject();
			try {
				
				int intHours=Integer.parseInt(hours);


			
				//TODO: synchronize with server
	
				json.put("username", SharedData.getInstance().getCurrentSystemUser().get_username());
				json.put("lotName", lotName);
				
				json.put("occasional", SharedData.getInstance().getOccasionalCost());
				json.put("reserveAhead", SharedData.getInstance().getReservationCost());
				json.put("routineHours", intHours);
				json.put("fullHours", (int)SharedData.getInstance().getFullCost() / (int)SharedData.getInstance().getReservationCost());
				json.put("businessHours", (int)SharedData.getInstance().getBusinessCost() / (int)SharedData.getInstance().getReservationCost());

				json.put("cmd", "requestCostChange");
	
				JSONObject ret = request(json, "SystemUserServices");
	
				System.out.println(ret);
				if(ret.getBoolean("result")){
					System.out.println("Changing routine hours SUCCEEDED!");
					
					informationAlert.setTitle("Request Success");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Your request has been sent to the adminstrator");
					informationAlert.showAndWait();
				
				}else{
					System.out.println("ERROR @ routine change hours");
					
					informationAlert.setTitle("Request Failed");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Something went wrong in sending the request.");
					informationAlert.showAndWait();
					
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}catch(NumberFormatException e){
				informationAlert.setTitle("change warning");
				informationAlert.setHeaderText(null);
				informationAlert.setContentText("new cast ONLY number value.");
				informationAlert.showAndWait();
			}
		}
    }

    
    @FXML
    void businessSubscriptionHoursChange(ActionEvent event) {

    	String hours = businessSubscriptionHoursTF.getText();
    	
    	if (hours.equals("")) {

			informationAlert.setTitle("change warning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please enter the desired new number of hours");
			informationAlert.showAndWait();
			return;
			
		} else {
			
			String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
			JSONObject json = new JSONObject();
			try {
				int intHours=Integer.parseInt(hours);
				
				json.put("username", SharedData.getInstance().getCurrentSystemUser().get_username());
				json.put("lotName", lotName);
				
				json.put("occasional", SharedData.getInstance().getOccasionalCost());
				json.put("reserveAhead", SharedData.getInstance().getReservationCost());
				json.put("routineHours", (int)SharedData.getInstance().getRoutineCost() / (int)SharedData.getInstance().getReservationCost());
				json.put("fullHours",  (int)SharedData.getInstance().getFullCost() / (int)SharedData.getInstance().getReservationCost());
				json.put("businessHours", intHours);

				json.put("cmd", "requestCostChange");
	
				JSONObject ret = request(json, "SystemUserServices");
	
				System.out.println(ret);
				if(ret.getBoolean("result")){
					System.out.println("Changing business hours SUCCEEDED!");
					
					informationAlert.setTitle("Request Success");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Your request has been sent to the adminstrator");
					informationAlert.showAndWait();
				
				}else{
					System.out.println("ERROR @ business change hours");
					
					informationAlert.setTitle("Request Failed");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Something went wrong in sending the request.");
					informationAlert.showAndWait();
					
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}catch(NumberFormatException e){
				informationAlert.setTitle("change warning");
				informationAlert.setHeaderText(null);
				informationAlert.setContentText("new cast ONLY number value.");
				informationAlert.showAndWait();
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
			
			String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
			JSONObject json = new JSONObject();
			try {
				int intHours=Integer.parseInt(hours);
				//TODO: synchronize with server
				
				json.put("username", SharedData.getInstance().getCurrentSystemUser().get_username());
				json.put("lotName", lotName);
				
				json.put("occasional", SharedData.getInstance().getOccasionalCost());
				json.put("reserveAhead", SharedData.getInstance().getReservationCost());
				json.put("routineHours", (int)SharedData.getInstance().getRoutineCost() / (int)SharedData.getInstance().getReservationCost());
				json.put("fullHours", intHours);
				json.put("businessHours", (int)SharedData.getInstance().getBusinessCost() / (int)SharedData.getInstance().getReservationCost());

				json.put("cmd", "requestCostChange");
	
				JSONObject ret = request(json, "SystemUserServices");
	
				System.out.println(ret);
				if(ret.getBoolean("result")){
					System.out.println("Changing full hours SUCCEEDED!");
					
					informationAlert.setTitle("Request Success");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Your request has been sent to the adminstrator");
					informationAlert.showAndWait();
				
				}else{
					System.out.println("ERROR @ full change hours");
					
					informationAlert.setTitle("Request Failed");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Something went wrong in sending the request.");
					informationAlert.showAndWait();
					
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}catch(NumberFormatException e){
				informationAlert.setTitle("change warning");
				informationAlert.setHeaderText(null);
				informationAlert.setContentText("new cast ONLY number value.");
				informationAlert.showAndWait();
			}
		}

    }

    @FXML
    void loadReports(ActionEvent event) {
    	ReportsParkingLotDirectorBorderPane.setVisible(true);
    	ChangePricesParkingLotDirectorBorderPane.setVisible(false);
    	viewAdministratorReqeustBorderPane.setVisible(false);
    	
    	parkingLotDirectorReportsButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	parkingLotDirectorReportsButton.getStyleClass().add("pressedButton");
    	parkingLotDirectorCahngePriceButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingLotDirectorCahngePriceButton.getStyleClass().add("loginView-buttons");
    	administratorRequestsButton.getStyleClass().removeAll("pressedButton", "focus");
    	administratorRequestsButton.getStyleClass().add("loginView-buttons");
    	
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
    	viewAdministratorReqeustBorderPane.setVisible(false);
    	
    	parkingLotDirectorCahngePriceButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	parkingLotDirectorCahngePriceButton.getStyleClass().add("pressedButton");
    	parkingLotDirectorReportsButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingLotDirectorReportsButton.getStyleClass().add("loginView-buttons");
    	administratorRequestsButton.getStyleClass().removeAll("pressedButton", "focus");
    	administratorRequestsButton.getStyleClass().add("loginView-buttons");
    	
    	
    	occasionalReservationPriceTF.setText("old: " + Double.toString(SharedData.getInstance().getOccasionalCost()));
    	regularReservationPriceTF.setText("old: " + Double.toString(SharedData.getInstance().getReservationCost()));
    	routinelySubscriptionHoursTF.setText("old: " + Double.toString(SharedData.getInstance().getRoutineCost() / (int)SharedData.getInstance().getReservationCost() ));
    	businessSubscriptionHoursTF.setText("old: " + Double.toString(SharedData.getInstance().getBusinessCost() / (int)SharedData.getInstance().getReservationCost() ));
    	fullSubscriptionHoursTF.setText("old: " + Double.toString(SharedData.getInstance().getFullCost()/ (int)SharedData.getInstance().getReservationCost() ));
    	

    }
    

    @FXML
    void loadAdministratorRequests(ActionEvent event) {
    	ReportsParkingLotDirectorBorderPane.setVisible(false);
    	ChangePricesParkingLotDirectorBorderPane.setVisible(false);
    	viewAdministratorReqeustBorderPane.setVisible(true);
    	
    	parkingLotDirectorCahngePriceButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingLotDirectorCahngePriceButton.getStyleClass().add("loginView-buttons");
    	parkingLotDirectorReportsButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingLotDirectorReportsButton.getStyleClass().add("loginView-buttons");
    	administratorRequestsButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	administratorRequestsButton.getStyleClass().add("pressedButton");

    	/*JSONObject ret = getAdminRequest();

		try {

			JSONArray ja = ret.getJSONArray("resArr");

			System.out.println(ja);

			int length = administratorRequestsListVB.getChildren().size();
			administratorRequestsListVB.getChildren().remove(0, length);
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}*/

    }

	
    
    /*private JSONObject getAdminRequest() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		JSONObject ret = new JSONObject();
		try {

			json.put("username", SharedData.getInstance().getCurrentUser().getUsername());
			ret = request(json, "UserServices");
			System.out.println(ret);
			if (ret.getBoolean("result")) {
				JSONArray reservs = ret.getJSONArray("resArr");
				System.out.println(reservs.toString());
				return ret;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}
	*/
    
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

			sentData.writeBytes(json.toString());

			sentData.close();
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
	

	public void setWelcome(String welcome) {
		// TODO Auto-generated method stub
		welcomeBanner.setText(welcome);
	}

	public void setTopOfParkingWorker(String _fullname) {
		// TODO Auto-generated method stub
		textInTopOfLogIn.setText(_fullname);
	}


}
