import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class LoginView implements java.util.Observer{
    
    private JFrame f;
    private JPanel p;
    private FlowLayout fl;
    private JLabel label1;
    private JLabel label2;
    private JTextField empId;
    private JPasswordField password;
    private JButton loginButton;
    private JButton exitButton;
    
    public LoginView(JFrame f){
	this.f = f;
	p = new JPanel();
	fl = new FlowLayout(FlowLayout.CENTER);
	label1 = new JLabel("Enter Employee Id: ");
	empId = new JTextField(10);
	label2 = new JLabel("Enter Password: ");
	password = new JPasswordField(12);



	loginButton = new JButton("Login");
	exitButton = new JButton("Exit");
	
	p.add(label1);
	p.add(empId);
	p.add(label2);
	p.add(password);
	p.add(loginButton);
	p.setLayout(fl);
	p.add(exitButton);


	f.setSize(200,200);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setContentPane(p);
	f.setVisible(true);
    }

    public void addController(ActionListener controller){
	exitButton.addActionListener(controller);
	loginButton.addActionListener(controller);
    }

    public void update(Observable subject, Object subjectChange){
	return;
    }
}