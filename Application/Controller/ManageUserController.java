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

	public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Exit")){
			new POSController();
			view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Add User")){
			addUser(view.getId(), view.getPassword(), User.Role.Normal);
			view.clearIdPassFields();
		}

		else if(ac.getActionCommand().equals("Delete"))
		{
			deleteUser(view.getDeleteField());
			view.clearDelField();
		}
	}
	
	public ManageUserController(JFrame applicationFrame){
		
		view = new ManageUserView(applicationFrame);
		view.addController(this);
		showUsers();
	}

	public void addUser(String newId, String newPassword, User.Role role){
	    	if(newId.length()>0 && newPassword.length() >0 ){
	       		 User newUser = new User(newId, newPassword, role);
				newUser.save();
				showUsers();}
	} 

	public void deleteUser(String id)
	{	if(id.length()>0){
			User deletedUser = User.retrieve(id);
			if(deletedUser.getRole() != User.Role.Admin){
					deletedUser.delete();
					showUsers();
				}
		}
	}
	private void showUsers(){	
		ArrayList<User> users = new ArrayList();
		users = User.getAll();
		view.showUsers(users);
	}
	public static void create() {
    	JFrame applicationFrame = new JFrame("Manage Users");
    	new ManageUserController(applicationFrame);
    }
    public void close(){
    view.closeFrame();
    }
}