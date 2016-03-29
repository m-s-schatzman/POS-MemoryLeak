import javax.swing.*;

public class Main{
  public static void main(String[] arg){
  	 JFrame loginFrame = new JFrame("Login");
  	 LoginController login = new LoginController(loginFrame);
  	 //Can comment following two lines in and comment out two above to skip login stage
  	 //JFrame posFrame = new JFrame("Memory Leak POS");
  	 //POSController posController = new POSController(posFrame);
  	}
}
