import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class ProcessRentalView {
    private JFrame f;
    private JPanel p;
    private FlowLayout fl;

    private JLabel idLabel;
    private JLabel quantityLabel;
    private JLabel dateLabel;
    private JTextField idTextField;
    private JTextField quantityTextField;
    private JTextField dateTextField;

    private JLabel totalItemsLabel;
    private JTextArea totalItems;
    private JLabel totalCostLabel;
    private JTextField totalCost;
    private JButton exitButton;
    private JButton addButton;
    private JButton checkoutButton;

    //Constructor
    public ProcessRentalView(){
        this.f = new JFrame("Process Rental");
        f.pack();
        p = new JPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        f.setSize(width/2, height/2);

        f.setLocationRelativeTo(null);
        fl = new FlowLayout(FlowLayout.CENTER);
        idLabel = new JLabel("Item ID: ");
        idTextField = new JTextField(5);
        quantityLabel = new JLabel("Quantity: ");
        quantityTextField = new JTextField(5);
        dateLabel = new JLabel("Date(MM/DD/YYYY): ");
        dateTextField = new JTextField(8);
        dateTextField.setText("");
        totalItemsLabel = new JLabel("Items:");
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
        p.add(idLabel);
        p.add(idTextField);
        p.add(quantityLabel);
        p.add(quantityTextField);
        p.add(dateLabel);
        p.add(dateTextField);
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

    //Constructor
    public void addController(ActionListener controller){
        exitButton.addActionListener(controller);
        addButton.addActionListener(controller);
        checkoutButton.addActionListener(controller);
    }

    //returns string in the ID field
    public String getId(){
        return idTextField.getText();
    }

    //Returns the string in the ID field
    public String getQuantity(){
        return quantityTextField.getText();
    }

    //returns date in the date field
    public String getDate(){
        return dateTextField.getText();
    }

    //update the list of Items with given string
    public void updateTotalItems(String cartList){
        totalItems.setText(cartList);
    }

    //Update the total cost of the purchase
    public void updateTotalCost(double total){
        totalCost.setText(""+total);
    }

    //Clear text fields
    public void returnToRental(){
        idTextField.setText("");
        quantityTextField.setText("");
        dateTextField.setText("MM/DD/YYYY");
        totalItems.setText("");
        totalCost.setText("");
    }

    //Close the view
    public void closeFrame(){
        f.setVisible(false);
        f.dispose();
    }
}
