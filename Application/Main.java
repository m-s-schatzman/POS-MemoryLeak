/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import javax.swing.*;
import java.util.Date;

public class Main{
  public static void main(String[] arg){
  	 DBConnection.openConnection();
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
}
