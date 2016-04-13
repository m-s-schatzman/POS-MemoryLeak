/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;

public class RentalUnitTest{

	@Test
	public void saveRental(){
		Rental rental = new Rental();
		int quantity = 5;
		Item item = Item.retrieve(1);
		Date retDate = new Date();
		RentalLineItem lineItem = new RentalLineItem(quantity, item, retDate);
		rental.addRentalLineItem(lineItem);
		assertTrue("Saving rental", rental.save());
	}
}