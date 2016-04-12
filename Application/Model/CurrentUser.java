/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

public class CurrentUser{

	private static User currentUser;

	private CurrentUser(){
	}

	public static boolean login(String username, String password){
		User user = User.retrieve(username);
		if(user != null && user.getPassword().equals(password)){
			currentUser = user;
			return true;
		}
		return false;
	}

	public static User getUser(){
		return currentUser;
	}

	public static void logout(){
		currentUser = null;
	}
}