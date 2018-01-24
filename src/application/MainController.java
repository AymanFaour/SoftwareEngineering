/**
 * Sample Skeleton for 'MainView.fxml' Controller Class
 */

package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.SharedData;
import model.User;

//package com.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainController {
	
	
	static String result;
	static String PORT;
	static String IP;
	
	Alert informationAlert = new Alert(AlertType.INFORMATION);
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	
	
	static void initialize(String h, String p){
		System.out.println("in the initializeer");
		//client = new Client(h, p);
		IP = h;
		PORT = p;
		System.out.println("after the declareatio");
	}
    @FXML
    private CheckBox BusinessCheckBox;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private  Button signiInButton;

    @FXML
    private TextField emailTextField;
    
    @FXML
    private TextField usernameRegisterationTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField companyNameTextField;

    @FXML
    private CheckBox systemWorkerCheckBox;

    @FXML
    private TextField workerIdTextField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField passwordRegisterationPasswordField;

    @FXML
    private Button guestButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label workedIdLabel;
    @FXML // fx:id="TrackReservationButton"
    private Button TrackReservationButton; // Value injected by FXMLLoader

    public static int TrakCheck=0;
    
    @FXML
    void continueasAGuest(ActionEvent event) {

    	ObservableList<String> setted = FXCollections.observableArrayList();
    	ObservableList<String> setted2 = FXCollections.observableArrayList();
    	
    	setted.clear();
    	for(Integer i = 0; i < 24; i++){
    		if(i < 10 ){
    			setted.add("0" + i.toString());
    		}
    		else
    			setted.add(i.toString());
    	}
    	
    	setted2.clear();
    	for(Integer i = 0; i < 60; i++){
    		if(i < 10 ){
    			setted2.add("0" + i.toString());
    		}
    		else
    			setted2.add(i.toString());
    	}
    	
    	
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GuestView.fxml"));
        Parent root;
		try {
			root = loader.load();
	        guestButton.getScene().setRoot(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        GuestController gu = loader.getController();
        gu.setMyComboBoxHoursData(setted);
        gu.setMyComboBoxMinutesData(setted2);
    }
    
    @FXML
    void registeration(ActionEvent event) { 
//    	System.out.println("HELLOOOOO FROM Registeration");
//    	System.out.println("We have new client, you are welcome");
    	
    	String _firstname = firstNameTextField.getText();
    	String _lastname = lastNameTextField.getText();
    	String _email = emailTextField.getText();
    	String _username = usernameRegisterationTextField.getText();
    	String _password = passwordRegisterationPasswordField.getText();
    	
    	
    	if(_firstname.equals("") || _lastname.equals("") || _email.equals("") ||
    			_username.equals("") || _password.equals("")){
    		
    		informationAlert.setTitle("Sign up warrning");
    		informationAlert.setHeaderText(null);
    		informationAlert.setContentText("Please fill all the above field to complete the registeration");
    		informationAlert.showAndWait();
    		
    	}else{
    		
    		try {
    			JSONObject toSend = new JSONObject();
    			toSend.put("firstName", _firstname);
    			toSend.put("lastName", _lastname);
    			toSend.put("email", _email);
    			toSend.put("username", _username);
    			toSend.put("password", _password);
    			toSend.put("balance", 0);
    			
    	    	if(BusinessCheckBox.isSelected()){
    	    		toSend.put("type", "b");
    	    		toSend.put("company", companyNameTextField.getText());
    	    	}
    	    	else{
    	    		toSend.put("type", "r");
    	    		toSend.put("company", "None");
    	    	}
    	    	
    	    	JSONObject ret = request(toSend, "Signup");
//        		System.out.println(ret.toString());
        		
        		if(ret.getBoolean("result")){
        			confirmAlert.setTitle("Sign up successeded");
        			confirmAlert.setHeaderText(null);
        			confirmAlert.setContentText("Registeration success, thanks for choosing us!!");
        			confirmAlert.showAndWait();
        		}else{
        			registerationFailed();
        		}
    			
    		} catch (JSONException | NullPointerException e1) {
    			
    			e1.printStackTrace();
    		}
    		
    	}
    	
    }
    
    void registerationFailed(){
    	informationAlert.setTitle("Sign up ");
    	informationAlert.setHeaderText(null);
    	informationAlert.setContentText("Username and Email must ne unique");
    	informationAlert.showAndWait();
    }
    
    void registerationSucceded(){	
    }
    
    @FXML
    void signInButton(ActionEvent event) throws JSONException {
//    	client.request();

//    	if(client == null){
//    		System.out.println("No Connection to the Server");
//    	}
    	
//    	System.out.println("TEST@@@@@@@@@@@@@@@");
    	String _username = usernameTextField.getText();
    	String _password = passwordPasswordField.getText();
    	
    	
    	if(_username.equals("") || _password.equals("")){
    		System.out.println("missing fields");
    		
    		informationAlert.setTitle("Sign in warrning");
    		informationAlert.setHeaderText(null);
    		informationAlert.setContentText("Please fill both the username and password.");
    		informationAlert.showAndWait();
    		
  
    	}else{
	    		if(systemWorkerCheckBox.isSelected() == false)
	    		{
	        		JSONObject json = new JSONObject().put("username", _username).put("password", _password);
	        		JSONObject ret = request(json, "Login");
	        		System.out.println(ret.toString());
	        		
	        		if(ret.getBoolean("result")){
	        			JSONObject temp = ret.getJSONObject("userInfo");
	        			String _firstname = temp.getString("firstName");
	        			String _lastname = temp.getString("lastName");
	        			String _passwrd = temp.getString("password");
	        			String _type = temp.getString("type");
	        			String _email = temp.getString("email");
	        			String _usernm = temp.getString("username");
	        			int _balance = temp.getInt("balance");
	        			String _company = temp.getString("company");
	        			
	        			
	        			SharedData.getInstance().setCurrentUser(new User(_usernm, _email, _passwrd, _firstname,
	        					_lastname, _type, _balance, _company));
	        			
	        			SignInCallBack();
	        		}else{
	        			SignInFailed();
	        		}
	    		}else{
	    			
	    		}
    	}
    	
        		   
//        		client.request("login",new JSONObject().put("username", _username).put("password", _password).toString());
    			

//    	System.out.println(_username);
//    	System.out.println(_password);
//    	
    }
     
    void SignInCallBack(){
    	String _username = usernameTextField.getText();
    	Scene currentScene = signiInButton.getScene();
    	
    	Parent mainLayout = null;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("LogInView.fxml"));
		try {
			mainLayout = loader.load();
		} catch (IOException | NullPointerException e) {
			
			e.printStackTrace();
		}
		LogInController logInController = loader.getController();
		logInController.setWelcome("Welcome to CPS");
		logInController.setTopOfLogInView(_username, Double.toString(SharedData.getInstance().getCurrentUser().getBalance()));
		
		if(SharedData.getInstance().getCurrentUser().getType().equals("b")){
			Button businessButton = new Button("Business Routinely Subscription");
			logInController.setBusinessButton(businessButton);
			String css = getClass().getResource("application.css").toExternalForm();
			businessButton.getStylesheets().clear();
			businessButton.getStylesheets().add(css);
			businessButton.getStyleClass().add("loginView-buttons");
			businessButton.setStyle("-fx-pref-width:200px; -fx-pref-height:40px;");
			businessButton.setId("businessRoutinelySubscriptionButton");
			businessButton.setOnAction(e-> {
				logInController.loadBusinessRoutinelySubscription(null);
			});
		}
		
		Scene scene = new Scene(mainLayout);
    	
    	Stage stage = (Stage) currentScene.getWindow();
		stage.setScene(scene);
		
		
		
    }

    void SignInFailed(){
    	
    	informationAlert.setTitle("Sign in Error");
    	informationAlert.setHeaderText(null);
    	informationAlert.setContentText("Username or Password uncorrect");
    	informationAlert.showAndWait();

    	
    }
    
    @FXML
    void BusinessCheckBoxEventHandler(ActionEvent event) {
    	CheckBox cb = (CheckBox) event.getSource();
		Scene currentScene = cb.getScene();
		TextField businessNameTf = (TextField) currentScene.lookup("#companyNameTextField");
    	if(cb.isSelected()){
    		businessNameTf.setVisible(true);
    	}
    	else{
    		businessNameTf.setVisible(false);
    	}
    }
    @FXML
    void TrakReservation(ActionEvent event) throws IOException 
    {
    	   
    	 	TrakCheck=0;
    		Stage popupwindow=new Stage();
    		      
    		popupwindow.initModality(Modality.APPLICATION_MODAL);
    		popupwindow.setTitle("Track Reservation");
   
    		Label reservationLabel= new Label("Reservation Id:");
    		reservationLabel.setStyle("-fx-pref-width: 80px");

    		Label carNumberLabel = new Label  ("Car Number:");
    		carNumberLabel.setStyle("-fx-pref-width: 80px");
    		TextField reservationTF= new TextField();     
    		TextField carNumberTF= new TextField();    
    		    
    		
    		Button trackbutton= new Button("Track");    
    		     
    		
    		
    		
    		     

    		HBox layout= new HBox(10);
    		HBox layout2= new HBox(10);
    		VBox vB=new VBox();
    		
    		
    		vB.setPadding(new Insets(10, 10, 10, 10));   
    		
    		layout.getChildren().addAll(reservationLabel,reservationTF);
    		layout2.getChildren().addAll(carNumberLabel,carNumberTF);
    		  
    		vB.getChildren().addAll(layout,layout2,trackbutton);
    		//layout.setAlignment(Pos.CENTER);
    		
    		trackbutton.setOnAction(e -> {
				try {
					trackbutton(vB);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
    		
    		Scene scene1= new Scene(vB, 300, 250);
    		      
    		popupwindow.setScene(scene1);
    		      
    		popupwindow.showAndWait();
    }
    
    
    void trackbutton(VBox vb) throws IOException 
    {
    	if(TrakCheck==0) {
    		TextArea  trakTA=new TextArea();
    		vb.getChildren().add(trakTA);
    		TrakCheck=1;
    		trakTA.setEditable(false);
    	}
    }
    	 
    @FXML
    void WorkerIDCheckBoxEventHandler(ActionEvent event) {
    	CheckBox cb = (CheckBox) event.getSource();
		Scene currentScene = cb.getScene();
		Label workerIdl = (Label) currentScene.lookup("#workedIdLabel");
		TextField workerIdTf = (TextField) currentScene.lookup("#workerIdTextField");
    	if(cb.isSelected()){
    		workerIdTf.setVisible(true);
    		workerIdl.setVisible(true);
    	}
    	else{
    		workerIdTf.setVisible(false);
    		workerIdl.setVisible(false);
    	}
    }
    
    
    JSONObject request(JSONObject json, String servletName){
    	HttpURLConnection connection = null;
		try {
		    //Create connection
		    URL url = new URL("http://" + IP + ":" + PORT + "/server/" + servletName);
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


