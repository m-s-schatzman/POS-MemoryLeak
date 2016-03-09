

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author QingYi
 */
public class SaleTaxCalc {
    
  public static double salesTax(Sale sale){
    double salePrice=sale.getTotal(); // get price from another class
    double tax;
    double taxRate=0;
    double totalPrice; // price to charge with tax
    String state="PA";//another class needs to pass in state info
    
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
    
    tax=salePrice*taxRate;
    totalPrice=tax+salePrice;
    System.out.println("The sales tax at "+state+" is: "+taxRate);
    System.out.println("The sales tax for this transaction is "+ tax);
    System.out.println("The total price for this transaction is "+totalPrice);
    return totalPrice;
  }
    
}
