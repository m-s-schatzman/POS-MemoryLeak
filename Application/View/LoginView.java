import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class LoginView implements java.util.Observer{
    
    private JFrame f;
    private JPanel p;
    private FlowLayout fl;
    private JTextField empId;
    private JButton loginButton;
    private JButton exitButton;
    
    public LoginView(JFrame f){
	this.f = f;
	p = new JPanel();
	fl = new FlowLayout(FlowLayout.CENTER);
	empId = new JTextField("Employee Id");


	loginButton = new JButton("Login");
	exitButton = new JButton("Exit");
	
	p.add(loginButton);
	p.setLayout(fl);
	p.add(exitButton);


	f.setSize(400,400);
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