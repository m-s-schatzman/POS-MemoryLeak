import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class ProcessSaleView implements java.util.Observer{
    
    private JFrame f;
    private JPanel p;
    private FlowLayout fl;
    private JTextField tfID;
    private JTextField tfQ;
    public JTextArea totalItems;
    public JTextField totalCost;
    private JButton exitButton;
    private JButton addButton;
    
    public ProcessSaleView(JFrame f){
	this.f = f;
	p = new JPanel();
	fl = new FlowLayout(FlowLayout.CENTER);
	tfID = new JTextField("Item Id");
	tfQ = new JTextField("1");
	totalItems = new JTextArea("Total Items: ");
	totalItems.setBounds(10,10,200,60);
	totalCost = new JTextField("");


	addButton = new JButton("Add Item");
	exitButton = new JButton("Exit");
	
	p.add(totalItems);
	p.add(totalCost);
	p.setLayout(fl);
	p.add(tfID);
	p.add(tfQ);
	p.add(exitButton);
	p.add(addButton);


	f.setSize(400,400);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setContentPane(p);
	f.setVisible(true);
    }

    public void addController(ActionListener controller){
	exitButton.addActionListener(controller);
	addButton.addActionListener(controller);
    }

    public void update(Observable subject, Object subjectChange){
	return;
    }

    public int getId(){
    	return Integer.parseInt(tfID.getText());
    }

    public int getQuantity(){
    	return Integer.parseInt(tfQ.getText());
    }

}