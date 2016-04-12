/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.lang.StringBuilder;

public class DBDriver{
	
	//Calling the main method of this class will do specific tasks for the database
    //Used for testing purposes, not compiled with main class
	public static void main(String[] args){
		if(args.length < 1){
			System.out.println("No argument given.\nTry create, tables, or populate.");
		}
		if(args[0].equals("create")){
			createDB();
		}else if(args[0].equals("tables")){
			createDBTables();
		}else if(args[0].equals("populate")){
			populateDB();
		}else{
			System.out.println("command not recognized: try create, tables, or populate");
		}
	}

	//Creates the database, may overwrite if already created
	public static void createDB(){
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
	public static void populateDB(){
		User.populateTable();
		Inventory.getInventory().populateDB();
	}

	//Runs DDL queries on database
	public static void createDBTables(){
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

		Connection conn = DBConnection.getConnection();
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