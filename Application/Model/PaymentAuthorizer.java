/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

//Payment Authorizer acts as an in-between of third party Payment software and POS system
public class PaymentAuthorizer {
    
    public static boolean authorizePayment (String cardNumber) {
        if(cardNumber.length() != 16 || !cardNumber.matches("[0-9]+")){
            return false;
        }
        //Send to third party payment
        return true;    
    }
}
