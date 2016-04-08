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
import java.awt.event.ActionEvent;

//When it prints receipt, it will need to print the rental rules clearly according to rubic
//Possibly use a simple formula of x*price per day it is rented...

public class ProcessRentalController implements ActionListener {

    private Rental currentRental;
    private ProcessRentalView view;

    public void actionPerformed(ActionEvent ac) {
        if (ac.getActionCommand().equals("Exit")) {
            POSController.create();
            view.closeFrame();
        } else if (ac.getActionCommand().equals("Add Item")) {
            addLineItem(view.getId(), view.getQuantity());
        } else if (ac.getActionCommand().equals("Checkout")) {
            printReceipt(currentRental.getCartList());
            currentRental = new Rental();
            view.returnToRental(); 
        }

    }

    private ProcessRentalController(JFrame applicationFrame) {
        currentRental = new Rental();
        view = new ProcessRentalView(applicationFrame);
        view.addController(this);

    }

    private void createRental() {
        currentRental = new Rental();
    }

    //Needs to be changed to handle rental line items, not line items
    private void addLineItem(int ID, int quantity) {
        Item item = Item.retrieve(ID);
        LineItem lineItem = new LineItem(quantity, item);
//        currentRental.addLineItem(lineItem);
        view.updateTotalItems(currentRental.getCartList());
        view.updateTotalCost(currentRental.getTotal());
    }

    //Needs to be changed to handle rental line items, not line items
    private void removeLineItem(int ID, int quantity) {
        Item item = Item.retrieve(ID);
        LineItem lineItem = new LineItem(quantity, item);
 //       currentRental.removeLineItem(lineItem);
    }

    private boolean processRental(String cardNumber) {
        double total = currentRental.getTotal();
        if (true == PaymentAuthorizer.authorizePayment(cardNumber, total)) {
           // currentRental.save();
            return true;
        }
        return false;
    }

    public static void create() {
        JFrame applicationFrame = new JFrame("Process Rental");
        new ProcessRentalController(applicationFrame);
    }
    
    private void printReceipt(String cartList){
        JFrame receiptFrame=new JFrame("Receipt");
        receiptFrame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		receiptFrame.setSize(width/2, height/2);
		receiptFrame.setLocationRelativeTo(null);
    	JPanel receiptPanel = new JPanel();
    	JTextArea finalItems = new JTextArea(cartList);
    	finalItems.setEditable(false);
    	JLabel finalLabel = new JLabel("Total Cost:");
    	JTextField finalTotal = new JTextField(5);
    	finalTotal.setEditable(false);
    	JTextField validated = new JTextField(10);
        finalTotal.setText(""+currentRental.getTotal());
        validated.setText("Payment Validated");
    	validated.setEditable(false);
		finalItems.setColumns(10);
		finalItems.setRows(12);
		receiptPanel.add(finalItems);
		receiptPanel.add(finalLabel);
		receiptPanel.add(finalTotal);
		receiptPanel.add(validated);
    	receiptFrame.setContentPane(receiptPanel);
    	receiptFrame.setVisible(true);
    	receiptFrame.setSize(400,400);
	}     
}