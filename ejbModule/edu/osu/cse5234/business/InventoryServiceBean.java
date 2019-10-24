package edu.osu.cse5234.business;

import edu.osu.cse5234.business.view.Inventory;
import edu.osu.cse5234.business.view.InventoryService;
import edu.osu.cse5234.business.view.Item;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class InventoryServiceBean
 */
@Stateless
public class InventoryServiceBean implements InventoryService {

	public static final String MY_QUERY = "Select i from Item i";
	
	@PersistenceContext
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public InventoryServiceBean() {
        // TODO Auto-generated constructor stub
    }

    public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
    public Inventory getAvailableInventory() {
    	List<Item> items = entityManager.createQuery(MY_QUERY, Item.class).getResultList();
    	Inventory inventory = new Inventory();
    	inventory.setItemList(items);
    	return inventory;
    }
	
    @Override
	public boolean validateQuantity(List<Item> items) {
    	HashMap<Integer, Integer> stock = new HashMap<Integer, Integer>();
    	Inventory inventory = getAvailableInventory();
    	for (Item availableItem : inventory.getItemList()) {
    		stock.put(availableItem.getItemNumber(), availableItem.getAvailableQuantity());
    	}
    	if (items == null || items.size() == 0) return false;
    	for (Item item : items) {
    		if (item.getQuantity() > stock.getOrDefault(item.getItemNumber(), 0)) {
    			return false;
    		}
    	}
		return true;
	}
	
    @Override
	public boolean updateInventory(List<Item> items) {
		return true;
	}
}
