package javacode;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * This class works with database by querying database and returns information to caller class;
 * executes SQL Statement (SELECT, DELETE, INSERT, UPDATE)
 * @author Denis Rozit
 *
 */
public class DataController {
	
	/**variable to store path to database file*/
	final private String pathFile="./db/data.db";
	/**variable to store connection string*/
	final private String pathConnection="jdbc:sqlite:"+pathFile;
	
	/**
	 * @param client Client
	 * @return client
	 * 
	 */
	public Client verifyClient(Client client) {
		
		//**assert - checking if database file exists or path to the file is correct*/
		assert (new File(pathFile)).exists():"Database file not found or path is wrong";
		
		try {
		    Connection conn=DriverManager.getConnection(pathConnection);
			Statement stmt = conn.createStatement();
			String stringQuery = "SELECT * FROM Clients WHERE ID="+client.getClientID()+
					" AND PIN='"+client.getClientPIN()+"';";

			ResultSet rs = stmt.executeQuery(stringQuery);
			while (rs.next()) {
				client.setCardID(Integer.parseInt(rs.getString("Card")));
				client.setClientEmail(rs.getString("Email"));
				client.setClientName(rs.getString("Name"));
				client.setIsAdmin(Boolean.parseBoolean(rs.getString("Admin")));
				client.setIsExist(true);
			};

			conn.close();
			stmt.close();

		}catch(Exception e) {
			client.setIsExist(false);
		}// end of try-catch
		
		return client;
	}// end of getClient
	
	/**
	 * @param ms MonthStatus
	 * @return ms
	 * 
	 */
	public MonthStatus getMonthStatus(MonthStatus ms) {
		
		try {
		    Connection conn=DriverManager.getConnection(pathConnection);
	    	
			Statement stmt = conn.createStatement();
			String stringQuery = "SELECT * FROM MonthStatus;";
			
			ResultSet rs = stmt.executeQuery(stringQuery);
			while (rs.next()) {
				ms.setMonthStatus(Integer.parseInt(rs.getString("ID")),
								Boolean.parseBoolean(rs.getString("Status")));
			};
			
			conn.close();
			stmt.close();
	
		}catch(Exception e) {
			JOptionPane.showConfirmDialog(null, e, "Error", JOptionPane.DEFAULT_OPTION);
		}// end of try-catch
		
		return ms;
	}// end of getMonthStatus
	
	/**
	 * @param ss SignStatus
	 * @param client Client
	 * @return ss SignStatus
	 * 
	 */
	public SignStatus getSignStatus(SignStatus ss, Client client) {
		
		try {
		    Connection conn=DriverManager.getConnection(pathConnection);
			Statement stmt = conn.createStatement();
			String stringQuery = "SELECT * FROM Signup WHERE ClientID='"+client.getClientID()+"';";
			
			ResultSet rs = stmt.executeQuery(stringQuery);
			while (rs.next()) {
				ss.setSignStatus(Integer.parseInt(rs.getString("MonthID")),
								Boolean.parseBoolean(rs.getString("SignStatus")));
			};
			
			conn.close();
			stmt.close();
	
		}catch(Exception e) {
			JOptionPane.showConfirmDialog(null, e, "Error", JOptionPane.DEFAULT_OPTION, 0, null);
		}// end of try-catch
		
		return ss;
	}// end of getSignStatus

