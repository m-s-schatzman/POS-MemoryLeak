import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

//Logger class used to log errors encountered at runtime
public class Logger{
	public static void logError(String error){
		//Just print to terminal for now, will eventually be some type of log file
		System.out.println(error);
	}

	//Display pop up window with error to display error encountered by program
	public static void displayError(String error){
		JFrame errorFrame = new JFrame("Error");
    	errorFrame.pack();
    	JPanel errorPanel = new JPanel();
    	JLabel errorLabel = new JLabel(error);
    	JButton returnButton = new JButton("OK");

    	returnButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e){
       			errorFrame.dispose();
    		}
		});

		errorPanel.add(errorLabel);
		errorPanel.add(returnButton);
    	errorFrame.setContentPane(errorPanel);
    	errorFrame.setVisible(true);
    	errorFrame.setSize(400,400);
	}
}