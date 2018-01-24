/**
 * Sample Skeleton for 'CustomerServiceView.fxml' Controller Class
 */

package application;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import model.SharedData;

public class CustomerServiceController {
	
	
	Alert informationAlert = new Alert(AlertType.INFORMATION);
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	
	

    @FXML // fx:id="welcomeBanner"
    private Label welcomeBanner; // Value injected by FXMLLoader

    @FXML // fx:id="HandlingComplaintsCustomerServiceBorderPane"
    private BorderPane HandlingComplaintsCustomerServiceBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="ParkingReservationCustomerServiceBorderPane"
    private BorderPane ParkingReservationCustomerServiceBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResLeavingMinuteComboBox"
    private ComboBox<String> CusSerparkResLeavingMinuteComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResArrivingMinuteComboBox"
    private ComboBox<String> CusSerparkResArrivingMinuteComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="signOutButton"
    private Button signOutButton; // Value injected by FXMLLoader

    @FXML // fx:id="HandlingComplaintsButton"
    private Button HandlingComplaintsButton; // Value injected by FXMLLoader

    @FXML // fx:id="parkingReservationButton"
    private Button parkingReservationButton; // Value injected by FXMLLoader

    @FXML // fx:id="balanceOnTopOfLogIn"
    private Text balanceOnTopOfLogIn; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResArrivingHourComboBox"
    private ComboBox<String> CusSerparkResArrivingHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="ParkingReservationParkingLotWorkerBorderPane1"
    private BorderPane ParkingReservationParkingLotWorkerBorderPane1; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerParkingReservationCarNumberTF"
    private TextField CusSerParkingReservationCarNumberTF; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResLeavingDateDP"
    private DatePicker CusSerparkResLeavingDateDP; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResArrivingDateDP"
    private DatePicker CusSerparkResArrivingDateDP; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResComboBox"
    private ComboBox<String> CusSerparkResComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResReserveParkingButton"
    private Button CusSerparkResReserveParkingButton; // Value injected by FXMLLoader

    @FXML // fx:id="CusSerparkResLeavingHourComboBox"
    private ComboBox<String> CusSerparkResLeavingHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="textInTopOfLogIn"
    private Text textInTopOfLogIn; // Value injected by FXMLLoader

    @FXML
    void signOut(ActionEvent event) {

    }



    @FXML
    void CustomerServiceParkingReservation(ActionEvent event) {

		String _carNumber = CusSerParkingReservationCarNumberTF.getText();
		String _arriveHour = CusSerparkResArrivingHourComboBox.getValue();
		String _arriveMinute = CusSerparkResArrivingMinuteComboBox.getValue();
		String _leaveHour = CusSerparkResLeavingHourComboBox.getValue();
		String _leaveMinute = CusSerparkResLeavingMinuteComboBox.getValue();
		String _lotName = CusSerparkResComboBox.getValue();
		LocalDate arriveLocalDate = CusSerparkResArrivingDateDP.getValue();
		LocalDate leaveLocalDate = CusSerparkResLeavingDateDP.getValue();
		Calendar arriveCal = Calendar.getInstance();
		Calendar leaveCal = Calendar.getInstance();
		Boolean flag = false;

		if (_leaveHour == null) {
			_leaveHour = "00";
			flag = true;
		}
		if (_leaveMinute == null) {
			_leaveMinute = "00";
		}

		if (_arriveHour == null || _arriveMinute == null) {

			informationAlert.setTitle("Reservation warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please fill arriving hour and minuts fields to complete the reservation");
			informationAlert.showAndWait();
			return;

		}

		if (arriveLocalDate != null) {
			Instant instant = Instant.from(arriveLocalDate.atStartOfDay(ZoneId.systemDefault()));
			Date date = Date.from(instant);
			arriveCal = toCalendar(date);
			arriveCal.set(Calendar.HOUR, Integer.parseInt(_arriveHour));
			arriveCal.set(Calendar.MINUTE, Integer.parseInt(_arriveMinute));
		} else {
			informationAlert.setTitle("Reservation warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText(
					"Please fill all the car number, arriving date and parking lot fields to complete the reservation");
			informationAlert.showAndWait();
			return;
		}

		if (leaveLocalDate != null) {
			Instant instant2 = Instant.from(leaveLocalDate.atStartOfDay(ZoneId.systemDefault()));
			if (_leaveHour.equals("00") && _leaveMinute.equals("00") && flag) {
				instant2 = Instant.from(leaveLocalDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1));
			}
			Date date2 = Date.from(instant2);
			leaveCal = toCalendar(date2);
			leaveCal.set(Calendar.HOUR, Integer.parseInt(_leaveHour));
			leaveCal.set(Calendar.MINUTE, Integer.parseInt(_leaveMinute));
			System.out.println(leaveCal.toString());
		} else {
			Instant instant2 = Instant.from(arriveLocalDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1));
			Date date2 = Date.from(instant2);
			leaveCal = toCalendar(date2);
			leaveCal.set(Calendar.HOUR, Integer.parseInt(_leaveHour));
			leaveCal.set(Calendar.MINUTE, Integer.parseInt(_leaveMinute));
			System.out.println(leaveCal.getTime().toString());

		}

		if (_carNumber.equals("") || _lotName == null) {

			informationAlert.setTitle("Reservation warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText(
					"Please fill all the car number, arriving date and parking lot fields to complete the reservation");
			informationAlert.showAndWait();
			return;

		} else {
			long deff = TimeUnit.MILLISECONDS
					.toMinutes(Math.abs(leaveCal.getTimeInMillis() - arriveCal.getTimeInMillis()));
			double cost = Math.round(deff / 60.0) * SharedData.getInstance().getReservationCost();
			long _start = arriveCal.getTime().getTime();
			long _end = leaveCal.getTime().getTime();

			String _name = SharedData.getInstance().getCurrentUser().getUsername();

			confirmAlert.setTitle("Confirmation Dialog");
			confirmAlert.setContentText("Would you like to reserve this parking for " + cost + "$ ?");

			Optional<ButtonType> result = confirmAlert.showAndWait();
			if (result.get() == ButtonType.OK) {

				if (SharedData.getInstance().getCurrentUser().getBalance() < cost) {

					informationAlert.setTitle("Reservation warrning");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText(
							"Insufficient fund, please make a deposit, you can do charge your wallet by clicking in Acount");
					informationAlert.showAndWait();

				} else {
					JSONObject json = new JSONObject();
					try {
						
						//TODO: synchronize with server

						json.put("carNumber", _carNumber);
						json.put("lotName", _lotName);
						json.put("username", _name);
						json.put("start", _start);
						json.put("end", _end);
						json.put("cost", cost);
						json.put("type", "r");
						json.put("activated", 0);
						json.put("cmd", "reserveAhead");

						// send to reservation servlet
//						JSONObject ret = request(json, "CustomerServiceReservationController");
//
////						System.out.println(ret.getBoolean("result"));
//						if (ret.getBoolean("result")) {
//							System.out.println("Old balance is: " + SharedData.getInstance().getCurrentUser().getBalance());
//							
////							updateBalance((-1) * cost);
////							System.out.println("New balance is: " + SharedData.getInstance().getCurrentUser().getBalance());
//						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}

	

    }
    
    JSONObject request(JSONObject json, String servletName){
    	HttpURLConnection connection = null;
		try {
		    //Create connection
		    URL url = new URL("http://" + SharedData.getInstance().getIP() + ":" + SharedData.getInstance().getPORT() + "/server/" + servletName);
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

	public Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
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
