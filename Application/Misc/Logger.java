/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.StandardOpenOption;

//Logger class used to log errors encountered at runtime
public class Logger{

	public static void logError(String error){
		//Just print to terminal for now, will eventually be some type of log fileE
		//System.out.println(error);
        try {
            Files.write(Paths.get("POSLog.txt"), (error+"\n").getBytes(), StandardOpenOption.APPEND);
        }catch (IOException ioe) {
            System.out.println("Error: logging failed");
        }
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
        errorFrame.setSize(400,400);
        errorFrame.setLocationRelativeTo(null);
    	errorFrame.setVisible(true);
    	errorFrame.getRootPane().setDefaultButton(returnButton);
	}
}