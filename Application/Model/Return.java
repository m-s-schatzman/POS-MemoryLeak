/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


import java.util.ArrayList;

/**
 *
 * @author yuchencai
 */

import java.util.*;
import java.sql.*;

public class Return {

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

    //Save this return into the database and save it's children return line items
    public void save(){
        Connection conn = DBConnection.getConnection();
        int id = 0;
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select max(id) as maximum from returned_item");
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
        String query = "insert into returned_item values ( "+id+" )";
        DBConnection.submitUpdate(query);

        Inventory inventory = Inventory.getInventory();
        //for(ReturnLineItem lineItem : cart){
          //  lineItem.save(id);
            //inventory.purchaseLineItem(LineItem);
        //}
    } 
}
