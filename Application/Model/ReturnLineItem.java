/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

public class ReturnLineItem extends LineItem{

	//Constructor
	public ReturnLineItem(int count, Item item){
		super(count, item);
	}

	//Save return line item to db using the id of the return it belongs to
	public void save(int returnId){
		String query = "insert into return_item values ( "+returnId+", "+item.getID()+", "+count+" )";
        DBConnection.submitUpdate(query);
	}
}