import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class ProcessSaleView implements java.util.Observer{
    
    private JFrame f;
    private JPanel p;
    private FlowLayout fl;
    private JTextField tf;
    private JButton exitButton;
    
    public ProcessSaleView(JFrame f){
	this.f = f;
	p = new JPanel();
	fl = new FlowLayout(FlowLayout.CENTER);
	tf = new JTextField("View Skeleton");
	exitButton = new JButton("Exit");
	
	p.setLayout(fl);
	p.add(tf);
	p.add(exitButton);

	f.setSize(400,400);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setContentPane(p);
	f.setVisible(true);
    }

    public void addController(ActionListener controller){
	exitButton.addActionListener(controller);
    }

    public void update(Observable subject, Object subjectChange){
	return;
    }
}