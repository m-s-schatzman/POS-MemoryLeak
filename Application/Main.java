import javax.swing.*;

public class Main{
  public static void main(String[] arg){
  	 createClosingHook();
  	 JFrame loginFrame = new JFrame("Login");
  	 LoginController login = new LoginController(loginFrame);
  	 //Can comment following two lines in and comment out two above to skip login stage
  	 //JFrame posFrame = new JFrame("Memory Leak POS");
  	 //POSController posController = new POSController(posFrame);
  	}

  	//Create a hook so that when database closes it will close connection to database
	private static void createClosingHook(){
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
    		public void run() {
    			DBConnection.closeConnection();
    		}
		}));
	}
}
