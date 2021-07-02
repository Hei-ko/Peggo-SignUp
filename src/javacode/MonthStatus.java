package javacode;

import java.util.HashMap;

/**
 * This class to store Month Status information.
 * Contains setters-getters methods.
 * @author Denis Rozit
 *
 */
public class MonthStatus {
	/** to initiate HashMap to store information about month availability for signing for bus pass
	 * with default values*/
	private HashMap<Integer, Boolean> monthStatus = new HashMap<Integer, Boolean>(){{
    put(1, false);
    put(2, false);  
    put(3, false);
    put(4, false);
    put(5, false);
    put(6, false);
    put(7, false);
    put(8, false);
    put(9, false);
    put(10, false);
    put(11, false);
    put(12, false);
	}};

	/**
	 * @param monthID Integer
	 * @param monthStatus boolean
	 * 
	 */	
	   public void setMonthStatus(Integer monthID, boolean monthStatus) 
	    {
	        this.monthStatus.replace(monthID, monthStatus);
	    }
	   
		/**
		 * @param monthID Integer
		 * @return boolean monthStatus.get(monthID)
		 * 
		 */	
	   public boolean getMonthStatus(Integer monthID) {
		   return monthStatus.get(monthID);
	   }
	
}// end of class
