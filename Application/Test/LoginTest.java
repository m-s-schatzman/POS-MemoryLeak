import org.junit.*;
import static org.junit.Assert.*;

public class LoginTest{

	@Test
	public void testUserValidate(){
		User testUser = new User("testname","testpassword",User.Role.Admin);
		testUser.save();
		LoginController loginCont = new LoginController();
		POSController posCont = loginCont.login("testname","testpassword");
		assertTrue("Login opens POSController", posCont != null);
		assertTrue("User is registered in db", CurrentUser.getUser().getUsername().equals("testname"));
		testUser.delete();
		posCont.close();
	}
}