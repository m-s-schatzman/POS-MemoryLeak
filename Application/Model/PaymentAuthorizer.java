//Payment Authorizer acts as an in-between of third party Payment software and POS system
public class PaymentAuthorizer {
    
    public static boolean authorizePayment (String cardNumber, double total) {
        if(cardNumber.length() != 16 || !cardNumber.matches("[0-9]+")){
            return false;
        }
        //Send to third party payment
        return true;    
    }
}
