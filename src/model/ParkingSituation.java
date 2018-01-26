package model;


import java.util.ArrayList;
import model.ParkingSlot;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ParkingSituation {
	
	private ArrayList<GridPane> gpAL;
	private int length;
	private int width;
	
    public ParkingSituation(int length, int width) {
		super();
		this.gpAL = new ArrayList<GridPane>();
		this.length = length;
		this.width = width;
	}

		public ArrayList<GridPane> getGridLayer(ParkingLot parkingLot) {
			ParkingSlot[][][] _parkingSlot = parkingLot.get_lot();
			for(int z = 0; z < parkingLot.getDepth(); z++){
				GridPane gp = new GridPane();
	            for(int y = 0; y < length; y++){
	                for(int x = 0; x < width; x++){
	                    Rectangle rec = new Rectangle();
	                    rec.setWidth(50);
	                    rec.setHeight(50);
	                    if(_parkingSlot[y][x][z].getStatus().equals(SpotStatus.Busy)){
	                    	rec.setFill(Color.BLUE);
	                    }
	                    else if(_parkingSlot[y][x][z].getStatus().equals(SpotStatus.Available)){
	                    	rec.setFill(Color.GREEN);
	                    }	                    
	                    else if(_parkingSlot[y][x][z].getStatus().equals(SpotStatus.Reserved)){
	                    	rec.setFill(Color.YELLOW);
	                    }
	                    else{
	                    	rec.setFill(Color.RED);
	                    }
                    	rec.setStroke(Color.BLACK);
	                    GridPane.setRowIndex(rec, y);
	                    GridPane.setColumnIndex(rec, x);
	                    // Create a new TextField in each Iteration
	
	                    // Iterate the Index using the loops  
	                    gp.getChildren().add(rec);
	                }
	            }
                gpAL.add(gp);
            }
			return gpAL;

        }
    }