/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import java.util.Date;
import java.text.SimpleDateFormat;

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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "insert into rental_item values ( "+rentalId+", "+item.getID()+", "+count+", '"+df.format(returnDate)+"' )";
        DBConnection.submitUpdate(query);
    }
}