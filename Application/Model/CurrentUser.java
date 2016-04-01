public class CurrentUser{
	
	private static User currentUser;

	private CurrentUser(){
	}

	public static boolean login(String username, String password){

	}

	public static String getUserName(){
		if(currentUser != null){
			return currentUser.getUserName();
		}
		return null;
	}

	public static void logout(){
		currentUser = null;
	}
}