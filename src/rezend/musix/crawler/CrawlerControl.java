package rezend.musix.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * This class configures the web crawler and starts it. The features were
 * configured for the specific usage in the ecommerce parser.
 * 
 * Basic on the BasicCrawlController from Yasser Ganjisaffar
 * 
 * @author Rafael Rezende
 * 
 */
public class CrawlerControl {

	public static void startCrawler() {

		CrawlConfig config = new CrawlConfig();

		// folder where intermediate crawl data is stored.
		config.setCrawlStorageFolder("crawlerData");

		/*
		 * Be polite: Make sure that we don't send more than 1 request per
		 * second (1000 milliseconds between requests).
		 */
		config.setPolitenessDelay(1000);

		/*
		 * This config parameter can be used to set your crawl to be resumable
		 * (meaning that you can resume the crawl from a previously
		 * interrupted/crashed crawl). Note: if you enable resuming feature and
		 * want to start a fresh crawl, you need to delete the contents of
		 * rootFolder manually.
		 */
		config.setResumableCrawling(false);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);

		try {
			CrawlController controller = new CrawlController(config,
					pageFetcher, robotstxtServer);

			// starting page
			controller.addSeed("http://www.musik-produktiv.ch/");

			/*
			 * Start the crawl. This is a blocking operation, meaning that your
			 * code will reach the line after this only when crawling is
			 * finished. The parameter '1' corresponds to the number of threads.
			 * A single thread is sufficient for this case.
			 */
			controller.startNonBlocking(ProductCrawler.class, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}