import org.junit.*;
import static org.junit.Assert.*;

public class LoginTest{

	@Test
	public void testUserValidate(){
		assertTrue("Admin is is registered in db", CurrentUser.login("Admin","admin"));
	}
}