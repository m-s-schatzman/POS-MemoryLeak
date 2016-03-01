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
    private int ID; //going to change to int for not, not sure how we will do it with back end inventory, may need to be changed back
    private double price;
    private String name;
    
    /** constructor */
    Item(int ID,double price,String name){
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
    public int getID(){
        return this.ID;
    }
    
    /** scanItem() may interact with database 
     * call inventory.findItem
      inventory will return that Item
     */
      /* Temporarily moving to inventory...
    public Item scanItem(int ID){
        db.getItem(ID);
    } 
    */
    
}
