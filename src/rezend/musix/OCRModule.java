/**
 * 
 */
package rezend.musix;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
	
	/**
	 * Scale factor of the price image for Tesseract OCR. It strongly affects
	 * the precision of the OCR. Recommended: 2 for AffineTransform; 3 for
	 * Graphics.
	 */
	static final int SCALE = 2;
	/**
	 * Flag to use graphics. readImage() will use AffineTransform otherwise.
	 * Default = false.
	 */
	static final boolean USE_GRAPHICS = false;
	
	
	static Tesseract instance = Tesseract.getInstance();
	
	
	/**
	 * Get value from the price image. This image must be scaled before running
	 * through Tesseract.
	 */
    public static String readImage() {
        
        String price = null;
        
        try {
        	File imageFile = new File("Preis_129900.png");
        	BufferedImage priceImg = ImageIO.read(imageFile);
        	BufferedImage scaledPriceImg = new BufferedImage(priceImg.getWidth()*SCALE, priceImg.getHeight()*SCALE, BufferedImage.TYPE_INT_RGB);
        	
        	// Scale solution using Graphics
        	if (USE_GRAPHICS){
	        	Graphics2D grph = (Graphics2D) scaledPriceImg.getGraphics();
	        	grph.scale(SCALE, SCALE);
	        	grph.drawImage(priceImg, 0, 0, null);
	        	grph.dispose();
        	}
        	else {
	        	// Scale solution using AffineTransform
	        	AffineTransform at = new AffineTransform();
	        	at.scale(2.0, 2.0);
	        	AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
	        	scaledPriceImg = scaleOp.filter(priceImg, scaledPriceImg);
        	}
        	
        	
        	ImageIO.write(scaledPriceImg, "png", new File("duke_double_size.png"));
        	
        	price = instance.doOCR(scaledPriceImg);
        	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return price;
    }
}