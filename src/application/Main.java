package application;
	
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
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
	public static double _reservationCost;
	public static double _occasonalCost;
	public static double _routineCost;
	public static double _businessCost;
	public static double _fullCost;
	public static String IP;
	public static String PORT;
	

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

        	IP = config.getString("host");
        	PORT = config.getString("port");
        	
//        	System.out.println(config + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        	
        	_currentParkingLot = new ParkingLot(config.getString("lotName"), 3, 3, config.getInt("width"));
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JSONObject ret = request(null,"SystemQueries");
        try {
			if(ret.getBoolean("result")){
				System.out.println(ret);
				
				JSONArray costs = ret.getJSONArray("Costs");
				
				_reservationCost = ((JSONObject) costs.get(1)).getDouble("cost");
				_occasonalCost = ((JSONObject) costs.get(0)).getDouble("cost");
				_routineCost = ((JSONObject) costs.get(2)).getDouble("cost");
				_businessCost = ((JSONObject) costs.get(3)).getDouble("cost");
				_fullCost = ((JSONObject) costs.get(4)).getDouble("cost");
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MainController.initialize(IP, PORT);
		launch(args);
	}
	
	
	static JSONObject request(JSONObject json, String servletName) {
		HttpURLConnection connection = null;
		try {
			// Create connection
			URL url = new URL("http://" + IP + ":" + PORT + "/server/" + servletName);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);

			// Send request
			DataOutputStream sentData = new DataOutputStream(connection.getOutputStream());

			if(json != null){
				sentData.writeBytes(json.toString());

				sentData.close();
			}
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
