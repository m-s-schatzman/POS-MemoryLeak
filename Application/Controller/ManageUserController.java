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
			addUser(view.getUsername(), view.getPassword(), view.getRole());
			view.clearFields();
		}else if(ac.getActionCommand().equals("Delete")){
			deleteUser(view.getDeleteField());
			view.clearFields();
		}
	}
	
	//Constructor
	public ManageUserController(){	
		view = new ManageUserView();
		view.addController(this);
		showUsers();
	}

	//Adds user to database
	public void addUser(String newUsername, String newPassword, String roleString){
		User.Role role = User.stringToRole(roleString);
	    if(role != null && newUsername.length()>0 && newPassword.length() >0 ){
	  		User newUser = new User(newUsername, newPassword, role);
			newUser.save();
			showUsers();
		}
	} 

	//Deletes user with given username from database
	public void deleteUser(String username){
		if(username.length()>0){
			User deletedUser = User.retrieve(username);
			if(deletedUser != null && deletedUser.getRole() != User.Role.Admin){
				deletedUser.delete();
				showUsers();
			}
		}
	}

	//Adds the list of Users to the view
	private void showUsers(){	
		ArrayList<User> users = new ArrayList<User>();
		users = User.getAll();
		view.showUsers(users);
	}

	//Closes the Controller and it's frame
    public void close(){
    	view.closeFrame();
    }
}