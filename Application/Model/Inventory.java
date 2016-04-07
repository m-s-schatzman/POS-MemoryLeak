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
		int count = getItemCount(lineItem.getItem(), "inventory");
		count -= lineItem.getCount();
		String query = "update inventory set quantity = "+count+" where item_id ="+lineItem.getItem().getID();
		DBConnection.submitUpdate(query);
	}

	//Increase the inventory count of a specific item by count specified in the lineItem
	public void returnLineItem(LineItem lineItem){
		int count = getItemCount(lineItem.getItem(), "returned_item");
		count += lineItem.getCount();
		String query = "update returned_item set quantity = "+count+" where item_id ="+lineItem.getItem().getID();
		DBConnection.submitUpdate(query);
	}

	//Gets the count of a specific Item in the inventory
	private int getItemCount(Item item, String tableName){
		Connection conn = DBConnection.getConnection();
        int count = 0;
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select count from "+tableName+" where item_id = "+item.getID());
            if(rs.next()){
                count = rs.getInt("count");
            }
            rs.close();
            s.close();
        }catch(SQLException sqle){
            Logger.logError(sqle.getMessage());
        }
        return count;
	}
}