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

//Return items need to be stored in different table

public class ProcessReturnController implements ActionListener{
    
    private Return currentReturn;
    private ProcessReturnView view;
    private boolean isCash;
    private boolean isCashBack;
    private boolean showCard = false;
    private boolean showCashField = false;
    private String card = "";
    

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
			if(isCash){
				try {
					double amountAsk = currentReturn.getTotal();
					processReturn(amountAsk, isCash);
				}catch (NumberFormatException e) {
    				Logger.logError(e.getMessage());
    			}
			}else if(isCashBack){
				try {
					card = view.getCardNum();
					double amountCashBack = Double.parseDouble(view.getCashAmount());
					if(PaymentAuthorizer.authorizePayment(card)){
						processReturn(amountCashBack, isCash);
					}else{
						Logger.logError("Customer has invalid Credit Card.");
						Logger.displayError("Payment not accepted. Please try again, or use alternative payment method.");
					}
				}catch (NumberFormatException e) {
    				Logger.logError(e.getMessage());
    			}
			}else {
				card = view.getCardNum();
				if(PaymentAuthorizer.authorizePayment(card)){
					double amountAsk = currentReturn.getTotal();
					processReturn(amountAsk, isCash);
				}else{
					Logger.logError("Customer has invalid Credit Card.");
					Logger.displayError("Payment not accepted. Please try again, or use alternative payment method.");
				}
			}

		}else {
			AbstractButton rButton = (AbstractButton) ac.getSource();
			String paymentType = rButton.getText();
			if(paymentType.equals("Cash")){
				isCash = true;
				isCashBack = false;
				if (showCard) {
					view.removeCardField();
					showCard = false;
				} 
				if (showCashField){
					view.removeCashField();
					showCashField = false;
				}
			}else if(paymentType.equals("Credit Card")){
				isCash = false;
				isCashBack = false;
				if(!showCard) {
					view.addCardField();
					showCard = true;
				}
				if (showCashField){
					view.removeCashField();
					showCashField = false;
				}
			}else if(paymentType.equals("Cash Back")){
				isCashBack = true;
				isCash = false;
				if(!showCard) {
					view.addCardField();
					showCard = true;
				}
				if(!showCashField){
					view.addCashField();
					showCashField = true;
				}
			}			
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
		//TODO: this needs to take damaged from view
		ReturnLineItem lineItem = new ReturnLineItem(quantity, item, false);
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
		//TODO: this needs to take damaged from view
		ReturnLineItem lineItem = new ReturnLineItem(quantity, item, true);
		currentReturn.removeLineItem(lineItem);
    }

    //Process the return and save to database
    public void processReturn(double amountAsk, boolean isCash){
    	// save for cash back?
    	if(isCashBack){
    		printCashBackComfirm(amountAsk);
    	} else{
			currentReturn.save();
	    	printReceipt(currentReturn.formatReceiptList(),amountAsk,isCash);
			currentReturn = new Return();
		}
		view.clearFields();
    }

    //print confirmation of cash back
    private void printCashBackComfirm(double amountCashBack){
    	JFrame confirmFrame = new JFrame("Confirmation");
    	confirmFrame.pack();
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		confirmFrame.setSize(width/3, height/9);
		confirmFrame.setLocationRelativeTo(null);

		JPanel cashBackPanel = new JPanel();	
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	JLabel changeLabel = new JLabel("  Total returned: $" + formatter.format(amountCashBack));
    	JLabel pay = new JLabel("  From Credit Card: "+card);
		cashBackPanel.setLayout(new BoxLayout(cashBackPanel,BoxLayout.Y_AXIS));
		cashBackPanel.add(changeLabel);
		cashBackPanel.add(pay);
		confirmFrame.setContentPane(cashBackPanel);
		confirmFrame.setVisible(true);
    	confirmFrame.setSize(400,400);
    }

    //Print the reciept
    private void printReceipt(String cartList, double amountAsk, boolean isCash){
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
    	double tax = TaxCalculator.getSalesTax(currentReturn);
		double total = amountAsk + tax;
    	finalTotal.setText(""+currentReturn.getTotal());
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	//double amountAsk = currentReturn.getTotal();
    	JLabel changeLabel ;//= new JLabel("Total returned: $" + formatter.format(amountAsk));
    	JLabel pay;
    	if(isCash) {
    		pay = new JLabel("In Cash");
    		changeLabel = new JLabel("Total returned: $" + formatter.format(amountAsk));
    	}
    	else {
    		pay = new JLabel("to Credit Card: "+card);
    		changeLabel = new JLabel("Total returned: $" + formatter.format(total));
    	}
		finalItems.setColumns(10);
		finalItems.setRows(12);
		receiptPanel.add(finalItems);
		receiptPanel.add(finalLabel);
		receiptPanel.add(finalTotal);
		receiptPanel.add(changeLabel);
		receiptPanel.add(pay);
    	receiptFrame.setContentPane(receiptPanel);
    	receiptFrame.setVisible(true);
    	receiptFrame.setSize(400,400);
	}
}