	/**
	 * @param ssNew SignStatus
	 * @param client Client
	 * @param ms MonthStatus
	 * 
	 */
	public void setSignStatus(SignStatus ssNew, Client client, MonthStatus ms) {
		SignStatus ssOld=new SignStatus();

		try {
			//pulling current SignStatus from database
		    Connection connSignStatus=DriverManager.getConnection(pathConnection);
			Statement stmtSignStatus = connSignStatus.createStatement();
			String stringQuerySignStatus = "SELECT * FROM Signup WHERE ClientID='"+client.getClientID()+"';";
			
			ResultSet rsSignStatus = stmtSignStatus.executeQuery(stringQuerySignStatus);
			while (rsSignStatus.next()) {
				ssOld.setSignStatus(Integer.parseInt(rsSignStatus.getString("MonthID")),
								Boolean.parseBoolean(rsSignStatus.getString("SignStatus")));
			};
			connSignStatus.close();
			stmtSignStatus.close();
			
			//updating MonthStatus with latest data from database
			Connection connMonthStatus=DriverManager.getConnection(pathConnection);
			Statement stmtMonthStatus = connMonthStatus.createStatement();
			String stringQueryMonthStatus = "SELECT * FROM MonthStatus;";
			
			ResultSet rsMonthStatus = stmtMonthStatus.executeQuery(stringQueryMonthStatus);
			while (rsMonthStatus.next()) {
				ms.setMonthStatus(Integer.parseInt(rsMonthStatus.getString("ID")),
								Boolean.parseBoolean(rsMonthStatus.getString("Status")));
			};
			connMonthStatus.close();
			stmtMonthStatus.close();
			
			// validating changes vs closed months before updating database
			for (int i=1; i<13; i++) {
				if((ssOld.getSignStatus(i)!=ssNew.getSignStatus(i))&&!ms.getMonthStatus(i)) {
					throw new CustomValidationException ("You are trying to update closed month ("+i+")");
				}// end if
			}// end for

		    Connection connSignUp=DriverManager.getConnection(pathConnection);
			Statement stmt = connSignUp.createStatement();
			
			stmt.executeUpdate("DELETE FROM Signup WHERE ClientID = "+client.getClientID()+"; "+
								"INSERT INTO Signup (ClientID, MonthID, SignStatus) VALUES "+
								"("+client.getClientID()+", 1, '"+ssNew.getSignStatus(1)+"'), "+
								"("+client.getClientID()+", 2, '"+ssNew.getSignStatus(2)+"'), "+
								"("+client.getClientID()+", 3, '"+ssNew.getSignStatus(3)+"'), "+
								"("+client.getClientID()+", 4, '"+ssNew.getSignStatus(4)+"'), "+
								"("+client.getClientID()+", 5, '"+ssNew.getSignStatus(5)+"'), "+
								"("+client.getClientID()+", 6, '"+ssNew.getSignStatus(6)+"'), "+
								"("+client.getClientID()+", 7, '"+ssNew.getSignStatus(7)+"'), "+
								"("+client.getClientID()+", 8, '"+ssNew.getSignStatus(8)+"'), "+
								"("+client.getClientID()+", 9, '"+ssNew.getSignStatus(9)+"'), "+
								"("+client.getClientID()+", 10, '"+ssNew.getSignStatus(10)+"'), "+
								"("+client.getClientID()+", 11, '"+ssNew.getSignStatus(11)+"'), "+
								"("+client.getClientID()+", 12, '"+ssNew.getSignStatus(12)+"');");
			connSignUp.close();
			stmt.close();
			JOptionPane.showConfirmDialog(null, "Information has been updated", "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
		}
		catch(CustomValidationException e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, 0, null);
		}
		catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "Please try again", "Error", JOptionPane.DEFAULT_OPTION, 0, null);
		
		}// end of try-catch
	}// end of setSignStatus
	
	/**
	 * @param client Client
	 * @return client
	 */
	public Client getClient(Client client) {
		
	    client.setClientName("");
	    client.setClientEmail("");
	    client.setCardID(0);
	    client.setClientPIN("");
	    client.setIsAdmin(false);
	    client.setIsExist(false);

		try {
		    Connection conn=DriverManager.getConnection(pathConnection);
			Statement stmt = conn.createStatement();
			String stringQuery = "SELECT * FROM Clients WHERE ID="+client.getClientID()+";";

			ResultSet rs = stmt.executeQuery(stringQuery);
			while (rs.next()) {
				client.setCardID(Integer.parseInt(rs.getString("Card")));
				client.setClientEmail(rs.getString("Email"));
				client.setClientName(rs.getString("Name"));
				client.setClientPIN(rs.getString("PIN"));
				client.setIsAdmin(Boolean.parseBoolean(rs.getString("Admin")));
				client.setIsExist(true);
			};

			conn.close();
			stmt.close();
		
		}catch(Exception e) {
			client.setIsExist(false);
		}// end of try-catch
		
		return client;

	}// end of getClient
	
	/**
	 * @param monthNumber int
	 * @param monthStatus boolean
	 * @param ms MonthStatus
	 * @return ms
	 * 
	 */
	public MonthStatus setMonthStatus(int monthNumber, boolean monthStatus, MonthStatus ms) {
		
		try {
		    Connection conn=DriverManager.getConnection(pathConnection);
			Statement stmt = conn.createStatement();
			String stringQuery = "UPDATE MonthStatus SET Status='"+Boolean.toString(monthStatus)+"' WHERE ID='"+monthNumber+"';";
			stmt.executeUpdate(stringQuery);

			ms.setMonthStatus(monthNumber, monthStatus);
			
			conn.close();
			stmt.close();
			
			//the following message is optional
			//JOptionPane.showConfirmDialog(null, "Information has been updated", "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
		
		}catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "Please try again", "Error", JOptionPane.DEFAULT_OPTION, 0, null);
		}// end of try-catch
		
		return ms;
	}// end of getMonthStatus

	/**
	 * @param newClient Client
	 * 
	 */
	public void createNewClient(Client newClient) {
		Client  client = new Client();

		try {
			//checking if exist in database
		    Connection connClient=DriverManager.getConnection(pathConnection);
			Statement stmtClient = connClient.createStatement();
			String stringQueryClient = "SELECT * FROM Clients WHERE ID="+newClient.getClientID()+";";

			ResultSet rsClient = stmtClient.executeQuery(stringQueryClient);
			while (rsClient.next()) {
				client.setClientID(Integer.parseInt(rsClient.getString("ID")));
				client.setIsExist(true);
			};
			connClient.close();
			stmtClient.close();

			if(client.getClientID()==newClient.getClientID()) {
				throw new CustomValidationException ("This Client ID already exists");
			}
			

			//saving new client's information into database
			Connection connNewClient=DriverManager.getConnection(pathConnection);
			Statement stmtNewClient = connNewClient.createStatement();

			stmtNewClient.executeUpdate("INSERT INTO Clients (ID, Admin) VALUES("+ newClient.getClientID()+", false);");

			connNewClient.close();
			stmtNewClient.close();

			JOptionPane.showConfirmDialog(null, "Information has been updated", "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
		}
		catch(CustomValidationException e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, 0, null);
		}
		catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "Please try again", "Error", JOptionPane.DEFAULT_OPTION, 0, null);
		
		}// end of try-catch
	}// end of setNewClient
	
	/** 
	 * @param client Client
	 * 
	 */
	public void updateClient(Client client) {

		try {
			//checking if exist in database
		    Connection connClientCheck=DriverManager.getConnection(pathConnection);
			Statement stmtClientCheck = connClientCheck.createStatement();
			String stringQueryClient = "SELECT * FROM Clients WHERE ID="+client.getClientID()+";";

			ResultSet rsClient = stmtClientCheck.executeQuery(stringQueryClient);
			while (rsClient.next()) {
				if(Integer.parseInt(rsClient.getString("ID"))!=client.getClientID()){
					throw new CustomValidationException ("This Client ID does not exist");};
			};
			connClientCheck.close();
			stmtClientCheck.close();
			
			//saving client's information into database
			Connection connNewClient=DriverManager.getConnection(pathConnection);
			Statement stmtNewClient = connNewClient.createStatement();
			stmtNewClient.executeUpdate("UPDATE Clients SET "
					+ "Name='"+client.getClientName() +"', "
					+ "Email='"+client.getClientEmail() +"', "
					+ "Card='"+client.getCardID() +"', "
					+ "PIN='"+client.getClientPIN() +"', "
					+ "Admin='"+client.getIsAdmin()
					+ "' WHERE ID="+client.getClientID()+";");
			
			connNewClient.close();
			stmtNewClient.close();
			
			JOptionPane.showConfirmDialog(null, "Information has been updated", "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
		}
		catch(CustomValidationException e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, 0, null);
		}
		catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "Please try again", "Error", JOptionPane.DEFAULT_OPTION, 0, null);
		
		}// end of try-catch
	}// end of updateClient

	/**
	 * @param client Client
	 * 
	 */
	public void deleteClient(Client client) {
		int checkID=0;
		try {
			//checking if exist in database
		    Connection connClient=DriverManager.getConnection(pathConnection);
			Statement stmtClient = connClient.createStatement();
			String stringQueryClient = "SELECT * FROM Clients WHERE ID="+client.getClientID()+";";

			ResultSet rsClient = stmtClient.executeQuery(stringQueryClient);
			while (rsClient.next()) {
				checkID=Integer.parseInt(rsClient.getString("ID"));
				
			};
			connClient.close();
			stmtClient.close();

			if(checkID!=client.getClientID()) {
				throw new CustomValidationException ("Client ID was not found");
			}
			
			      int result = JOptionPane.showConfirmDialog(null, "Would you like to delete client with ID# "+client.getClientID(), 
			               "Please Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		      
			      if (result == JOptionPane.CANCEL_OPTION) {
			    	  throw new CustomValidationException ("Operation has been cancelled");
				  }// end of CANCEL
			

			//deleting client's info from database
			Connection connNewClient=DriverManager.getConnection(pathConnection);
			Statement stmtNewClient = connNewClient.createStatement();
			stmtNewClient.executeUpdate("DELETE FROM Clients WHERE ID="+ client.getClientID()+";");
			connNewClient.close();
			stmtNewClient.close();
			
			JOptionPane.showConfirmDialog(null, "Information has been updated", "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
		}
		catch(CustomValidationException e) {
			JOptionPane.showConfirmDialog(null, e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, 0, null);
		}
		catch(Exception e) {
			JOptionPane.showConfirmDialog(null, "Please try again", "Error", JOptionPane.DEFAULT_OPTION, 0, null);
		
		}// end of try-catch
	}// end of setNewClient


	/**
	 * @param monthNumber int
	 * @return mr
	 * 
	 */
	public String[][] getMonthReport(int monthNumber) {
		ArrayList <String> listID = new ArrayList <String>();
		ArrayList <String> listName = new ArrayList <String>();
		ArrayList <String> listCard = new ArrayList <String>();
		ArrayList <String> listSignStatus = new ArrayList <String>();
		
		
		try {
		    Connection connReport=DriverManager.getConnection(pathConnection);
			Statement stmtReport = connReport.createStatement();
			String stringReport = "SELECT * FROM Signup su JOIN Clients cl ON su.ClientID=cl.ID WHERE su.MonthID='"+monthNumber+"';";
			ResultSet rsReport = stmtReport.executeQuery(stringReport);
			
			while (rsReport.next()) {
				
				listID.add(rsReport.getString("ID"));
				listName.add(rsReport.getString("Name"));
				listCard.add(rsReport.getString("Card"));
				listSignStatus.add(rsReport.getString("SignStatus"));
				
			};
			
			connReport.close();
			stmtReport.close();
		}catch(Exception e) {
			// do nothing - empty table
			JOptionPane.showConfirmDialog(null, e, "Error", JOptionPane.DEFAULT_OPTION, 0, null);
		}// end of try-catch
		
		String[][]mr=new String[listID.size()][4];
		for(int i=0; i<listID.size(); i++) {
			mr[i][0]=listID.get(i);
			mr[i][1]=listName.get(i);
			mr[i][2]=listCard.get(i);
			mr[i][3]=listSignStatus.get(i);
		}
		
	return mr;
	}
	
}// end of DataController
