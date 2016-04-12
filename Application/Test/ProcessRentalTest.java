import org.junit.*;
import static org.junit.Assert.*;

public class ProcessRentalTest{

	
@Test
	public void testAddLineItem(){ 
	  RentalLineItem rentalLineItem = new RentalLineItem(2, 12345, "4/11/2016"); //date might not be the right format
	  assertEquals("quantity", rentalLineItem.getCount());
	  assertEquals("item", rentalLineItem.getItem().getID());
	  assertEquals("returnDate", rentalLineItem.getReturnDate());
	  }
	
	
	 
	
}
