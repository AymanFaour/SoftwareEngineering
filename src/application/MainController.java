/**
 * Sample Skeleton for 'MainView.fxml' Controller Class
 */

package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
	static User _currentUser;
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
    
    @FXML
    void continueasAGuest(ActionEvent event) {
    	System.out.println("HELLOOOOO FROM Guest");
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
        			informationAlert.setTitle("Sign in Error");
        	    	informationAlert.setHeaderText(null);
        	    	informationAlert.setContentText("Username or Email must be unique");
        	    	informationAlert.showAndWait();
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
    	
//    	System.out.println("registeration succeseded");
    	
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
        			
        			
        			_currentUser = new User(_usernm, _email, _passwrd, _firstname, _lastname, _type, _balance, _company);
        			
        			SignInCallBack();
        		}else{
        			informationAlert.setTitle("Sign in Error");
        	    	informationAlert.setHeaderText(null);
        	    	informationAlert.setContentText("Username or Password uncorrect");
        	    	informationAlert.showAndWait();
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
		logInController.setTopOfLogInView(_username, "2000");
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


