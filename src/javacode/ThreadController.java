package javacode;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class starts and control threads.
 * Called by ClientForm class.
 * @author Denis Rozit
 *
 */
public class ThreadController implements Runnable {
    /** Creates a new thread for message check*/
	private Thread MessageCheck;
    /** AtomicBoolean to check if thread should be stopped
     * checking in while loop*/
    private final AtomicBoolean running = new AtomicBoolean(false);
    
	/**
	 *
	 */
    public void start() {
        MessageCheck = new Thread(this);
        MessageCheck.start();
    }
 
	/**
	 * 
	 */
    public void stopThis() {
        running.set(false);
    }
    
	/**
	 * 
	 */
 	public void run() {
		running.set(true);
		// TODO Auto-generated method stub
		String oldMessage="";
		String newMessage;
		
		NewMessage<String> message = new NewMessage<String>();
		
		// makilng loop while atomic boolean is true. It can be changed (by ClientForm in this project)
		// to "false" - it's mean to stop the thread
		while(running.get()) {
			
			//reading text file
			newMessage=TxtController.readTextMessage();
			if(!oldMessage.equals(newMessage)&&!newMessage.equals("")) {
				//JOptionPane.showConfirmDialog(null, newMessage, "Message", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
				//this line was substitute by generic class - to include it in project as per task/guidelines
				message.showTxt(newMessage);
			}
			oldMessage=newMessage;
			
			//taking pause (putting thread on sleep) for predetermined period
			try {
				Thread.sleep(1500); //15000=15sec
			}catch(Exception e){}
		}//end of loop while
	}
}
