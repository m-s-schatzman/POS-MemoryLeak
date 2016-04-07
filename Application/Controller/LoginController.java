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
		/*
		else
		{
			createInvalidMessage();
		} */
  	}

/*
  	private void createInvalidMessage()
  	{
  		f = new JFrame("Invalid Credentials");
  		JPanel p = new JPanel();
    	f.pack();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int height = screenSize.height;
	    int width = screenSize.width;
	    f.setSize((width/2) - 600, (height/2) - 250);
	   	f.setLocationRelativeTo(null);
    	JPanel invalidPanel = new JPanel();
    	JLabel message = new JLabel("Invalid Login. Please try again");

    	p.add(message);
   	    f.setSize(200,200);
	    f.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	    f.setContentPane(p);
	    f.setVisible(true);
  	}
  	*/


}