import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginController implements ActionListener{

	private LoginView view;
	private JFrame f;

	//Routes the actions performed in the view to their respective actions in the controller
    public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Exit")){
	    	System.exit(1);
		}else if(ac.getActionCommand().equals("Login")){
			login();
    	}
    	else if(ac.getActionCommand().equals("Ok"))
    	{
    		f.setVisible(false);
    		f.dispose();
    	}
	}

	//Constuctor
	//Create should be called to make this LoginController
	private LoginController(JFrame applicationFrame){
		view = new LoginView(applicationFrame);
		view.addController(this);
    }

	//Creates new login view and controller
	public static void create(){
		JFrame loginFrame = new JFrame("Login");
  		new LoginController(loginFrame);
  	}

  	//Attempt to login user into the application
  	private void login(){
  		String userName = view.getUserName();
		String password = view.getPassword();
		if(CurrentUser.login(userName, password)){
			POSController.create();
        	view.closeFrame();
		}
		else
		{
			Logger.logError("Invalid Login Credentials. Please Try Again.");
			Logger.displayError("Invalid Login Credentials. Please Try Again.");
		}
  	}


}