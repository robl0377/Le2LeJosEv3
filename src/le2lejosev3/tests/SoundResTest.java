/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.Sound;
import lejos.hardware.Button;

/**
 * Test for playing a sound resource from the classpath.
 * 
 * @author Roland Blochberger
 */
public class SoundResTest {

	private static Class<?> clazz = SoundResTest.class;
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

		// the list of sound files available as resources
		String[] soundFiles = new String[] { "Blue", "Green", "Ready", "Red", "Yellow" };

		for (String soundFile : soundFiles) {
			// display filename and hint
			Display.textGrid("Playing:", true, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
			Display.textGrid(soundFile, false, 2, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
			Display.textGrid("Press Button", false, 0, 5, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// play sound file once and wait until done
			log.fine("Playing " + soundFile);
			Sound.playFile(soundFile, 100, Sound.WAIT);
			log.fine("Playing done");

			// Wait until button press
			Button.waitForAnyPress();
		}

		log.fine("The End");
	}

}
