package rezend.musix;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
				.getURLContent("www.musik-produktiv.ch/boss-loop-station-rc-300.aspx");
		
		System.out.println(urlContent);

		// RETRIEVE PRODUCT NAME

		// this pattern is specific for the given website
		Pattern pattProduct = Pattern.compile(
				"<h2 style=\"text-align:left;\">(.*?)</h2>", Pattern.DOTALL);
		// create a matcher from the given pattern for the URL content
		Matcher matcher = pattProduct.matcher(urlContent);
		// find the first pattern match
		matcher.find();
		// extract the value in between (.*?)
		System.out.println("product: " + matcher.group(1));
		// there should be only one valid match in a given page. If more than
		// one match is found, then a warning is raised
		if (matcher.find())
			System.err.println(ErrorCodes.MULTIPLE_MATCH);

		// RETRIEVE PRODUCT CODE

		// this pattern is specific for the given website
		Pattern pattBestellnum = Pattern.compile(
				"<div .*?Bestellnummer: (.*?)</div>", Pattern.DOTALL);
		// create a matcher from the given pattern for the URL content
		matcher = pattBestellnum.matcher(urlContent);
		// find the first pattern match
		matcher.find();
		// extract the value in between (.*?)
		System.out.println("bestellnummber: " + matcher.group(1));
		// there should be only one valid match in a given page. If more than
		// one match is found, then a warning is raised
		if (matcher.find())
			System.err.println(ErrorCodes.MULTIPLE_MATCH);

		// RETRIEVE PRODUCT PRICE

		// this pattern is specific for the given website
		Pattern pattPriceImage = Pattern.compile(
				"<b>Unser Preis:</b></span> <img src=\"(.*?)\".*?/>",
				Pattern.DOTALL);
		// create a matcher from the given pattern for the URL content
		matcher = pattPriceImage.matcher(urlContent);
		// find the first pattern match
		matcher.find();
		// extract the value in between (.*?)
		String urlPriceImage = matcher.group(1);
		// there should be only one valid match in a given page. If more than
		// one match is found, then a warning is raised
		if (matcher.find())
			System.err.println(ErrorCodes.MULTIPLE_MATCH);

		// read the value from the provided price image url
		System.out.println("price: " + OCRModule.readImage(urlPriceImage));
	}
}
