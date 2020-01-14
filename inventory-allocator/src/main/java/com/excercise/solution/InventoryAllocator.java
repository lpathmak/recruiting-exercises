package com.excercise.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Class can be used to find best way to allocate order items to warehouses and returns possible shipments
 */
public class InventoryAllocator {

    /**
     *
     * @param order
     * @param warehouses
     * @return all shipments needed for order
     */
    public List<Shipment> allocateInventory(Order order, List<Warehouse> warehouses){

        List<Shipment> shipments = new ArrayList<>();

        if(order == null || warehouses == null) {
            return shipments;
        }

        for(Warehouse w : warehouses){
            if(w == null){
                continue;
            }

            Shipment shipment = w.createShipment(order);

            if (shipment != null){
                shipments.add(shipment);
            }
        }

        if(!order.allItemsAllocated()){
            return new ArrayList<>();
        }

        return shipments;
    }
}
