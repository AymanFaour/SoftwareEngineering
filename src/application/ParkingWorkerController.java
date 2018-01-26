/**
 * Sample Skeleton for 'ParkingWrokerView.fxml' Controller Class
 */

package application;

import java.io.IOException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

public class ParkingWorkerController {

	Alert informationAlert = new Alert(AlertType.INFORMATION);
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	
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

    @FXML // fx:id="ParkingReservationCreditCardIdTF"
    private TextField ParkingReservationCreditCardIdTF; // Value injected by FXMLLoader
 
    @FXML // fx:id="DepthComboBox"
    private ComboBox<String> DepthComboBox; // Value injected by FXMLLoader
   
    @FXML // fx:id="WidthComboBox"
    private ComboBox<String> WidthComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="HeightComboBox"
    private ComboBox<String> HeightComboBox; // Value injected by FXMLLoader

    private ObservableList<String> myComboBoxParkResComboBox = FXCollections.observableArrayList();
    private ObservableList<String> myComboBoxHoursData = FXCollections.observableArrayList();
    private ObservableList<String> myComboBoxMinutesData = FXCollections.observableArrayList();
    
    private ObservableList<String> myComboBoxWidth = FXCollections.observableArrayList();
    private ObservableList<String> myComboBoxDepth = FXCollections.observableArrayList();
    private ObservableList<String> myComboBoxHeight = FXCollections.observableArrayList();
    //private ObservableList<String> myComboBoxComplaintParkingData = FXCollections.observableArrayList();
    
    
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
    void disabledParkingSpot(ActionEvent event) {
    	
    	
    	
    	
    	
    	if((HeightComboBox.getValue() == null) || (WidthComboBox.getValue() == null) || (DepthComboBox.getValue() == null)) {

			informationAlert.setTitle("disable Spot warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please fill all the positions");
			informationAlert.showAndWait();
			return;
			
		} else {
			
			Integer _x = Integer.parseInt(HeightComboBox.getValue());
	    	Integer _y = Integer.parseInt(WidthComboBox.getValue());
	    	Integer _z = Integer.parseInt(DepthComboBox.getValue());
	    	
			
			String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
			
			int _high = _x - 1;
			int _width = _y - 1;
			int _depth = _z - 1;
			
			boolean canI = SharedData.getInstance().getCurrentParkingLot().CanDisapled();
			
			if(canI){
				
				if(SharedData.getInstance().getCurrentParkingLot().IsBusy(_high, _width, _depth)){
					
					informationAlert.setTitle("Disapling slot warrning");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("There are a car parking in the wanted slot, please wait for the slot to be availabe");
					informationAlert.showAndWait();
					
				}else if(SharedData.getInstance().getCurrentParkingLot().IsDisapled(_high, _width, _depth)){
					
					informationAlert.setTitle("Disapling slot warrning");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Pay attention that this parking slot is already disapled.");
					informationAlert.showAndWait();
					
				}else if(SharedData.getInstance().getCurrentParkingLot().IsAvailable(_high, _width, _depth)){
					
					System.out.println("we have " + SharedData.getInstance().getCurrentParkingLot().getDisableSlots() + " disapled Slots");
					boolean temp = SharedData.getInstance().getCurrentParkingLot().disaplySlot(_high, _width, _depth);
					System.out.println("we have " + SharedData.getInstance().getCurrentParkingLot().getDisableSlots() + " disapled Slots");
					
					if(temp){
					
						informationAlert.setTitle("Disapling slot Succeeded");
						informationAlert.setHeaderText(null);
						informationAlert.setContentText("Disapling slot succeeded.");
						informationAlert.showAndWait();
					
					}else{
						
						informationAlert.setTitle("Disapling slot Error");
						informationAlert.setHeaderText(null);
						informationAlert.setContentText("Something went wrong!!.");
						informationAlert.showAndWait();
						
					}
					
				}
				
			}
			
			JSONObject json = new JSONObject();
			try {
				
				//TODO: synchronize with server
	
				
				json.put("lotName", lotName);
				json.put("cmd", "disableSpot");
	
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
    void activateParkingSpot(ActionEvent event) {

    	String spotId=DisaParkSpotSpotIdTF.getText();
    	
    	if (spotId.equals("")) {

			informationAlert.setTitle("activate Spot warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please enter spot ID");
			informationAlert.showAndWait();
			return;
			
		} else {
			String lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
			JSONObject json = new JSONObject();
			try {
				
				//TODO: synchronize with server
	
				json.put("spotID", spotId);
				json.put("lotName", lotName);
				json.put("cmd", "activateSpote");
	
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
    void ReferenceToAlternativeParking(ActionEvent event) {
		String _carNumber = AlternativeParkingCarNumberTF.getText();
		String _lotName = alternativeComboBox.getValue();
		String _orderId=alternativeParkingReservationIdTF.getText();
		if (_carNumber.equals("") || _orderId.equals("") || _lotName == null) {

			informationAlert.setTitle("Reference To Alternative Parking warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please fill all the fields to complete the methode");
			informationAlert.showAndWait();
			return;

		} else {
					JSONObject json = new JSONObject();
					try {
						
						//TODO: synchronize with server
		
						json.put("carNumber", _carNumber);
						json.put("lotName", _lotName);
						json.put("orderID", _orderId);
						json.put("cmd", "ReferToAlternative");
						
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

    @FXML
    void parkingWorkerReserveParking(ActionEvent event) {
		String _carNumber = ParkingReservationCarNumberTF.getText();
		String _creditCardNumber= ParkingReservationCreditCardIdTF.getText();
		String _arriveHour = parkResArrivingHourComboBox.getValue();
		String _arriveMinute = parkResArrivingMinuteComboBox.getValue();
		String _leaveHour = parkResLeavingHourComboBox.getValue();
		String _leaveMinute = parkResLeavingMinuteComboBox.getValue();
		String _lotName = parkResComboBox.getValue();
		LocalDate arriveLocalDate = parkResArrivingDateDP.getValue();
		LocalDate leaveLocalDate = parkResLeavingDateDP.getValue();
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

		if (_carNumber.equals("") || _lotName == null||_creditCardNumber.equals("")) {

			informationAlert.setTitle("Reservation warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText(
					"Please fill all the car number,credit card id, arriving date and parking lot fields to complete the reservation");
			informationAlert.showAndWait();
			return;

		} else {
			long deff = TimeUnit.MILLISECONDS
					.toMinutes(Math.abs(leaveCal.getTimeInMillis() - arriveCal.getTimeInMillis()));
			double cost = Math.round(deff / 60.0) * SharedData.getInstance().getReservationCost();
			long _start = arriveCal.getTime().getTime();
			long _end = leaveCal.getTime().getTime();
			long _now = Calendar.getInstance().getTime().getTime();
			System.out.println(_now + " and the start is" + _start + " and the end is " + _end);
			if (_now > _start || _now > _end || _start >= _end) {
				informationAlert.setTitle("Reservation Warning");
				informationAlert.setHeaderText(null);
				informationAlert.setContentText("Please adjust dates and hours to convenient values");
				informationAlert.showAndWait();
			}else{
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
    }



	public Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
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
    	
    	ArrayList<String> parkingLotNames = new ArrayList<String>();
    	parkingLotNames.add("Ben Gurion");
    	parkingLotNames.add("Carmel");
    	parkingLotNames.add("Hadar");
    	parkingLotNames.add("Horev");
    	parkingLotNames.add("Hanita");
    	parkingLotNames.add("Neve Shaanan");
    	
    	myComboBoxParkResComboBox.clear();
    	for(int i = 0; i < parkingLotNames.size(); i++){
    		myComboBoxParkResComboBox.add(parkingLotNames.get(i));
    	}
    	parkResComboBox.setItems(myComboBoxParkResComboBox);

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
    	
    	myComboBoxWidth.clear();
    	for(Integer i = 1; i <SharedData.getInstance().getCurrentParkingLot().getWidth()+1 ; i++){
    		if(i < 10 ){
    			myComboBoxWidth.add("0" + i.toString());
    		}
    		else
    			myComboBoxWidth.add(i.toString());
    	}
    	
    	myComboBoxHeight.clear();
    	for(Integer i = 1; i <SharedData.getInstance().getCurrentParkingLot().getHeight()+1 ; i++){
    		if(i < 10 ){
    			myComboBoxHeight.add("0" + i.toString());
    		}
    		else
    			myComboBoxHeight.add(i.toString());
    	}
    	myComboBoxDepth.clear();
    	for(Integer i = 1; i < SharedData.getInstance().getCurrentParkingLot().getDepth()+1 ; i++){
    		if(i < 10 ){
    			myComboBoxDepth.add("0" + i.toString());
    		}
    		else
    			myComboBoxDepth.add(i.toString());
    	}
    	DepthComboBox.setItems(myComboBoxDepth);
    	WidthComboBox.setItems(myComboBoxWidth);
    	HeightComboBox.setItems(myComboBoxHeight);
        
    	

    }
	public void setWelcome(String welcome) {
		// TODO Auto-generated method stub
		welcomeBanner.setText(welcome);
	}
	public void setTopOfParkingWorker(String _username) {
		// TODO Auto-generated method stub
		textInTopOfLogIn.setText(_username);
	}

 

}
