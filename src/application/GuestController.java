/**
 * Sample Skeleton for 'GuestView.fxml' Controller Class
 */

package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GuestController {

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
    	// TODO: desturct the _currentUser and to change the view
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
    	System.out.println("pressed pressed");
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

}
