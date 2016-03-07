import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class ProcessSaleView{
    
    private JFrame f;
    private JPanel p;
    private FlowLayout fl;
    private JLabel jlID;
    private JLabel jlQ;
    private JTextField tfID;
    private JTextField tfQ;
    private JLabel totalItemsLabel;
    private JTextArea totalItems;
    private JLabel totalCostLabel;
    private JTextField totalCost;
    private JButton exitButton;
    private JButton addButton;
    
    public ProcessSaleView(JFrame f){
	this.f = f;
	p = new JPanel();
	fl = new FlowLayout(FlowLayout.CENTER);
	jlID = new JLabel("Item ID: ");
	tfID = new JTextField(10);
	jlQ = new JLabel("Quantity: ");
	tfQ = new JTextField(10);
	totalItemsLabel = new JLabel("Total Items: ");
	totalItems = new JTextArea("");
	totalItems.setBounds(10,10,200,60);
	totalCostLabel = new JLabel("Total Cost: ");
	totalCost = new JTextField(10);


	addButton = new JButton("Add Item");
	exitButton = new JButton("Exit");
	
	p.add(jlID);
	p.add(tfID);
	p.add(jlQ);
	p.add(tfQ);
	p.add(addButton);
	p.add(exitButton);
	p.add(totalItemsLabel);
	p.add(totalItems);
	p.add(totalCostLabel);
	p.add(totalCost);
	p.setLayout(fl);


	f.setSize(400,400);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setContentPane(p);
	f.setVisible(true);
    }

    public void addController(ActionListener controller){
	exitButton.addActionListener(controller);
	addButton.addActionListener(controller);
    }

    public int getId(){
    	return Integer.parseInt(tfID.getText());
    }

    public int getQuantity(){
    	return Integer.parseInt(tfQ.getText());
    }

    public void updateTotalItems(String cartList){
    	//totalItems.append("\n" + itemName + " " + quantity);
        totalItems.setText(cartList);
    }

    public void updateTotalCost(double total){
    	totalCost.setText(""+total);
    }

}