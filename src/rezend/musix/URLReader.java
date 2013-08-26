/**
 * 
 */
package rezend.musix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Rafael Rezende
 * 
 */
public final class URLReader {
	
	public static String getURLContent(URL url){

		// Holds the URL content
		StringBuilder urlContent = new StringBuilder();
		
		try {
			// open connection to the provided URL
			URLConnection urlConn = url.openConnection();
			// copy content to a buffer
			BufferedReader urlContentBuffer = new BufferedReader(new InputStreamReader(
				urlConn.getInputStream()));
			
			// using StringBuilder instead of String concatenation within a loop for
			// performance reasons, since Strings are immutable.
			String urlLine = "";
			while ((urlLine = urlContentBuffer.readLine()) != null) {
				urlContent.append(urlLine);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urlContent.toString();
	}
	
	
	
	public static String getURLContent(String urlStr){
		
		String urlContent = null;
		try {
			URL url = new URL(urlStr);
			urlContent = getURLContent(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return urlContent;
	}
	
	
	
	public static void printURLContent(String urlStr){
		
		try {
			URL url = new URL(urlStr);
			URLConnection urlConn = url.openConnection();
			BufferedReader urlContentBuffer = new BufferedReader(new InputStreamReader(
					urlConn.getInputStream()));
			String inputLine;

			while ((inputLine = urlContentBuffer.readLine()) != null){
				System.out.println(inputLine);
			}
			urlContentBuffer.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
