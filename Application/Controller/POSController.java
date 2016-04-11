import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POSController implements ActionListener{
	
	private POSView view;

	//Handles action performed in view
	public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Process Sale")){
        	ProcessSaleController.create();
        	view.closeFrame();
		}else if(ac.getActionCommand().equals("Process Rental")){
			//ProcessRentalController.create();
			//view.closeFrame();
		}else if(ac.getActionCommand().equals("Process Return")){
			ProcessReturnController.create();
			view.closeFrame();
		}else if(ac.getActionCommand().equals("Manage Users")){
			ManageUserController.create();
        	view.closeFrame();
		}else if(ac.getActionCommand().equals("Logout")){
			CurrentUser.logout();
			new LoginController();
        	view.closeFrame();
		}
	}

	//Constructor
	public POSController(){
		view = new POSView();
		view.addController(this);
	}
}