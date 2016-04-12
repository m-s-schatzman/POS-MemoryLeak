import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.DecimalFormat;

//Return items need to be stored in different table

public class ProcessReturnController implements ActionListener{
    
    private Return currentReturn;
    private ProcessReturnView view;

    public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Exit")){
	    	new POSController();
			view.closeFrame();
		}else if(ac.getActionCommand().equals("Add Item")){
			String idString = view.getId();
			String quantityString = view.getQuantity();
			try{
				int id = Integer.parseInt(idString);
				int quantity = Integer.parseInt(quantityString);
				addLineItem(id, quantity);
			}catch(Exception ex){
				Logger.logError(ex.getMessage());
			}
		}else if(ac.getActionCommand().equals("Return")){
			processReturn();
		}
    }

    //Constructor
    public ProcessReturnController(){
		currentReturn = new Return();
		view = new ProcessReturnView();
		view.addController(this);
    }
    
    //Adds the given line item to the current return
    public void addLineItem(int ID, int quantity){
		Item item = Item.retrieve(ID);
		if(item == null){
			return;
		}
		ReturnLineItem lineItem = new ReturnLineItem(quantity, item);
		currentReturn.addLineItem(lineItem);
		view.updateTotalItems(currentReturn.getCartList());
		view.updateTotalCost(currentReturn.getTotal());
    }

    //Removes the given line item from the current return
    public void removeLineItem(int ID, int quantity){
		Item item = Item.retrieve(ID);
		if(item == null){
			return;
		}
		ReturnLineItem lineItem = new ReturnLineItem(quantity, item);
		currentReturn.removeLineItem(lineItem);
    }

    //Process the return and save to database
    public void processReturn(){
		currentReturn.save();
	    printReceipt(currentReturn.getCartList());
		currentReturn = new Return();
		view.clearFields();
    }

    //Print the reciept
    private void printReceipt(String cartList){
    	JFrame receiptFrame = new JFrame("Receipt");
    	receiptFrame.pack();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		receiptFrame.setSize(width/2, height/2);
		receiptFrame.setLocationRelativeTo(null);
    	JPanel receiptPanel = new JPanel();
    	JTextArea finalItems = new JTextArea(cartList);
    	finalItems.setEditable(false);
    	JLabel finalLabel = new JLabel("Total returned:");
    	JTextField finalTotal = new JTextField(5);
    	finalTotal.setEditable(false);
    	JTextField validated = new JTextField(10);
    	finalTotal.setText(""+currentReturn.getTotal());
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	double amountAsk = currentReturn.getTotal();
    	JLabel changeLabel = new JLabel("Total returned: $" + formatter.format(amountAsk));

		finalItems.setColumns(10);
		finalItems.setRows(12);
		receiptPanel.add(finalItems);
		receiptPanel.add(finalLabel);
		receiptPanel.add(finalTotal);
		receiptPanel.add(changeLabel);
    	receiptFrame.setContentPane(receiptPanel);
    	receiptFrame.setVisible(true);
    	receiptFrame.setSize(400,400);
	}
}