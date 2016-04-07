/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yuchencai
 */

import java.util.*;
import java.sql.*;

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
    
    /** removeLineItem() */
    public boolean removeLineItem(LineItem lineItem){
        return cart.remove(lineItem);
    }

    /** save the sale to the db */
    public void save(){
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
        }
        String query = "insert into sale values ( "+id+" )";
        DBConnection.submitUpdate(query);

        Inventory inventory = Inventory.getInventory();
        for(LineItem lineItem : cart){
            lineItem.save(id);
            inventory.purchaseLineItem(lineItem);
        }
    }
}
