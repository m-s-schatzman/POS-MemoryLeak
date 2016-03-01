/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Cameron
 */
 import java.util.ArrayList;
public class Inventory {

 ArrayList<Item> items = new ArrayList<Item>();

 public Inventory(){
 items.add(new Item(0, 1, "Apple"));
 items.add(new Item(1, 1, "Banana"));
 items.add(new Item(2, 3, "Capn Crunch"));
}
public Item scanItem(int ID) {
	return items.get(ID);
}
}
