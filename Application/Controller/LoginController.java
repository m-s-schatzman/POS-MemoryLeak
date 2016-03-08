import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginController extends java.util.Observable implements ActionListener{

	private LoginView view;
	private String userName;
	private String password;
	private boolean authenticated;
	private Database db = Database.getDatabase();

    public void actionPerformed(ActionEvent ac){
	if(ac.getActionCommand().equals("Exit")){
	    System.exit(1);
	}
	if(ac.getActionCommand().equals("Login")){
		userName = view.getUserName();
		password = view.getPassword();
		authenticated = db.authenticate(userName, password);
		if(authenticated)
		{
			JFrame applicationFrame = new JFrame("Memory Leak POS");
        	ProcessSaleController controller = new ProcessSaleController(applicationFrame);
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

	//else if createSale
	//else if addLineItem
	//else if removeLineItem
	//else if processSale

    public LoginController(JFrame applicationFrame){
	view = new LoginView(applicationFrame);
	view.addController(this);
    }
}