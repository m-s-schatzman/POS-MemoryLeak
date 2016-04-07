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
public class Item {
    private int ID;
    private String name;
    private double price;
    
    /** constructor */
    public Item(int ID, String name, double price){
        this.ID = ID;
        this.name = name; 
        this.price = price;
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

    //Save item in the database
    public void save(){
        String query = "insert into item values ( "+ID+", '"+name+"', "+price+" )";
        DBConnection.submitUpdate(query);
    }

    //Retrieve given item from the database given an id
    public static Item retrieve(int id){
        Item item = null;
        Connection conn = DBConnection.getConnection();
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from item where id = " + id);
            if(rs.next()){
                item = new Item(rs.getInt("id"),rs.getString("name"),rs.getDouble("price"));
            }
            rs.close();
            s.close();
        }catch(SQLException sqle){
            Logger.logError(sqle.getMessage());
        }
        return item;
    }
}
