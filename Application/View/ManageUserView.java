/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

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
    private JPasswordField password;
    private JTextArea totalUsers;
    private JLabel delLabel;
    private JTextField delField;
    private JButton exitButton;
    private JButton addButton;
    private JButton deleteButton;


    //Constructor
    public ManageUserView(){
        this.f = new JFrame("Manage Users");
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
        password = new JPasswordField(5);
        totalUsers = new JTextArea("");
        totalUsers.setEditable(false);
        totalUsers.setColumns(10);
        totalUsers.setRows(12);
        delField = new JTextField(10);



        addButton = new JButton("Add User");
        exitButton = new JButton("Exit");
        deleteButton = new JButton("Delete");

        p.add(id);
        p.add(tfID);
        p.add(pwd);
        p.add(password);
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

    //Add ActionListener to buttons
    public void addController(ActionListener controller){
        exitButton.addActionListener(controller);
        addButton.addActionListener(controller);
        deleteButton.addActionListener(controller);
    }

    //Gets the text in username field
    public String getUsername(){
        return tfID.getText();
    }

    //Gets the text in the password field
    public String getPassword(){
        return new String(password.getPassword());
    }

    //Gets the text in the password field
    public String getDeleteField(){
        return delField.getText();
    }

    //Gets the role
    public String getRole(){
        return "Normal";
    }//TODO fix this when the UI is ready for it. 

    //Display the list of Users in the pane
    public void showUsers(ArrayList<User> users){
        totalUsers.setText("");
        for(int i = 0; i < users.size(); i++){
            totalUsers.append(users.get(i).getUsername() + " : "+users.get(i).getRole().toString()+"\n");
        }
    }

    //Clear all of the text fields
    public void clearFields(){
        tfID.setText("");
        password.setText("");
        delField.setText("");
    }

    //Close the View and JFrame
    public void closeFrame(){
        f.setVisible(false);
        f.dispose();
    }
}