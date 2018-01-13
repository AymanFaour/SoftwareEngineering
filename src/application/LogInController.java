/**
 * Sample Skeleton for 'LogInView.fxml' Controller Class
 */

package application;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LogInController {
	
	Alert informationAlert = new Alert(AlertType.INFORMATION);
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);

	@FXML // fx:id="welcomeBanner"
    private Label welcomeBanner; // Value injected by FXMLLoader

    @FXML // fx:id="businessRoutineSubscriptionBorderPane"
    private BorderPane businessRoutineSubscriptionBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="regRouSubRoutinelyLeavingHourTF"
    private TextField regRouSubRoutinelyLeavingHourTF; // Value injected by FXMLLoader

    @FXML // fx:id="regRouSubRegularRoutineSubscriptionButton"
    private Button regRouSubRegularRoutineSubscriptionButton; // Value injected by FXMLLoader

    @FXML // fx:id="parkResCarNumberTF"
    private TextField parkResCarNumberTF; // Value injected by FXMLLoader

    @FXML // fx:id="fulSubFullSubscriptionButton"
    private Button fulSubFullSubscriptionButton; // Value injected by FXMLLoader

    @FXML // fx:id="parkingReservationButton"
    private Button parkingReservationButton; // Value injected by FXMLLoader

    @FXML // fx:id="balanceOnTopOfLogIn"
    private Text balanceOnTopOfLogIn; // Value injected by FXMLLoader

    @FXML // fx:id="complaintBorderPane"
    private BorderPane complaintBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="regularRoutineSubscriptionBorderPane"
    private BorderPane regularRoutineSubscriptionBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="parkResLeavingHourTF"
    private TextField parkResLeavingHourTF; // Value injected by FXMLLoader

    @FXML // fx:id="regRouSubCarNumberTF"
    private TextField regRouSubCarNumberTF; // Value injected by FXMLLoader

    @FXML // fx:id="textInTopOfLogIn"
    private Text textInTopOfLogIn; // Value injected by FXMLLoader

    @FXML // fx:id="regRouSubRoutinelyParkingLotTF"
    private TextField regRouSubRoutinelyParkingLotTF; // Value injected by FXMLLoader

    @FXML // fx:id="parkResArrivingDateTF"
    private TextField parkResArrivingDateTF; // Value injected by FXMLLoader

    @FXML // fx:id="businessRoutinelySubscriptionButton"
    private Button businessRoutinelySubscriptionButton; // Value injected by FXMLLoader

    @FXML // fx:id="fullSubscriptionBorderPane"
    private BorderPane fullSubscriptionBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="parkResParkingLotNameTF"
    private TextField parkResParkingLotNameTF; // Value injected by FXMLLoader

    @FXML // fx:id="regularRoutinelySubscriptionButton"
    private Button regularRoutinelySubscriptionButton; // Value injected by FXMLLoader

    @FXML // fx:id="fulSubCarNumberTF"
    private TextField fulSubCarNumberTF; // Value injected by FXMLLoader

    @FXML // fx:id="parkResArrivingHourTF"
    private TextField parkResArrivingHourTF; // Value injected by FXMLLoader

    @FXML // fx:id="viewProfileBorderPane"
    private BorderPane viewProfileBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="parkResReserveParkingButton"
    private Button parkResReserveParkingButton; // Value injected by FXMLLoader

    @FXML // fx:id="parkResLeavingDateTF"
    private TextField parkResLeavingDateTF; // Value injected by FXMLLoader

    @FXML // fx:id="fullSubscriptionButton"
    private Button fullSubscriptionButton; // Value injected by FXMLLoader

    @FXML // fx:id="viewReservationButton"
    private Button viewReservationButton; // Value injected by FXMLLoader

    @FXML // fx:id="parkingReservationBorderPane"
    private BorderPane parkingReservationBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="complaintButton"
    private Button complaintButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="signOutButton"
    private Button signOutButton; // Value injected by FXMLLoader


    
    public void setWelcome(String s){
    	welcomeBanner.setText(s);
    }
    
    public void setTopOfLogInView(String name, String balance){
    	textInTopOfLogIn.setText(name);
    	balanceOnTopOfLogIn.setText(balance);
    }
    
    @FXML
    void loadParkingReservation(ActionEvent event) {
    	businessRoutineSubscriptionBorderPane.setVisible(false);
    	regularRoutineSubscriptionBorderPane.setVisible(false);
    	parkingReservationBorderPane.setVisible(true);
    	fullSubscriptionBorderPane.setVisible(false);
    	complaintBorderPane.setVisible(false);
    	viewProfileBorderPane.setVisible(false);
    	
    	parkingReservationButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	parkingReservationButton.getStyleClass().add("pressedButton");
    	businessRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	businessRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	regularRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	regularRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	fullSubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	fullSubscriptionButton.getStyleClass().add("loginView-buttons");
    	viewReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	viewReservationButton.getStyleClass().add("loginView-buttons");
    	complaintButton.getStyleClass().removeAll("pressedButton", "focus");
    	complaintButton.getStyleClass().add("loginView-buttons");  	
    }

    @FXML
    void loadRegularRoutinelySubscription(ActionEvent event) {
    	businessRoutineSubscriptionBorderPane.setVisible(false);
    	regularRoutineSubscriptionBorderPane.setVisible(true);
    	parkingReservationBorderPane.setVisible(false);
    	fullSubscriptionBorderPane.setVisible(false);
    	complaintBorderPane.setVisible(false);
    	viewProfileBorderPane.setVisible(false);

    	regularRoutinelySubscriptionButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	regularRoutinelySubscriptionButton.getStyleClass().add("pressedButton");
    	businessRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	businessRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	fullSubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	fullSubscriptionButton.getStyleClass().add("loginView-buttons");
    	viewReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	viewReservationButton.getStyleClass().add("loginView-buttons");
    	complaintButton.getStyleClass().removeAll("pressedButton", "focus");
    	complaintButton.getStyleClass().add("loginView-buttons");
    	
    }

    @FXML
    void loadBusinessRoutinelySubscription(ActionEvent event) {
    	businessRoutineSubscriptionBorderPane.setVisible(true);
    	regularRoutineSubscriptionBorderPane.setVisible(false);
    	parkingReservationBorderPane.setVisible(false);
    	fullSubscriptionBorderPane.setVisible(false);
    	complaintBorderPane.setVisible(false);
    	viewProfileBorderPane.setVisible(false);

    	businessRoutinelySubscriptionButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	businessRoutinelySubscriptionButton.getStyleClass().add("pressedButton");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	regularRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	regularRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	fullSubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	fullSubscriptionButton.getStyleClass().add("loginView-buttons");
    	viewReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	viewReservationButton.getStyleClass().add("loginView-buttons");
    	complaintButton.getStyleClass().removeAll("pressedButton", "focus");
    	complaintButton.getStyleClass().add("loginView-buttons");
    	
    }

    @FXML
    void loadFullSubscription(ActionEvent event) {
    	businessRoutineSubscriptionBorderPane.setVisible(false);
    	regularRoutineSubscriptionBorderPane.setVisible(false);
    	parkingReservationBorderPane.setVisible(false);
    	fullSubscriptionBorderPane.setVisible(true);
    	complaintBorderPane.setVisible(false);
    	viewProfileBorderPane.setVisible(false);

    	fullSubscriptionButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	fullSubscriptionButton.getStyleClass().add("pressedButton");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	regularRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	regularRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	businessRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	businessRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	viewReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	viewReservationButton.getStyleClass().add("loginView-buttons");
    	complaintButton.getStyleClass().removeAll("pressedButton", "focus");
    	complaintButton.getStyleClass().add("loginView-buttons");
    	
    }

    @FXML
    void loadViewReservation(ActionEvent event) {
    	businessRoutineSubscriptionBorderPane.setVisible(false);
    	regularRoutineSubscriptionBorderPane.setVisible(false);
    	parkingReservationBorderPane.setVisible(false);
    	fullSubscriptionBorderPane.setVisible(false);
    	complaintBorderPane.setVisible(false);
    	viewProfileBorderPane.setVisible(true);

    	viewReservationButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	viewReservationButton.getStyleClass().add("pressedButton");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	regularRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	regularRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	fullSubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	fullSubscriptionButton.getStyleClass().add("loginView-buttons");
    	businessRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	businessRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	complaintButton.getStyleClass().removeAll("pressedButton", "focus");
    	complaintButton.getStyleClass().add("loginView-buttons");
    	
    }

    @FXML
    void loadComplaint(ActionEvent event) {
    	businessRoutineSubscriptionBorderPane.setVisible(false);
    	regularRoutineSubscriptionBorderPane.setVisible(false);
    	parkingReservationBorderPane.setVisible(false);
    	fullSubscriptionBorderPane.setVisible(false);
    	complaintBorderPane.setVisible(true);
    	viewProfileBorderPane.setVisible(false);

    	complaintButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	complaintButton.getStyleClass().add("pressedButton");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	regularRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	regularRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	fullSubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	fullSubscriptionButton.getStyleClass().add("loginView-buttons");
    	viewReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	viewReservationButton.getStyleClass().add("loginView-buttons");
    	businessRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	businessRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	
    }
    
    @FXML
    void reserveParking(ActionEvent event) {

    	String _carNumber = parkResCarNumberTF.getText();
    	
    	String _arriveHour = parkResArrivingHourTF.getText();
    	String _leaveHour = parkResLeavingHourTF.getText();
    	String _arriveDate = parkResArrivingDateTF.getText();
    	String _leaveDate = parkResLeavingDateTF.getText();
    	
    	String _lotName = parkResParkingLotNameTF.getText();
    	
    	
    	
    	// get a calendar using the default time zone and locale.
        Calendar _arriveCalendar = Calendar.getInstance();
        _arriveCalendar.set(Calendar.YEAR, 2018);
        _arriveCalendar.set(Calendar.MONTH, Calendar.JANUARY);
        _arriveCalendar.set(Calendar.DATE, 13);
        _arriveCalendar.set(Calendar.HOUR_OF_DAY, 19);
        _arriveCalendar.set(Calendar.MINUTE, 00);
        _arriveCalendar.set(Calendar.SECOND, 0);
        _arriveCalendar.set(Calendar.MILLISECOND, 0);
        
        Calendar _leaveCalendar = Calendar.getInstance();
        _leaveCalendar.set(Calendar.YEAR, 2018);
        _leaveCalendar.set(Calendar.MONTH, Calendar.JANUARY);
        _leaveCalendar.set(Calendar.DATE, 14);
        _leaveCalendar.set(Calendar.HOUR_OF_DAY, 20);
        _leaveCalendar.set(Calendar.MINUTE, 30);
        _leaveCalendar.set(Calendar.SECOND, 0);
        _leaveCalendar.set(Calendar.MILLISECOND, 0);
        
        
        if(_carNumber.equals("") || _lotName.equals("")){
        	
        	informationAlert.setTitle("Reservation warrning");
    		informationAlert.setHeaderText(null);
    		informationAlert.setContentText("Please fill all the car number, arriving date and parking lot fields to complete the reservation");
    		informationAlert.showAndWait();
        	
        }else{
        	
        	long deff = TimeUnit.MILLISECONDS.toMinutes(Math.abs(_leaveCalendar.getTimeInMillis() - _arriveCalendar.getTimeInMillis()));
            long cost = Math.round(deff/60.0) * 4;
            long _start = _arriveCalendar.getTime().getTime();
            long _end = _leaveCalendar.getTime().getTime();
            
            
//            System.out.println(_arriveCalendar.getTime().getTime());
//            System.out.println(_leaveCalendar.getTime().toString());
//            System.out.println(deff);
//            System.out.println( Math.round((deff / 60.0)));
//            
            String _name = MainController._currentUser.getUsername();
                    
            confirmAlert.setTitle("Confirmation Dialog");
            confirmAlert.setContentText("Would you like to reserve this parking for " + cost + "$ ?");
            
            
            
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if(result.get() == ButtonType.OK){
            	
            	if(MainController._currentUser.getBalance() < cost){
            		
            		informationAlert.setTitle("Reservation warrning");
            		informationAlert.setHeaderText(null);
            		informationAlert.setContentText("Insufficient fund, please make a deposit, you can do charge your wallet by clicking in Acount");
            		informationAlert.showAndWait();
            		
            	}else{
            		JSONObject json = new JSONObject();  
                    try {
                    	
            			json.put("carNumber", _carNumber);
            			json.put("lotName", _lotName);
            			json.put("username", _name);
            			json.put("start", _start);
            			json.put("end", _end);
            			json.put("cmd", "reserveAhead");
            			
            			// send to reservation servlet
            			JSONObject ret = request(json, "ReservationController");
            			
            			System.out.println(ret.getBoolean("result"));
            			if(ret.getBoolean("result")){
            				System.out.println("Old balance is: " + MainController._currentUser.getBalance());
            				MainController._currentUser.setBalance(MainController._currentUser.getBalance() - cost);
            				updateBalance((-1)*cost);
            				System.out.println("New balance is: " + MainController._currentUser.getBalance());
            			}
            		} catch (JSONException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
            	}	
            }
        }
        
                   
    }

    @FXML
    void buyRegularRoutineSubscription(ActionEvent event) {

    }
    
    /*void reserveParking(){
    	String _carNumber = parkResCarNumberTF.getText();
    	String _arriveHour = parkResArrivingHourTF.getText();
    	String _leaveHour = parkResLeavingHourTF.getText();
    	String _arriveDate = parkResArrivingDateTF.getText();
    	String _leaveDate = parkResLeavingDateTF.getText();
    	String _lotName = parkResParkingLotNameTF.getText();
    	
    	
    	
    	
    	if(_carNumber.equals("") || _arriveHour.equals("") || _leaveHour.equals("") ||
    			_arriveDate.equals("") || _leaveDate.equals("") ||  _lotName.equals("")){
    		
    		informationAlert.setTitle("Reservation warrning");
    		informationAlert.setHeaderText(null);
    		informationAlert.setContentText("Please fill all the above field to complete the reservation");
    		informationAlert.showAndWait();
    		
    	}else{
    		
    		try {
    			JSONObject toSend = new JSONObject();
    			toSend.put("carNumber", _carNumber);
    			toSend.put("arriveHour", _arriveHour);
    			toSend.put("leaveHour", _leaveHour);
    			toSend.put("arriveDate", _arriveDate);
    			toSend.put("leaveDate", _leaveDate);
    			toSend.put("lotName", _lotName);
    			
    			
    		} catch (JSONException | NullPointerException e1) {
    			
    			e1.printStackTrace();
    		}
    		
    	}
    
    	
    }*/
    

    @FXML
    void signOut(ActionEvent event) {
    	
//    	getReserves();
    	
    	
//    	System.out.println(getReserves());
    	MainController._currentUser = null;
    	
    	
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
    
    /*
     *  function that get as parameter a change of the balance, change the balance of the current user in the DB
     */
    void updateBalance(long cost){
    	
    	JSONObject json = new JSONObject(), ret = new JSONObject();
    	
    	try {
    		
    		json.put("username", MainController._currentUser.getUsername());
    		json.put("change", cost);
    		json.put("cmd", "balance");
    		ret = request(json, "UpdateUserInfo");
    		
    		if(ret.getBoolean("result")){
//				System.out.println(ret.toString());
			}
			
    		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /*
     *  function that return a JSONArray for all the reserves of a specific user name 
     */
    JSONArray getReserves(){
    	
    	JSONObject json = new JSONObject();
    	JSONObject ret = new JSONObject();
    	try {
    		
			json.put("username", MainController._currentUser.getUsername());
			ret = request(json, "UserServices");
			System.out.println(ret);
			if(ret.getBoolean("result")){
				JSONArray reservs = ret.getJSONArray("resArr");
				System.out.println(reservs.toString());
				return reservs;
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    	
    }
    
    
    JSONObject request(JSONObject json, String servletName){
    	HttpURLConnection connection = null;
		try {
		    //Create connection
		    URL url = new URL("http://" + MainController.IP + ":" + MainController.PORT + "/server/" + servletName);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream sentData = new DataOutputStream (connection.getOutputStream());
		   
		    sentData.writeBytes(json.toString());
		    
		    sentData.close();
		    JSONObject ret;

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      //response.append('\r');
		    }
		    
		    rd.close();
//		    System.out.println(response.toString() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
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
