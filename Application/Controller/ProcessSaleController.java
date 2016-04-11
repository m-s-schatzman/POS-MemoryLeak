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

    public void actionPerformed(ActionEvent ac){
		if(ac.getActionCommand().equals("Exit")){
	    	new POSController();
			view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Add Item")){
			addLineItem(view.getId(), view.getQuantity());
		}
		else if(ac.getActionCommand().equals("Checkout"))
		{
			double amountGiven = view.getCashAmount();
			double change = amountGiven - currentSale.getTotal(); 
			processSale(change);
		}
		else
		{
			AbstractButton rButton = (AbstractButton) ac.getSource();
			String choice = rButton.getText();
			if(choice.equals("Cash"))
			{
				view.addCashField();
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

    private void processSale(double change){
	   	currentSale.save();
	    printReceipt(currentSale.getCartList(), change);
		currentSale = new Sale();
		view.returnToSale();
    }

    public static void create() {
    	JFrame applicationFrame = new JFrame("Process Sale");
    	new ProcessSaleController(applicationFrame);
    }

    private void printReceipt(String cartList, double change){
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
    	NumberFormat formatter = new DecimalFormat("#0.00");     
    	JLabel changeLabel = new JLabel("Your change: $" + formatter.format(change));

    	validated.setText("Payment Validated");
    	validated.setEditable(false);
		finalItems.setColumns(10);
		finalItems.setRows(12);
		receiptPanel.add(finalItems);
		receiptPanel.add(finalLabel);
		receiptPanel.add(finalTotal);
		receiptPanel.add(changeLabel);
		receiptPanel.add(validated);
    	receiptFrame.setContentPane(receiptPanel);
    	receiptFrame.setVisible(true);
    	receiptFrame.setSize(400,400);
	}
}