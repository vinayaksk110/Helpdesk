package utilities;

import java.text.*;
import java.util.*;

public class DateNTime {
	DateFormat dateFormat = null;
	Date currentDate = null;
	public String displayDateNTime = null;
	
	public String printCurrentDateTime(){
		//get current date time with Date()
		currentDate = new Date();
				
		// Create object of SimpleDateFormat class and decide the format
		 dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");		 
		 
		 // Now format the date
		displayDateNTime = dateFormat.format(currentDate);
		
		return displayDateNTime;
	}
	
	public String printCurrentTime(){
		//get current date time with Date()
		currentDate = new Date();
				
		// Create object of SimpleDateFormat class and decide the format
		 dateFormat = new SimpleDateFormat("h:mm:ss a");		 
		 
		 // Now format the date
		displayDateNTime = dateFormat.format(currentDate);
		
		return displayDateNTime;
	}
	/**
	 * 
	 * @param timeFormat : takes the format of the time in which it should be displayed
	 * @return : The current time in the specified format
	 */
	public String printCurrentTime(String timeFormat){
		//get current date time with Date()
		currentDate = new Date();
				
		// Create object of SimpleDateFormat class and decide the format
		 dateFormat = new SimpleDateFormat(timeFormat);		 
		 
		 // Now format the date
		displayDateNTime = dateFormat.format(currentDate);
		
		return displayDateNTime;
	}
	
	public String printCurrentDate(){
		//get current date time with Date()
		currentDate = new Date();
				
		// Create object of SimpleDateFormat class and decide the format
		 dateFormat = new SimpleDateFormat("dd/MM/yy");		 
		 
		 // Now format the date
		displayDateNTime = dateFormat.format(currentDate);
		
		return displayDateNTime;
	}

}
