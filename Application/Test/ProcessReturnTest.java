/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import org.junit.*;
import static org.junit.Assert.*;

public class ProcessReturnTest{

	private Item item1;
    private Item item2;
    private Return r;

    @Before
    public void setUp() {
    	int id1 = 1; 
    	int id2 = 2; 
        item1 = Item.retrieve(id1);
        item2 = Item.retrieve(id2);
        r = new Return();
    }

	@Test
	public void testAddLineItem()
	{
		int qt1 = 2,qt2 = 3;
		assertEquals("Banana", item2.getName());

		ReturnLineItem lineItem1 = new ReturnLineItem(qt1, item1, true);
		ReturnLineItem lineItem2 = new ReturnLineItem(qt2, item2, false);

		r.addLineItem(lineItem1);
		r.addLineItem(lineItem2);

		assertEquals(5, r.getTotal(), .0001);
	}
}