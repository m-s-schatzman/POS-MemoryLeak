import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageUserController implements ActionListener{

	private ManageUserView view;

	//Handle actions performed in the view
	public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Exit")){
			new POSController();
			view.closeFrame();
		}else if(ac.getActionCommand().equals("Add User")){
			addUser(view.getId(), view.getPassword(), User.Role.Normal);
			view.clearIdPassFields();
		}else if(ac.getActionCommand().equals("Delete")){
			deleteUser(view.getDeleteField());
			view.clearDelField();
		}
	}
	
	//Constructor
	public ManageUserController(){	
		view = new ManageUserView();
		view.addController(this);
		showUsers();
	}

	//Adds user to database
	public void addUser(String newId, String newPassword, User.Role role){
	    if(newId.length()>0 && newPassword.length() >0 ){
	  		User newUser = new User(newId, newPassword, role);
			newUser.save();
			showUsers();
		}
	} 

	//Deletes user with given username from database
	public void deleteUser(String username){
		if(username.length()>0){
			User deletedUser = User.retrieve(username);
			if(deletedUser.getRole() != User.Role.Admin){
				deletedUser.delete();
				showUsers();
			}
		}
	}

	//Adds the list of Users to the view
	private void showUsers(){	
		ArrayList<User> users = new ArrayList();
		users = User.getAll();
		view.showUsers(users);
	}

	//Closes the Controller and it's frame
    public void close(){
    	view.closeFrame();
    }
}