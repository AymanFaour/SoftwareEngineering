/**
 * Sample Skeleton for 'SystemWrokerView.fxml' Controller Class
 */

package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SystemWorkerController {

    @FXML // fx:id="balanceOnTopOfLogIn"
    private Text balanceOnTopOfLogIn; // Value injected by FXMLLoader

    @FXML // fx:id="parkResReserveParkingButton11"
    private Button parkResReserveParkingButton11; // Value injected by FXMLLoader

    @FXML // fx:id="welcomeBanner"
    private Label welcomeBanner; // Value injected by FXMLLoader

    @FXML // fx:id="parkResCarNumberTF11"
    private TextField parkResCarNumberTF11; // Value injected by FXMLLoader

    @FXML // fx:id="parkResComboBox1"
    private ComboBox<?> parkResComboBox1; // Value injected by FXMLLoader

    @FXML // fx:id="parkResCarNumberTF12"
    private TextField parkResCarNumberTF12; // Value injected by FXMLLoader

    @FXML // fx:id="textInTopOfLogIn"
    private Text textInTopOfLogIn; // Value injected by FXMLLoader

    @FXML // fx:id="parkResCarNumberTF1"
    private TextField parkResCarNumberTF1; // Value injected by FXMLLoader

    @FXML // fx:id="signOutButton"
    private Button signOutButton; // Value injected by FXMLLoader

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

    }

 
}
