package totechallenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Solves the tote challenge with products from a CSV file and prints the sum of the product ids.
 * @author Timothee
 */
public class Program {

	/**
	 * Solves the tote challenge with products from a CSV file and prints the sum of the product ids.
	 * @param args Console arguments.
	 */
	public static void main(String[] args) throws IllegalArgumentException {
		// Check that there is at least one parameter
		if (args.length == 0)
			throw new IllegalArgumentException("The CSV file path is required as the first argument.");
			
		// Retrieve the product line list
		List<String> productLineList;
		try {
			productLineList = Files.readAllLines(Paths.get(args[0]));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		// Retrieve the list of selected products
		List<Product> productList = new ProductParser().parseProducts(productLineList);
		List<Integer> selectedProductIdList = new ToteChallengeSolver(45,30,35).getMostValuableProductList(productList);
		
		// Compute the product id sum 
		int productIdSum = 0;
		for (Integer productId : selectedProductIdList) {
			productIdSum += productId;
		}
		
		// Print the product id sum
		System.out.println(productIdSum);
	}

}
