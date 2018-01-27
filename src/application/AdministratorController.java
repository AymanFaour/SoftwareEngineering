package application;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ParkingLot;
import model.ParkingSituation;
import model.SharedData;

public class AdministratorController {

    @FXML
    private Button currentSituationButton;

    @FXML
    private Label welcomeBanner;

    @FXML
    private BorderPane changePricesRequestsBorderPane;

    @FXML
    private ComboBox<String> parkLotNameComboBox;

    @FXML
    private DatePicker toDateDP;

    @FXML
    private Button getReportButton;

    @FXML
    private Button signOutButton;

    @FXML
    private BorderPane reportsBorderPane;

    @FXML
    private DatePicker fromDateDP;

    @FXML
    private Button reportsButton;

    @FXML
    private Text textInTopOfLogIn;

    @FXML
    private ComboBox<String> ReportComboBox;

    @FXML
    private Button changePricesRequestsButton;
    
    @FXML // fx:id="changePricesRequestVbox"
    private VBox changePricesRequestVbox; // Value injected by FXMLLoader

    
    private ObservableList<String> myComboBoxData = FXCollections.observableArrayList();
    private ObservableList<String> myComboxReport = FXCollections.observableArrayList();
    private Stage popupwindow;

    
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
    void getCurrentSituation(ActionEvent event) {

    	popupwindow=new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Current Situation");
		
		VBox vB=new VBox();
		vB.setPadding(new Insets(10, 10, 10, 10));
		
		vB.getChildren().clear();
		ParkingLot pl = SharedData.getInstance().getCurrentParkingLot();
		ParkingSituation ps = new ParkingSituation(pl.getHeight(), pl.getWidth());
		ArrayList<GridPane> gpAL = ps.getGridLayer(pl);
		for(int i = 0 ; i < gpAL.size(); i++){
			Label layerNumber = new Label("Layer #" + Integer.toString(i+1) +":");
			vB.getChildren().add(layerNumber);
			vB.getChildren().add(gpAL.get(i));
		}
		
		
		Scene scene1= new Scene(vB, 520, 520);
		      
		popupwindow.setScene(scene1);
		

		popupwindow.showAndWait();
//
//    	currentSituationCallBack(event);
    }
 
    
  
  
    
    @FXML
    void getReport(ActionEvent event) {

    }

    @FXML
    void loadReporsBorderPane(ActionEvent event) {
    	reportsBorderPane.setVisible(true);
    	changePricesRequestsBorderPane.setVisible(false); 
 

    	reportsButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	reportsButton.getStyleClass().add("pressedButton");
    	changePricesRequestsButton.getStyleClass().removeAll("pressedButton", "focus");
    	changePricesRequestsButton.getStyleClass().add("loginView-buttons");
    	
    	ArrayList<ParkingLot> parkingLotNames = SharedData.getInstance().getParkingLotsAL();
        
    	myComboBoxData.clear();
    	for(int i = 0; i < parkingLotNames.size(); i++){
    		myComboBoxData.add(parkingLotNames.get(i).get_name());
    	}
    	
    	parkLotNameComboBox.setItems(myComboBoxData);
    	ArrayList<String> Reports = new ArrayList<String>();
    	Reports.add("Reservations");
    	Reports.add("Complaints");
    	Reports.add("Disabled Parking Spots");
    	Reports.add("Regular Routinely Subscriptions");
    	Reports.add("Business Routinely Subscriptions");
    	Reports.add("Full Subscriptions");
    	myComboxReport.clear();     
    	for(int i = 0; i < Reports.size(); i++){
    		myComboxReport.add(Reports.get(i));
    	}
    	
    	ReportComboBox.setItems(myComboxReport);
    	
    }

