//Class used to connect to the locally stored derby database
public class PersistentStorage {

	private static PersistentStorage persistentStorage;
	
	private PersistentStorage(){
 	}

 	//Gets single instantiated instance of the PersistentStorage class
	public static PersistentStorage getInstance(){
		if(persistentStorage == null){
			persistentStorage = new PersistentStorage();
		}		
		return persistentStorage;
	}
}