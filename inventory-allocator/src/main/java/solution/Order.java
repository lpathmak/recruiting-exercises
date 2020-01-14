package solution;

import java.util.HashMap;
import java.util.Set;

/**
 * Class represents a customer order of what items needed
 * Ex: { apple: 5, banana: 5, orange: 5 }
 *
 */

public class Order {

    private HashMap <String, Integer> items;

    public Order(){
        items = new HashMap<>();
    }

    public Order(HashMap <String, Integer> items) {
        this.items = new HashMap<>();
        for (String key : items.keySet()){
            int val = items.get(key);
            this.items.put(key, val);
        }
    }

    public HashMap<String, Integer> getItems(){
        return items;
    }

    public void setItems(HashMap<String, Integer> items){
        this.items = items;
    }

    public Set<String> getItemNames(){
        return items.keySet();
    }

    public int getItemAmount(String itemName){
        return items.get(itemName);
    }

    public void addToItem(String itemName, int amount) {
        items.put(itemName, items.getOrDefault(itemName, 0) + amount);
    }

    public void updateItem(String itemName, int amount){
        items.put(itemName, Math.max(amount, 0));
    }

    /**
     * Checks if all items can be shipped
     * All items should be 0 if allocated
     *
     */
    public boolean allItemsAllocated (){

        for (String item : items.keySet()){
            if(items.get(item) != 0){
                return false;
            }
        }

        return true;
    }
}
