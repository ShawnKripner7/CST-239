package cart;

import java.util.ArrayList;

import product.SalableProduct;

/**
 * Represents the customer's shopping cart.
 *
 * @author Shawn Kripner
 */
public class ShoppingCart {

    private ArrayList<SalableProduct> cartItems;

    private static final int MAX_CART_SIZE = 10;

    /**
     * Creates a new shopping cart.
     */
    public ShoppingCart() {
        initializeCart();
    }

    /**
     * Initializes an empty shopping cart.
     */
    public void initializeCart() {

        cartItems =
                new ArrayList<SalableProduct>();
    }

    /**
     * Adds a product to the shopping cart.
     *
     * @param product the product to add
     * @return true if added successfully
     */
    public boolean addProduct(
            SalableProduct product) {

        if (cartItems.size()
                >= MAX_CART_SIZE) {

            return false;
        }

        return cartItems.add(product);
    }

    /**
     * Removes a product from the shopping cart.
     *
     * @param product the product to remove
     * @return true if removed successfully
     */
    public boolean removeProduct(
            SalableProduct product) {

        return cartItems.remove(product);
    }

    /**
     * Returns the shopping cart.
     *
     * @return the shopping cart ArrayList
     */
    public ArrayList<SalableProduct>
            getShoppingCart() {

        return cartItems;
    }

    /**
     * Empties the shopping cart.
     */
    public void emptyShoppingCart() {

        cartItems.clear();
    }
}