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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SharedData;

public class LogInController {
	
	Alert informationAlert = new Alert(AlertType.INFORMATION);
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);

	@FXML // fx:id="welcomeBanner"
    private Label welcomeBanner; // Value injected by FXMLLoader

    @FXML // fx:id="businessRoutineSubscriptionBorderPane"
    private BorderPane businessRoutineSubscriptionBorderPane; // Value injected by FXMLLoader

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

    @FXML // fx:id="businessRoutinelySubscriptionButton"
    private Button businessRoutinelySubscriptionButton; // Value injected by FXMLLoader

    @FXML // fx:id="fullSubscriptionBorderPane"
    private BorderPane fullSubscriptionBorderPane; // Value injected by FXMLLoader

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
    
    @FXML // fx:id="parkResComboBox"
    private ComboBox<String> parkResComboBox; // Value injected by FXMLLoader
    private ObservableList<String> myComboBoxData = FXCollections.observableArrayList();
    private ObservableList<String> myComboBoxHoursData = FXCollections.observableArrayList();
    private ObservableList<String> myComboBoxMinutesData = FXCollections.observableArrayList();
    private ObservableList<String> myComboBoxComplaintParkingData = FXCollections.observableArrayList();
    
    @FXML // fx:id="regRouComboBox"
    private ComboBox<String> regRouComboBox; // Value injected by FXMLLoader
    
    @FXML // fx:id="parkResArrivingDateDP"
    private DatePicker parkResArrivingDateDP; // Value injected by FXMLLoader
    
    @FXML // fx:id="parkResLeavingDateDP"
    private DatePicker parkResLeavingDateDP; // Value injected by FXMLLoader

    @FXML // fx:id="parkResArrivingMinuteComboBox"
    private ComboBox<String> parkResArrivingMinuteComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="parkResArrivingHourComboBox"
    private ComboBox<String> parkResArrivingHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="parkResLeavingMinuteComboBox"
    private ComboBox<String> parkResLeavingMinuteComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="parkResLeavingHourComboBox"
    private ComboBox<String> parkResLeavingHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="MyAccountButton"
    private Button MyAccountButton; // Value injected by FXMLLoader

    @FXML // fx:id="MyAccountBorderPane"
    private BorderPane MyAccountBorderPane; // Value injected by FXMLLoader
    
    @FXML // fx:id="AmountTF"
    private TextField AmountTF; // Value injected by FXMLLoader

    @FXML // fx:id="CreditCardIDTF"
    private TextField CreditCardIDTF; // Value injected by FXMLLoader

    @FXML // fx:id="MyAccountDepositButton"
    private Button MyAccountDepositButton; // Value injected by FXMLLoader

    @FXML
    private ComboBox<String> regRouSubRoutineHourComboBox;

    @FXML
    private ComboBox<String> regRouSubRoutineMinuteComboBox;
    
    @FXML // fx:id="reservationsList"
    private VBox reservationsList; // Value injected by FXMLLoader

   
    @FXML // fx:id="ActualParkingLeavingMinuteComboBox"
    private ComboBox<String> ActualParkingLeavingMinuteComboBox; // Value injected by FXMLLoader
   
    @FXML // fx:id="ActualParkingLeavingDateDP"
    private DatePicker ActualParkingLeavingDateDP; // Value injected by FXMLLoader
  
    @FXML // fx:id="ActualParkingLeavingHourComboBox"
    private ComboBox<String> ActualParkingLeavingHourComboBox; // Value injected by FXMLLoader
   
    @FXML // fx:id="ActualParkingNumberTF"
    private TextField ActualParkingNumberTF; // Value injected by FXMLLoader

  
    @FXML // fx:id="ActualParkingButton"
    private Button ActualParkingButton; // Value injected by FXMLLoader

    @FXML // fx:id="ActualParkingBorderPane"
    private BorderPane ActualParkingBorderPane; // Value injected by FXMLLoader
    
    @FXML // fx:id="ComplaintReservationIdTF"
    private TextField ComplaintReservationIdTF; // Value injected by FXMLLoader

    @FXML // fx:id="ComplaintTA"
    private TextArea ComplaintTA; // Value injected by FXMLLoader

    @FXML // fx:id="ComplaintSendButton"
    private Button ComplaintSendButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="ComplaintCarNumberTF"
    private TextField ComplaintCarNumberTF; // Value injected by FXMLLoader

    @FXML // fx:id="subscriptionsList"
    private VBox subscriptionsList; // Value injected by FXMLLoader
    
    @FXML // fx:id="fullSubscriptionsList"
    private VBox fullSubscriptionsList; // Value injected by FXMLLoader
    
    @FXML // fx:id="complaintComboBox"
    private ComboBox<String> complaintComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="addWrokerToBusinessAccountButton"
    private Button addWrokerToBusinessAccountButton; // Value injected by FXMLLoader
    
    @FXML // fx:id="listOfAddedWorkersBusinessAcocuntVBOX"
    private VBox listOfAddedWorkersBusinessAcocuntVBOX; // Value injected by FXMLLoader
    
    @FXML // fx:id="remWrokerFromBusinessAccountButton"
    private Button remWrokerFromBusinessAccountButton; // Value injected by FXMLLoader

    @FXML // fx:id="buyBusinessSubscriptionButton"
    private Button buyBusinessSubscriptionButton; // Value injected by FXMLLoader

    @FXML // fx:id="activationBusinessCarTF"
    private TextField activationBusinessCarTF; // Value injected by FXMLLoader

    @FXML // fx:id="activationBusinessCodeTF"
    private TextField activationBusinessCodeTF; // Value injected by FXMLLoader

    @FXML // fx:id="busRouLotNameComboBox"
    private ComboBox<String> busRouLotNameComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="busRouSubRoutineHourComboBox"
    private ComboBox<String> busRouSubRoutineHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="buRouSubRoutineMinuteComboBox"
    private ComboBox<String> busRouSubRoutineMinuteComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="logInButtonListVbox"
    private VBox logInButtonListVbox; // Value injected by FXMLLoader
    
    @FXML // fx:id="parkingReservationCostText"
    private Text parkingReservationCostText; // Value injected by FXMLLoader

    
    private int businessAccountWorkersCounter = 0;
    
    public int getBusinessAccountWorkersCounter() {
		return businessAccountWorkersCounter;
	}

	public void setBusinessAccountWorkersCounter(int businessAccountWorkersCounter) {
		this.businessAccountWorkersCounter = businessAccountWorkersCounter;
	}

	public void setWelcome(String s){
    	welcomeBanner.setText(s);
    }
    
    public void setTopOfLogInView(String name, String balance){
    	textInTopOfLogIn.setText(name);
    	balanceOnTopOfLogIn.setText(balance);
    }
    
    public void setBusinessButton(Button b){
    	int counter = 0;
    	for (int i = 0; i < logInButtonListVbox.getChildren().size(); i++){
    		if(((Button) logInButtonListVbox.getChildren().get(i)).getText() == "Test"){
    			counter++;
    		}
    	}
    	if (counter != logInButtonListVbox.getChildren().size())
    		logInButtonListVbox.getChildren().add(4, b);
    }
    
    @FXML
    void loadParkingReservation(ActionEvent event) {
    	businessRoutineSubscriptionBorderPane.setVisible(false);
    	regularRoutineSubscriptionBorderPane.setVisible(false);
    	parkingReservationBorderPane.setVisible(true);
    	fullSubscriptionBorderPane.setVisible(false);
    	complaintBorderPane.setVisible(false);
    	viewProfileBorderPane.setVisible(false);
    	MyAccountBorderPane.setVisible(false);
    	ActualParkingBorderPane.setVisible(false); 
 

    	parkingReservationButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	parkingReservationButton.getStyleClass().add("pressedButton");
    	if(SharedData.getInstance().getCurrentUser().getType().equals("b")){
    		Button businessBut = (Button) activationBusinessCodeTF.getScene().lookup("#businessRoutinelySubscriptionButton");
    		businessBut.getStyleClass().removeAll("pressedButton", "focus");
    		businessBut.getStyleClass().add("loginView-buttons");
    	}
    	regularRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	regularRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	fullSubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	fullSubscriptionButton.getStyleClass().add("loginView-buttons");
    	viewReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	viewReservationButton.getStyleClass().add("loginView-buttons");
    	complaintButton.getStyleClass().removeAll("pressedButton", "focus");
    	complaintButton.getStyleClass().add("loginView-buttons");
    	MyAccountButton.getStyleClass().removeAll("pressedButton", "focus");
    	MyAccountButton.getStyleClass().add("loginView-buttons");
    	ActualParkingButton.getStyleClass().removeAll("pressedButton", "focus");
    	ActualParkingButton.getStyleClass().add("loginView-buttons");
    	
    	ArrayList<String> parkingLotNames = new ArrayList<String>();
    	parkingLotNames.add("Ben Gurion");
    	parkingLotNames.add("Carmel");
    	parkingLotNames.add("Hadar");
    	parkingLotNames.add("Horev");
    	parkingLotNames.add("Hanita");
    	parkingLotNames.add("Neve Shaanan");
    
    	
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
    	
    	myComboBoxData.clear();
    	for(int i = 0; i < parkingLotNames.size(); i++){
    		myComboBoxData.add(parkingLotNames.get(i));
    	}
    	parkResComboBox.setItems(myComboBoxData);
    	parkResArrivingHourComboBox.setItems(myComboBoxHoursData);
    	parkResArrivingMinuteComboBox.setItems(myComboBoxMinutesData);
    	parkResLeavingHourComboBox.setItems(myComboBoxHoursData);
    	parkResLeavingMinuteComboBox.setItems(myComboBoxMinutesData);
    }

    @FXML
    void loadRegularRoutinelySubscription(ActionEvent event) {
    	businessRoutineSubscriptionBorderPane.setVisible(false);
    	regularRoutineSubscriptionBorderPane.setVisible(true);
    	parkingReservationBorderPane.setVisible(false);
    	fullSubscriptionBorderPane.setVisible(false);
    	complaintBorderPane.setVisible(false);
    	viewProfileBorderPane.setVisible(false);
    	MyAccountBorderPane.setVisible(false);
    	ActualParkingBorderPane.setVisible(false);


    	
    	regularRoutinelySubscriptionButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	regularRoutinelySubscriptionButton.getStyleClass().add("pressedButton");
    	if(SharedData.getInstance().getCurrentUser().getType().equals("b")){
    		Button businessBut = (Button) activationBusinessCodeTF.getScene().lookup("#businessRoutinelySubscriptionButton");
    		businessBut.getStyleClass().removeAll("pressedButton", "focus");
    		businessBut.getStyleClass().add("loginView-buttons");
    	}
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	fullSubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	fullSubscriptionButton.getStyleClass().add("loginView-buttons");
    	viewReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	viewReservationButton.getStyleClass().add("loginView-buttons");
    	complaintButton.getStyleClass().removeAll("pressedButton", "focus");
    	complaintButton.getStyleClass().add("loginView-buttons");
    	MyAccountButton.getStyleClass().removeAll("pressedButton", "focus");
    	MyAccountButton.getStyleClass().add("loginView-buttons");
    	ActualParkingButton.getStyleClass().removeAll("pressedButton", "focus");
    	ActualParkingButton.getStyleClass().add("loginView-buttons");
    	
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
    	
    	
    	regRouSubRoutineHourComboBox.setItems(myComboBoxHoursData);
    	regRouSubRoutineMinuteComboBox.setItems(myComboBoxMinutesData);
    

    	ArrayList<String> parkingLotNames = new ArrayList<String>();
    	parkingLotNames.add("Ben Gurion");
    	parkingLotNames.add("Carmel");
    	parkingLotNames.add("Hadar");
    	parkingLotNames.add("Horev");
    	parkingLotNames.add("Hanita");
    	parkingLotNames.add("Neve Shaanan");
    	
    	myComboBoxData.clear();
    	for(int i = 0; i < parkingLotNames.size(); i++){
    		myComboBoxData.add(parkingLotNames.get(i));
    	}
    	regRouComboBox.setItems(myComboBoxData);
    }
    

    @FXML
    void loadBusinessRoutinelySubscription(ActionEvent event) {
    	businessRoutineSubscriptionBorderPane.setVisible(true);
    	regularRoutineSubscriptionBorderPane.setVisible(false);
    	parkingReservationBorderPane.setVisible(false);
    	fullSubscriptionBorderPane.setVisible(false);
    	complaintBorderPane.setVisible(false);
    	viewProfileBorderPane.setVisible(false);
    	MyAccountBorderPane.setVisible(false);
    	ActualParkingBorderPane.setVisible(false);

    	if(SharedData.getInstance().getCurrentUser().getType().equals("b")){
    		Button businessBut = (Button) activationBusinessCodeTF.getScene().lookup("#businessRoutinelySubscriptionButton");
    		businessBut.getStyleClass().removeAll("loginView-buttons", "focus");
    		businessBut.getStyleClass().add("pressedButton");
    	}
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
    	MyAccountButton.getStyleClass().removeAll("pressedButton", "focus");
    	MyAccountButton.getStyleClass().add("loginView-buttons");
    	ActualParkingButton.getStyleClass().removeAll("pressedButton", "focus");
    	ActualParkingButton.getStyleClass().add("loginView-buttons");
    	
    	this.setBusinessAccountWorkersCounter(0);
    	int length = this.listOfAddedWorkersBusinessAcocuntVBOX.getChildren().size();
    	System.out.println(length);
    	for (int i = length - 2; i > 0 ; i--){
    		this.listOfAddedWorkersBusinessAcocuntVBOX.getChildren().remove(i);
    	}
    	
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
    	
    	myComboBoxComplaintParkingData.clear();     
    	for(int i = 0; i < parkingLotNames.size(); i++){
    		myComboBoxComplaintParkingData.add(parkingLotNames.get(i));
    	}
    	
    	busRouLotNameComboBox.setItems(myComboBoxComplaintParkingData);
    	busRouSubRoutineHourComboBox.setItems(myComboBoxHoursData);
    	busRouSubRoutineMinuteComboBox.setItems(myComboBoxMinutesData);
    	
    	
    }
    

    @FXML
    void addWorkerToBusinessAcocunt(ActionEvent event) {
		HBox hb = new HBox();
		hb.setStyle("-fx-pref-height: 30; -fx-padding: 5 0 0 10;");
		reservationsList.getChildren().add(hb);
		
		this.setBusinessAccountWorkersCounter(this.getBusinessAccountWorkersCounter() + 1);
		
		TextField tf = new TextField();
		String css = getClass().getResource("application.css").toExternalForm();
		tf.getStylesheets().clear();
		tf.getStylesheets().add(css);
		
		tf.getStyleClass().add("text-field-first");
		tf.setPromptText("Car Number");
		hb.getChildren().add(tf);
		
		tf.setId("businessWorkerTF" + Integer.toString(getBusinessAccountWorkersCounter()));
		
		int lenOfWorkersList = listOfAddedWorkersBusinessAcocuntVBOX.getChildren().size();
		listOfAddedWorkersBusinessAcocuntVBOX.getChildren().add(lenOfWorkersList - 1, hb);
		
		System.out.println("this is the counter from add:" + this.getBusinessAccountWorkersCounter());
    }
    

    @FXML
    void remWorkerFromBusinessAcocunt(ActionEvent event) {
    	
		int lenOfWorkersList = listOfAddedWorkersBusinessAcocuntVBOX.getChildren().size();
		if(lenOfWorkersList > 2){
			listOfAddedWorkersBusinessAcocuntVBOX.getChildren().remove(lenOfWorkersList - 2);
			this.setBusinessAccountWorkersCounter(this.getBusinessAccountWorkersCounter()-1);
		}
		System.out.println("this is the counter from remove:" + this.getBusinessAccountWorkersCounter());
    }
    
    @FXML
    void loadFullSubscription(ActionEvent event) {
    	businessRoutineSubscriptionBorderPane.setVisible(false);
    	regularRoutineSubscriptionBorderPane.setVisible(false);
    	parkingReservationBorderPane.setVisible(false);
    	fullSubscriptionBorderPane.setVisible(true);
    	complaintBorderPane.setVisible(false);
    	viewProfileBorderPane.setVisible(false);
    	MyAccountBorderPane.setVisible(false);
    	ActualParkingBorderPane.setVisible(false);

    
    	fullSubscriptionButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	fullSubscriptionButton.getStyleClass().add("pressedButton");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	regularRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	regularRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	if(SharedData.getInstance().getCurrentUser().getType().equals("b")){
    		Button businessBut = (Button) activationBusinessCodeTF.getScene().lookup("#businessRoutinelySubscriptionButton");
    		businessBut.getStyleClass().removeAll("pressedButton", "focus");
    		businessBut.getStyleClass().add("loginView-buttons");
    	}
    	viewReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	viewReservationButton.getStyleClass().add("loginView-buttons");
    	complaintButton.getStyleClass().removeAll("pressedButton", "focus");
    	complaintButton.getStyleClass().add("loginView-buttons");
    	MyAccountButton.getStyleClass().removeAll("pressedButton", "focus");
    	MyAccountButton.getStyleClass().add("loginView-buttons");
    	ActualParkingButton.getStyleClass().removeAll("pressedButton", "focus");
    	ActualParkingButton.getStyleClass().add("loginView-buttons");
    
    }

    @FXML
    void loadViewReservation(ActionEvent event) {
    	businessRoutineSubscriptionBorderPane.setVisible(false);
    	regularRoutineSubscriptionBorderPane.setVisible(false);
    	parkingReservationBorderPane.setVisible(false);
    	fullSubscriptionBorderPane.setVisible(false);
    	complaintBorderPane.setVisible(false);
    	viewProfileBorderPane.setVisible(true);
    	MyAccountBorderPane.setVisible(false);
    	ActualParkingBorderPane.setVisible(false);

    	
    	viewReservationButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	viewReservationButton.getStyleClass().add("pressedButton");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	regularRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	regularRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	fullSubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	fullSubscriptionButton.getStyleClass().add("loginView-buttons");
    	if(SharedData.getInstance().getCurrentUser().getType().equals("b")){
    		Button businessBut = (Button) activationBusinessCodeTF.getScene().lookup("#businessRoutinelySubscriptionButton");
    		businessBut.getStyleClass().removeAll("pressedButton", "focus");
    		businessBut.getStyleClass().add("loginView-buttons");
    	}
    	complaintButton.getStyleClass().removeAll("pressedButton", "focus");
    	complaintButton.getStyleClass().add("loginView-buttons");
    	MyAccountButton.getStyleClass().removeAll("pressedButton", "focus");
    	MyAccountButton.getStyleClass().add("loginView-buttons");
    	ActualParkingButton.getStyleClass().removeAll("pressedButton", "focus");
    	ActualParkingButton.getStyleClass().add("loginView-buttons");

    	
    	
    	
    	JSONObject ret = getReserves();
    	
    
		
		
    	
    	try {
    		
    		JSONArray ja = ret.getJSONArray("resArr");
    		JSONArray ja2 = ret.getJSONArray("routSubArr");
    		JSONArray ja3 = ret.getJSONArray("fullSubArr");
    		
//    		System.out.println(ja);
    		
    		
    		int length = reservationsList.getChildren().size();
    		reservationsList.getChildren().remove(0, length);
    		
//    		ja.put(new JSONObject().put("ID", "12").put("arriving hour", "18:00").put("leaving hour", "20:00")
//					.put("arriving date", "jan 12").put("leaving date", "jan 14").put("status", "in queue")
//					.put("car id", "2000").put("parking lot name","majdal shams"));
//    		
//    		ja.put(new JSONObject().put("ID", "20").put("arriving hour", "18:00").put("leaving hour", "20:00")
//					.put("arriving date", "jan 12").put("leaving date", "jan 14").put("status", "parking")
//					.put("car id", "2001").put("parking lot name","majdal shams"));
//    		
//    		ja.put(new JSONObject().put("ID", "70").put("arriving hour", "18:00").put("leaving hour", "20:00")
//					.put("arriving date", "jan 12").put("leaving date", "jan 14").put("status", "parking")
//					.put("car id", "20002").put("parking lot name","majdal shams"));
//    		
//    		
    		
    		for(int i = 0; i < ja.length(); i++){

        	    Label resId = new Label(Integer.toString(((JSONObject) ja.get(i)).getInt("reserveID")));
        	    resId.setStyle("-fx-pref-width: 40;");
        		Label arriving = new Label(((JSONObject) ja.get(i)).getString("start").substring(0, 16));
        		arriving.setStyle("-fx-pref-width: 80; -fx-font-size: 8px;");
        		Label leaving = new Label(((JSONObject) ja.get(i)).getString("end").substring(0, 16));
        		leaving.setStyle("-fx-pref-width: 80; -fx-font-size: 8px;");
        		Label carId = new Label(((JSONObject) ja.get(i)).getString("carNumber"));
        		carId.setStyle("-fx-pref-width: 80;");
        		Label parkingLotName = new Label(((JSONObject) ja.get(i)).getString("lotName"));
        		parkingLotName.setStyle("-fx-pref-width: 100;");
        		Label status = null;
        		if(((JSONObject) ja.get(i)).getBoolean("activated") == false){
        			status = new Label("In Queue");
        			status.setStyle("-fx-pref-width: 60;");
        		}else{
        			status = new Label("Parking");
        			status.setStyle("-fx-pref-width: 60;");
        		}
   
    		
				HBox hb = new HBox();
				hb.getChildren().add(resId);
				hb.getChildren().add(carId);
				hb.getChildren().add(arriving);
				hb.getChildren().add(leaving);
				hb.getChildren().add(parkingLotName);
				hb.getChildren().add(status);
				hb.setStyle("-fx-border-style: solid inside;-fx-pref-height: 30;-fx-border-width: 0 0 2 0;"
						+ "-fx-border-color: #d0e6f8; -fx-padding: 1.5 0 0 5;");
				reservationsList.getChildren().add(hb);
				
				if(status.getText() == "In Queue"){
					Button activateButton = new Button("Enter");
					activateButton.setId("activateButton" + resId.getText());
					String css = getClass().getResource("application.css").toExternalForm();
					activateButton.getStylesheets().clear();
					activateButton.getStylesheets().add(css);
					activateButton.setOnAction(e -> activateParking(e, resId.getText(), carId.getText(), arriving.getText(), leaving.getText(), parkingLotName.getText()));
					
					activateButton.getStyleClass().add("activate-button");
					hb.getChildren().add(activateButton);
					
					HBox sep = new HBox();
					sep.setStyle("-fx-pref-width:5px;");
					hb.getChildren().add(sep);
					
					Button cancelReservation = new Button("Cancel");
					cancelReservation.setId("cancelReservation" + resId.getText());
					cancelReservation.getStylesheets().clear();
					cancelReservation.getStylesheets().add(css);
					cancelReservation.setOnAction(e -> cancel(e, resId.getText()));
					cancelReservation.getStyleClass().add("cancel-button");
					hb.getChildren().add(cancelReservation);
				}
				
				if(status.getText() == "Parking"){
					Button deActivateButton = new Button("Exit");
					deActivateButton.setId("deactivateButton" + resId.getText());
					String css = getClass().getResource("application.css").toExternalForm();
					deActivateButton.getStylesheets().clear();
					deActivateButton.getStylesheets().add(css);
					deActivateButton.setOnAction(e -> deActivateParking(e));
					deActivateButton.getStyleClass().add("deactivate-button");
					hb.getChildren().add(deActivateButton);
				}
				

					
				/***/
				
    		}
    		
    		
    		int length2 = subscriptionsList.getChildren().size();
    		subscriptionsList.getChildren().remove(0, length2);
    		
//    		System.out.println(ja2);
    		
			for(int i = 0; i < ja2.length(); i++){
				
//				System.out.println("int the for with i = "  + Integer.toString(i) + "@@@@@@@@@@@@@@@@@");

				HBox hb = new HBox();
				
        	    Label subId = new Label(Integer.toString(((JSONObject) ja2.get(i)).getInt("routineSubID")));
        	    subId.setStyle("-fx-pref-width: 40;");
        		Label leaving = new Label(((JSONObject) ja2.get(i)).getString("end").substring(0,11));
        		leaving.setStyle("-fx-pref-width: 80; -fx-font-size: 8px;");
        		Label starting = new Label(((JSONObject) ja2.get(i)).getString("start").substring(0,11));
        		starting.setStyle("-fx-pref-width: 80; -fx-font-size: 8px;");
        		Label leavingHour = new Label(((JSONObject) ja2.get(i)).getString("leaveHour"));
        		leavingHour.setStyle("-fx-pref-width: 80;");
        		Label carId = new Label(((JSONObject) ja2.get(i)).getString("carNumber"));
        		carId.setStyle("-fx-pref-width: 80;");
        		Label parkingLotName = new Label(((JSONObject) ja2.get(i)).getString("lotName"));
        		parkingLotName.setStyle("-fx-pref-width: 80;");
        		
        		
        		
        		Label status = null;
        		if(((JSONObject) ja2.get(i)).getInt("used") == 0){
        			status = new Label("Not Used");
        			status.setStyle("-fx-pref-width: 60;");
        		}else if(((JSONObject) ja2.get(i)).getInt("used") == 1){
        			status = new Label("Parking");
        			status.setStyle("-fx-pref-width: 60;");
        		}else{
        			status = new Label("Used");
        			status.setStyle("-fx-pref-width: 60;");
        		}


		        hb.getChildren().add(subId);
		        hb.getChildren().add(carId);
				hb.getChildren().add(parkingLotName);
				hb.getChildren().add(starting);
				hb.getChildren().add(leaving);
				hb.getChildren().add(leavingHour);
				
				hb.getChildren().add(status);
				hb.setStyle("-fx-border-style: solid inside;-fx-pref-height: 30;-fx-border-width: 0 0 2 0;"
						+ "-fx-border-color: #d0e6f8; -fx-padding: 1.5 0 0 5;");
				subscriptionsList.getChildren().add(hb);
				
				if(status.getText().equals("Not Used")){
					Button activateButton = new Button("Enter");
					activateButton.setId("subActivateButton" + subId.getText());
					String css = getClass().getResource("application.css").toExternalForm();
					activateButton.getStylesheets().clear();
					activateButton.getStylesheets().add(css);
					activateButton.setOnAction(e -> activateParkingSub(e, subId.getText(),
							parkingLotName.getText()));
					activateButton.getStyleClass().add("activate-button");
					hb.getChildren().add(activateButton);	
				}
				
				if(status.getText().equals("Parking")){
					Button deActivateButton = new Button("Exit");
					deActivateButton.setId("subDeactivateButton" + subId.getText());
					String css = getClass().getResource("application.css").toExternalForm();
					deActivateButton.getStylesheets().clear();
					deActivateButton.getStylesheets().add(css);
					deActivateButton.setOnAction(e -> deActivateParkingSub(e, subId.getText()));
					deActivateButton.getStyleClass().add("deactivate-button");
					hb.getChildren().add(deActivateButton);
				}
			}
			
			
    		int length3 = fullSubscriptionsList.getChildren().size();
    		fullSubscriptionsList.getChildren().remove(0, length3);
    		
			for(int i = 0; i < ja3.length(); i++){
				
				
				HBox hb = new HBox();
				
        	    Label subId = new Label(Integer.toString(((JSONObject) ja3.get(i)).getInt("fullSubID")));
        	    subId.setStyle("-fx-pref-width: 40;");
        		Label leaving = new Label(((JSONObject) ja3.get(i)).getString("end").substring(0,11));
        		leaving.setStyle("-fx-pref-width: 80; -fx-font-size: 8px;");
        		Label starting = new Label(((JSONObject) ja3.get(i)).getString("start").substring(0,11));
        		starting.setStyle("-fx-pref-width: 80; -fx-font-size: 8px;");
        		Label carId = new Label(((JSONObject) ja3.get(i)).getString("carNumber"));
        		carId.setStyle("-fx-pref-width: 80;");
        		
        		
        		
        		Label status = null;
        		if(((JSONObject) ja3.get(i)).getBoolean("isParking")){
        			status = new Label("Parking");
        			status.setStyle("-fx-pref-width: 80;");
        		}else{
        			status = new Label("Not Parking");
        			status.setStyle("-fx-pref-width: 80;");
        		}
    		

		        hb.getChildren().add(subId);
				hb.getChildren().add(carId);
				hb.getChildren().add(starting);
		        hb.getChildren().add(leaving);
				hb.getChildren().add(status);
				hb.setStyle("-fx-border-style: solid inside;-fx-pref-height: 30;-fx-border-width: 0 0 2 0;"
						+ "-fx-border-color: #d0e6f8; -fx-padding: 1.5 0 0 5;");
				fullSubscriptionsList.getChildren().add(hb);
				
				if(status.getText().equals("Not Parking")){
					Button activateButton = new Button("Enter");
					activateButton.setId("fullSubActivateButton" + subId.getText());
					String css = getClass().getResource("application.css").toExternalForm();
					activateButton.getStylesheets().clear();
					activateButton.getStylesheets().add(css);
					activateButton.setOnAction(e -> activateParkingFullSub(e, subId.getText()));
					activateButton.getStyleClass().add("activate-button");
					hb.getChildren().add(activateButton);	
				}
				
				if(status.getText().equals("Parking")){
					Button deActivateButton = new Button("Exit");
					deActivateButton.setId("fullSubDeactivateButton" + subId.getText());
					String css = getClass().getResource("application.css").toExternalForm();
					deActivateButton.getStylesheets().clear();
					deActivateButton.getStylesheets().add(css);
					deActivateButton.setOnAction(e -> deActivateParkingFullSub(e, subId.getText()));
					deActivateButton.getStyleClass().add("deactivate-button");
					hb.getChildren().add(deActivateButton);
				}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
    
    }

    private void deActivateParkingFullSub(ActionEvent e, String text) {
    	System.out.println("In the deActivateFullSub function");
	}

	private void activateParkingFullSub(ActionEvent e, String text) {
		System.out.println("In the activateFullSub function");
	}

	private void deActivateParkingSub(ActionEvent e, String subId) {
		// TODO Auto-generated method stub
		System.out.println("this is the subId " + subId);
	}

	private void activateParkingSub(ActionEvent e, String subId, String lotName) {
		// TODO Auto-generated method stub
		System.out.println(subId + " " + lotName);
	}

	private void deActivateParking(ActionEvent e) {
    	Button b = (Button) e.getSource();
		System.out.println("Okay " + b.getId().substring(16));
	}
    

	@FXML
    void loadComplaint(ActionEvent event) {
    	businessRoutineSubscriptionBorderPane.setVisible(false);
    	regularRoutineSubscriptionBorderPane.setVisible(false);
    	parkingReservationBorderPane.setVisible(false);
    	fullSubscriptionBorderPane.setVisible(false);
    	complaintBorderPane.setVisible(true);
    	viewProfileBorderPane.setVisible(false);
    	MyAccountBorderPane.setVisible(false);
    	ActualParkingBorderPane.setVisible(false);

    
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
    	if(SharedData.getInstance().getCurrentUser().getType().equals("b")){
    		Button businessBut = (Button) activationBusinessCodeTF.getScene().lookup("#businessRoutinelySubscriptionButton");
    		businessBut.getStyleClass().removeAll("pressedButton", "focus");
    		businessBut.getStyleClass().add("loginView-buttons");
    	}
    	MyAccountButton.getStyleClass().removeAll("pressedButton", "focus");
    	MyAccountButton.getStyleClass().add("loginView-buttons");
    	ActualParkingButton.getStyleClass().removeAll("pressedButton", "focus");
    	ActualParkingButton.getStyleClass().add("loginView-buttons");
    	ArrayList<String> parkingLotNames = new ArrayList<String>();
    	parkingLotNames.add("Ben Gurion");
    	parkingLotNames.add("Carmel");
    	parkingLotNames.add("Hadar");
    	parkingLotNames.add("Horev");
    	parkingLotNames.add("Hanita");
    	parkingLotNames.add("Neve Shaanan");
    	
    	myComboBoxComplaintParkingData.clear();     
    	for(int i = 0; i < parkingLotNames.size(); i++){
    		myComboBoxComplaintParkingData.add(parkingLotNames.get(i));
    	}
    	complaintComboBox.setItems(myComboBoxComplaintParkingData);
    }
    
    @FXML
    void loadMyAccount(ActionEvent event) {
      	businessRoutineSubscriptionBorderPane.setVisible(false);
    	regularRoutineSubscriptionBorderPane.setVisible(false);
    	parkingReservationBorderPane.setVisible(false);
    	fullSubscriptionBorderPane.setVisible(false);
    	complaintBorderPane.setVisible(false);
    	viewProfileBorderPane.setVisible(false);
    	MyAccountBorderPane.setVisible(true);
    	ActualParkingBorderPane.setVisible(false);


    	MyAccountButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	MyAccountButton.getStyleClass().add("pressedButton");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	regularRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	regularRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	fullSubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	fullSubscriptionButton.getStyleClass().add("loginView-buttons");
    	viewReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	viewReservationButton.getStyleClass().add("loginView-buttons");
    	if(SharedData.getInstance().getCurrentUser().getType().equals("b")){
    		Button businessBut = (Button) activationBusinessCodeTF.getScene().lookup("#businessRoutinelySubscriptionButton");
    		businessBut.getStyleClass().removeAll("pressedButton", "focus");
    		businessBut.getStyleClass().add("loginView-buttons");
    	}
    	complaintButton.getStyleClass().removeAll("pressedButton", "focus");
    	complaintButton.getStyleClass().add("loginView-buttons");
    	ActualParkingButton.getStyleClass().removeAll("pressedButton", "focus");
    	ActualParkingButton.getStyleClass().add("loginView-buttons");
    
    }
    @FXML
    void loadActualParking(ActionEvent event) { /** THIS IS THE OCCASIONAL PARKING 2ere be osama **/
    	businessRoutineSubscriptionBorderPane.setVisible(false);
    	regularRoutineSubscriptionBorderPane.setVisible(false);
    	parkingReservationBorderPane.setVisible(false);
    	fullSubscriptionBorderPane.setVisible(false);
    	complaintBorderPane.setVisible(false);
    	viewProfileBorderPane.setVisible(false);
    	MyAccountBorderPane.setVisible(false);
    	ActualParkingBorderPane.setVisible(true);
    	
    	
    	ActualParkingButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	ActualParkingButton.getStyleClass().add("pressedButton");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");
    	regularRoutinelySubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	regularRoutinelySubscriptionButton.getStyleClass().add("loginView-buttons");
    	fullSubscriptionButton.getStyleClass().removeAll("pressedButton", "focus");
    	fullSubscriptionButton.getStyleClass().add("loginView-buttons");
    	viewReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	viewReservationButton.getStyleClass().add("loginView-buttons");
    	if(SharedData.getInstance().getCurrentUser().getType().equals("b")){
    		Button businessBut = (Button) activationBusinessCodeTF.getScene().lookup("#businessRoutinelySubscriptionButton");
    		businessBut.getStyleClass().removeAll("pressedButton", "focus");
    		businessBut.getStyleClass().add("loginView-buttons");
    	}
    	complaintButton.getStyleClass().removeAll("pressedButton", "focus");
    	complaintButton.getStyleClass().add("loginView-buttons");
    	MyAccountButton.getStyleClass().removeAll("pressedButton", "focus");
    	MyAccountButton.getStyleClass().add("loginView-buttons");
    	
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
    	
       
    	ActualParkingLeavingMinuteComboBox.setItems(myComboBoxMinutesData);
        ActualParkingLeavingHourComboBox.setItems(myComboBoxHoursData);
       
    	
    	
    }



	public Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	
	private void cancel(ActionEvent e, String id) {
    
//		Button b = (Button) e.getSource();
//		System.out.println("Okay " + b.getId());
//		System.out.println("Okay " + id);
		
		JSONObject json = new JSONObject();
		JSONObject ret = new JSONObject();
		
		try {
			
			json.put("rid", id);
			json.put("cmd", "cancelReserve");
			ret = request(json, "ReservationController");
			System.out.println(ret);
			if(ret.getBoolean("result")){
				
				loadViewReservation(null);
				double refund = 0;
				refund = ret.getDouble("refund");
				updateBalance(refund);
			}
			
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	
	
	private void activateParking(ActionEvent e, String resId, String carId, String arriving, String leaving, String parkingLotName) {
    	
		System.out.println(resId);
		System.out.println(carId);
		System.out.println(arriving);
		System.out.println(leaving);
		System.out.println(parkingLotName);
		
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    try {
			
	    	Calendar arrivingCal = Calendar.getInstance();
			arrivingCal.setTime(sdf.parse(arriving));
			System.out.println(arrivingCal.getTime().getTime());
			System.out.println(arrivingCal.getTime());
			
			Calendar leavingCal = Calendar.getInstance();
			leavingCal.setTime(sdf.parse(leaving));
			System.out.println(leavingCal.getTime());
			
			if(parkingLotName.equals(SharedData.getInstance().getCurrentParkingLot().get_name())){
				
				
				
			}else{
				
				informationAlert.setTitle("Wrong Parking lot");
				informationAlert.setHeaderText(null);
				informationAlert.setContentText("The parking lot in the reservation dosn't correspond to the current parking lot. \n inorder to use this reservation please go to the parking lot \n writtin in the reservation.");
				informationAlert.showAndWait();			
			}
		
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		
	}
	
	@FXML
	void reserveParking(ActionEvent event) {
		String _carNumber = parkResCarNumberTF.getText();
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

			informationAlert.setTitle("Reservation Warning");
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
//			System.out.println(arriveCal.getTimeInMillis());
		} else {
			informationAlert.setTitle("Reservation Warning");
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
//			System.out.println(leaveCal.toString());
		} else {
			Instant instant2 = Instant.from(arriveLocalDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1));
			Date date2 = Date.from(instant2);
			leaveCal = toCalendar(date2);
			leaveCal.set(Calendar.HOUR, Integer.parseInt(_leaveHour));
			leaveCal.set(Calendar.MINUTE, Integer.parseInt(_leaveMinute));
//			System.out.println(leaveCal.getTime().toString());

		}

		if (_carNumber.equals("") || _lotName == null) {

			informationAlert.setTitle("Reservation Warning");
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
			long _now = Calendar.getInstance().getTime().getTime();
			System.out.println(_now + " and the start is" + _start + " and the end is " + _end);
			if(_now > _start || _now > _end || _start >= _end){
				informationAlert.setTitle("Reservation Warning");
				informationAlert.setHeaderText(null);
				informationAlert.setContentText("please adjust dates");
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
							JSONObject ret = request(json, "ReservationController");
	
	//						System.out.println(ret.getBoolean("result"));
							if (ret.getBoolean("result")) {
								System.out.println("Old balance is: " + SharedData.getInstance().getCurrentUser().getBalance());
								
								updateBalance((-1) * cost);
								System.out.println("New balance is: " + SharedData.getInstance().getCurrentUser().getBalance());
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

	
	@FXML
    void activateBusinessSubscriptionByCodeAndCar(ActionEvent event) {
    	
    	String _activationCode = activationBusinessCodeTF.getText();
    	String _carNumber = activationBusinessCarTF.getText();
    	String _username = SharedData.getInstance().getCurrentUser().getUsername();
    	
    	if(_activationCode.equals("") || _carNumber.equals("")){
    		informationAlert.setTitle("Reservation warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please fill all the above field to complete the reservation");
			informationAlert.showAndWait();
    	}else{
    	
	    	JSONObject json = new JSONObject();
	    	JSONObject ret = new JSONObject();
	    	try {
				json.put("carNumber", _carNumber);
				json.put("code",_activationCode);
				json.put("username", _username);
				
				json.put("cmd", "ActivateBusinessSubscription");
				
				ret = request(json, "SubscriptionController");
				System.out.println(ret);
				if(ret.getBoolean("result")){
					informationAlert.setTitle("Purchasing routine subscription Succeeded");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Purchasing routine subscription finished Successfully.");
					informationAlert.showAndWait();
				}else{
					if(ret != null){
						informationAlert.setTitle("Reservation warrning");
						informationAlert.setHeaderText(null);
						informationAlert.setContentText(ret.getString("info"));
						informationAlert.showAndWait();
					}
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
    	}
    }
	
	
	@FXML
	void buyRegularRoutineSubscription(ActionEvent event) {
		String _carNumber = regRouSubCarNumberTF.getText();
		String _lotName = regRouComboBox.getValue();
		String _routLeaveHour = regRouSubRoutineHourComboBox.getValue();
		String _routLeaveMinute = regRouSubRoutineMinuteComboBox.getValue();

		Calendar leaveCal = Calendar.getInstance();
		// System.out.println(leaveCal.toString() + "@@@@@@@@@@@@@@@@@@@");

		if (_routLeaveHour == null) {
			_routLeaveHour = "23";
		}

		if (_routLeaveMinute == null) {
			_routLeaveMinute = "59";
		}

		if (_carNumber.equals("") || (_lotName == null)) {
			informationAlert.setTitle("Reservation warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please fill all the above field to complete the reservation");
			informationAlert.showAndWait();

		} else {

			long _start = leaveCal.getTime().getTime();
			leaveCal.add(Calendar.MONTH, 1);
			// long _end = leaveCal.getTime().getTime();
			long _end = leaveCal.getTime().getTime();

			int routLeaveHourInt = Integer.parseInt(_routLeaveHour);
			int routLeaveMinuteInt = Integer.parseInt(_routLeaveMinute);

			String leaveHour = "";
			if (routLeaveMinuteInt < 10) {
				leaveHour = (routLeaveHourInt) + ":0" + (routLeaveMinuteInt);
			} else {
				leaveHour = (routLeaveHourInt) + ":" + (routLeaveMinuteInt);
			}

			if (routLeaveHourInt < 10) {
				leaveHour = "0" + leaveHour;
			}

			String _name = SharedData.getInstance().getCurrentUser().getUsername();

			JSONObject json = new JSONObject();
			try {

				confirmAlert.setTitle("Confirmation Dialog");
				confirmAlert.setContentText("Would you like to reserve this parking for " + Double.toString(
						SharedData.getInstance().getRoutineCost()) + "$ ?");

				Optional<ButtonType> result = confirmAlert.showAndWait();
				if (result.get() == ButtonType.OK) {
					if (SharedData.getInstance().getCurrentUser().getBalance() < 
							SharedData.getInstance().getRoutineCost()) {

						informationAlert.setTitle("Reservation warrning");
						informationAlert.setHeaderText(null);
						informationAlert.setContentText(
								"Insufficient fund, please make a deposit, you can do charge your wallet by clicking in Acount");
						informationAlert.showAndWait();

					} else {

						
						json.put("carNumber", _carNumber);
						json.put("lotName", _lotName);
						json.put("username", _name);
						json.put("leave", leaveHour);
						json.put("start", _start);
						json.put("end", _end);
						json.put("cmd", "RegularRoutineSubscription");

						JSONObject ret = request(json, "SubscriptionController");

						System.out.println(ret.getBoolean("result"));
						if (ret.getBoolean("result")) {
							System.out.println("Old balance is: " + SharedData.getInstance().getCurrentUser().getBalance());
//							MainController._currentUser.setBalance(MainController._currentUser.getBalance() - 240);
							updateBalance((-1) * SharedData.getInstance().getRoutineCost());
							System.out.println("New balance is: " + SharedData.getInstance().getCurrentUser().getBalance());

							informationAlert.setTitle("Depositing Succeeded");
							informationAlert.setHeaderText(null);
							informationAlert.setContentText("Purchasing routine subscription finished Successfully.");
							informationAlert.showAndWait();

						} else {
							
						}

					}
				} else {

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}

	 @FXML
	    void buyBusinessSubscription(ActionEvent event) {
	    	
			String _lotName = busRouLotNameComboBox.getValue();
//	    	String _lotName = "Carmel";
			String _routLeaveHour = busRouSubRoutineHourComboBox.getValue();
			String _routLeaveMinute = busRouSubRoutineMinuteComboBox.getValue();

//	    	String _routLeaveHour = "07";
//	    	String _routLeaveMinute = "44";
	    	
	    	Calendar leaveCal = Calendar.getInstance();
			// System.out.println(leaveCal.toString() + "@@@@@@@@@@@@@@@@@@@");

			if (_routLeaveHour == null) {
				_routLeaveHour = "23";
			}

			if (_routLeaveMinute == null) {
				_routLeaveMinute = "59";
			}

			if ((_lotName == null)) {
				informationAlert.setTitle("Reservation warrning");
				informationAlert.setHeaderText(null);
				informationAlert.setContentText("Please fill the parking lot field to complete the reservation");
				informationAlert.showAndWait();
				return;

			}
			
			int routLeaveHourInt = Integer.parseInt(_routLeaveHour);
			int routLeaveMinuteInt = Integer.parseInt(_routLeaveMinute);

			String leaveHour = "";
			if (routLeaveMinuteInt < 10) {
				leaveHour = (routLeaveHourInt) + ":0" + (routLeaveMinuteInt);
			} else {
				leaveHour = (routLeaveHourInt) + ":" + (routLeaveMinuteInt);
			}

			if (routLeaveHourInt < 10) {
				leaveHour = "0" + leaveHour;
			}

	    	
	    	
	    	JSONObject json = new JSONObject();
			
			
			long _start = leaveCal.getTime().getTime();
			leaveCal.add(Calendar.MONTH, 1);
			long _end = leaveCal.getTime().getTime();
	    	try {
				json.put("username", SharedData.getInstance().getCurrentUser().getUsername());
				json.put("company", SharedData.getInstance().getCurrentUser().getCompnay());
				json.put("lotName", SharedData.getInstance().getCurrentParkingLot().get_name());
				
				boolean flag1 = true;
				boolean flag2 = true;
				
				for(int i=0; i < businessAccountWorkersCounter+1; i++){
		    		
//		    		System.out.println(((TextField)(listOfAddedWorkersBusinessAcocuntVBOX.getScene().lookup("businessWorkerTF" + Integer.toString(i))));
//		    		System.out.println(((TextField)listOfAddedWorkersBusinessAcocuntVBOX.getChildren()).getText());
		    		
		    		TextField checkInput = (TextField)(listOfAddedWorkersBusinessAcocuntVBOX.getScene().lookup("#businessWorkerTF" + Integer.toString(i)));
//		    		System.out.println(temp.getText());
		    		if(checkInput.getText().equals("")){
		    			flag1 = false;
		    		}
		    	}
				
				if(flag1){
					if( ((businessAccountWorkersCounter+1) * SharedData.getInstance().getBusinessCost()) > SharedData.getInstance().getCurrentUser().getBalance() ){
						flag2 = false;
					}
				}
				else{
					informationAlert.setTitle("Business Subscription Warrning");
					informationAlert.setHeaderText(null);
					informationAlert.setContentText("Please fill all car number fields to complete the purchase");
					informationAlert.showAndWait();
					return;
				}
				
				if(flag2){
					String carsNumber = "";
					for(int i=0; i < businessAccountWorkersCounter+1; i++){
			    		
	//		    		System.out.println(((TextField)(listOfAddedWorkersBusinessAcocuntVBOX.getScene().lookup("businessWorkerTF" + Integer.toString(i))));
	//		    		System.out.println(((TextField)listOfAddedWorkersBusinessAcocuntVBOX.getChildren()).getText());
			    		
			    		TextField temp = (TextField)(listOfAddedWorkersBusinessAcocuntVBOX.getScene().lookup("#businessWorkerTF" + Integer.toString(i)));
	//		    		System.out.println(temp.getText());
			    		carsNumber += temp.getText() + ";";
			    	}
					json.put("cars", carsNumber);
					json.put("start", _start);
					json.put("end", _end);
					json.put("leaveHour", leaveHour);
					json.put("lotName", _lotName);
					json.put("cmd", "BusinessSubscription");
					
					JSONObject ret = new JSONObject();
					ret = request(json, "SubscriptionController");
					
					if(ret.getBoolean("result")){
						System.out.println(ret);
						
						informationAlert.setTitle("Business Subscription Succeeded");
						informationAlert.setHeaderText(null);
						informationAlert.setContentText("Business Subscription for" + Integer.toString(businessAccountWorkersCounter) + "employees finished Successfully."
								+ "\nPlease share this Activation key: " + ret.getString("code") + " to your employees");
						
						informationAlert.showAndWait();
						
						updateBalance((-1) * SharedData.getInstance().getBusinessCost() * (businessAccountWorkersCounter+1));
						
					}
					
				
	    		}else{
	    			informationAlert.setTitle("Business Subscription Warrning");
	    			informationAlert.setHeaderText(null);
	    			informationAlert.setContentText("Insufficient Funds");
	    			informationAlert.showAndWait();
	    			return;
	    		}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
	    	
	    }
	
	@FXML
	void buyfulSubFullSubscription(ActionEvent event) {
		
		String _carNumber = fulSubCarNumberTF.getText();
		Calendar leaveCal = Calendar.getInstance();

		if (_carNumber.equals("")) {
			informationAlert.setTitle("Full Subscription warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please fill all the above field to complete the Subscription");
			informationAlert.showAndWait();

		} else {

			long _start = leaveCal.getTime().getTime();
			leaveCal.add(Calendar.MONTH, 1);
			long _end = leaveCal.getTime().getTime();
					
			String _name = SharedData.getInstance().getCurrentUser().getUsername();

			JSONObject json = new JSONObject();
			try {

				confirmAlert.setTitle("Confirmation Dialog");
				confirmAlert.setContentText("Would you like to reserve this parking for " + Double.toString(SharedData.getInstance().getFullCost()) + "$ ?");

				Optional<ButtonType> result = confirmAlert.showAndWait();
				if (result.get() == ButtonType.OK) {
					
					if (SharedData.getInstance().getCurrentUser().getBalance() < SharedData.getInstance().getFullCost()) {

						informationAlert.setTitle("Full Subscription warrning");
						informationAlert.setHeaderText(null);
						informationAlert.setContentText("Insufficient fund, please make a deposit, you can do charge your wallet by clicking in Acount");
						informationAlert.showAndWait();

					} else {

						
						json.put("carNumber", _carNumber);
						json.put("username", _name);
						json.put("start", _start);
						json.put("end", _end);
						json.put("cmd", "FullSubscription");

						JSONObject ret = request(json, "SubscriptionController");

						System.out.println(ret.getBoolean("result"));
						if (ret.getBoolean("result")) {
							System.out.println("Old balance is: " + SharedData.getInstance().getCurrentUser().getBalance());
//							MainController._currentUser.setBalance(MainController._currentUser.getBalance() - 288);
							updateBalance((-1) * SharedData.getInstance().getFullCost());
							System.out.println("New balance is: " + SharedData.getInstance().getCurrentUser().getBalance());

							informationAlert.setTitle("Depositing Succeeded");
							informationAlert.setHeaderText(null);
							informationAlert.setContentText("Purchasing full subscription finished Successfully.");
							informationAlert.showAndWait();

						} 

					}
				} 
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	}

	@FXML
	void signOut(ActionEvent event) {

//		 getReserves();

		// System.out.println(getReserves());
		SharedData.getInstance().setCurrentUser(null);

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
	 * function that get as parameter a change of the balance, change the
	 * balance of the current user in the DB
	 */
	Boolean updateBalance(double cost) {

		JSONObject json = new JSONObject(), ret = new JSONObject();

		try {

			json.put("username", SharedData.getInstance().getCurrentUser().getUsername());
			json.put("change", cost);
			json.put("cmd", "balance");
			ret = request(json, "UpdateUserInfo");

			if (ret.getBoolean("result")) {
				SharedData.getInstance().getCurrentUser().setBalance
				(SharedData.getInstance().getCurrentUser().getBalance() + cost);
				balanceOnTopOfLogIn.setText(Double.toString(SharedData.
						getInstance().getCurrentUser().getBalance()));
				return true;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return false;

	}

	/*
	 * function that return a JSONArray for all the reserves of a specific user
	 * name
	 */
	JSONObject getReserves() {

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

	@FXML
	void makeDeposit(ActionEvent event) {

		int _creditID = 0;
		int _change = 0;
		try {
			_creditID = Integer.parseInt(CreditCardIDTF.getText());
			_change = Integer.parseInt(AmountTF.getText());
		} catch (NumberFormatException e) {

			informationAlert.setTitle("Reservation warrning");
			informationAlert.setHeaderText(null);
			informationAlert
					.setContentText("Dear friend, Please make sure to fill both the fields with numeric values");
			informationAlert.showAndWait();

		}

		System.out.println(_creditID + "  " + _change);

		Boolean ret = updateBalance(_change);

		if (ret) {

			informationAlert.setTitle("Depositing Succeeded");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Your Balance was updated Successfully.");
			informationAlert.showAndWait();

		} else {

		}

	}
	


	@FXML
	void reserveActualParking(ActionEvent event) {

		String _carNumber = ActualParkingNumberTF.getText();
		String _lotName = SharedData.getInstance().getCurrentParkingLot().get_name();
		String _ocLeaveHour = ActualParkingLeavingHourComboBox.getValue();
		String _ocLeaveMinute = ActualParkingLeavingMinuteComboBox.getValue();
		LocalDate leaveLocalDate = ActualParkingLeavingDateDP.getValue();
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

			String _name = SharedData.getInstance().getCurrentUser().getUsername();

			if(_start > _end){
				informationAlert.setTitle("Reservation Warning");
				informationAlert.setHeaderText(null);
				informationAlert.setContentText("please adjust dates");
				informationAlert.showAndWait();
			}
			else{
				JSONObject json = new JSONObject();
				try {
					confirmAlert.setTitle("Confirmation Dialog");
					confirmAlert.setContentText("Would you like to reserve this parking for " + cost + "$ ?");
	
					Optional<ButtonType> result = confirmAlert.showAndWait();
					if (result.get() == ButtonType.OK) {
	
							json.put("carNumber", _carNumber);
							json.put("lotName", _lotName);
							json.put("username", _name);
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
								
								loadViewReservation(null);
								
						}
					} else {
	
					}
	
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@FXML // make complaint but AL OS decided to name it makeSend for mysterious reasons
	void makeSend(ActionEvent event) {

		String _carNumber = ComplaintCarNumberTF.getText();
		String _lotName = complaintComboBox.getValue();
		String _orderId = ComplaintReservationIdTF.getText();
		String _complaint = ComplaintTA.getText();
		Calendar _cal = Calendar.getInstance();
		

		if (_carNumber.equals("") || (_lotName == null) || _orderId.equals("") || _complaint.equals("")) {
			informationAlert.setTitle("complaint warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText("Please fill all the above field to complete your complaint");
			informationAlert.showAndWait();

		} else {
			
			String _name = SharedData.getInstance().getCurrentUser().getUsername();
			int _orderIdInt=Integer.parseInt(_orderId);
			JSONObject json = new JSONObject();
			try {
				long _date = _cal.getTime().getTime();
		
				json.put("carNumber", _carNumber);
				json.put("lotName", _lotName);
				json.put("username", _name);
				json.put("orderID", _orderIdInt);
				json.put("content", _complaint);
				json.put("date", _date);
				json.put("cmd", "makeComplaint");

				JSONObject ret = request(json, "CustomerService");

				System.out.println(ret.getBoolean("result"));
				if (ret.getBoolean("result")) {
					confirmAlert.setTitle("Confirmation Dialog");
					confirmAlert.setContentText("Dear "+_name+", your complaint is in process, we'll update you ASAP.");
					confirmAlert.showAndWait();
				}
				else{
					informationAlert.setTitle("Complaint Error");
			    	informationAlert.setHeaderText(null);
			    	informationAlert.setContentText("Failed to send complaint, try again later");
			    	informationAlert.showAndWait();
				} 
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
	
		

	}

	JSONObject request(JSONObject json, String servletName) {
		HttpURLConnection connection = null;
		try {
			// Create connection
			URL url = new URL("http://" + SharedData.getInstance().getIP() + ":" + SharedData.getInstance().getPORT() + "/server/" + servletName);
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
