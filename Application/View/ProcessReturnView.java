import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class ProcessReturnView{

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
    private JButton returnButton;

    
    public ProcessReturnView(JFrame f){
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
       totalCostLabel = new JLabel("Total returned:");
       totalCost = new JTextField(10);
       totalCost.setEditable(false);


       addButton = new JButton("Add Item");
       exitButton = new JButton("Exit");
       returnButton = new JButton("Return");

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
       p.add(returnButton);
       p.setLayout(fl);


	   //f.setSize(400,400);
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f.setContentPane(p);
       f.setVisible(true);
   }

   public void addController(ActionListener controller){
       exitButton.addActionListener(controller);
       addButton.addActionListener(controller);
       returnButton.addActionListener(controller);
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

	public void returnToSale()
    {
    	tfID.setText("");
    	tfQ.setText("");
    	totalItems.setText("");
    	totalCost.setText("");
    }
}