import org.junit.*;
import static org.junit.Assert.*;

public class ProcessRentalTest{

	
@Test
	public void testAddLineItem(){ 
	  Rental r = new Rental();
	  int ID = 1;
	  int quantity = 1;
	  Item item=Item.retrieve(ID);f
	  assertEquals("Apple", item.getName());
	  RentalLineItem rentalLineItem = new RentalLineItem(quanty, item, "4/11/2016"); //date might not be the right format
	  r.addLineItem(rentalLineItem);
	  assertEquals("Apple : 1"+" ", r.getCartList());
	  }
}

