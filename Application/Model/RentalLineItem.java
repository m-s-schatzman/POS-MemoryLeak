import java.util.Date;

public class RentalLineItem extends LineItem{
    
    private Date returnDate;
    
    /** constructor */
    public RentalLineItem(int count, Item item, Date returnDate){
        super(count, item);
        this.returnDate = returnDate;
    }

    /** return returnDate */
    public Date getReturnDate(){
        return this.returnDate;
    }

    /** save rentalLineItem to db */
    public void save(int rentalId){
        //This return date will likely need to be in another format, but this should work for now
        String query = "insert into rental_item values ( "+rentalId+", "+item.getID()+", "+count+", "+returnDate.toString()+" )";
        DBConnection.submitUpdate(query);
    }
}