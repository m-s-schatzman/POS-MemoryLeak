/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author yuchencai
 */

public class LineItem {
    protected int count;
    protected Item item;
    
    /** constructor */
    LineItem(int count, Item item){
        this.count =count;
        this.item = item;
    }
    
    /** create a lineItem with count and item */
    public LineItem createLineItem(int count, Item item){
        return new LineItem(count,item);
    }
    
    /** return amount of this lineItem */
    public double getPrice(){
        return count*item.getPrice();
    }
    
    /** return Item */
    public Item getItem(){
        return this.item;
    }

    /** return count */
    public int getCount(){
        return this.count;
    }

    /** set count of Item */
    public void setCount(int count){
        this.count = count;
    }
}
