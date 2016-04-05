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
		showUsers();
	}

	public void addUser(String newid,String newpassword, User.Role r){
		User myadder=new User(newid,newpassword,r);
				//myadder.save();
				//showUsers();
		
	} 
	public static void showUsers(){
		
		ArrayList<User> shows=new ArrayList();
		shows=User.getAll();
		for(User i:shows){
			System.out.println(i.getUsername());
			System.out.println(i.getPassword());
			System.out.println(i.getRole().toString());}
	
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
			addUser(view.getId(), view.getPassword(),view.getRole());
		}
	}

}