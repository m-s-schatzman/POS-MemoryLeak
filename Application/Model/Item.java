/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author yuchencai
 */
public class Item {
    private String ID;
    private double price;
    private String name;
    
    /** constructor */
    Item(String ID,double price,String name){
    this.ID = ID;
    this.price = price;
    this.name = name; 
}
    /** get price */
    public double getPrice(){
        return this.price;
    }
    
    /** get name */
    public String getName(){
        return this.name;
    }
    
    /** get ID*/
    public String getID(){
        return this.ID;
    }
    
    /** scanItem() may interact with database 
     * call inventory.findItem
      inventory will return that Item
     */
    static Item scanItem(String ID){
     
        return null;
    }
    
}
