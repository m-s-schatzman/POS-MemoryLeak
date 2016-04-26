/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import java.util.ArrayList;
import java.sql.*;

public class Inventory{

	private static Inventory inventory;

	//Constructor
	//Private, only single instance, should be accessed by getInventory
	private Inventory(){
	}

	//Gets single instance of Inventory
	public static Inventory getInventory(){
		if(inventory == null){
			inventory = new Inventory();
		}
		return inventory;
	}

	//Decrease the inventory count of a specific item by count specified in the lineItem
	public void purchaseLineItem(LineItem lineItem){
		int count = getInventoryItemCount(lineItem.getItem());
		count -= lineItem.getCount();
		String query = "update inventory set quantity = "+count+" where item_id ="+lineItem.getItem().getID();
		DBConnection.submitUpdate(query);
	}

	//Increase the inventory count of a specific item by count specified in the lineItem
	public void returnLineItem(ReturnLineItem lineItem){
        if(lineItem.getDamaged() == true){
		  int count = getReturnedItemCount(lineItem.getItem());
		  count += lineItem.getCount();
		  String query = "update returned_item set quantity = "+count+" where item_id ="+lineItem.getItem().getID();
		  DBConnection.submitUpdate(query);
        }else{
            int count = getInventoryItemCount(lineItem.getItem());
            count += lineItem.getCount();
            String query = "update inventory set quantity = "+count+" where item_id ="+lineItem.getItem().getID();
            DBConnection.submitUpdate(query);
        }
	}

	//Gets the count of a specific Item in the inventory
	public int getInventoryItemCount(Item item){
		Connection conn = DBConnection.getConnection();
        int count = 0;
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select quantity from inventory where item_id = "+item.getID());
            if(rs.next()){
                count = rs.getInt("quantity");
            }
            rs.close();
            s.close();
        }catch(SQLException sqle){
            Logger.logError(sqle.getMessage());
        }
        return count;
	}

	//Gets the count of a specific Item in the returned items
	public int getReturnedItemCount(Item item){
		Connection conn = DBConnection.getConnection();
        int count = 0;
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select quantity from returned_item where item_id = "+item.getID());
            if(rs.next()){
                count = rs.getInt("quantity");
            }
            rs.close();
            s.close();
        }catch(SQLException sqle){
            Logger.logError(sqle.getMessage());
        }
        return count;
	}


	//Populates the db with sample items and adds them to the inventory
	public void populateDB(){
		ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item(1,"Apple", 1.00));
        items.add(new Item(2,"Banana", 1.00));
        items.add(new Item(3,"Capn Crunch", 3.00));
        items.add(new Item(4,"Mac n Cheese", 3.00));
        items.add(new Item(5,"Secret Sauce", 99.00));
        items.add(new Item(6,"Tortillas", 0.50));
        items.add(new Item(7,"Pancakes", 2.00));
        items.add(new Item(8,"Pasta Sauce", 5.00));
        items.add(new Item(9,"Pizza Pie", 8.00));

        String query;
        for(Item item : items){
        	item.save();
        	query = "insert into inventory values ( "+item.getID()+", "+100+" )";
        	DBConnection.submitUpdate(query);
        	query = "insert into returned_item values ( "+item.getID()+", 0 )";
        	DBConnection.submitUpdate(query);
        }
	}
}