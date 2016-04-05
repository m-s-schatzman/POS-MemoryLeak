import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Need to implement adding and removing users from db according to rubic
//We also need some sort of "role" associated with each user.
//	-Thinking about implementing "level" attribute in db and use admin/normal enum in User class

public class ManageUserController implements ActionListener{

	private ManageUserView view;

	public ManageUserController(JFrame applicationFrame){
		
		view = new ManageUserView(applicationFrame);
		view.addController(this);
	}

	public void addUser(String newid,String newpassword){
		System.out.println(newid);
		System.out.println(newpassword);
	} 

	public static void create() {
    	JFrame applicationFrame = new JFrame("Manage Users");
    	new ManageUserController(applicationFrame);
    }

	public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Exit")){
			POSController.create();
			view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Add User")){
			addUser(view.getId(), view.getPassword());
		}
	}

}