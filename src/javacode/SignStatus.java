package javacode;

import java.util.HashMap;

/**
 * This class to store information about signed months.
 * Contains setter-getter methods.
 * @author Denis Rozit
 *
 */
public class SignStatus {
	/** to initiate HashMap to store information about month sign-up status for bus pass
	 * with default values*/
	private HashMap<Integer, Boolean> signStatus = new HashMap<Integer, Boolean>(){{
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
		 * @param signStatus boolean
		 * 
		 */
	   public void setSignStatus(Integer monthID, boolean signStatus) 
	    {
	        this.signStatus.replace(monthID, signStatus);
	    }
	   
		/**
		 * @param monthID Integer
		 * @return boolean signStatus.get(monthID)
		 * 
		 */
	   public boolean getSignStatus(Integer monthID) {
		   return signStatus.get(monthID);
	   }
	
}// end of class
