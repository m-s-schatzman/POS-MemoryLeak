import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//When it prints receipt, it will need to print the rental rules clearly according to rubic
//Possibly use a simple formula of x*price per day it is rented...

public class ProcessRentalController implements ActionListener {

    private Rental currentRental;
    private ProcessRentalView view;

    //Handles event that happens in the View
    public void actionPerformed(ActionEvent ac) {
        if (ac.getActionCommand().equals("Exit")) {
            new POSController();
            view.closeFrame();
        }else if (ac.getActionCommand().equals("Add Item")) {
            addLineItem(view.getId(), view.getQuantity());
        }else if (ac.getActionCommand().equals("Checkout")) {
            printReceipt(currentRental.getCartList());
            currentRental = new Rental();
            view.returnToRental(); 
        }
    }

    //Constructor
    public ProcessRentalController() {
        currentRental = new Rental();
        view = new ProcessRentalView();
        view.addController(this);
    }

    //Adds a line item to the current rental
    private void addLineItem(int ID, int quantity){
    	Item item=Item.retrieve(ID);
    	RentalLineItem rentalLineItem = new RentalLineItem(quantity, item);
    	currentRental.addLineItem(rentalLineItem);
    	view.updateTotalItems(currentRental.getCartList());
    	view.updateTotalCost(currentRental.getTotal());
    }
   
    //Removes given line item from the current rental
    private void removeLineItem(int ID, int quantity) {
        Item item = Item.retrieve(ID);
        RentalLineItem rentalLineItem = new RentalLineItem(quantity, item);
        currentRental.removeLineItem(rentalLineItem);
    }

    //Process the current Rental
    //Save to database
    private void processRental(double change){
    	currentRental.save();
    	printReceipt(currentRental.getCartList(),change);
    	currentRental=new Rental();
    	view.returnToRental();
    }
    
    //Prints the receipt in new JFrame
    private void printReceipt(String cartList){
        JFrame receiptFrame=new JFrame("Receipt");
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
        finalTotal.setText(""+currentRental.getTotal());
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