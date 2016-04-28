/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import java.util.*;
import java.sql.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Sale {
    
    // not sure what stack means
    private ArrayList<LineItem> cart;
    
    /** constructor */
    public Sale(){
        cart = new ArrayList<LineItem>();
    }
    
    /** calculate total amount */
    public double getTotal(){
        double total=0;
        for(int i =0; i<cart.size();i++){
            total += cart.get(i).getPrice();
        }
        return total;
    }

    public double getTax(){
        return TaxCalculator.getSalesTax(getTotal());
    }
    
    /** add lineitem to the cart unless cart already has a lineitem of the same item, then it will add the quantity */
    public void addLineItem(LineItem lineItem){
        for(LineItem cartItem : cart){
            if(lineItem.getItem().getID() == cartItem.getItem().getID()){
                cartItem.setCount(lineItem.getCount() + cartItem.getCount());
                return;
            }
        }
        cart.add(lineItem);
    }

    public String getCartList(){
        String cartList = "";
        for(LineItem cartItem : cart){
            cartList += cartItem.getItem().getName() + " : " + cartItem.getCount() +"\n";
        }
        return cartList;
    }

    public String formatReceiptList(){
        String receiptList = "";
        NumberFormat formatter = new DecimalFormat("#0.00");
        for(LineItem cartItem : cart){
            receiptList += cartItem.getCount() + " x " + cartItem.getItem().getName() + " @ " + formatter.format(cartItem.getItem().getPrice())  + "\n";
        }
        return receiptList;
    }
    
    /** removeLineItem() */
    public boolean removeLineItem(LineItem lineItem){
        return cart.remove(lineItem);
    }

    /** save the sale to the db */
    public boolean save(){
        Connection conn = DBConnection.getConnection();
        int id = 0;
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select max(id) as maximum from sale");
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
        String query = "insert into sale values ( "+id+" )";
        if(!DBConnection.submitUpdate(query)){
            return false;
        }

        Inventory inventory = Inventory.getInventory();
        for(LineItem lineItem : cart){
            lineItem.save(id);
            inventory.purchaseLineItem(lineItem);
        }
        return true;
    }
}
