package totechallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a set of products.
 * 
 * @author Timothee
 */
public class ProductSet {
	
	/**
	 * List of product ids.
	 */
	private List<Integer> productIdList;

	/**
	 * Product price in dollars.
	 */
	private int price;
	
	/**
	 * Weight in grams.
	 */
	private int weight;
	
	/**
	 * Initializes an instance of Product.
	 */
	public ProductSet() {
		this.productIdList = new ArrayList<Integer>();
	}
	
	/**
	 * @return The list of product ids.
	 */
	public List<Integer> getProductIdList() {
		return this.productIdList;
	}

	/**
	 * @return The price in dollars.
	 */
	public int getPrice() {
		return this.price;
	}
	
	/**
	 * @return The weight in grams.
	 */
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * Adds a product to a new product set.
	 * @param product The product to add.
	 * @return The new product set with the added product.
	 */
	public ProductSet addProduct(Product product) {
		// Initialize the product set
		ProductSet productSet = new ProductSet();
		productSet.productIdList = new ArrayList<Integer>(this.productIdList);
		if (null == product)
		{
			productSet.price = this.price;
			productSet.weight = this.weight;
		}
		else
		{
			productSet.productIdList.add(product.getId());
			productSet.price = this.price + product.getPrice();
			productSet.weight = this.weight + product.getWeight();
		}
		
		// Return the product set
		return productSet;
	}
}
