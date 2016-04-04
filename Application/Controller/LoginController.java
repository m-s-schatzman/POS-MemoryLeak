import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginController extends java.util.Observable implements ActionListener{

	private LoginView view;

    public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Exit")){
	    	System.exit(1);
		}
		if(ac.getActionCommand().equals("Login")){
			String userName = view.getUserName();
			String password = view.getPassword();
			if(CurrentUser.login(userName, password)){
				POSController.create();
        		view.closeFrame();
			} 
			/*
			else
			{
        		view.invalidMessage();
        	}
        	*/
    	}
	}

	//Creates new login view and controller
	public static void create(){
		JFrame loginFrame = new JFrame("Login");
  		new LoginController(loginFrame);
  	}

	//else if createSale
	//else if addLineItem
	//else if removeLineItem
	//else if processSale

    public LoginController(JFrame applicationFrame){
		view = new LoginView(applicationFrame);
		view.addController(this);
    }
}