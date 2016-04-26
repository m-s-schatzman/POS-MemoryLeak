/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import org.junit.*;
import static org.junit.Assert.*;

public class ReturnUnitTest{

	@Test
	public void saveReturn(){
		Return ret = new Return();
		int quantity = 5;
		Item item = Item.retrieve(1);
		ReturnLineItem lineItem = new ReturnLineItem(quantity, item, true);
		ret.addLineItem(lineItem);
		assertTrue("Save return", ret.save());
	}
}