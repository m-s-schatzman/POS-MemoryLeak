/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

public class ReturnLineItem extends LineItem{

	private boolean damaged;

	//Constructor
	public ReturnLineItem(int count, Item item, boolean damaged){
		super(count, item);
		this.damaged = damaged;
	}

	//Get if item is damaged
	public boolean getDamaged(){
		return damaged;
	}

	//Save return line item to db using the id of the return it belongs to
	public void save(int returnId){
		String query = "insert into return_item values ( "+returnId+", "+item.getID()+", "+count+", "+(damaged ? 1 : 0)+" )";
        DBConnection.submitUpdate(query);
	}
}