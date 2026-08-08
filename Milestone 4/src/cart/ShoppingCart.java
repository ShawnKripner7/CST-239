package cart;

import product.SalableProduct;

/**
 * Represents the customer's shopping cart.
 *
 * @author Shawn Kripner
 */
public class ShoppingCart {

    private SalableProduct[] cartItems;

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
        cartItems = new SalableProduct[10];
    }

    /**
     * Adds a product to the shopping cart.
     *
     * @param product the product to add
     * @return true if added successfully
     */
    public boolean addProduct(
            SalableProduct product) {

        for (int i = 0;
                i < cartItems.length;
                i++) {

            if (cartItems[i] == null) {

                cartItems[i] = product;

                return true;
            }
        }

        return false;
    }

    /**
     * Removes a product from the shopping cart.
     *
     * @param product the product to remove
     * @return true if removed successfully
     */
    public boolean removeProduct(
            SalableProduct product) {

        for (int i = 0;
                i < cartItems.length;
                i++) {

            if (cartItems[i] == product) {

                cartItems[i] = null;

                return true;
            }
        }

        return false;
    }

    /**
     * Returns the shopping cart.
     *
     * @return the shopping cart array
     */
    public SalableProduct[] getShoppingCart() {
        return cartItems;
    }

    /**
     * Empties the shopping cart.
     */
    public void emptyShoppingCart() {

        for (int i = 0;
                i < cartItems.length;
                i++) {

            cartItems[i] = null;
        }
    }
}