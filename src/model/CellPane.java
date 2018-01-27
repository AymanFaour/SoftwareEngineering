package model;


import model.ParkingSlot;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class CellPane extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public CellPane(int height, int width, int depth, ParkingSlot[][][] parkingSlot) {
    	if(parkingSlot[height][width][depth].getStatus().equals(SpotStatus.Busy)){
    		setBackground(Color.BLUE);
        }
        else if(parkingSlot[height][width][depth].getStatus().equals(SpotStatus.Available)){
        	setBackground(Color.GREEN);
        }	                    
        else if(parkingSlot[height][width][depth].getStatus().equals(SpotStatus.Reserved)){
        	setBackground(Color.YELLOW);
        }
        else{
        	setBackground(Color.RED);
        }     	
   }

    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(20, 20);
    }
}
