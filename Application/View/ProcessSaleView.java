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
    private JLabel paymentOption;
    private final JRadioButton ccOption;
    private final JRadioButton cashOption;
    private JButton exitButton;
    private JButton addButton;
    private JButton checkoutButton;
    private JLabel cashFieldLabel;
    private JTextField cashField;
    private JLabel ccFieldLabel;
    private JTextField ccField;

    
    public ProcessSaleView(JFrame f){
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
       paymentOption = new JLabel("Select Payment Method: ");

       ccOption = new JRadioButton("Credit Card", false);
       cashOption = new JRadioButton("Cash", false);
       ButtonGroup group = new ButtonGroup();
       group.add(ccOption);
       group.add(cashOption);

       cashFieldLabel = new JLabel("Given cash amount: $");
       cashField = new JTextField(3);
       ccFieldLabel = new JLabel("Enter credit card number: ");
       ccField = new JTextField(11);


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
       p.add(paymentOption);
       p.add(ccOption);
       p.add(cashOption);
       p.add(checkoutButton);
       p.setLayout(fl);


	   //f.setSize(400,400);
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f.setContentPane(p);
       f.setVisible(true);
   }

   public void addController(ActionListener controller){
       exitButton.addActionListener(controller);
       addButton.addActionListener(controller);
       checkoutButton.addActionListener(controller);
       ccOption.addActionListener(controller);
       cashOption.addActionListener(controller);
   }

    public String getId(){
    	return tfID.getText();
    }

    public String getQuantity(){
    	return tfQ.getText();
    }

    public String getCashAmount(){
      return cashField.getText();
    }

    public String getCcNumber(){
      return ccField.getText();
    }

    public void updateTotalItems(String cartList){
    	//totalItems.append("\n" + itemName + " " + quantity);
        totalItems.setText(cartList);
    }

    public void updateTotalCost(double total){
    	totalCost.setText(""+total);
    }

    public void showCashField()
    {
      p.remove(ccFieldLabel);
      p.remove(ccField);
      p.add(cashFieldLabel);
      p.add(cashField);
      p.setLayout(fl);
      f.setContentPane(p);
    }

    public void showCCField()
    {
      p.remove(cashFieldLabel);
      p.remove(cashField);
      p.add(ccFieldLabel);
      p.add(ccField);
      p.setLayout(fl);
      f.setContentPane(p);
    }

	public void returnToSale()
    {
    	tfID.setText("");
    	tfQ.setText("");
    	totalItems.setText("");
    	totalCost.setText("");
      cashField.setText("");
      ccField.setText("");
    }
  

  public void closeFrame(){
    f.setVisible(false);
    f.dispose();
  }
}