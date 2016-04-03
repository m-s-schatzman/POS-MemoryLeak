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

public class Return {

    private ArrayList<LineItem> cart;
    
    /** constructor */
    Return(){
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
    
    /** add a lineItem to return 

     */
    public void addLineItem(LineItem lineItem){
       // LineItem newLineItem = new LineItem(quantity, Item.scanItem(id));
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
    
    /** removeLineItem()

     */
    public boolean removeLineItem(LineItem lineItem){
        return cart.remove(lineItem);
    }

    public void save(){
	//Nothing for now, need back end db
    }
    
 
}
