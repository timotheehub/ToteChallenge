package totechallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solves the Tote Challenge, which consists to find which products from a catalog can be selected in order to maximize
 * the dollar value of the products in the tote and minimize the weight as long as it doesn't impact the dollar value.
 * Each product can only be selected one and the total volume of the products must be smaller than the volume of the
 * tote.
 * 
 * @author Timothee
 */
public class ToteChallengeSolver {

	/**
	 * Length in centimeters.
	 */
	private int length;
	
	/**
	 * Width in centimeters.
	 */
	private int width;
	
	/**
	 * Height in centimeters
	 */
	private int height;
	
	/**
	 * Initializes an instance of ToteChallengeSolver.
	 * @param length Length in centimeters.
	 * @param width Width in centimeters.
	 * @param height Height in centimeters.
	 */
	public ToteChallengeSolver(int length, int width, int height) {
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Returns the list of products that fit together in the tote, maximizes the dollar value and minimize the weight 
	 * as long as it doesn't impact the dollar value.
	 * @param productList Catalog of products.
	 * @return The list of product ids that fit together in the tote, maximizes the dollar value and minimize the weight
	 * as long as it doesn't impact the dollar value. 
	 */
	public List<Integer> getMostValuableProductList(List<Product> productList) {
		// Check that the product list is not null nor empty
		if ((null == productList) || (0 == productList.size()))
			return null;
		
		// Filter out the products that don't fit in the tote
		List<Product> fittingProductList = this.filterOutLargeProducts(productList);
		
		// Initialize the product sets for the previous and current rows of the matrix
		// We don't need to keep the entire matrix in memory
		int toteVolume = this.length * this.width * this.height;
		ProductSet[] previousProductSets;
		ProductSet[] currentProductSets = new ProductSet[toteVolume + 1];
		
		// Initialize the price for the null product (index 0)
		for (int j = 0; j < currentProductSets.length; j++) {
			currentProductSets[j] = new ProductSet();
		}
		
		// Compute the product sets
		for (int i = 0; i < fittingProductList.size(); i++) {
			// Update the product sets
			previousProductSets = currentProductSets;
			currentProductSets = new ProductSet[toteVolume + 1];
			
			// Initialize the product variables
			Product product = fittingProductList.get(i);
			int productVolume = product.getVolume();
			
			// Compute the row of prices for the product
			for (int j = 0; j <= toteVolume; j++) {
				// If the product is larger than the volume, copy the value from the previous row
				if (j < productVolume)
					currentProductSets[j] = previousProductSets[j];
				
				// Otherwise, check whether the set with the product gives better result
				else {
					// Compute the price of the product set that includes the current product
					int priceWithProduct = previousProductSets[j-productVolume].getPrice() + product.getPrice();
					
					// If the combination of products from the previous row has a higher value or the same value 
					// and a lower weight, we copy the product set of the previous row
					if ((previousProductSets[j].getPrice() > priceWithProduct)
					|| ((previousProductSets[j].getPrice() == priceWithProduct) 
							&& (previousProductSets[j].getWeight() < 
									previousProductSets[j-productVolume].getWeight() + product.getWeight())))
						currentProductSets[j] = previousProductSets[j];
					
					// Otherwise we use the product set with the product
					else
						currentProductSets[j] = previousProductSets[j-productVolume].addProduct(product);
				}
			}
		}
		
		// Return the list of product ids that fit together in the tote, maximizes the dollar value and minimize the
		// weight as long as it doesn't impact the dollar value
		return currentProductSets[toteVolume].getProductIdList();
	}
	
	/**
	 * Filters out the products that don't fit individually in the tote.
	 * @param productList The original list of products.
	 * @return The list of products that fit individually in the tote.
	 */
	private List<Product> filterOutLargeProducts(List<Product> productList) {
		// Check that the product list is not null
		if (null == productList)
			return null;

		// Sort the tote side lengths
		int[] orderedToteSideLengths = new int[] { this.length, this.width, this.height };
		Arrays.sort(orderedToteSideLengths);
		
		// Build the fitting product list
		List<Product> fittingProductList = new ArrayList<Product>(productList.size());
		for (Product product : productList) {
			// Sort the product side lengths
			int[] orderedProductSideLengths = new int[] { 
					product.getLength(),
					product.getWidth(),
					product.getHeight() };
			Arrays.sort(orderedProductSideLengths);
			
			// Check whether the product fits
			boolean doesFit = true;
			for (int i = 0; i < orderedToteSideLengths.length; i++) {
				if (orderedProductSideLengths[i] > orderedToteSideLengths[i]) {
					doesFit = false;
					break;
				}
			}
			
			// Add the product to the list if it fits
			if (doesFit)
				fittingProductList.add(product);
		}
		
		// Return the fitting product list
		return fittingProductList;
	}
}
