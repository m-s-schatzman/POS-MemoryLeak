/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import org.junit.*;
import static org.junit.Assert.*;

public class SaleUnitTest{

	@Test
	public void saveSale(){
		Sale sale = new Sale();
		int quantity = 5;
		Item item = Item.retrieve(1);
		LineItem lineItem = new LineItem(quantity, item);
		sale.addLineItem(lineItem);
		assertTrue("Save sale", sale.save());
	}
}