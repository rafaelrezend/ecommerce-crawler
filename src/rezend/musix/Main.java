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
		
		String urlContent = URLReader.getURLContent("http://www.musik-produktiv.ch/boss-loop-station-rc-300.aspx");
		
//		System.out.println(urlContent);
//		
//		String pattern = "(?i)(<title.*?>)(.+?)(</title>)";
//		String updated = urlContent.replaceAll(pattern, "$2");
//		System.out.println("title: " + updated);
		
//		Pattern pattTitle = Pattern.compile("<head>.*?<title>(.*?)</title>.*?</head>", Pattern.DOTALL);
//		Matcher matchTitle = pattTitle.matcher(urlContent);
//		while (matchTitle.find()) {
//		    System.out.println("head title: " + matchTitle.group(1));
//		}
		
		Pattern pattProduct = Pattern.compile("<h2 style=\"text-align:left;\">(.*?)</h2>", Pattern.DOTALL);
		Matcher matchProduct = pattProduct.matcher(urlContent);
		while (matchProduct.find()) {
		    System.out.println("product: " + matchProduct.group(1));
		}
		
		Pattern pattBestellnum = Pattern.compile("<div .*?Bestellnummer: (.*?)</div>", Pattern.DOTALL);
		Matcher matchBestellnum = pattBestellnum.matcher(urlContent);
		while (matchBestellnum.find()) {
		    System.out.println("bestellnummber: " + matchBestellnum.group(1));
		}
		
		Pattern pattPriceImage = Pattern.compile("<b>Unser Preis:</b></span> <img src=\"(.*?)\".*?/>", Pattern.DOTALL);
		Matcher matchPriceImage = pattPriceImage.matcher(urlContent);
		while (matchPriceImage.find()){
			System.out.println("price image link: " + matchPriceImage.group(1));
			String link = matchPriceImage.group(1).replaceAll("&amp;", "&");
			System.out.println("price image link: " + link);
			
			System.out.println("price: " + OCRModule.readImage(link));
		}
		
//		<b>Unser Preis:</b></span> <img src="http://www.musik-produktiv.ch/shop/Preis.aspx?artikel_id=122132&amp;format=redit14" alt="Preis" style="margin-bottom:-5px;" />
		
	}

}
