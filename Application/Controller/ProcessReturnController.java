import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.DecimalFormat;


//Return items need to be stored in differt table 
public class ProcessReturnController implements ActionListener{
    private Return currentReturn;
    private ProcessReturnView view;
    private String card;

    public void actionPerformed(ActionEvent ac){

		if(ac.getActionCommand().equals("Exit")){
	    	new POSController();
			view.closeFrame();
		}
		else if(ac.getActionCommand().equals("Add Item")){
			addLineItem(view.getId(), view.getQuantity());
		}
		else if(ac.getActionCommand().equals("Return"))
		{
			card = view.getCardNum();
			double amountAsk = currentReturn.getTotal();
			processReturn(amountAsk);
		}
		else {
			AbstractButton rButton = (AbstractButton) ac.getSource();
			String choice = rButton.getText();
			if(choice.equals("Credit Card")){
				view.addCardField();
				card = "";
			}
				
			else if(choice.equals("Cash")){
				if(card!=null) view.removeCardField();
			}
		}
		
    }

    private ProcessReturnController(JFrame applicationFrame){
		currentReturn = new Return();
		view = new ProcessReturnView(applicationFrame);
		view.addController(this);
    }
    

    private void addLineItem(int ID, int quantity){
		Item item = Item.retrieve(ID);
		ReturnLineItem lineItem = new ReturnLineItem(quantity, item);
		currentReturn.addLineItem(lineItem);
		view.updateTotalItems(currentReturn.getCartList());
		view.updateTotalCost(currentReturn.getTotal());
    }

    private void removeLineItem(int ID, int quantity){
		Item item = Item.retrieve(ID);
		ReturnLineItem lineItem = new ReturnLineItem(quantity, item);
		currentReturn.removeLineItem(lineItem);
    }

    private void processReturn(double amountAsk){
		currentReturn.save();
	    printReceipt(currentReturn.getCartList(), amountAsk);
		currentReturn = new Return();
		view.returnToSale();
    }

    public static void create() {
    	JFrame applicationFrame = new JFrame("Process Return");
    	new ProcessReturnController(applicationFrame);
    }

    private void printReceipt(String cartList, double amountAsk){
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
    	JLabel changeLabel = new JLabel("Total returned: $" + formatter.format(amountAsk));
    	JLabel pay;
    	if(card.length()>0) {
    		pay = new JLabel("to Credit Card: " + card);


    	}
    	else {
    		pay = new JLabel("In Cash");


    	}

    	validated.setText("Refund Validated");
    	validated.setEditable(false);
		finalItems.setColumns(10);
		finalItems.setRows(12);
		receiptPanel.add(finalItems);
		receiptPanel.add(finalLabel);
		receiptPanel.add(finalTotal);
		receiptPanel.add(changeLabel);
		receiptPanel.add(pay);
		if(card.length()>0) receiptPanel.add(validated);
    	receiptFrame.setContentPane(receiptPanel);
    	receiptFrame.setVisible(true);
    	receiptFrame.setSize(400,400);
    	
	}
}