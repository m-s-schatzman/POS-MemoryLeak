/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

/**
 *
 * @author yuchencai
 */

public class LineItem {
    protected int count;
    protected Item item;
    
    /** constructor */
    public LineItem(int count, Item item){
        this.count =count;
        this.item = item;
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

    //Save lineItem in the database with the given id of its corresponding sale
    public void save(int saleId){
        String query = "insert into sale_item values ( "+saleId+", "+item.getID()+", "+count+" )";
        DBConnection.submitUpdate(query);
    }
}
