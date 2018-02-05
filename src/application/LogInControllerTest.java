package application;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import model.ParkingReservation;

public class LogInControllerTest {

	Calendar start=Calendar.getInstance();
	Calendar end=Calendar.getInstance();
	
	ParkingReservation pR=new ParkingReservation("1213", "carmel", "ali", start, end, 10);
	

}
