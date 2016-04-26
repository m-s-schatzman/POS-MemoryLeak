/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.DecimalFormat;


//Need to implement multiple payment types (cash and credit card) according to rubic

public class ProcessSaleController implements ActionListener{
    private Sale currentSale;
    private ProcessSaleView view;
    private String paymentType;
    private JLabel paymentLabel;
    private boolean inCash;

    //Action Event handler
    public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Exit")){
	    	new POSController();
			view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Add Item")){
			int id = 0;
			int quantity = 0;
			try {
   				 id = Integer.parseInt(view.getId());
   				 quantity = Integer.parseInt(view.getQuantity());
   				 addLineItem(id, quantity);
  			} catch (NumberFormatException e) {
    			Logger.logError("Invalid input. Try Again.");
    			Logger.displayError("Invalid input. Try Again.");
  			}
  		}
		else if(ac.getActionCommand().equals("Checkout"))
		{
			if(inCash)
			{
				try {
						int amountGiven = Integer.parseInt(view.getCashAmount());
						processSale(amountGiven, inCash);
					}
					catch (NumberFormatException e) 
  					{
    					Logger.logError("Cash not given.");
    					Logger.displayError("You have opted to pay in cash so please do so.");
  					}
			}
			else
			{
				String ccNumber = view.getCcNumber();
				if(PaymentAuthorizer.authorizePayment(ccNumber))
				{
					double ccNumberInt = Double.parseDouble(ccNumber);
					processSale(ccNumberInt, inCash);
				}
				else
				{
					Logger.logError("Customer has invalid Credit Card.");
					Logger.displayError("Payment not accepted. Please try again, or use alternative payment method.");
				}
			}
		}
		else
		{
			AbstractButton rButton = (AbstractButton) ac.getSource();
			paymentType = rButton.getText();
			if(paymentType.equals("Cash"))
			{
				inCash = true;
				view.showCashField();
			}
			else if(paymentType.equals("Credit Card"))
			{
				inCash = false;
				view.showCCField();
			}
        	
		}
    }

    //Constructor
    public ProcessSaleController(){
		currentSale = new Sale();
		view = new ProcessSaleView();
		view.addController(this);
    }

    //Add line item to the current sale
    private void addLineItem(int ID, int quantity){
		if(ID<=0 && quantity<=0){
			return;
		}
		Item item = Item.retrieve(ID);
		if(item == null){
			return;
		}
		//CAM addition to make sure inventory updates are within range
		int amountLeft= Inventory.getInventory().getInventoryItemCount(item);
    	if(quantity > amountLeft){
    		Logger.displayError("Quantity of Item is too high for the Store's inventory \n" + "There are only " + amountLeft +" of them.");
    		return;
    	}
		LineItem lineItem = new LineItem(quantity, item);
		currentSale.addLineItem(lineItem);
		view.updateTotalItems(currentSale.getCartList());
		view.updateTotalCost(currentSale.getTotal());
	}

	//Save the sale in db and remove items from inventory, print receipt
    private void processSale(double payment, boolean isCash){
	   	currentSale.save();
	    printReceipt(currentSale.formatReceiptList(), payment, isCash);
		currentSale = new Sale();
		view.clearFields();
    }

    //Print the receipt with given sale
    private void printReceipt(String receiptList, double payment, boolean isCash){
    	JFrame receiptFrame = new JFrame("Receipt");
    	receiptFrame.pack();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		receiptFrame.setSize(width/2, height/2);
		receiptFrame.setLocationRelativeTo(null);
		double tax = TaxCalculator.getSalesTax(currentSale);
		double total = currentSale.getTotal() + tax;
		NumberFormat formatter = new DecimalFormat("#0.00");
    	JPanel receiptPanel = new JPanel();
    	JTextArea finalItems = new JTextArea(receiptList);
    	finalItems.setEditable(false);
    	JLabel taxTotal = new JLabel("Tax Amount: $" + formatter.format(tax));
    	JLabel finalTotal = new JLabel("Total Cost: $" + formatter.format(total));
    	JTextField validated = new JTextField(11);
    	if(isCash)
    	{     
    		paymentLabel = new JLabel("Your change: $" + formatter.format(payment - total));
    	}
    	else
    	{
    		validated.setText("Credit Card validated");
    	}
    	validated.setEditable(false);
		finalItems.setColumns(10);
		finalItems.setRows(12);
		receiptPanel.add(finalItems);
		receiptPanel.add(taxTotal);
		receiptPanel.add(finalTotal);
		if(paymentLabel!=null)
		{
			receiptPanel.add(paymentLabel);
		}
		if(validated.getText().equals("Credit Card validated"))
		{
			receiptPanel.add(validated);
		}
    	receiptFrame.setContentPane(receiptPanel);
    	receiptFrame.setVisible(true);
    	receiptFrame.setSize(400,400);
	}
}