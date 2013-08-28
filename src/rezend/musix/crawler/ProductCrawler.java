/**
 * 
 */
package rezend.musix.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;

import rezend.musix.core.LinksList;

/**
 * This web crawler is a producer of product links. It writes links to a global
 * singleton. Written based on the BasicCrawler from Yasser Ganjisaffar.
 * 
 * Further improvement could be to check if the webpage contains a product or
 * not before adding to the list of links. The current implementation adds every
 * link.
 * 
 * @author Rafael Rezende
 * 
 */
public class ProductCrawler extends WebCrawler {

	private final static Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|bmp|gif|jpe?g"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	/*
	 * (non-Javadoc) Limit the domain of search.
	 * 
	 * @see
	 * edu.uci.ics.crawler4j.crawler.WebCrawler#shouldVisit(edu.uci.ics.crawler4j
	 * .url.WebURL)
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches()
				&& href.startsWith("http://www.musik-produktiv.ch/");
	}

	/*
	 * (non-Javadoc) Action to take when a page is visited.
	 * 
	 * @see
	 * edu.uci.ics.crawler4j.crawler.WebCrawler#visit(edu.uci.ics.crawler4j.
	 * crawler.Page)
	 */
	@Override
	public void visit(Page page) {

		// get its own URL
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);

		// get the singleton instance of the ProductList
		LinksList pl = LinksList.getInstance();

		// store the url
		pl.put(url);
	}
}