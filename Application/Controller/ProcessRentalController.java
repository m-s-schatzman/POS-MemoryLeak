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

public class ProcessRentalController implements ActionListener {

    private Rental currentRental;
    private ProcessRentalView view;

    public void actionPerformed(ActionEvent ac) {
        if (ac.getActionCommand().equals("Exit")) {
            System.exit(1);
        } else if (ac.getActionCommand().equals("Add Item")) {
            addLineItem(view.getId(), view.getQuantity());
        } else if (ac.getActionCommand().equals("Checkout")) {
            printReceipt(currentRental.getCartList());
            currentRental = new Rental();
            view.returnToRental(); //////
        }

    }

    public ProcessRentalController(JFrame applicationFrame) {
        currentRental = new Rental();
        view = new ProcessRental(applicationFrame);
        view.addController(this);

    }

    public void createRental() {
        currentRental = new Rental();
    }

    public void addLineItem(int ID, int quantity) {
        Item item = Item.scanItem(ID);
        LineItem lineItem = new LineItem(quantity, item);
        currentRental.addLineItem(lineItem);
        view.updateTotalItems(currentRental.getCartList());
        view.updateTotalCOst(currentRental.getTotal());
    }

    public void removeLineItem(int ID, int quantity) {
        Item item = Item.scanItem(ID);
        LineItem lineItem = new LineItem(quantity, item);
        currentRental.removeLineItem(lineItem);
    }

    public boolean processRental(int cardNumber) {
        double total = currentRental.getRental();
        if (true == PaymentAuthorizer.PaymentAuth(cardNumber, total)) {
            currentRenal.save();
            return true;
        }
        return false;
    }
    
    public void printReceipt(String cartList){
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
        finalTotal.setText(""+currentRental.getToatal());
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
    
    
    

