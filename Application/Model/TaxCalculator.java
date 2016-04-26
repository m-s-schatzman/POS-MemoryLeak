/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

public class TaxCalculator {
  private static double saleTotal;
  private static double totalTax;
  private static double taxRate;
    
  public static double getSalesTax(Sale sale){
    saleTotal = sale.getTotal();
    totalTax = 0;
    taxRate = 0;
    String state = "NJ";//best state in the continental US. Long Live 
    
    switch (state){
      case "PA": taxRate=0.06;
      break;
      case "NY": taxRate=0.04;
      break;
      case "MA": taxRate=0.0625;
      break;
      case "NH": taxRate=0;
      break;
      case "NJ": taxRate=0.07;
      break;
      default: System.out.println("Invalide State Information! Please check again");
    }
    totalTax = saleTotal * taxRate;
    return totalTax;
  }
}
