import javax.swing.*;

public class Main{
  public static void main(String[] arg){
  	 JFrame loginFrame = new JFrame("Login");
  	 LoginController login = new LoginController(loginFrame);
  	 JFrame ManageUFrame=new JFrame("MANAGE USER");
  	  ManageUserController MUC= new ManageUserController(ManageUFrame);
  	  
  	    }
}
