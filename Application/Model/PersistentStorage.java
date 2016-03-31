//Class used to connect to the locally stored derby database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersistentStorage {

	private static Connection conn;
	
	private PersistentStorage(){
 	}

	//Gets connection to the database
	//Opens connection if not yet opened
	public static Connection getConnection(){
		if(conn == null){
			try{
				conn = DriverManager.getConnection("jdbc:derby:Output/POSDatabase");
			}catch(SQLException sqle){
				System.out.println(sqle.getMessage());
			}
		}
		return conn;
	}

	//Closes connection to database
	public static void closeConnection(){
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException sqle){
				System.out.println(sqle.getMessage());
			}
		}
	}
}