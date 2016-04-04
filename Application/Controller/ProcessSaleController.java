import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Need to implement multiple payment types (cash and credit card) according to rubic

public class ProcessSaleController implements ActionListener{
    private Sale currentSale;
    private ProcessSaleView view;

    public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Exit")){
	    	System.exit(1);
		}
		else if(ac.getActionCommand().equals("Add Item")){
			addLineItem(view.getId(), view.getQuantity());
		}
		else if(ac.getActionCommand().equals("Checkout"))
		{
			printReceipt(currentSale.getCartList());
			currentSale = new Sale();
			view.returnToSale();
		}
		//else if createSale
		//else if addLineItem
		//else if removeLineItem
		//else if processSale
    	}

    public ProcessSaleController(JFrame applicationFrame){
		currentSale = new Sale();
		view = new ProcessSaleView(applicationFrame);
		view.addController(this);
    }
    
    public void createSale(){
		currentSale = new Sale();
    }

    public void addLineItem(int ID, int quantity){
		Item item = Item.scanItem(ID);
		LineItem lineItem = new LineItem(quantity, item);
		currentSale.addLineItem(lineItem);
		view.updateTotalItems(currentSale.getCartList());
		view.updateTotalCost(currentSale.getTotal());
    }

    public void removeLineItem(int ID, int quantity){
		Item item = Item.scanItem(ID);
		LineItem lineItem = new LineItem(quantity, item);
		currentSale.removeLineItem(lineItem);
    }

    public boolean processSale(int cardNumber){
		double total = currentSale.getTotal();
		if(true == PaymentAuthorizer.PaymentAuth(cardNumber, total)){
	    	currentSale.save();
	    	return true;
		}
		return false;
    }

    public static void create() {
    	JFrame applicationFrame = new JFrame("Process Sale");
    	new ProcessSaleController(applicationFrame);
    }

    public void printReceipt(String cartList){
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
    	JTextField validated = new JTextField(10);
    	finalTotal.setText(""+currentSale.getTotal());
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