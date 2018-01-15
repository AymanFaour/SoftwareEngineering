/**
 * Sample Skeleton for 'GuestView.fxml' Controller Class
 */

package application;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GuestController {
	
	Alert informationAlert = new Alert(AlertType.INFORMATION);
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);

    @FXML // fx:id="GuestLeavingHourComboBox"
    private ComboBox<String> GuestLeavingHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="welcomeBanner"
    private Label welcomeBanner; // Value injected by FXMLLoader

    @FXML // fx:id="GuestUserIdTF"
    private TextField GuestUserIdTF; // Value injected by FXMLLoader

    @FXML // fx:id="GuestLeavingMinuteComboBox"
    private ComboBox<String> GuestLeavingMinuteComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="GuestLeavingDateDP"
    private DatePicker GuestLeavingDateDP; // Value injected by FXMLLoader

    @FXML // fx:id="GuestUserEmailTF"
    private TextField GuestUserEmailTF; // Value injected by FXMLLoader

    @FXML // fx:id="textInTopOfLogIn"
    private Text textInTopOfLogIn; // Value injected by FXMLLoader

    @FXML // fx:id="GuestReserveParkingButton"
    private Button GuestReserveParkingButton; // Value injected by FXMLLoader

    @FXML // fx:id="GuestCarNumberTF"
    private TextField GuestCarNumberTF; // Value injected by FXMLLoader

    @FXML // fx:id="signOutButton"
    private Button signOutButton; // Value injected by FXMLLoader

    private ObservableList<String> myComboBoxHoursData = FXCollections.observableArrayList();
    
    private ObservableList<String> myComboBoxMinutesData = FXCollections.observableArrayList();
    

    @FXML
    void signOut(ActionEvent event) {
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


    @FXML 
    void reserveParking(ActionEvent event) {

		String _carNumber = GuestCarNumberTF.getText();
		String _lotName = Main._currentParkingLot.get_name();
		String _ocLeaveHour = GuestLeavingHourComboBox.getValue();
		String _ocLeaveMinute = GuestLeavingMinuteComboBox.getValue();
		LocalDate leaveLocalDate = GuestLeavingDateDP.getValue();
		Calendar arriveCal = Calendar.getInstance();
		Calendar leaveCal = Calendar.getInstance();
		boolean flag = false;

		if (_ocLeaveHour == null) {
			_ocLeaveHour = "00";
			flag = true;
		}

		if (_ocLeaveMinute == null) {
			_ocLeaveMinute = "00";
		}

		if (leaveLocalDate != null) {
			Instant leaveInstant = Instant.from(leaveLocalDate.atStartOfDay(ZoneId.systemDefault()));
			if (_ocLeaveHour.equals("00") && _ocLeaveMinute.equals("00") && flag) {
				leaveInstant = Instant.from(leaveLocalDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1));
			}
			Date leaveDate = Date.from(leaveInstant);
			leaveCal = toCalendar(leaveDate);
			leaveCal.set(Calendar.HOUR, Integer.parseInt(_ocLeaveHour));
			leaveCal.set(Calendar.MINUTE, Integer.parseInt(_ocLeaveMinute));
//			System.out.println(leaveCal.toString());
		}

		if (_carNumber.equals("")  || (leaveLocalDate == null)) {
			informationAlert.setTitle("Reservation warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please fill all the above field to complete the reservation");
			informationAlert.showAndWait();

		} else {

			long _start = arriveCal.getTime().getTime();
			long _end = leaveCal.getTime().getTime();
			long deff = TimeUnit.MILLISECONDS
					.toMinutes(Math.abs(leaveCal.getTimeInMillis() - arriveCal.getTimeInMillis()));
			long cost = Math.round(deff / 60.0) * 5;
			int ocLeaveHourInt = Integer.parseInt(_ocLeaveHour);
			int ocLeaveMinuteInt = Integer.parseInt(_ocLeaveMinute);

			
			String leaveHour = "";
			if (ocLeaveMinuteInt < 10) {
				leaveHour = (ocLeaveHourInt) + ":0" + (ocLeaveMinuteInt);
			} else {
				leaveHour = (ocLeaveHourInt) + ":" + (ocLeaveMinuteInt);
			}

			if (ocLeaveHourInt < 10) {
				leaveHour = "0" + leaveHour;
			}

			JSONObject json = new JSONObject();
			try {
				confirmAlert.setTitle("Confirmation Dialog");
				confirmAlert.setContentText("Would you like to reserve this parking for " + cost + "$ ?");

				Optional<ButtonType> result = confirmAlert.showAndWait();
				if (result.get() == ButtonType.OK) {

						json.put("carNumber", _carNumber);
						json.put("lotName", _lotName);
						json.put("username", "GUEST");
						json.put("leave", leaveHour);
						json.put("start", _start);
						json.put("end", _end);
						json.put("type", "o");
						json.put("activated", 1);
						json.put("cmd", "reserveAhead");

						JSONObject ret = request(json, "ReservationController");

						System.out.println(ret.getBoolean("result"));
						if (ret.getBoolean("result")) {
							
							
							informationAlert.setTitle("Parking succeeded");
							informationAlert.setHeaderText(null);
							informationAlert.setContentText("Please pay attention that the payment is after exiting the car.");
							informationAlert.showAndWait();
							
							// TODO: consider the payment issues in the exit
							// TODO: call the occasional enterPark function
							
							signOut(null);
							
					}
				} else {

				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

	
    }
    
    
	public Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public ObservableList<String> getMyComboBoxHoursData() {
		return myComboBoxHoursData;
	}


	public void setMyComboBoxHoursData(ObservableList<String> myComboBoxHoursData) {
		this.myComboBoxHoursData = myComboBoxHoursData;
		this.GuestLeavingHourComboBox.setItems(this.myComboBoxHoursData);
	}


	public ObservableList<String> getMyComboBoxMinutesData() {
		return myComboBoxMinutesData;
	}


	public void setMyComboBoxMinutesData(ObservableList<String> myComboBoxMinutesData) {
		this.myComboBoxMinutesData = myComboBoxMinutesData;
		this.GuestLeavingMinuteComboBox.setItems(this.myComboBoxMinutesData);
	}
	
	JSONObject request(JSONObject json, String servletName) {
		HttpURLConnection connection = null;
		try {
			// Create connection
			URL url = new URL("http://" + MainController.IP + ":" + MainController.PORT + "/server/" + servletName);
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

}
