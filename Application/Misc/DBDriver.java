import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.lang.StringBuilder;

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
		}else{
			System.out.println("command not recognized: try create, populate, or clear");
		}
	}

	//Creates the database, may overwrite if already created
	private static void createDB(){
		Connection conn = null;
			try{
				conn = DriverManager.getConnection("jdbc:derby:Output/POSDatabase;create=true");
				createDDL(conn);
				conn.close();
			}catch(SQLException sqle){
				Logger.logError(sqle.getMessage());
			}
	}

	//Populates the database with items and users
	//Creates inventory
	private static void populateDB(){
		User.populateTable();
	}

	//Clears all the the entities currently in the database
	private static void clearDB(){
		User.clearTable();
	}

	//Runs DDL queries on database
	private static void createDDL(Connection conn){
		StringBuilder sb = new StringBuilder();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("Output/ProjectDDL.txt"));
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();
		} catch(IOException ioe) {
			Logger.logError(ioe.getMessage());
		}

		String [] queryList = sb.toString().split(";");
		for(int count = 0; count < queryList.length; count++){
			try{
				Statement s = conn.createStatement();
				s.execute(queryList[count]);
				s.close();
			}catch(SQLException sqle){
				Logger.logError(sqle.getMessage());
			}
		}
	}
}