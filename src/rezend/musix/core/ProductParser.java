/**
 * 
 */
package rezend.musix.core;

import rezend.musix.constants.Regex;
import rezend.musix.tools.ContentParser;
import rezend.musix.tools.OCRModule;
import rezend.musix.tools.URLReader;

/**
 * This class uses the ContentParser to retrieve all relevant information from
 * its respective website. It relies on Regex to filter the URL content.
 * 
 * @author Rafael Rezende
 * 
 */
public final class ProductParser{
	
	public static Product parseProductFromURL(String urlStr){
		
		// get the content of the URL page
		String urlContent = URLReader
				.getURLContent(urlStr);
		
		// retrieve product id
		String id = ContentParser
				.parseContent(urlContent, Regex.M_PRODUKTIV_ID);
		
		// if id is null, then no product has been found in this page
		if (id == null)
			return null;
		
		// retrieve product name
		String product = ContentParser.parseContent(urlContent,
				Regex.M_PRODUKTIV_PRODUCT);

		// retrieve product price link
		String priceLink = ContentParser.parseContent(urlContent,
				Regex.M_PRODUKTIV_PRICE_LINK);
		
		// apply OCR to extract the value from the image
		double price = OCRModule.readImage(priceLink);

		// return the respective Product
		return new Product(Integer.parseInt(id), product, price);
	}
}
