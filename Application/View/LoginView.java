import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class LoginView{
    
    private JFrame f;
    private JPanel p;
    private FlowLayout fl;
    private JLabel idLabel;
    private JLabel passwordLabel;
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;
    
    //Constructor
    public LoginView(){
	   this.f = new JFrame("Login");
	   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   int height = screenSize.height;
	   int width = screenSize.width;
	   f.setSize((width/2) - 600, (height/2) - 250);

	   f.setLocationRelativeTo(null);
	   p = new JPanel();
	   fl = new FlowLayout(FlowLayout.CENTER);
	   idLabel = new JLabel("Enter Employee Id: ");
	   idField = new JTextField(10);
	   passwordLabel = new JLabel("Enter Password: ");
	   passwordField = new JPasswordField(12);
	   loginButton = new JButton("Login");
	   exitButton = new JButton("Exit");
	
	   p.add(idLabel);
	   p.add(idField);
	   p.add(passwordLabel);
	   p.add(passwordField);
	   p.add(loginButton);
	   p.setLayout(fl);
	   p.add(exitButton);

	   f.setSize(200,200);
	   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   f.setContentPane(p);
	   f.setVisible(true);
       f.getRootPane().setDefaultButton(loginButton); //Allows user to hit enter button to login
    }

    //Add ActionListener to buttons
    public void addController(ActionListener controller){
	   exitButton.addActionListener(controller);
	   loginButton.addActionListener(controller);
    }

    //Get the String in the username field
    public String getUsername(){
    	return idField.getText();
    }

    //Get the String in the password field
    public String getPassword(){
    	return passwordField.getText();
    }

    //Close the view and the JFrame
    public void closeFrame(){
    	f.setVisible(false);
    	f.dispose();
    }
}