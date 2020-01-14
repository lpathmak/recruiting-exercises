package solution;

import java.util.HashMap;

/**
 * Class represents a single warehouse and its information
 * Ex: { name: owd, inventory: { apple: 5, orange: 10 } }
 *
 */

public class Warehouse {

    private String name;
    private HashMap<String, Integer> inventory;

    public Warehouse(String name) {
        this.name = name;
        this.inventory = new HashMap<>();
    }

    public Warehouse(String name,HashMap<String, Integer> inventory) {
        this.name = name;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getInventory(){
        return inventory;
    }

    public void setInventory(HashMap<String, Integer> inventory){
        this.inventory = inventory;
    }

    public void updateInventory(String itemName, int amount){
        inventory.put(itemName, amount);
    }

    public Shipment createShipment(Order order) {

        HashMap<String, Integer> items = new HashMap<>();
        Order orderChanges = new Order(order.getItems());

        for(String item : order.getItemNames()) {
            if(inventory.containsKey(item)) {
                int amount = inventory.get(item);

                if(amount > 0) {
                    orderChanges.updateItem(item, Math.max(order.getItemAmount(item) - amount, 0));
                    items.put(item, Math.min(order.getItemAmount(item), amount));
                }
            }
        }

        if(items.isEmpty()) {
            return null;
        }

        order.setItems(orderChanges.getItems());
        return new Shipment(name, items);
    }
}
