/**
 * Sample Skeleton for 'CustomerServiceView.fxml' Controller Class
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CpsMailBox;
import model.SharedData;

public class CustomerServiceController {
	
	
	Alert informationAlert = new Alert(AlertType.INFORMATION);
	Alert errorAlert = new Alert(AlertType.ERROR);
	Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
	
	


    @FXML // fx:id="ParkingReservationCustomerServiceBorderPane"
    private BorderPane ParkingReservationCustomerServiceBorderPane; // Value injected by FXMLLoader

    @FXML // fx:id="welcomeBanner"
    private Label welcomeBanner; // Value injected by FXMLLoader

    @FXML // fx:id="HandlingComplaintsCustomerServiceBorderPane"
    private BorderPane HandlingComplaintsCustomerServiceBorderPane; // Value injected by FXMLLoader

   
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
    
    @FXML // fx:id="CusSerParkingReservationCreditCardIdTF"
    private TextField CusSerParkingReservationCreditCardIdTF; // Value injected by FXMLLoader

    @FXML // fx:id="complaintsListVBox"
    private VBox complaintsListVBox; // Value injected by FXMLLoader

    
    private ObservableList<String> myComboBoxHoursData = FXCollections.observableArrayList();
    private ObservableList<String> myComboBoxMinutesData = FXCollections.observableArrayList();
    private Stage popupwindow;

    @FXML
    void signOut(ActionEvent event) {
		SharedData.getInstance().setCurrentSystemUser(null);

		Scene currentScene = signOutButton.getScene();
		Parent mainLayout = null;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("ParkingWorkerView.fxml"));
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
    void CustomerServiceParkingReservation(ActionEvent event) {

		String _carNumber = CusSerParkingReservationCarNumberTF.getText();
		String _CrediCardId = CusSerParkingReservationCreditCardIdTF.getText();
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

		if (_carNumber.equals("") || _lotName == null||_CrediCardId.equals("")) {

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
    	
    	
    	CusSerparkResLeavingHourComboBox.setItems(myComboBoxHoursData);
    	CusSerparkResLeavingMinuteComboBox.setItems(myComboBoxMinutesData);
    	CusSerparkResArrivingHourComboBox.setItems(myComboBoxHoursData);
    	CusSerparkResArrivingMinuteComboBox.setItems(myComboBoxMinutesData);

    }
	

    void HandlingComplaintsPopUp(ActionEvent event) throws IOException 
    {
    		popupwindow=new Stage();
    		      
    		popupwindow.initModality(Modality.APPLICATION_MODAL);
    		popupwindow.setTitle("Handling Complaints");
   
    		Label reservationLabel= new Label("Reservation Id:");
    		reservationLabel.setStyle("-fx-pref-width: 80px");

    		Label carNumberLabel = new Label  ("Car Number:");
    		carNumberLabel.setStyle("-fx-pref-width: 80px");
    		
    		Label RefundLable= new Label("Refund :");
    		RefundLable.setStyle("-fx-pref-width: 60px");
    		TextField RefundTF=new TextField();
    		
    		Text response= new Text("Response :");
    		Text complaint= new Text("Complaint :");
    		Text complaintText =new Text("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    		TextArea responseText =new TextArea();
    		ScrollPane complaintScrollPane =new ScrollPane();
    		ScrollPane responseScrollPane =new ScrollPane();
    		
    		complaintText.setWrappingWidth(480);
    		complaintText.setStyle("-fx-pref-width: 480px");
    		complaintText.setStyle("-fx-pref-height: 200px");
    		
    		//complaintText.setEditable(false);
    		complaintScrollPane.setContent(complaintText);
    		complaintScrollPane.setStyle("-fx-pref-width: 480px");
    		complaintScrollPane.setStyle("-fx-pref-height: 200px; -fx-padding: 0 0 0 5;");
    		
    		responseText.setWrapText(true);
    		responseText.setStyle("-fx-pref-width: 480px");
    		responseText.setStyle("-fx-pref-height: 200px");
    		
    		responseScrollPane.setStyle("-fx-pref-width: 480px");
    		responseScrollPane.setStyle("-fx-pref-height: 200px");
    		responseScrollPane.setContent(responseText);
    		
    	
    		Button sendButton= new Button("Respond");
    		HBox layout= new HBox();
    		HBox layout2= new HBox();
    		
    		VBox vB=new VBox();
    		vB.setPadding(new Insets(10, 10, 10, 10));   
    		
    		layout.setStyle("-fx-padding: 5 5 0 0;");
    		layout.getChildren().addAll(RefundLable,RefundTF);
    		layout2.getChildren().add(sendButton);
    		layout2.setStyle("-fx-padding: 5 5 0 0;");
    		vB.getChildren().addAll(complaint,complaintScrollPane,response,responseScrollPane,layout,layout2);		
    		sendButton.setOnAction(e -> {
				try {
		    		sendTheComplaintResponse(vB);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
    		
    		
    		Scene scene1= new Scene(vB, 520, 520);
    		      
    		popupwindow.setScene(scene1);
    		      
    		popupwindow.showAndWait();
    }
    
    void sendTheComplaintResponse(VBox vb) throws IOException  {
    	int checkRefund=0;
    	String refundTF=((TextField)(((HBox)vb.getChildren().get(4)).getChildren().get(1))).getText();	
    	String response=((TextArea)((ScrollPane)vb.getChildren().get(3)).getContent()).getText();
    	try {
    		 Double.parseDouble(refundTF);
    	}
    	catch (Exception e) {
    		checkRefund=1;
    	}
    	System.out.println(response);
    	System.out.println(refundTF);
    	if (refundTF.equals("") || response.equals("") ) {

			informationAlert.setTitle("warning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText(
					"Please fill the  response and refund fields.");
			informationAlert.showAndWait();
			return;

		}
    	
    	if (checkRefund==1||refundTF.indexOf('-')!=-1) {

			informationAlert.setTitle(" warrning");
			informationAlert.setHeaderText(null);
			informationAlert.setContentText(
					"refund value must contain only numbers.");
			informationAlert.showAndWait();
			return;

		}
		CpsMailBox mail = new CpsMailBox("cps.team4@gmail.com", "200200200","cps.client4@gmail.com");
		mail.sendMail(response,refundTF);
    	this.popupwindow.close();
    	
    	
    }
    
    


    @FXML
    void loadHandlingComplaints(ActionEvent event) {
    	HandlingComplaintsCustomerServiceBorderPane.setVisible(true);
    	ParkingReservationCustomerServiceBorderPane.setVisible(false);

    	HandlingComplaintsButton.getStyleClass().removeAll("loginView-buttons", "focus");
    	HandlingComplaintsButton.getStyleClass().add("pressedButton");
    	parkingReservationButton.getStyleClass().removeAll("pressedButton", "focus");
    	parkingReservationButton.getStyleClass().add("loginView-buttons");

		int length = complaintsListVBox.getChildren().size();
		complaintsListVBox.getChildren().remove(0, length);
		
		JSONObject json = new JSONObject();
		JSONObject ret = new JSONObject();
		try {
			json.put("cmd", "getComplaints");
			ret = request(json, "CustomerService");
			System.out.println(ret.toString());
			if(ret.getBoolean("result")){
				JSONArray ja = ret.getJSONArray("complaints");
				for(int i = 0; i < ja.length(); i++){
	        	    Label resId = new Label(Integer.toString(((JSONObject) ja.get(i)).getInt("complaintID")));
	        	    resId.setStyle("-fx-pref-width: 50; -fx-padding: 3.5 0 0 0");
	        		Label carNumber = new Label(((JSONObject) ja.get(i)).getString("carNumber"));
	        		carNumber.setStyle("-fx-pref-width: 80; -fx-padding: 3.5 0 0 0");
	        		Label lotName = new Label(((JSONObject) ja.get(i)).getString("lotName"));
	        		lotName.setStyle("-fx-pref-width: 80; -fx-padding: 3.5 0 0 0");
	        		Label status = null;
	        		status = new Label("Not Handled");
	        		status.setStyle("-fx-pref-width: 80; -fx-padding: 3.5 0 0 0");

	   
	    		
					HBox hb = new HBox();
					hb.getChildren().add(resId);
					hb.getChildren().add(carNumber);
					hb.getChildren().add(lotName);
					hb.getChildren().add(status);
					hb.setStyle("-fx-border-style: solid inside;-fx-pref-height: 30;-fx-border-width: 0 0 2 0;"
							+ "-fx-border-color: #d0e6f8; -fx-padding: 1.5 0 0 5;");
					complaintsListVBox.getChildren().add(hb);
					
					if(status.getText() == "Not Handled"){
						Button handleComplaintButton = new Button("Handle");
						handleComplaintButton.setId("handleComplaintButton");
						String css = getClass().getResource("application.css").toExternalForm();
						handleComplaintButton.getStylesheets().clear();
						handleComplaintButton.getStylesheets().add(css);
						handleComplaintButton.setOnAction(e -> complainHandlerCallBack(e));
						
						handleComplaintButton.getStyleClass().add("loginView-buttons");
						hb.getChildren().add(handleComplaintButton);
					}
					
					if(status.getText() == "Handled"){

					}
	    		}
			}
			
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		
		
		
    	JSONArray ja = new JSONArray();
		try {
			ja.put(new JSONObject().put("resID", 12).put("car number", "987101023").put("lot name", "Horev")
					.put("status", false).put("complaint body", "I found scratches on the car"));


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }



	private void complainHandlerCallBack(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			HandlingComplaintsPopUp(null);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
