import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POSController implements ActionListener{
	private POSView view;

	public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Process Sale")){
        	ProcessSaleController.create();
        	view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Process Rental")){
			//ProcessRentalController.create();
			//view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Process Return")){
			ProcessReturnController.create();
			view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Manage Users")){
			ManageUserController.create();
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