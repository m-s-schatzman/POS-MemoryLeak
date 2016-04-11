import org.junit.*;
import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageUserTest{

	@Test
	public void testAddandDeleteUser(){
		JFrame applicationFrame = new JFrame("Manage Users Test");
		ManageUserController muc=new ManageUserController(applicationFrame);
		muc.addUser("testname","12345", User.Role.Normal);
		assertEquals("testname",User.retrieve("testname").getUsername());
		assertEquals("12345",User.retrieve("testname").getPassword());
		assertEquals(User.Role.Normal, User.retrieve("testname").getRole());
		muc.deleteUser("testname");
		assertEquals(null,User.retrieve("testname"));
		muc.close();
	}
}