/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

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
			String username = view.getUsername();
			String password = view.getPassword();
			login(username, password);
    	}
	}

	//Constuctor
	public LoginController(){
		view = new LoginView();
		view.addController(this);
    }

  	//Attempt to login user into the application
  	public POSController login(String username, String password){
  		if(username == null || password == null){
  			return null;
  		}
		if(CurrentUser.login(username, password)){
        	view.closeFrame();
        	return new POSController();
		}else{
			Logger.displayError("Invalid Login Credentials. Please Try Again.");
		}
		return null;
  	}

  	//Closes the controller and it's frame
  	public void close(){
  		view.closeFrame();
  	}
}