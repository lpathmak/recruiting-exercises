package com.excercise.solution;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class used to test inventoryAllocator class
 */

public class InventoryAllocatorTest {

    InventoryAllocator allocator;

    Order nullOrder;
    Order emptyOrder;
    Order oneItemOrder;
    Order manyItemOrder;

    Warehouse nullWarehouse;
    Warehouse emptyWarehouse;
    Warehouse oneItemWarehouse;
    Warehouse manyItemWarehouse;

    /**
     * Setup variables
     */
    public InventoryAllocatorTest(){

        allocator = new InventoryAllocator();

        //null order
        nullOrder = null;

        //empty order
        emptyOrder = new Order(new HashMap<>());

        //1 item order
        HashMap<String, Integer> oneItem = new HashMap<>();
        oneItem.put("apple", 3);
        oneItemOrder = new Order(oneItem);

        //big order
        HashMap<String, Integer> manyItems = new HashMap<>();
        manyItems.put("apple", 10);
        manyItems.put("banana", 14);
        manyItems.put("grape", 10);
        manyItems.put("avocado", 3);
        manyItems.put("cherry", 3);
        manyItemOrder = new Order(manyItems);

        //null warehouse
        nullWarehouse = null;

        //empty warehouse
        emptyWarehouse = new Warehouse("none");

        //1 item warehouse
        HashMap<String, Integer> oneItemInventory = new HashMap<>();
        oneItemInventory.put("apple", 7);
        oneItemWarehouse = new Warehouse("one", oneItemInventory);

        //many items warehouse
        HashMap<String, Integer> manyItemsInventory = new HashMap<>();
        manyItemsInventory.put("apple", 3);
        manyItemsInventory.put("banana", 30);
        manyItemsInventory.put("grape", 20);
        manyItemsInventory.put("avocado", 15);
        manyItemsInventory.put("cherry", 3);
        manyItemsInventory.put("orange", 15);
        manyItemWarehouse = new Warehouse("many", manyItemsInventory);

    }

    /**
     * Test null order and null warehouse
     * @return empty shipment
     */
    @Test
    public void nullOrderAndNullWarehouse(){
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(nullWarehouse);
        List<Shipment> result = allocator.allocateInventory(nullOrder, warehouses);

        assertEquals(new ArrayList<>(), result);

    }

    /**
     * Test empty order and empty warehouse
     * @return empty shipment
     */

    @Test
    public void emptyOrderAndEmptyWarehouse(){
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(emptyWarehouse);
        List<Shipment> result = allocator.allocateInventory(emptyOrder, warehouses);

        assertEquals(new ArrayList<>(), result);
    }

    /**
     * Test a small order and small warehouse that can accommodate items
     * @return single shipment
     */
    @Test
    public void oneOrderAndOneItemWarehouse(){

        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(oneItemWarehouse);
        List<Shipment> result = allocator.allocateInventory(oneItemOrder, warehouses);

        HashMap <String, Integer> expectedItems =  new HashMap<>();
        expectedItems.put("apple", 3);

        List<Shipment> expected = new ArrayList<>();
        expected.add(new Shipment(oneItemWarehouse.getName(), expectedItems));

        assertTrue(equalShipmentsCheck(expected, result));
    }

    /**
     * Test a big order and two warehouse that can accommodate items
     * @return two shipments, item split should be seen
     */
    @Test
    public void bigOrderAndManyItemWarehouse(){

        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(oneItemWarehouse);
        warehouses.add(manyItemWarehouse);
        List<Shipment> result = allocator.allocateInventory(manyItemOrder, warehouses);

        HashMap <String, Integer> expectedFirstWarehouse =  new HashMap<>();
        expectedFirstWarehouse.put("apple", 7);

        HashMap <String, Integer> expectedSecondWarehouse =  new HashMap<>();
        expectedSecondWarehouse.put("apple", 3);
        expectedSecondWarehouse.put("banana", 14);
        expectedSecondWarehouse.put("grape", 10);
        expectedSecondWarehouse.put("avocado", 3);
        expectedSecondWarehouse.put("cherry", 3);

        List<Shipment> expected = new ArrayList<>();
        expected.add(new Shipment(oneItemWarehouse.getName(), expectedFirstWarehouse));
        expected.add(new Shipment(manyItemWarehouse.getName(), expectedSecondWarehouse));

        assertTrue(equalShipmentsCheck(expected, result));
    }

    /**
     * Test a big order and small warehouse that cannot accommodate
     * @return empty shipment
     */

    @Test
    public void bigOrderAndOneItemWarehouse(){

        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(oneItemWarehouse);
        List<Shipment> result = allocator.allocateInventory(manyItemOrder, warehouses);

        assertEquals(new ArrayList<>(), result);
    }

    /**
     *
     * @param expected
     * @param result
     * @return true if both list of shipments have same content
     */

    private boolean equalShipmentsCheck(List<Shipment> expected, List<Shipment> result) {

        if(expected.size() != result.size()){
            return false;
        }

        for (int i = 0; i < result.size(); i++){
            if(!expected.get(i).equals(result.get(i))){
                return false;
            }
        }

        return true;
    }

}
