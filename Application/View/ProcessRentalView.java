/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Qing
 * @author qiy317
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class ProcessRentalView {
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
    private JButton checkoutButton;
    
    public ProcessRentalView(JFrame f){
       this.f = f;
       f.pack();
       p = new JPanel();
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       int height = screenSize.height;
       int width = screenSize.width;
       f.setSize(width/2, height/2);

       f.setLocationRelativeTo(null);
       fl = new FlowLayout(FlowLayout.CENTER);
       jlID = new JLabel("Item ID: ");
       tfID = new JTextField(5);
       jlQ = new JLabel("Quantity: ");
       tfQ = new JTextField(5);
       totalItemsLabel = new JLabel("Total Items:");
       totalItems = new JTextArea("");
       totalItems.setEditable(false);
       totalItems.setColumns(10);
       totalItems.setRows(12);
       totalCostLabel = new JLabel("Total Cost:");
       totalCost = new JTextField(10);
       totalCost.setEditable(false);


       addButton = new JButton("Add Item");
       exitButton = new JButton("Exit");
       checkoutButton = new JButton("Checkout");
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
       p.add(checkoutButton);
       p.setLayout(fl);

       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f.setContentPane(p);
       f.setVisible(true);
        
    }
    
    public void addController(ActionListener controller){
       exitButton.addActionListener(controller);
       addButton.addActionListener(controller);
       checkoutButton.addActionListener(controller);
   }

    public int getId(){
    	return Integer.parseInt(tfID.getText());
    }

    public int getQuantity(){
    	return Integer.parseInt(tfQ.getText());
    }
    
    public void updateTotalItems(String cartList){
        totalItems.setText(cartList);
    }

    public void updateTotalCost(double total){
    	totalCost.setText(""+total);
    }

	public void returnToRental()
    {
    	tfID.setText("");
    	tfQ.setText("");
    	totalItems.setText("");
    	totalCost.setText("");
    }

  public void closeFrame(){
      f.setVisible(false);
      f.dispose();
  }
    
}
