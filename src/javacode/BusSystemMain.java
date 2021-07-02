/**
 * 
 */
package javacode;

import java.awt.GridLayout;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import forms.*;

/**
 * Main Class to start the application
 * @author Denis Rozit
 *
 */
public class BusSystemMain {
	/**
	 * Setting boolean chckboxAdmin=false by default
	 * determines what form should be launched:
	 * false - ClientForm, true - AdminForm
	 */
	private static boolean chckboxAdmin=false;
	
	/**
	 * @param args the Main class to start the application
	 * 
	 */	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Client client = new Client();
		MonthStatus ms = new MonthStatus();
		DataController dc = new DataController();
		boolean runMessage=true;
		
		do {
		client = LoginInfo.loginForm(client);
		client = dc.verifyClient(client);
		} while (!client.getIsExist());
		
		if (client.getIsAdmin()&&chckboxAdmin) {
			AdminForm.main(ms, dc);
		}else {
			ClientForm.main(client, ms, dc, runMessage);
		}
		
	
	}// end of main
	
	
		/**Private inner class LoginForm - for security reasons*/
	private static class LoginInfo {
		/**
		 * @param client Client
		 * @return client updated with ClientID and ClientPIN
		 */
		 private static Client loginForm (Client client){
			 
			  JTextField loginField = new JTextField(5);
		      JTextField pinField = new JTextField(5);
		      JCheckBox adminField = new JCheckBox();
		      ImageIcon icon = new ImageIcon("./images/peggo.jpg");

		      JPanel myPanel = new JPanel(new GridLayout(5, 3));
		      myPanel.add(new JLabel("Login"));
		      myPanel.add(loginField);
		      myPanel.add(new JLabel("PIN"));
		      myPanel.add(pinField);
		      myPanel.add(Box.createHorizontalStrut(1)); // a spacer
		      myPanel.add(Box.createHorizontalStrut(1)); // a spacer
		      myPanel.add(new JLabel("Finance"));
		      myPanel.add(adminField);
		      
		      int result = JOptionPane.showConfirmDialog(null, myPanel, 
		               "Please Log In", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
		      
		      if (result == JOptionPane.OK_OPTION) {
		    	  //**assert -- checking if no Client ID entered*/
		    	  assert (!loginField.getText().isEmpty()):"No Client ID entered";
		    	 try { 
		         client.setClientID(Integer.valueOf(loginField.getText()));
		         client.setClientPIN(pinField.getText());
		         BusSystemMain.chckboxAdmin=adminField.isSelected();
		    	 }catch(Exception e){
		    		 //do nothing and return back to main
		    	 }
		         
		      }// end of if_OK
		      
		      if (result == JOptionPane.CANCEL_OPTION) {
		    	  System.exit(0);
			  }// end of CANCEL
		      
		      return client;
		}// end of inner class method
	}//end of inner class
	
}// end of outer class
