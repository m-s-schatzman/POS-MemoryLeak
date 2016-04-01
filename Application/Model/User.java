import java.util.*;
import java.sql.*;

public class User {

	private String username;
	private String password;

	public User(String username, String password){
		this.username = username;
		this.password = password;
	}

	public String getUsername(){
		return this.username;
	}

	public String getPassword(){
		return this.password;
	}

	//Saves given instance of User into the database
	public void save(){
		String query = "insert into employee values ( '"+username+"', '"+password+"' )";
		DBConnection.submitQuery(query);
	}

	//retrieves a given user from the database using a username
	public static User retrieve(String username){
		Connection conn = DBConnection.getConnection();
		try{
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select * from user where name = "+username);
			while(rs.next()){
				User user = new User(rs.getString("name"),rs.getString("password"));
				rs.close();
				s.close();
				return user;
			}
		}catch(SQLException sqle){
			Logger.logError(sqle.getMessage());
		}
		return null;
	}

	//deletes the given user from the database
	public void delete(){
		String query = "delete from employee where name = '"+username+"' )";
		DBConnection.submitQuery(query);
	}
}