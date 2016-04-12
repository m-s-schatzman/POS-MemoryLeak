import org.junit.*;
import static org.junit.Assert.*;

public class ProcessReturnTest{

	private Item item1;
    private Item item2;
    //private Return return;

    @Before
    public void setUp() {
    	int id1 = 1; 
    	int id2 = 2; 
        item1 = Item.retrieve(id1);
        item2 = Item.retrieve(id2);
        //return = new return();
    }

	@Test
	public void testAddLineItem()
	{
		int qt1 = 2,qt2 = 3;
		assertEquals("Banana", item2.getName());

		ReturnLineItem lineItem1 = new ReturnLineItem(qt1, item1);
		ReturnLineItem lineItem2 = new ReturnLineItem(qt2, item2);

		//return.addLineItem(lineItem1);
		//return.addLineItem(lineItem2);

		//assertEquals(8, return.getTotal(), .0001);
	}
}