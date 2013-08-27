package rezend.musix;

import rezend.musix.constants.Regex;
import rezend.musix.tool.ContentParser;
import rezend.musix.tool.OCRModule;
import rezend.musix.tool.URLReader;

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

		String urlContent = URLReader
				.getURLContent("http://www.musik-produktiv.ch/boss-loop-station-rc-300.aspx");
		
		System.out.println(urlContent);

		// Retrieve product name
		String product = ContentParser.parseContent(urlContent, Regex.M_PRODUKTIV_PRODUCT);
		System.out.println(product);
		
		String id = ContentParser.parseContent(urlContent, Regex.M_PRODUKTIV_ID);
		System.out.println(id);
		
		String priceLink = ContentParser.parseContent(urlContent, Regex.M_PRODUKTIV_PRICE_LINK);
		double price = OCRModule.readImage(priceLink);
		System.out.println(price);
	}
}
