import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

public class POSView{

  private JFrame f;
  private JPanel p;
  private FlowLayout fl;
  private JButton processSaleButton;
  private JButton processRentalButton;
  private JButton processReturnButton;
  private JButton manageUsersButton;
  private JButton logoutButton;

  public POSView(){
    this.f = new JFrame("Memory Leak POS");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = screenSize.height;
    int width = screenSize.width;
    f.setSize((width/2) - 600, (height/2) - 250);

    f.setLocationRelativeTo(null);
    p = new JPanel();
    fl = new FlowLayout(FlowLayout.CENTER);

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
    f.setSize(200,200);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setContentPane(p);
    f.setVisible(true);
  }

  public void addController(ActionListener controller){
   processSaleButton.addActionListener(controller);
   processRentalButton.addActionListener(controller);
   processReturnButton.addActionListener(controller);
   manageUsersButton.addActionListener(controller);
   logoutButton.addActionListener(controller);
  }

  public void closeFrame(){
    f.setVisible(false);
    f.dispose();
  }
}