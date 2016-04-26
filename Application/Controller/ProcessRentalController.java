/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.Date;
import java.awt.*;
import java.text.SimpleDateFormat;

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
            String idString = view.getId();
            String quantityString = view.getQuantity();
            String dateString = view.getDate();
            try{
                int id = Integer.parseInt(idString);
                int quantity = Integer.parseInt(quantityString);
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                Date date = format.parse(dateString);
                if(quantity <= 0){
                    Logger.logError("User entered 0 for amount of items.");
                    Logger.displayError("Quanity of item must be greater than 0.");
                }else{
                    addLineItem(id, quantity, date);
                }
            }catch(Exception ex){
                Logger.logError(ex.getMessage());
            }
        }else if (ac.getActionCommand().equals("Checkout")) {
            processRental();
        }
    }

    //Constructor
    public ProcessRentalController() {
        currentRental = new Rental();
        view = new ProcessRentalView();
        view.addController(this);
    }

    //Adds a line item to the current rental
    public void addLineItem(int ID, int quantity, Date returnDate){
    	if(ID<=0 && quantity<=0){
            return;
        }
        Item item = Item.retrieve(ID);
        if(item == null){
            return;
        }
        int amountLeft= Inventory.getInventory().getInventoryItemCount(item);
        if(quantity > amountLeft){
            Logger.displayError("Quantity of Item is too high for the Store's inventory \n" + "There are only " + amountLeft +" of them.");
            return;
        }

    	RentalLineItem rentalLineItem = new RentalLineItem(quantity, item, returnDate);
    	//CAM addition to make sure inventory updates are within range	
    	currentRental.addRentalLineItem(rentalLineItem);
    	view.updateTotalItems(currentRental.getCartList());
    	view.updateTotalCost(currentRental.getTotal());
    }
   
    //Removes given line item from the current rental
    public void removeLineItem(int ID, int quantity) {
        Item item = Item.retrieve(ID);
        if(item == null){
            return;
        }
        RentalLineItem rentalLineItem = new RentalLineItem(quantity, item, new Date());
        currentRental.removeRentalLineItem(rentalLineItem);
    }

    //Process the current Rental
    //Save to database
    public void processRental(){
    	currentRental.save();
    	printReceipt(currentRental.getCartList());
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
        JTextArea rentalMessage = new JTextArea("Rentals will cost 5% of the price of the item per day.\nThe full price of the item will be \ncharged if not returned in 20 days.");
		finalItems.setColumns(10);
		finalItems.setRows(12);
		receiptPanel.add(finalItems);
		receiptPanel.add(finalLabel);
		receiptPanel.add(finalTotal);
		receiptPanel.add(validated);
        receiptPanel.add(rentalMessage);
    	receiptFrame.setContentPane(receiptPanel);
    	receiptFrame.setVisible(true);
    	receiptFrame.setSize(400,400);
	}     
}