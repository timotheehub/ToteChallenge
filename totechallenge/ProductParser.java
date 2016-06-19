package totechallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses products from a CSV.
 * 
 * @author Timothee
 */
public class ProductParser {

	/**
	 * Default constructor.
	 */
	public ProductParser() {
	}
	
	/**
	 * Parses products from a CSV.
	 * @param productLineList The list of product CSV lines.
	 * @return The list of products.
	 */
	public List<Product> parseProducts(List<String> productLineList) {
		// Check that the product CSV isn't empty
		if ((null == productLineList) || productLineList.isEmpty())
			return new ArrayList<Product>();
		
		// Build the list of products
		List<Product> productList = new ArrayList<Product>();
		for (String productCsvLine : productLineList) {
			// Skip the line if it's empty
			if (productCsvLine.isEmpty())
				continue;
			
			// Retrieve the product details
			String[] productDetailStrings = productCsvLine.split(",");
			if (6 != productDetailStrings.length)
				continue;
			
			// Convert the product details to integers
			int[] productDetails = new int[productDetailStrings.length];
			for (int i = 0; i < productDetails.length; i++) {
				productDetails[i] = Integer.parseInt(productDetailStrings[i]);
			}
			
			// Add the product to the list
			productList.add(new Product(
					productDetails[0], 
					productDetails[1], 
					productDetails[2], 
					productDetails[3],
					productDetails[4],
					productDetails[5]));
		}
		
		// Return the list of products
		return productList;
	}

}
