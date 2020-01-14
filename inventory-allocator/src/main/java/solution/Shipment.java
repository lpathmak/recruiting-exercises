package solution;

import java.util.HashMap;

/**
 * Class represents which warehouse ships item after allocation
 * Ex: [{ owd: { apple: 1 } }]
 *
 */

public class Shipment {

    private String name;
    private HashMap<String, Integer> items;

    public Shipment(String name){
        this.name = name;
        this.items = new HashMap<>();
    }

    public Shipment(String name, HashMap<String, Integer> items){
        this.name = name;
        this.items = items;
    }

    public String getName(){
        return name;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }

    public void updateItem(String itemName, int amount){
        items.put(itemName, Math.max(amount, 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shipment obj = (Shipment) o;
        return name.equals(obj.getName()) &&
                items.equals(obj.getItems());
    }
}
