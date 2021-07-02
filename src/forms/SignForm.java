package forms;

import java.awt.*;
import javax.swing.*;
import javacode.*;

/**
 * This class is called by ClientForm to show a form
 * for sign-up or unsign-up from monthly bus pass
 * @author Denis Rozit
 *
 */
public class SignForm {
	
	/**
	 * @param dc DataController
	 * @param ss SignStatus
	 * @param ms MonthStatus
	 * @param client Client
	 * 
	 */
	public static void signForm (DataController dc, SignStatus ss, MonthStatus ms, Client client){
		
		ms=dc.getMonthStatus(ms); //updating MonthStatus
		
			      JCheckBox janSign = new JCheckBox("January");
			      JCheckBox febSign = new JCheckBox("February");
			      JCheckBox marSign = new JCheckBox("March");
			      JCheckBox aprSign = new JCheckBox("April");
			      JCheckBox maySign = new JCheckBox("May");
			      JCheckBox junSign = new JCheckBox("June");
			      JCheckBox julSign = new JCheckBox("July");
			      JCheckBox augSign = new JCheckBox("August");
			      JCheckBox sepSign = new JCheckBox("September");
			      JCheckBox octSign = new JCheckBox("October");
			      JCheckBox novSign = new JCheckBox("November");
			      JCheckBox decSign = new JCheckBox("December");
			      
			      janSign.setSelected(ss.getSignStatus(1));
			      febSign.setSelected(ss.getSignStatus(2));
			      marSign.setSelected(ss.getSignStatus(3));
			      aprSign.setSelected(ss.getSignStatus(4));
			      maySign.setSelected(ss.getSignStatus(5));
			      junSign.setSelected(ss.getSignStatus(6));
			      julSign.setSelected(ss.getSignStatus(7));
			      augSign.setSelected(ss.getSignStatus(8));
			      sepSign.setSelected(ss.getSignStatus(9));
			      octSign.setSelected(ss.getSignStatus(10));
			      novSign.setSelected(ss.getSignStatus(11));
			      decSign.setSelected(ss.getSignStatus(12));

	      JPanel myPanel = new JPanel(new GridLayout(6, 3));
	  
			      myPanel.add(janSign).setEnabled(ms.getMonthStatus(1));       
			      myPanel.add(julSign).setEnabled(ms.getMonthStatus(7));  
			      myPanel.add(febSign).setEnabled(ms.getMonthStatus(2));     
			      myPanel.add(augSign).setEnabled(ms.getMonthStatus(8));   
			      myPanel.add(marSign).setEnabled(ms.getMonthStatus(3)); 
			      myPanel.add(sepSign).setEnabled(ms.getMonthStatus(9));    
			      myPanel.add(aprSign).setEnabled(ms.getMonthStatus(4)); 
			      myPanel.add(octSign).setEnabled(ms.getMonthStatus(10)); 
			      myPanel.add(maySign).setEnabled(ms.getMonthStatus(5));  
			      myPanel.add(novSign).setEnabled(ms.getMonthStatus(11));   
			      myPanel.add(junSign).setEnabled(ms.getMonthStatus(6)); 
			      myPanel.add(decSign).setEnabled(ms.getMonthStatus(12));
    
	      int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "Peggo Sign Up", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	      
	      if (result == JOptionPane.OK_OPTION) {
	    	  
	    	  ss.setSignStatus(1, janSign.isSelected());
	    	  ss.setSignStatus(2, febSign.isSelected());
	    	  ss.setSignStatus(3, marSign.isSelected());
	    	  ss.setSignStatus(4, aprSign.isSelected());
	    	  ss.setSignStatus(5, maySign.isSelected());
	    	  ss.setSignStatus(6, junSign.isSelected());
	    	  ss.setSignStatus(7, julSign.isSelected());
	    	  ss.setSignStatus(8, augSign.isSelected());
	    	  ss.setSignStatus(9, sepSign.isSelected());
	    	  ss.setSignStatus(10, octSign.isSelected());
	    	  ss.setSignStatus(11, novSign.isSelected());
	    	  ss.setSignStatus(12, decSign.isSelected());
	    	  
	    	  dc.setSignStatus(ss, client, ms);
	      }
	      

	}// end of method 
}// end of class
