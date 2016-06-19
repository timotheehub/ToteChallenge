package totechallenge;

/**
 * Represents a product.
 * 
 * @author Timothee
 */
public class Product {

	/**
	 * Product id.
	 */
	private int id;
	
	/**
	 * Product price in dollars.
	 */
	private int price;
	
	/**
	 * Product length in centimeters.
	 */
	private int length;
	
	/**
	 * Product width in centimeters.
	 */
	private int width;
	
	/**
	 * Product height in centimeters.
	 */
	private int height;
	
	/**
	 * Product weight in grams.
	 */
	private int weight;

	/**
	 * Initializes an instance of Product.
	 * @param id Product id.
	 * @param price Price in dollars.
	 * @param length Length in centimeters.
	 * @param width Width in centimeters.
	 * @param height Height in centimeters.
	 * @param weight Weight in grams.
	 */
	public Product(int id, int price, int length, int width, int height, int weight) {
		this.id = id;
		this.price = price;
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
	}

	/**
	 * @return The product id.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return The price in dollars.
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * @return The length in centimeters.
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * @return The width in centimeters.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * @return The height in centimeters.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return The weight in grams.
	 */
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * @return The volume in centimeter cubes.
	 */
	public int getVolume() {
		return this.length * this.width * this.height;
	}
}
