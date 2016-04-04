import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProcessReturnController implements ActionListener{
    private Return currentReturn;
    private ProcessReturnView view;

    public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Exit")){
	    	System.exit(1);
		}
		else if(ac.getActionCommand().equals("Add Item")){
			addLineItem(view.getId(), view.getQuantity());
		}
		else if(ac.getActionCommand().equals("Return"))
		{
			printReceipt(currentReturn.getCartList());
			currentReturn = new Return();
			view.returnToSale();
		}
		//else if createSale
		//else if addLineItem
		//else if removeLineItem
		//else if processSale
    	}

    public ProcessReturnController(JFrame applicationFrame){
		currentReturn = new Return();
		view = new ProcessReturnView(applicationFrame);
		view.addController(this);
    }
    
    public void createReturn(){
		currentReturn = new Return();
    }

    public void addLineItem(int ID, int quantity){
		Item item = Item.scanItem(ID);
		LineItem lineItem = new LineItem(quantity, item);
		currentReturn.addLineItem(lineItem);
		view.updateTotalItems(currentReturn.getCartList());
		view.updateTotalCost(currentReturn.getTotal());
    }

    public void removeLineItem(int ID, int quantity){
		Item item = Item.scanItem(ID);
		LineItem lineItem = new LineItem(quantity, item);
		currentReturn.removeLineItem(lineItem);
    }

    public boolean processReturn(int cardNumber){
		double total = currentReturn.getTotal();
		if(true == PaymentAuthorizer.PaymentAuth(cardNumber, total)){
	    	currentReturn.save();
	    	return true;
		}
		return false;
    }

    public static void create() {
    	JFrame applicationFrame = new JFrame("Process Return");
    	new ProcessReturnController(applicationFrame);
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
    	JLabel finalLabel = new JLabel("Total returned:");
    	JTextField finalTotal = new JTextField(5);
    	finalTotal.setEditable(false);
    	JTextField validated = new JTextField(10);
    	finalTotal.setText(""+currentReturn.getTotal());
    	validated.setText("Refund Validated");
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