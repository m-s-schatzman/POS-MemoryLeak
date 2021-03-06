/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POSController implements ActionListener{
	
	private POSView view;

	//Handles action performed in view
	public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Process Sale")){
        	new ProcessSaleController();
        	view.closeFrame();
		}else if(ac.getActionCommand().equals("Process Rental")){
			new ProcessRentalController();
			view.closeFrame();
		}else if(ac.getActionCommand().equals("Process Return")){
			new ProcessReturnController();
			view.closeFrame();
		}else if(ac.getActionCommand().equals("Manage Users")){
			new ManageUserController();
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

	//Closes Controller and it's JFrame
	public void close(){
		view.closeFrame();
	}
}