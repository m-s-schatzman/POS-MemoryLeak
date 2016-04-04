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

	public static String getUserName(){
		if(currentUser != null){
			return currentUser.getUsername();
		}
		return null;
	}

	public static void logout(){
		currentUser = null;
	}
}