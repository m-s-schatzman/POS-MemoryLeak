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
    private JLabel paymentOption;
    private final JRadioButton ccOption;
    private final JRadioButton cashOption;
    private JButton exitButton;
    private JButton addButton;
    private JButton returnButton;
    private JLabel cardLabel;
    private JTextField card;

    //Constructor
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
        paymentOption = new JLabel("Select Payment Method: ");
        ccOption = new JRadioButton("Credit Card", false);
        cashOption = new JRadioButton("Cash", false);
        ButtonGroup group = new ButtonGroup();
        group.add(ccOption);
        group.add(cashOption);

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
        p.add(paymentOption);
        p.add(ccOption);
        p.add(cashOption);
        p.add(returnButton);
        p.setLayout(fl);


        //f.setSize(400,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(p);
        f.setVisible(true);
    }

    //Adds an ActionListener to each button
    public void addController(ActionListener controller){
       exitButton.addActionListener(controller);
       addButton.addActionListener(controller);
       returnButton.addActionListener(controller);
       ccOption.addActionListener(controller);
       cashOption.addActionListener(controller);
    }

    //Gets the String from the id field
    public String getId(){
        return tfID.getText();
    }

    //Gets the String in the quantity field
    public String getQuantity(){
        return tfQ.getText();
    }

    //Updates the list of items
    public void updateTotalItems(String cartList){
        totalItems.setText(cartList);
    }

    //Update the total cost field
    public void updateTotalCost(double total){
        totalCost.setText(""+total);
    }

    //Adds the card field
    public void addCardField(){
        if (cardLabel==null){
            cardLabel = new JLabel("Card Number: ");
            card = new JTextField(10);
            p.add(cardLabel);
            p.add(card);
            p.setLayout(fl);
            f.setContentPane(p);
        }
    }

    //Removes the card field
    public void removeCardField(){
        p.remove(card);
        p.remove(cardLabel);
        p.setLayout(fl);
        f.setContentPane(p);
    }

    //Gets the card number from the card number field
    public String getCardNum(){
        return card.getText();
    }

    //Clears all of the text fields
    public void clearFields(){
        tfID.setText("");
        tfQ.setText("");
        totalItems.setText("");
        totalCost.setText("");
    }

    //Closes the view and JFrame
    public void closeFrame(){
        f.setVisible(false);
        f.dispose();
    }
}