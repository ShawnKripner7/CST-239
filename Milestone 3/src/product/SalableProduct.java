package product;

/**
 * Represents a product that can be sold in the store.
 * 
 * @author Shawn Kripner
 */
public class SalableProduct implements Comparable<SalableProduct> {

	private String name;
	private String description;
	private double price;
	private int quantity;

	/**
	 * Creates a new salable product.
	 * 
	 * @param name the name of the product
	 * @param description the description of the product
	 * @param price the price of the product
	 * @param quantity the quantity available
	 */
	public SalableProduct(String name, String description, double price, int quantity) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	/**
	 * Gets the product name.
	 * 
	 * @return the product name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the product name.
	 * 
	 * @param name the new product name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the product description.
	 * 
	 * @return the product description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the product description.
	 * 
	 * @param description the new product description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the product price.
	 * 
	 * @return the product price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the product price.
	 * 
	 * @param price the new product price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the available quantity.
	 * 
	 * @return the available quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the available quantity.
	 * 
	 * @param quantity the new available quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Compares this product to another product by name (case-insensitive),
	 * then by price if the names are the same.
	 * 
	 * @param other the other product to compare
	 * @return a negative integer, zero, or a positive integer as this product
	 *         is less than, equal to, or greater than the specified product
	 */
	@Override
	public int compareTo(SalableProduct other) {

		int nameComparison = this.name.compareToIgnoreCase(other.name);

		if (nameComparison != 0) {
			return nameComparison;
		}

		return Double.compare(this.price, other.price);
	}
}