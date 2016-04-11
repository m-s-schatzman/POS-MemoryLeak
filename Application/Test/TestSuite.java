import org.junit.*;
import static org.junit.Assert.*;
 
import java.util.*;
 
public class TestSuite{
	public static void main(String args[]){
      org.junit.runner.JUnitCore.main("TestSuite");
    }

    @Test
    public void testTest(){
        assertTrue(true);
    }
}