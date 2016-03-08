import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProcessSaleController implements ActionListener{
    private Sale currentSale;
    private ProcessSaleView view;
    private Database db = new Database();

    public void actionPerformed(ActionEvent ac){
	if(ac.getActionCommand().equals("Exit")){
	    System.exit(1);
	}
	else if(ac.getActionCommand().equals("Add Item")){
		addLineItem(view.getId(), view.getQuantity());
	}
	else if(ac.getActionCommand().equals("Checkout"))
	{
		view.printReceipt();
	}
	else if(ac.getActionCommand().equals("Finalize"))
	{
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
	Item item = db.scanItem(ID);
	LineItem lineItem = new LineItem(quantity, item);
	currentSale.addLineItem(lineItem);
	view.updateTotalItems(currentSale.getCartList());
	view.updateTotalCost(currentSale.getTotal());
    }

    public void removeLineItem(int ID, int quantity){
	Item item = db.scanItem(ID);
	LineItem lineItem = new LineItem(quantity, item);
	currentSale.removeLineItem(lineItem);
    }

    public boolean processSale(int cardNumber){
	double total = currentSale.getTotal();
	if(true == PaymentTrans.PaymentAuth(cardNumber, total)){
	    currentSale.save();
	    return true;
	}
	return false;
    }
}