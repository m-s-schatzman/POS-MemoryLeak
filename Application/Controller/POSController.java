import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POSController implements ActionListener{
	private POSView view;

	public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Process Sale")){
			JFrame applicationFrame = new JFrame("Process Sale");
        	ProcessSaleController controller = new ProcessSaleController(applicationFrame);
        	view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Process Rental")){
			//Add process rental controller
		}
		else if(ac.getActionCommand().equals("Process Return")){
			//Add Process return controller
		}
		else if(ac.getActionCommand().equals("Manage Users")){
			JFrame applicationFrame = new JFrame("Manage Users");
        	ManageUserController controller = new ManageUserController(applicationFrame);
        	view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Logout")){
			//NEED TO LOGOUT CURRENT USER ONCE IMPLEMENTED
			JFrame applicationFrame = new JFrame("Login");
        	LoginController controller = new LoginController(applicationFrame);
        	view.closeFrame();
		}
	}

	public POSController(JFrame applicationFrame){
		view = new POSView(applicationFrame);
		view.addController(this);
	}
}