package application;
	
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.ParkingLot;
//import model.ParkingLot;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application{
	private Parent mainLayout;
	private Stage primaryStage;
	public static ParkingLot _currentParkingLot;

	public static void setprimary(Stage prim){
		
	}
	
	
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("CPS Project");
			showMainView();
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}
	
	private void showMainView() {
		// TODO Auto-generated method stub

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("MainView.fxml"));
		
		try {
			mainLayout = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		} 
		Scene scene = new Scene(mainLayout);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	} 

	public static void main(String[] args) {

	    String parent = System.getProperty("user.dir");
        try {

        	FileReader fr = null;
        	BufferedReader br = null;
        	
        	fr = new FileReader(parent+"/config.txt");
        	br = new BufferedReader(fr);
        	
        	StringBuffer sb = new StringBuffer();
        	String st = null;


        	while( (st = br.readLine()) != null){
        		sb.append(st);
        	}
        	
        	fr.close();
        	br.close();
        	
        	JSONObject config = new JSONObject(sb.toString());

        	
//        	System.out.println(config + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        	
        	_currentParkingLot = new ParkingLot(config.getString("lotName"), 3, 3, config.getInt("width"));
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		MainController.initialize("192.168.1.17", "8080");
		launch(args);
	}
	
	
}
