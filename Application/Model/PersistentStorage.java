//Class used to connect to the locally stored derby database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersistentStorage {

	private static PersistentStorage persistentStorage;
	
	private PersistentStorage(){
 	}

 	//Gets single instantiated instance of the PersistentStorage class
	public static PersistentStorage getInstance(){
		if(persistentStorage == null){
			persistentStorage = new PersistentStorage();
		}		
		return persistentStorage;
	}

	//Executes query passed to it and returns the result set
	//Will return null if exception is thrown
	public ResultSet executeQuery(String query){
		Connection conn = getConnection();
		Statement s;
		try{
			s = conn.createStatement();
			ResultSet rs = s.executeQuery(query);
			conn.close();
			return rs;
		}catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		return null;
	}

	//Gets connection to the database
	private Connection getConnection(){
		try{
			return DriverManager.getConnection("jdbc:derby:Output/POSDatabase");
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
			return null;
		}
	}

	//Calling the main method of this class will create a new database
    //Should not be done if database already created
	public static void main(String[] args){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection("jdbc:derby:Output/POSDatabase;create=true");
			conn.close();
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
	}
}