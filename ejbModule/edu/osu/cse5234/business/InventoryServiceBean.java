package edu.osu.cse5234.business;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.business.view.Item;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
public class InventoryServiceBean implements InventoryService {

    /**
     * Default constructor. 
     */
    public InventoryServiceBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Inventory getAvailableInventory() {
    	String[] gums = { "Bubble", "Spearmint", "Peppermint", "Wintergreen", "Cinnamon" };
    	List<Item> items = new ArrayList<Item>();
    	for (int i = 0; i < 5; i++) {
    		Item item = new Item();
    		item.setName(gums[i]);
    		item.setPrice(i + .5);
    		item.setQuantity((i / 3) + 1);
    		items.add(item);
    	}
    	Inventory inventory = new Inventory();
    	inventory.setItemList(items);
    	return inventory;
    }
	
    @Override
	public boolean validateQuantity(List<Item> items) {
		return true;
	}
	
    @Override
	public boolean updateInventory(List<Item> items) {
		return true;
	}
}
