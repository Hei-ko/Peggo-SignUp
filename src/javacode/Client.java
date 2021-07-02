package javacode;
/**
 * This class to store and pass within the app an information 
 * about client.
 * Contains setters-getters methods.
 * @author Denis Rozit
 *
 */
public class Client {
		/**Variable to store client's name*/
	private String clientName;
		/**Variable to store client's email*/
    private String clientEmail;
	/**Variable to store client's ID#*/
    private int clientID;
	/**Variable to store client's card#*/
    private int cardID;
	/**Variable to store client's PIN*/
    private String clientPIN;
	/**Variable to set if client has access to AdminForm*/
    private boolean isAdmin=false;
	/**Variable to set if client ID is exist
	 * equals to false if Client doesn't exist*/
    private boolean isExist=false; 
    
	/**
	 * @return clientName
	 * 
	 */
    public String getClientName() 
    {
        return clientName;
    }
    
	/**
	 * @param clientName String to set Client Name variable
	 * 
	 */
    public void setClientName(String clientName) 
    {
        this.clientName = clientName;
    }
    
	/**
	 * @return clientEmail
	 * 
	 */
    public String getClientEmail() 
    {
        return clientEmail;
    }
     
	/**
	 * @param clientEmail String to set Client Email variable
	 * 
	 */
    public void setClientEmail(String clientEmail) 
    {
        this.clientEmail = clientEmail;
    }
	
	/**
	 * @return clientID
	 * 
	 */
    public int getClientID() 
    {
        return clientID;
    }
     
	/**
	 * @param clientID int to set Client ID# variable
	 * 
	 */
    public void setClientID(int clientID) 
    {
        this.clientID = clientID;
    }
    
	/**
	 * @return cardID
	 * 
	 */
    public int getCardID() 
    {
        return cardID;
    }
     
	/**
	 * @param cardID int to set Client's Card# variable
	 * 
	 */
    public void setCardID(int cardID) 
    {
        this.cardID = cardID;
    }
    
	/**
	 * @return clientPIN
	 * 
	 */
    public String getClientPIN() 
    {
        return clientPIN;
    }
     
	/**
	 * @param clientPIN String to set Client PIN variable
	 * 
	 */
    public void setClientPIN(String clientPIN) 
    {
        this.clientPIN = clientPIN;
    }
    
	/**
	 * @return isAdmin 
	 * 
	 */
    public boolean getIsAdmin() 
    {
        return isAdmin;
    }
     
	/**
	 * @param isAdmin boolean to set isAdmin variable to "true" if this client can run AdminForm
	 * 
	 */
    public void setIsAdmin(boolean isAdmin) 
    {
        this.isAdmin = isAdmin;
    }
    
	/**
	 * @return isExist 
	 * 
	 */
    public boolean getIsExist() 
    {
        return isExist;
    }
     
	/**
	 * @param isExist boolean to set isExist variable to "true" if this client exist in database
	 * 
	 */
    public void setIsExist(boolean isExist) 
    {
        this.isExist = isExist;
    }
    
	/**
	 *
	 */
    public void deleteInfo() {
    	
    	this.clientName="";
    	this.clientEmail="";
    	this.clientID=0;
    	this.cardID=0;
    	this.clientPIN="";
    	this.isAdmin=false;
    	this.isExist=false; 
    }
    
}// end of Client
