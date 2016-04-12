/*------------------------------------------------------------------------------*/
// Memory Leak
// Adam Svetec, Cameron Clifton, Jason Yang, Mark Schatzman, Qing Yi, Yuchen Cai
/*------------------------------------------------------------------------------*/

import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Rental{

	private ArrayList<RentalLineItem> cart;
	private int id;

	/** constructor */
	public Rental(){
		cart = new ArrayList<RentalLineItem>();
	}

	/** get total of cart */
	public double getTotal(){
		double total=0;
        for(int i =0; i<cart.size();i++){
            total += cart.get(i).getPrice();
        }
        return total;
	}

	/** adds new rental line item to the cart*/
	public void addRentalLineItem(RentalLineItem lineItem){
		for(RentalLineItem cartItem : cart){
            if(lineItem.getItem().getID() == cartItem.getItem().getID() && lineItem.getReturnDate().equals(cartItem.getReturnDate())){
                cartItem.setCount(lineItem.getCount() + cartItem.getCount());
                return;
            }
        }
        cart.add(lineItem);
	}

	/** removes given rentalLineItem from the cart */
	public boolean removeRentalLineItem(LineItem lineItem){
		return cart.remove(lineItem);
	}
	
	/** gets String list of rental line items containing name, count, and return date of each item*/
	public String getCartList(){
		String cartList = "";
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        for(RentalLineItem cartItem : cart){
            cartList += cartItem.getItem().getName() + " : " + cartItem.getCount() +" : "+sdf.format(cartItem.getReturnDate())+"\n";
        }
        return cartList;
	}

	/** save instance to db */
    public void save(){
		Connection conn = DBConnection.getConnection();
        int id = 0;
        try{
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select max(id) as maximum from rental");
            if(rs.next()){
                id = rs.getInt("maximum") + 1;
            }else{
                id = 1;
            }
            rs.close();
            s.close();
        }catch(SQLException sqle){
            Logger.logError(sqle.getMessage());
        }
        String query = "insert into rental values ( "+id+" )";
        DBConnection.submitUpdate(query);

        Inventory inventory = Inventory.getInventory();
        for(RentalLineItem lineItem : cart){
            lineItem.save(id);
            inventory.purchaseLineItem(lineItem);
        }
    }
}