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
 * Test for playing tones and sound files.
 * 
 * @author Roland Blochberger
 */
public class SoundRepeatTest {

	private static Class<?> clazz = SoundRepeatTest.class;
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

		// -----------------------------------------------
		// play tone in background until keypress
		int freq = 440;
		// display frequency and hint
		Display.textGrid("Playing:" + freq + "Hz", true, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press Button", false, 0, 5, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// play tone
		log.fine("Playing " + freq + "Hz");
		Sound.playTone(freq, 0.12F, 80, Sound.REPEAT);
		log.fine("Playing done");

		// Wait until button press
		Button.waitForAnyPress();

		// Stop tone
		Sound.stop();

		// -----------------------------------------------
		// play sound file in background until keypress
		String soundFile = "airhiss2";
		// display filename and hint
		Display.textGrid("Playing:", true, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid(soundFile, false, 2, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press Button", false, 0, 5, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// play sound file once and wait until done
		log.fine("Playing " + soundFile);
		Sound.playFile(soundFile, 100, Sound.REPEAT);
		log.fine("Playing done");

		// Wait until button press
		Button.waitForAnyPress();

		// Stop tone
		Sound.stop();

		// -----------------------------------------------
		// play sound resource in background until keypress
		String soundRes = "Yellow";
		// display name and hint
		Display.textGrid("Playing:", true, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid(soundRes, false, 2, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press Button", false, 0, 5, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// play sound file once and wait until done
		log.fine("Playing " + soundRes);
		Sound.playFile(soundRes, 100, Sound.REPEAT);
		log.fine("Playing done");

		// Wait until button press
		Button.waitForAnyPress();

		// Stop tone
		Sound.stop();

		log.fine("The End");
	}

}
