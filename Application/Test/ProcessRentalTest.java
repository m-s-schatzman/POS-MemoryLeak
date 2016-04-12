/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import org.junit.*;
import static org.junit.Assert.*;

public class ProcessRentalTest{

	@Test
	public void testAddLineItem(){ 
	  Rental r = new Rental();
	  int ID = 1;
	  int quantity = 1;
	  Item item=Item.retrieve(ID);
	  assertEquals("Apple", item.getName());
	  RentalLineItem rentalLineItem = new RentalLineItem(quanty, item, "2016-04-12"); //date might not be the right format
	  r.addRentalLineItem(rentalLineItem);
	  assertEquals("Apple : 1"+" ", r.getCartList());
	  }
}

