/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import org.junit.*;
import static org.junit.Assert.*;

public class UserUnitTest{

	@Test
	public void saveUser(){
		User user = new User("testUser", "passwd", User.Role.Admin);
		assertTrue("User sale", user.save());
		assertTrue("User delete", user.delete());
	}
}