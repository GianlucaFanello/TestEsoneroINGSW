package it.unical.demacs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//*Represents an inventory that holds products and their quantities
//*This class allows adding products, making orders, and tracking the availability of items.

// *This class was created solely for educational purposes. The code is not optimized, but is designed to allow students to practice and apply JUnit features.
public class Inventory {

    private final HashMap<String, Integer> availableProducts;

    public enum OrderStatus{
        ORDERED,
        NO_SUCH_ITEM,
        NOT_ENOUGH_ITEMS,
    }

    public Inventory() {
        this.availableProducts = new HashMap<>();
    }

    /**
     * Adds a given quantity of a product to the inventory.
     * If the product already exists, its quantity is updated.
     *
     * @param product  The name of the product to add.
     * @param quantity The quantity of the product to add.
     * @throws IllegalArgumentException If the quantity is less than or equal to zero.
     */
    public void addProduct(String product, int quantity){
        if(quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be non negative");
        }

        String normalizedProduct = product.toLowerCase().trim();
        if(availableProducts.containsKey(normalizedProduct)){
            availableProducts.put(normalizedProduct, availableProducts.get(normalizedProduct) + quantity);
        }else{
            availableProducts.put(normalizedProduct, quantity);
        }

    }

    /**
     * Attempts to place an order for a product with a specified quantity.
     * If the product is not available or there is insufficient quantity,
     * an appropriate order status is returned.
     *
     * @param product  The name of the product to order.
     * @param quantity The quantity of the product to order.
     * @return The status of the order.
     * @throws IllegalArgumentException If the quantity is less than or equal to zero.
     */
    public OrderStatus makeOrder(String product, int quantity){
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        String normalizedProduct = product.toLowerCase().trim();

        if (!availableProducts.containsKey(normalizedProduct)) {
            return OrderStatus.NO_SUCH_ITEM;
        }

        int availableQuantity = availableProducts.get(normalizedProduct);
        if (availableQuantity < quantity) {
            return OrderStatus.NOT_ENOUGH_ITEMS;
        }

        availableProducts.put(normalizedProduct, availableQuantity - quantity);

        if(availableProducts.get(normalizedProduct) == 0) {
            availableProducts.remove(normalizedProduct);
        }

        return OrderStatus.ORDERED;
    }

    public int getQuantity(String product) {
        return availableProducts.get(product.toLowerCase().trim());
    }

    public Map<String, Integer> getAvailableProducts() {
        return Collections.unmodifiableMap(availableProducts);
    }
}
