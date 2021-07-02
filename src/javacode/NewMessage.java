/**
 * 
 */
package javacode;
import javax.swing.JOptionPane;

/**
 * This class shows pop-up window with text message
 * Goal - to create generic class as per project guidelines
 * 
 * @author Denis Rozit
 * 
 */
public class NewMessage <T> {
	
	 /**
	 * @param obj1 - generic class set by T
	 */
	public void showTxt (T obj1){
		JOptionPane.showConfirmDialog(null, obj1, "Message", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
	}// end of method
}// end of class
