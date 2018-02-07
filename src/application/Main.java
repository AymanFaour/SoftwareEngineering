package application;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.BarChartSample;
import model.ParkingLot;
import model.ParkingSlot;
import model.SharedData;
import sun.awt.RepaintArea;
//import model.ParkingLot;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Main extends Application {
	private Parent mainLayout;
	private Stage primaryStage;

	public static void setprimary(Stage prim) {

	}

	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("CPS Project");
			showMainView();
			/*
			BarChartSample bcs = new BarChartSample("hello");

			BarChartSample bcs2 = new BarChartSample("hello");

			BarChartSample bcs3 = new BarChartSample("hello");
			
			Stage popupwindow=new Stage();
			popupwindow.initModality(Modality.APPLICATION_MODAL);
			popupwindow.setTitle("total");

			BarChart<String,Number> bc = bcs.getBc();
			BarChart<String,Number> bc2 = bcs2.getBc();
			BarChart<String,Number> bc3 = bcs3.getBc();
			
			VBox vB = new VBox();

	        vB.getChildren().add(bc);
	        vB.getChildren().add(bc2);
	        vB.getChildren().add(bc3);
	        //vB.getChildren().add(bc);
	        Scene scene  = new Scene(vB,800,600);
	        popupwindow.setScene(scene);    
			popupwindow.showAndWait();
			 */
		} catch (Exception e) {
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

		/*
		ParkingLot temp = new ParkingLot("carmel", 3, 3, 3);
		
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.set(2000, 10, 10, 10, 10, 10);
		end.set(2028, 10, 10, 10, 10, 10);

		temp.InsertCar("11", start, end);
		System.out.println("11, " + end.getTime().toString());
		System.out.println("the time in the lot(0,0,0) is: " + temp.get_lot()[0][0][0].getLeave().getTime().toString());
		
		Calendar end1 = Calendar.getInstance();
		end1.set(2030, 10, 10, 10, 10, 10);
		temp.InsertCar("22", start, end1);
		System.out.println("22, " + end1.getTime().toString());
		System.out.println("the time in the lot(1,0,0) is: " + temp.get_lot()[1][0][0].getLeave().getTime().toString());

		
		Calendar end2 = Calendar.getInstance();
		end2.set(2001, 10, 10, 10, 10, 10);
		temp.InsertCar("33", start, end2);
		
		ArrayList<ParkingSlot> re = temp.rePark();
		
		System.out.println(re.get(0).getLeave().getTime().toString() + "\n" + re.get(1).getLeave().getTime().toString() + "\n" + re.get(2).getLeave().getTime().toString());
		*/
		String parent = System.getProperty("user.dir");
		try {

			FileReader fr = null;
			BufferedReader br = null;

			fr = new FileReader(parent + "/config.txt");
			br = new BufferedReader(fr);

			StringBuffer sb = new StringBuffer();
			String st = null;

			while ((st = br.readLine()) != null) {
				sb.append(st);
			}

			fr.close();
			br.close();

			JSONObject config = new JSONObject(sb.toString());

			SharedData.getInstance().setIP(config.getString("host"));
			SharedData.getInstance().setPORT(config.getString("port"));
			SharedData.getInstance().setCPSEmail(config.getString("cpsemail"));
			SharedData.getInstance().setCPSPassword(config.getString("cpsemailpassword"));
			
			// System.out.println(config + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

			ParkingLot park = new ParkingLot(config.getString("lotName"), 3, 3, config.getInt("width"));
			// System.out.println("@@@@@@@@@@@@@@" +
			// park.get_lot()[0][0][0].getStatus());
			SharedData.getInstance().setCurrentParkingLot(park);

		} catch (Exception e) {
			e.printStackTrace();
		}

		JSONObject ret = request(null, "SystemQueries");
		try {
			if (ret.getBoolean("result")) {
				System.out.println(ret);

				JSONArray costs = ret.getJSONArray("Costs");
				
				SharedData.getInstance().setOccasionalCost(((JSONObject) costs.get(0)).getDouble("cost"));
				SharedData.getInstance().setReservationCost(((JSONObject) costs.get(1)).getDouble("cost"));
				SharedData.getInstance().setRoutineCost(((JSONObject) costs.get(2)).getDouble("cost"));
				SharedData.getInstance().setBusinessCost(((JSONObject) costs.get(3)).getDouble("cost"));
				SharedData.getInstance().setFullCost(((JSONObject) costs.get(4)).getDouble("cost"));
				
				JSONArray parkingLotsJA = ret.getJSONArray("lots");
				ArrayList<ParkingLot> parkingLotsAL= new ArrayList<ParkingLot>();
				for (int i = 0; i < parkingLotsJA.length(); i++){
					parkingLotsAL.add(new ParkingLot(parkingLotsJA.getJSONObject(i).getString("lotName")
							, 3, 3, parkingLotsJA.getJSONObject(i).getInt("width")));
				}
				
				SharedData.getInstance().setParkingLotsAL(parkingLotsAL);

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MainController.initialize(SharedData.getInstance().getIP(), SharedData.getInstance().getPORT());
		launch(args);
		

	}
	
	/**
	 * a method that talks with the server in servlet mechanism.
	 * Sending a request to the server by sending a json object that contains the data we want to send to the server,
	 * and the servlet name.
	 * 
	 * @param json 
	 * @param servletName 
	 * @return
	 */

	static JSONObject request(JSONObject json, String servletName) {
		HttpURLConnection connection = null;
		try {
			// Create connection
			URL url = new URL("http://" + SharedData.getInstance().getIP() + ":" + SharedData.getInstance().getPORT()
					+ "/server/" + servletName);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);

			// Send request
			DataOutputStream sentData = new DataOutputStream(connection.getOutputStream());

			if (json != null) {
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
