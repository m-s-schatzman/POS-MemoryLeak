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

public class Sale {
    
    // not sure what stack means
    private ArrayList<LineItem> cart;
    
    /** constructor */
    Sale(){
        cart = new ArrayList<LineItem>();
    }
    
    /** calculate total amount
     * @return  */
    public double getTotal(){
        double total=0;
        for(int i =0; i<cart.size();i++){
            total += cart.get(i).getPrice();
        }
        return total;
    }
    
    /** add a lineItem to sale 
     * 
     *  didn't consider if ID is valid 
     * will the validation deal with database as well?
     * 
     */
    public void addLineItem(LineItem lineItem){
       // LineItem newLineItem = new LineItem(quantity, Item.scanItem(id));
        cart.add(lineItem);
    }
    
    /** removeLineItem()
     * maybe remove item from LineItem rather than remove the whole LineItem 
     * ???
     */
    public boolean removeLineItem(LineItem lineItem){
        return cart.remove(lineItem);
    }
    
 
}