    @FXML
    void loadChangePricesRequestsBorderPane(ActionEvent event) {
    	reportsBorderPane.setVisible(false);
    	changePricesRequestsBorderPane.setVisible(true); 
 

    	changePricesRequestsButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	changePricesRequestsButton.getStyleClass().add("pressedButton");
    	reportsButton.getStyleClass().removeAll("pressedButton", "focus");
    	reportsButton.getStyleClass().add("loginView-buttons");
    	
    	//int length = changePricesRequestVbox.getChildren().size();
    	//changePricesRequestVbox.getChildren().remove(0, length);
		
    	 Label  occasionalReservationPrice= new Label("10");
    	 occasionalReservationPrice.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
 		Label RegularReservationPrice = new Label("10");
 		RegularReservationPrice.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
 		Label  routinelySubscriptionHours = new Label("10");
 		routinelySubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
 		Label   businessSubscriptionHours = new Label("10");
 		businessSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
 		Label   fullSubscriptionHours = new Label("10");
 		fullSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
 		
 		Label  NewOccasionalReservationPrice= new Label("15");
 		NewOccasionalReservationPrice.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		Label newRegularReservationPrice = new Label("15");
		newRegularReservationPrice.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		Label  newRoutinelySubscriptionHours = new Label("15");
		newRoutinelySubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		Label   newbusinessSubscriptionHours = new Label("15");
		newbusinessSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		Label   newFullSubscriptionHours = new Label("15");
		newFullSubscriptionHours.setStyle("-fx-pref-width: 90; -fx-padding: 3.5 0 0 0");
		
    	HBox oldHbox = new HBox();
    	oldHbox.getChildren().add(occasionalReservationPrice);
    	oldHbox.getChildren().add(RegularReservationPrice);
    	oldHbox.getChildren().add(routinelySubscriptionHours);
    	oldHbox.getChildren().add(businessSubscriptionHours);
    	oldHbox.getChildren().add(fullSubscriptionHours);
    	oldHbox.setStyle("-fx-background-color: red");
    	oldHbox.getStyleClass().add("hbox");
    
    	Button approve=new Button("Approve");
		Button refuse=new Button("Refuse");
				
		HBox newHbox = new HBox();
		newHbox.getChildren().add(NewOccasionalReservationPrice);
		newHbox.getChildren().add(newRegularReservationPrice);
		newHbox.getChildren().add(newRoutinelySubscriptionHours);
		newHbox.getChildren().add(newFullSubscriptionHours);
		newHbox.getChildren().add(newbusinessSubscriptionHours);
		newHbox.getChildren().add(approve);
		newHbox.getChildren().add(refuse);
		oldHbox.setStyle("-fx-background-color: green");
		
		
		
		approve.setId("approveButton" /*+ resId.getText()*/);
		String css = getClass().getResource("application.css").toExternalForm();
		approve.getStylesheets().clear();
		approve.getStylesheets().add(css);
		//approve.setOnAction(e -> approve(e,requestId.getText());
		approve.getStyleClass().add("approve-button");
		approve.setStyle("-fx-color: #d0e6f8;");
	
		refuse.setId("refuseButton" /*+ resId.getText()*/);
		refuse.getStylesheets().clear();
		refuse.getStylesheets().add(css);
		//approve.setOnAction(e -> refuse(e, requestId.getText());
		refuse.getStyleClass().add("approve-button");
		refuse.setStyle("-fx-color: #8d2626;");
	
		newHbox.setStyle("-fx-background-color:#98FB98;-fx-border-style: solid inside;-fx-pref-height: 30;-fx-border-width: 0 0 2 0;"
				+ "-fx-border-color: #d0e6f8; -fx-padding: 1.5 0 0 5;");
		oldHbox.setStyle("-fx-background-color: #FF4500;-fx-border-style: solid inside;-fx-pref-height: 30;-fx-border-width: 0 0 2 0;"
				+ "-fx-border-color: #d0e6f8; -fx-padding: 1.5 0 0 5;");
		changePricesRequestVbox.getChildren().add(oldHbox);
		changePricesRequestVbox.getChildren().add(newHbox);

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
