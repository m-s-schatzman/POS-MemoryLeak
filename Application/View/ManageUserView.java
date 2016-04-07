import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class ManageUserView{

 private JFrame f;
    private JPanel p;
    private FlowLayout fl;
    private JLabel id;
    private JLabel pwd;
    private JTextField tfID;
    private JTextField tfPwd;
    private JTextArea totalUsers;
    private JLabel delLabel;
    private JTextField delField;
    private JButton exitButton;
    private JButton addButton;
    private JButton deleteButton;

    
    public ManageUserView(JFrame f){
       this.f = f;
       f.pack();
       p = new JPanel();
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       int height = screenSize.height;
       int width = screenSize.width;
       f.setSize(width/2, height/2);

       f.setLocationRelativeTo(null);
       fl = new FlowLayout(FlowLayout.CENTER);
       id = new JLabel("User ID:");
       tfID = new JTextField(5);
       pwd = new JLabel("User Password:");
       tfPwd = new JTextField(5);
       totalUsers = new JTextArea("");
       totalUsers.setEditable(false);
       totalUsers.setColumns(10);
       totalUsers.setRows(12);
       delField = new JTextField(10);
       


       addButton = new JButton("Add User");
       exitButton = new JButton("Exit");
       deleteButton = new JButton("Delete User");

       p.add(id);
       p.add(tfID);
       p.add(pwd);
       p.add(tfPwd);
       p.add(addButton);
       p.add(exitButton);
       p.add(totalUsers);
       p.add(delField);
       p.add(deleteButton);
       p.setLayout(fl);


	   //f.setSize(400,400);
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f.setContentPane(p);
       f.setVisible(true);
   }

   public void addController(ActionListener controller){
       exitButton.addActionListener(controller);
       addButton.addActionListener(controller);
       deleteButton.addActionListener(controller);
   }
      public String getId(){
    	return tfID.getText();
    }

    public String getPassword(){
    	return tfPwd.getText();
    }

    public User.Role getRole(){
    	return User.stringToRole("Normal");
      }//TODO fix this when the UI is ready for it. 

public void showUser(User user)
{
  totalUsers.append(user.getUsername() + "\n");
}

public void clearIdPassFields()
{
  tfID.setText("");
  tfPwd.setText("");
}


    public void closeFrame(){
      f.setVisible(false);
      f.dispose();
    }
}
 	