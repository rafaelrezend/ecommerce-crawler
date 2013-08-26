/**
 * 
 */
package rezend.musix;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * @author Rafael Rezende
 *
 */
public final class OCRModule {
	
	static Tesseract instance = Tesseract.getInstance();
	static final int SCALE = 3;
	
	/**
     * Test of doOCR method, of class Tesseract.
     */
    public static void readImage() {
        System.out.println("doOCR on a PNG image");
        
        String price = null;
        
        try {
        	File imageFile = new File("Preis_129900.png");
        	BufferedImage priceImg = ImageIO.read(imageFile);
        	
        	BufferedImage scaledPriceImg = new BufferedImage(priceImg.getWidth()*SCALE, priceImg.getHeight()*SCALE, BufferedImage.TYPE_INT_RGB);
        	Graphics2D grph = (Graphics2D) scaledPriceImg.getGraphics();
        	grph.scale(SCALE, SCALE);

        	// everything drawn with grph from now on will get scaled.
        	grph.drawImage(priceImg, 0, 0, null);
        	grph.dispose();
        	
        	ImageIO.write(scaledPriceImg, "png", new File("duke_double_size.png"));
        	
        	price = instance.doOCR(scaledPriceImg);
        
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(Double.parseDouble(price));
    }
}