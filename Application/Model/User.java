import java.util.*;

public class User {

	private String userId;
	private String password;

	User(String userId, String password){
		this.userId = userId;
		this.password = password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUserId(){
		return this.userId;
	}

	public String getPassword()
	{
		return this.password;
	}
}