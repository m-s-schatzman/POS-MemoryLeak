import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDriver{
	
	//Calling the main method of this class will create a new database
    //Should not be done if database already created
	public static void main(String[] args){
		if(args.length < 1){
			System.out.println("No argument given.\nTry create, populate, or clear.");
		}
		if(args[0].equals("create")){
			createDB();
		}else if(args[0].equals("populate")){
			populateDB();
		}else if(args[0].equals("clear")){
			clearDB();
		}
	}

	//Creates the database, may overwrite if already created
	private static void createDB(){
		Connection conn = null;
			try{
				conn = DriverManager.getConnection("jdbc:derby:Output/POSDatabase;create=true");
				conn.close();
			}catch(SQLException sqle){
				Logger.logError(sqle.getMessage());
			}
	}

	//Populates the database with items and users
	//Creates inventory
	private static void populateDB(){

	}

	//Clears all the the entities currently in the database
	private static void clearDB(){

	}
}