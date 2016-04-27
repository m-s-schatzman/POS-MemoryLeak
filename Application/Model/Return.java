/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import java.util.ArrayList;
import java.util.*;
import java.sql.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Return extends Sale{

    private ArrayList<ReturnLineItem> cart;
    
    /** Constructor */
    public Return(){
        cart = new ArrayList<ReturnLineItem>();
    }
    
    /** calculate total amount */
    public double getTotal(){
        double total=0;
        for(int i =0; i<cart.size();i++){
            total += cart.get(i).getPrice();
        }
        return total;
    }
    
    /** add the given line item to the cart */
    public void addLineItem(ReturnLineItem lineItem){
       // LineItem newLineItem = new LineItem(quantity, Item.scanItem(id));
        for(LineItem cartItem : cart){
            if(lineItem.getItem().getID() == cartItem.getItem().getID()){
                cartItem.setCount(lineItem.getCount() + cartItem.getCount());
                return;
            }
        }
        cart.add(lineItem);
    }

    //Gets a formatted string version of the cart
    public String getCartList(){
        String cartList = "";
        for(LineItem cartItem : cart){
            cartList += cartItem.getItem().getName() + " : " + cartItem.getCount() +"\n";
        }
        return cartList;
    }
    
    /** remove given line item from the cart */
    public boolean removeLineItem(ReturnLineItem lineItem){
        return cart.remove(lineItem);
    }

    public String formatReceiptList(){
        String receiptList = "";
        NumberFormat formatter = new DecimalFormat("#0.00");
        for(LineItem cartItem : cart){
            receiptList += cartItem.getCount() + " x " + cartItem.getItem().getName() + " @ " + formatter.format(cartItem.getItem().getPrice())  + "\n";
        }
        return receiptList;
    }

    //Save this return into the database and save it's children return line items
    public boolean save(){
        Connection conn = DBConnection.getConnection();
        int id = 0;
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select max(id) as maximum from return");
            if(rs.next()){
                id = rs.getInt("maximum") + 1;
            }else{
                id = 1;
            }
            rs.close();
            s.close();
        }catch(SQLException sqle){
            Logger.logError(sqle.getMessage());
            return false;
        }
        String query = "insert into return values ( "+id+" )";
        if(!DBConnection.submitUpdate(query)){
            return false;
        }

        Inventory inventory = Inventory.getInventory();
        for(ReturnLineItem lineItem : cart){
            lineItem.save(id);
            inventory.returnLineItem(lineItem);
        }
        return true;
    } 
}
