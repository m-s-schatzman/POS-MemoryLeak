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
   				 if(quantity == 0)
   				 {
   				 	Logger.logError("User entered 0 for amount of items.");
   				 	Logger.displayError("Quanity of item must be greater than 0.");
   				 }
   				 else
   				 	addLineItem(id, quantity);
  				} catch (NumberFormatException e) 
  				{
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
						double change = amountGiven - currentSale.getTotal(); 
						processSale(change, inCash);
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
		//else if createSale
		//else if addLineItem
		//else if removeLineItem
		//else if processSale
    	}

    private ProcessSaleController(JFrame applicationFrame){
		currentSale = new Sale();
		view = new ProcessSaleView(applicationFrame);
		view.addController(this);
    }

    private void addLineItem(int ID, int quantity){
		if(ID>=0 && quantity>0){
		Item item = Item.retrieve(ID);
		LineItem lineItem = new LineItem(quantity, item);
		currentSale.addLineItem(lineItem);
		view.updateTotalItems(currentSale.getCartList());
		view.updateTotalCost(currentSale.getTotal());
		
		} 
		   }

    private void processSale(double payment, boolean isCash){
	   	currentSale.save();
	    printReceipt(currentSale.getCartList(), payment, isCash);
		currentSale = new Sale();
		view.clearFields();
    }

    public static void create() {
    	JFrame applicationFrame = new JFrame("Process Sale");
    	new ProcessSaleController(applicationFrame);
    }

    private void printReceipt(String cartList, double payment, boolean isCash){
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
    	JLabel finalLabel = new JLabel("Total Cost:");
    	JTextField finalTotal = new JTextField(5);
    	finalTotal.setEditable(false);
    	JTextField validated = new JTextField(11);
    	finalTotal.setText(""+currentSale.getTotal());
    	NumberFormat formatter = new DecimalFormat("#0.00");
    	if(isCash)
    	{     
    		paymentLabel = new JLabel("Your change: $" + formatter.format(payment));
    	}
    	else
    	{
    		validated.setText("Credit Card validated");
    	}
    	validated.setEditable(false);
		finalItems.setColumns(10);
		finalItems.setRows(12);
		receiptPanel.add(finalItems);
		receiptPanel.add(finalLabel);
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