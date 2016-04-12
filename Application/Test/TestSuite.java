/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;
import org.junit.internal.TextListener;

//Add classes to here to have them run as part of the test suite
@RunWith(Suite.class)
@Suite.SuiteClasses({
   LoginTest.class, 
   ManageUserTest.class,
   //ProcessRentalTest.class,
   ProcessReturnTest.class,
   ProcessSaleTest.class,
   SystemTest.class
})
 
public class TestSuite{
	public static void main(String args[]){
		//Open db or create it if not yet created
		DBConnection.openConnection();
		//Run test classes and print output to System.out
		JUnitCore junit = new JUnitCore();
    	junit.addListener(new TextListener(System.out));
    	junit.run(TestSuite.class);
    }
}