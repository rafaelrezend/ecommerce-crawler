/**
 * 
 */
package rezend.music.constants;

/**
 * This class holds the regular expressions used to identify values and links
 * within a URL content.
 * 
 * @author Rafael Rezende
 * 
 */
public class Regex {
	
	public final static String M_PRODUKTIV_PRODUCT = "<h2 style=\"text-align:left;\">(.*?)</h2>";
	public final static String M_PRODUKTIV_ID = "<div .*?Bestellnummer: (.*?)</div>";
	public final static String M_PRODUKTIV_PRICE_LINK = "<b>Unser Preis:</b></span> <img src=\"(.*?)\".*?/>";

}
