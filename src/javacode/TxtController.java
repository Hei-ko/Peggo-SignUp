package javacode;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * This class performs read-write functions from/to txt file.
 * @author Denis Rozit
 *
 */
public class TxtController {
	
	/**variable to store path to Message file*/
	final private static String pathTextFile="TextMessage.txt";
	
	/**
	 * @param textMessage String
	 */
	public static void saveTextMessage(String textMessage) {

		try {
			//writing in the file
			FileWriter myWriter = new FileWriter(pathTextFile);
			
			//writing title of the table
			myWriter.write(textMessage);
			
			// to make sure everything was written
			myWriter.flush();
			
			//to close the file
			myWriter.close();

			JOptionPane.showConfirmDialog(null, "Information has been updated", "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
					
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Please try again", "Error", JOptionPane.DEFAULT_OPTION, 0, null);
		}
		
	}// end of method

	/**
	 * @return textMessage
	 */
	public static String readTextMessage() {
		
		//**assert - checking if text message file exists or path to the file is correct*/
		assert (new File(pathTextFile)).exists():"Text Message file not found or path is wrong";
		
		String textMessage="";
		try {
			File myStreamScanner = new File(pathTextFile);
			Scanner myReaderScanner = new Scanner(myStreamScanner);
			// reading information line by line
			while (myReaderScanner.hasNextLine()) {
				textMessage += myReaderScanner.nextLine()+"\n";
			}
			myReaderScanner.close();

		} catch (Exception e) {
			textMessage="***error***";
		}
		
		return textMessage;
	}// end of method
		
}// end of class
