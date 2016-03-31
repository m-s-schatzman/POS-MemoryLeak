//Logger class used to log errors encountered at runtime
public class Logger{
	public static void logError(String error){
		//Just print to terminal for now, will eventually be some type of log file
		System.out.println("Error: "+error);
	}
}