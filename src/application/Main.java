package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	private Parent mainLayout;
	private Stage primaryStage;

	public static void setprimary(Stage prim){
		
	}
	
	@Override
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
/*		String parent = System.getProperty("user.dir");
*/		
		MainController.initialize("10.0.0.6", "8080");
		launch(args);
	}
	
	
}
