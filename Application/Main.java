/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import javax.swing.*;
import java.util.Date;

public class Main{
  public static void main(String[] arg){
     JFrame connectingFrame = openConnectionFrame();
  	 DBConnection.openConnection();
     closeConnectionFrame(connectingFrame);
     Logger.logError("Connection Opened Successfully : "+ new Date().toString());
  	 createClosingHook();
  	 new LoginController();
  	 //Can comment following two lines in and comment out above lines to skip login stage
  	 //JFrame posFrame = new JFrame("Memory Leak POS");
  	 //POSController posController = new POSController(posFrame);
  	}

  	//Create a hook so that when database closes it will close connection to database
	private static void createClosingHook(){
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
    		public void run() {
    			DBConnection.closeConnection();
          Logger.logError("Connection Closed Successfully : "+ new Date().toString());
    		}
		}));
	}

  //Open a frame to indicate that the application is currently connecting to the database
  private static JFrame openConnectionFrame(){
    JFrame receiptFrame = new JFrame("Receipt");
    receiptFrame.pack();
    JPanel connectionPane = new JPanel();
    JLabel message = new JLabel("Connecting to Database");
    connectionPane.add(message);
    receiptFrame.setContentPane(connectionPane);
    receiptFrame.setSize(400,400);
    receiptFrame.setLocationRelativeTo(null);
    receiptFrame.setVisible(true);
    return receiptFrame;
  }

  //Close the connecting frame
  private static void closeConnectionFrame(JFrame f){
    f.setVisible(false);
    f.dispose();
  }
}
