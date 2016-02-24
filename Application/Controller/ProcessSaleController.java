public class ProcessSaleController extends java.util.Observable {
    private Sale currentSale;
    //private SaleView view;

    public ProcessSaleController(){
	currentSale = new Sale();
    }

    public void createSale(){
	currentSale = new Sale();
	notifyObservers(currentSale);
    }

    public void addLineItem(int ID, int quantity){
	Item item = Item.scanItem(ID);
	LineItem lineItem = new LineItem(quantity, item);
	currentSale.addLineItem(lineItem);
	notifyObservers(currentSale);
    }

    public void removeLineItem(int ID, int quantity){
	Item item = Item.scanItem(ID);
	LineItem lineItem = new LineItem(quantity, item);
	currentSale.removeLineItem(lineItem);
	notifyObservers(currentSale);
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