import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

 public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Exit")){
	    	System.exit(1);
		}
		else if(ac.getActionCommand().equals("Add User")){
			addUser(view.getId(), view.getPassword());
		}
}

}