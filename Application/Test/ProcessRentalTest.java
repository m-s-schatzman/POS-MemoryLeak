/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;

public class ProcessRentalTest{

	@Test
	public void testAddLineItem(){ 
	  Rental r = new Rental();
	  int ID = 1;
	  int quantity = 1;
	  Item item = Item.retrieve(ID);
	  assertEquals("Apple", item.getName());
	  RentalLineItem rentalLineItem = new RentalLineItem(quantity, item, new Date());
	  r.addRentalLineItem(rentalLineItem);
	  //assertEquals("Apple : 1"+" ", r.getCartList());
	  }
	  
	@Test
	public void testRemoveLineItem(){
		Rental r=new Rental();
		int ID=1;
		int quantity=1;
		Item item=Item.retrieve(ID);
		RentalLineItem rentalLineItem = new RentalLineItem(quantity, item, new Date());
		r.removeRentalLineItem(rentalLineItem);
	//assertEquals("Apple : 0"+" ", r.getCartList());
		assertEquals("Apple : "+(rentalLineItem.getCount()-1)+" ", r.getCartList());
	}
}

