import org.junit.*;
import static org.junit.Assert.*;
import java.sql.*;

public class SystemTest{

	@Test
	public void testDDLSchema(){
		Connection conn = DBConnection.getConnection();
		try{
 			Statement s = conn.createStatement();
 			ResultSet rs = s.executeQuery("select count(*) as COUNT from SYS.SYSTABLES");
 			assertTrue("Tables are created", rs.next() && rs.getInt("COUNT") > 23);
 			rs.close();
 			s.close();
 		}catch(SQLException sqle){
 			assertTrue("Exception thrown reading database", false);
 		}
	}

	@Test
	public void testDBPopulation(){
		Connection conn = DBConnection.getConnection();
		try{
 			Statement s = conn.createStatement();
 			ResultSet rs = s.executeQuery("select count(*) as COUNT from inventory");
 			assertTrue("inventory is populated", rs.next() && rs.getInt("COUNT") > 0);
 			rs.close();
 			rs = s.executeQuery("select count(*) as COUNT from returned_item");
 			assertTrue("returned_item is populated", rs.next() && rs.getInt("COUNT") > 0);
 			rs.close();
 			rs = s.executeQuery("select count(*) as COUNT from employee");
 			assertTrue("employee is populated", rs.next() && rs.getInt("COUNT") > 0);
 			rs.close();
 			s.close();
 		}catch(SQLException sqle){
 			assertTrue("Exception thrown reading database", false);
 		}
	}
}