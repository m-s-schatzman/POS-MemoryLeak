import javax.swing.*;

public class Main{
  public static void main(String[] arg){
      JFrame applicationFrame = new JFrame("Memory Leak Project");
      ProcessSaleController controller = new ProcessSaleController(applicationFrame);
  }
}
