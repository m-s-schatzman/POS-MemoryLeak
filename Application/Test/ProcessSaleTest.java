/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import org.junit.*;
import static org.junit.Assert.*;

public class ProcessSaleTest{
	@Test
	public void testAddLineItem()
	{
		Sale sale = new Sale();
		int id =  1;
		int quantity = 5;
		Item item = Item.retrieve(id);
		assertEquals("Apple", item.getName());
		LineItem lineItem = new LineItem(quantity, item);
		sale.addLineItem(lineItem);
		assertEquals("Apple : 5" +"\n", sale.getCartList());
		assertEquals(5, sale.getTotal(), .0001);
	}
}