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
			ProcessRentalController.create();
			view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Process Return")){
			//ProcessReturnController.create();
			//view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Manage Users")){
			ManageUserController.create();
        	view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Logout")){
			CurrentUser.logout();
			LoginController.create();
        	view.closeFrame();
		}
	}

	private POSController(JFrame applicationFrame){
		view = new POSView(applicationFrame);
		view.addController(this);
	}

	//Creates a new view and controller for POS
	public static void create(){
		JFrame applicationFrame = new JFrame("Memory Leak POS");
       	new POSController(applicationFrame);
	}
}