/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Cameron
 */
import java.util.ArrayList;
public class Database {

	private ArrayList<Item> items = new ArrayList<Item>();
 	private ArrayList<User> users = new ArrayList<User>();
 	private static Database databaseInstance;


	private Database(){
 		items.add(new Item(0, 1, "Apple"));
 		items.add(new Item(1, 1, "Banana"));
 		items.add(new Item(2, 3, "Capn Crunch"));
 		users.add(new User("MarkyMark", "funk"));
	}

	public Item getItem(int ID) {
		return items.get(ID);
	}

	public boolean authenticate(String userName, String password){
		System.out.println("In authenticate: " + userName + " " + password);
		for(int i = 0; i < users.size(); i++){
			if(users.get(i).getUserId().equals(userName)){
				if(users.get(i).getPassword().equals(password)){
					return true;
				}
			}
		}
		return false;
	}

	public static Database getDatabase(){
		if(databaseInstance == null){
			databaseInstance = new Database();
		}
		return databaseInstance;
	}
}
