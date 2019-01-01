/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import lejos.hardware.Button;

/**
 * Test for displaying an image resource.
 * 
 * @author Roland Blochberger
 */
public class DisplayImageResTest {

	private static Class<?> clazz = DisplayImageResTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// the list of image files available as resources
		String[] imageFiles = new String[] { "Backward", "EV3", "Right" };

		for (String imageFile : imageFiles) {
			
			// display image file on top left corner
			log.fine("Display image " + imageFile);
			Display.image(imageFile, true, 0, 0);
			log.fine("Display image done");

			// Wait until button press
			Button.waitForAnyPress();
		}

		log.fine("The End");
	}

}
