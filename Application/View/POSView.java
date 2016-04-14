/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class POSView{

    private JFrame f;
    private JPanel p;
    private FlowLayout fl;
    private JLabel roleLabel;
    private JButton processSaleButton;
    private JButton processRentalButton;
    private JButton processReturnButton;
    private JButton manageUsersButton;
    private JButton logoutButton;

    //Constructor
    public POSView(){
        this.f = new JFrame("Memory Leak POS");
        p = new JPanel();
        fl = new FlowLayout(FlowLayout.CENTER);
        roleLabel=new JLabel();
        p.add(roleLabel);
        roleLabel.setText("Current User Level is: " + CurrentUser.getUser().getRole().toString());
        processSaleButton = new JButton("Process Sale");
        p.add(processSaleButton);
        processRentalButton = new JButton("Process Rental");
        p.add(processRentalButton);
        processReturnButton = new JButton("Process Return");
        p.add(processReturnButton);
        manageUsersButton = new JButton("Manage Users");
        if(CurrentUser.getUser().getRole() == User.Role.Admin){
          p.add(manageUsersButton);
        }
        logoutButton = new JButton("Logout");
        p.add(logoutButton);

        p.setLayout(fl);
        f.setSize(200,225);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(p);
        f.setVisible(true);
    }

    //Adds Action Listener to the buttons
    public void addController(ActionListener controller){
        processSaleButton.addActionListener(controller);
        processRentalButton.addActionListener(controller);
        processReturnButton.addActionListener(controller);
        manageUsersButton.addActionListener(controller);
        logoutButton.addActionListener(controller);
    }

    //Closes View and JFrame
    public void closeFrame(){
        f.setVisible(false);
        f.dispose();
    }
}