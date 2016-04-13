/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import java.util.*;
import java.sql.*;

public class User {

	//Enum to hold the roles of the users
	public enum Role {
    	Normal,
    	Admin
	}

	//Convert a given string into it's corresponding Role
	public static Role stringToRole(String role){
		if(role.equals(Role.Normal.toString())){
			return Role.Normal;
		}else if(role.equals(Role.Admin.toString())){
			return Role.Admin;
		}
		return null;
	}

	private String username;
	private String password;
	private Role role;

	public User(String username, String password, Role role){
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public String getUsername(){
		return this.username;
	}

	public String getPassword(){
		return this.password;
	}

	public Role getRole(){
		return role;
	}

	//Saves given instance of User into the database
	public boolean save(){
		String query = "insert into employee values ( '"+username+"', '"+password+"', '"+role.toString()+"' )";
		if(!DBConnection.submitUpdate(query)){
			return false;
		}
		return true;
	}

	//getAll Users
	public static ArrayList<User> getAll(){
		ArrayList<User> users=new ArrayList<>();
		try{	
			Connection conn=DBConnection.getConnection();
			Statement statement = conn.createStatement();
			String query="select * from employee";
			ResultSet result=statement.executeQuery(query);
			while(result.next()){
				String un=result.getString("name");
				String pwd=result.getString("password");
				String role=result.getString("role");
				Role r=stringToRole(role);
				users.add(new User(un,pwd,r));
			}
			result.close();
		}
		catch(SQLException sqle){
			Logger.logError(sqle.getMessage());
		}
		return users;
	}

	//retrieves a given user from the database using a username
	public static User retrieve(String username){
		Connection conn = DBConnection.getConnection();
		try{
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select * from employee where name = '"+username+"'");
			while(rs.next()){
				User user = new User(rs.getString("name"),rs.getString("password"), stringToRole(rs.getString("role")));
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
	public boolean delete(){
		String query = "delete from employee where name = '"+username+"'";
		if(!DBConnection.submitUpdate(query)){
			return false;
		}
		return true;
	}
	
	//Add original user list to DB
	public static void populateTable(){
		new User("Normal","Normal", Role.Normal).save();
		new User("Admin","Admin", Role.Admin).save();
	}
}