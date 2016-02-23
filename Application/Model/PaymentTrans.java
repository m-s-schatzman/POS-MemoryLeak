/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

import java.util.Scanner;
     
 /*
 * @author Jason
 */
public class PaymentTrans {

    /**
     * @param args the command line arguments
     */
    public static String PaymentAuth (long cardAmount, int cash, int TotalPay) {
        Scanner ms = new Scanner(System.in);
        System.out.println("Payment Option: Cash or Card?");
        String option = ms.next();
        if (option == "Cash") {
            if (cash == TotalPay) {
                return "Success! Payment has been authorized";
            }
            else {
                return "Payment fail! Please check amount";
            }
        }
        else if (option == "Card") {
            if (cardAmount == TotalPay) {
                return "Success! Payment has been authorized";
            }
            else {
                return "Payment fail! Please check card or amount";
            }
        }
    }
}
