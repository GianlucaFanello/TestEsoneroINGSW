package it.unical.demacs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static it.unical.demacs.Inventory.OrderStatus.*;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();
    }

    @Test
    public void addProductThrowsOnNegative() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
           inventory.addProduct("mozzarella",-1);

        });
        assertEquals(ex.getMessage(),"Quantity must be non negative");

    }

    @Test
    public void addProductCreateSingleProduct() {
        inventory.addProduct("pomodori",10);
        assertEquals(10,inventory.getAvailableProducts().get("pomodori"));
    }


    @ParameterizedTest
    @CsvSource({"pomodori,10","arancie,50"})
    void addProductCreateSingleProductsParams(String name, int quantity) {
        inventory.addProduct(name,quantity);
        assertEquals(quantity,inventory.getAvailableProducts().get(name));
    }

    @Test
    public void addProductCreateMultipleProducts() {
        inventory.addProduct("pomodori",10);
        assertTrue(inventory.getAvailableProducts().containsKey("pomodori"));
    }

    @Test
    public void addProductIncreaseQuantityOnExistingProduct() {
        inventory.addProduct("pomodori",10);
        inventory.addProduct("POMODORI",60);
        inventory.addProduct("pomodori",15);
        assertEquals(85,inventory.getAvailableProducts().get("pomodori"));

    }

    @Test
    public void makeOrderThrowsOnNegative() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            inventory.makeOrder("pomodori",-1);
        });
        assertEquals(ex.getMessage(),"Quantity must be greater than zero");
    }

    @Test
    public void makeOrderNoSuchItemStatus() {
        assertEquals(NO_SUCH_ITEM,inventory.makeOrder("pomodori",10));
    }

    @Test
    public void makeOrderNotEnoughItemStatus() {
        inventory.addProduct("pomodori",10);
        assertEquals(NOT_ENOUGH_ITEMS,inventory.makeOrder("pomodori",20));
    }

    @Test
    public void makeOrderOrdered() {
        inventory.addProduct("pomodori",10);
        assertEquals(ORDERED,inventory.makeOrder("pomodori",10));
    }

    @Test
    public void makeOrderPresence() {
        inventory.addProduct("pomodori",10);
        inventory.makeOrder("pomodori",10);
        assertTrue(!inventory.getAvailableProducts().containsKey("pomodori"));
    }
}
