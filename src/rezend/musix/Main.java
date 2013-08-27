package rezend.musix;

import rezend.musix.core.ProductDAO;
import rezend.musix.core.Product;
import rezend.musix.core.ProductParser;

/**
 * Preferably open-source libraries
 * 
 * @author Rafael Rezende
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Product prod = ProductParser.parseProductFromURL("http://www.musik-produktiv.ch/boss-loop-station-rc-300.aspx");
		
		ProductDAO.connect();
		ProductDAO.addProduct(prod);
		ProductDAO.printProducts();
	}
}
