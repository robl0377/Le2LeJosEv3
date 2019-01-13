/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import lejos.hardware.Button;
import lejos.utility.Delay;

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

		// -------------------------------------
		// Display images and wait for keypress
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

		// -------------------------------------
		// wait until no key is pressed
		while (Button.getButtons() != 0) {
			Delay.msDelay(10L);
		}
		// Display image over and over until keypress
		while (Button.getButtons() == 0) {
			// display image file on top left corner
			log.fine("Display image EV3");
			Display.image("EV3", true, 0, 0);
			log.fine("Display image done");
		}

		log.fine("The End");
	}

}
