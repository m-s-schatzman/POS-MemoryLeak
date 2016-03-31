import java.util.Date;

public class RentalLineItem extends LineItem{
    
    private Date returnDate;
    
    /** constructor */
    private RentalLineItem(int count, Item item, Date returnDate){
        super(count, item);
        this.returnDate = returnDate;
    }
    
    /** create a rentallineItem with count and item */
    public RentalLineItem createRentalLineItem(int count, Item item, Date returnDate){
        return new RentalLineItem(count,item, returnDate);
    }

    /** return returnDate */
    public Date getReturnDate(){
        return this.returnDate;
    }
}