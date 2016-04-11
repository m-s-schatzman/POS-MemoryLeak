import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginController implements ActionListener{

	private LoginView view;

	//Routes the actions performed in the view to their respective actions in the controller
    public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Exit")){
	    	System.exit(1);
		}else if(ac.getActionCommand().equals("Login")){
			login();
    	}
	}

	//Constuctor
	public LoginController(){
		view = new LoginView();
		view.addController(this);
    }

  	//Attempt to login user into the application
  	private void login(){
  		String userName = view.getUserName();
		String password = view.getPassword();
		if(CurrentUser.login(userName, password)){
			new POSController();
        	view.closeFrame();
		}
		else
		{
			Logger.displayError("Invalid Login Credentials. Please Try Again.");
		}
  	}
}