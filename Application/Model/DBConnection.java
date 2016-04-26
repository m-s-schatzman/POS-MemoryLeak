/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Class used to connect to the locally stored derby database
public class DBConnection {

	private static Connection conn;
	
	private DBConnection(){
 	}

 	//This will open the connection to the database and create it if not already created
 	//If the database has not yet been created, it will also create the tables and populate them
 	public static boolean openConnection(){
 		DBDriver.createDB();
 		try{
 			conn = getConnection();
 			Statement s = conn.createStatement();
 			ResultSet rs = s.executeQuery("select count(*) as COUNT from SYS.SYSTABLES");
 			//Check if tables have been populated... Definitely better way to do this
 			boolean created = (rs.next() && rs.getInt("COUNT") > 23);
 			rs.close();
 			s.close();
 			if(!created){
 				DBDriver.createDBTables();
 				DBDriver.populateDB();
 			}
 			return true;
 		}catch(SQLException sqle){
 			Logger.logError(sqle.getMessage());
 		}
 		return false;
 	}

	//Gets connection to the database
	//Opens connection if not yet opened
	public static Connection getConnection(){
		if(conn == null){
			try{
				conn = DriverManager.getConnection("jdbc:derby:Output/POSDatabase");
			}catch(SQLException sqle){
				Logger.logError(sqle.getMessage());
			}
		}
		return conn;
	}

	//Closes connection to database
	public static boolean closeConnection(){
		if(conn != null){
			try{
				conn.close();
				return true;
			}catch(SQLException sqle){
				Logger.logError(sqle.getMessage());
			}
		}
		return false;
	}
	
	//Submits query without needing result set
	public static boolean submitUpdate(String query){

		try{
			Statement s = getConnection().createStatement();
			s.executeUpdate(query);
			s.close();
			return true;
		}catch(SQLException sqle){
			Logger.logError(sqle.getMessage());
			return false;
		}
	}
	
	public static int checkInventorySize(int itemID){
		try{
			Statement s = conn.createStatement();
			int quantity=0;
 			ResultSet rs = s.executeQuery("select quantity from inventory where item_id = "+ itemID);
 			int counter=0;
 			while(rs.next()){
 				quantity = rs.getInt("quantity");
 				counter++;
 			}
 			if(counter==0){
 				return -1;}
 			return quantity;
 			}
 		catch(SQLException sqle){
			Logger.logError(sqle.getMessage());
			return -1;
		}
		}
	
	
	
	
}