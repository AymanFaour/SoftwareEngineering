package model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
 
public class BarChartSample {
    final private String sunday = "Sunday";
    final private String monday = "Monday";
    final private String tuesday = "Tuesday";
    final private String wednesday = "Wednesday";
    final private String thursday = "Thursday";
    final private String friday = "Friday";
    final private String saturday = "Saturday";
    final CategoryAxis xAxis;
    final NumberAxis yAxis;
    private Stage popupwindow;
    final BarChart<String,Number> bc;
    
    public BarChartSample(String title){
		popupwindow=new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle(title);

        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        
        bc = new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle(title);
        xAxis.setLabel("Day");       
        yAxis.setLabel("");
 
        XYChart.Series series1 = new XYChart.Series();       
        series1.getData().add(new XYChart.Data(sunday, 0.10));
        series1.getData().add(new XYChart.Data(monday, 0.23));
        series1.getData().add(new XYChart.Data(tuesday, 0.30));
        series1.getData().add(new XYChart.Data(wednesday, 0.17));
        series1.getData().add(new XYChart.Data(thursday, 0.10));
        series1.getData().add(new XYChart.Data(friday, 0.08));
        series1.getData().add(new XYChart.Data(saturday, 0.02));      
         
        VBox vB = new VBox();
        Label lb = new Label("Median");
        
        bc.getData().addAll(series1);
        vB.getChildren().add(lb);
        Scene scene  = new Scene(bc,800,600);
        popupwindow.setScene(scene);    
		popupwindow.showAndWait();
    }

    
    public String getMedian(){
    	return null;
    }
}