/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import org.junit.*;
import static org.junit.Assert.*;

public class ProcessReturnTest{

	private Item item1;
    private Item item2;
    private Return r1;
    private Return r2;
    int qt1,qt2;
    ReturnLineItem lineItem1;// = new ReturnLineItem(qt1, item1, true);
	ReturnLineItem lineItem2;

    @Before
    public void setUp() {
    	int id1 = 1; 
    	int id2 = 2; 
    	qt1 = 2;
    	qt2 = 3;
        item1 = Item.retrieve(id1);
        item2 = Item.retrieve(id2);
        r1 = new Return();
        r2 = new Return();

		lineItem1 = new ReturnLineItem(qt1, item1, true);
		lineItem2 = new ReturnLineItem(qt2, item2, false);
    }

	@Test
	public void testAddLineItem()
	{
		lineItem2 = new ReturnLineItem(qt2, item2, false);
		assertEquals("Banana", item2.getName());


		r1.addLineItem(lineItem1);
		r1.addLineItem(lineItem2);
		

		assertEquals(5, r1.getTotal(), .0001);

	}

	@Test
	public void testRemoveLineItem(){
		lineItem2 = new ReturnLineItem(qt2, item2, false);
		r2.addLineItem(lineItem2);
		
		r2.removeLineItem(lineItem2);
		assertEquals(0, r2.getCartList().length());
	}

}