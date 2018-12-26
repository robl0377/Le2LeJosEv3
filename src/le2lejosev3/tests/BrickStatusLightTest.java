/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.BrickStatusLight;
import le2lejosev3.pblocks.Display;
import lejos.hardware.Button;

/**
 * Test for the brick status light.
 * 
 * @author Roland Blochberger
 */
public class BrickStatusLightTest {

	private static Class<?> clazz = BrickStatusLightTest.class;
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

		Display.textGrid("Brick Status Light", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);

		Display.textGrid("Reset         ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// reset to green blinking
		log.fine("reset");
		BrickStatusLight.reset();
		// Wait until button press
		Button.waitForAnyPress();

		Display.textGrid("Green steady  ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// green steady
		log.fine("green");
		BrickStatusLight.on(BrickStatusLight.COLOR_GREEN, BrickStatusLight.CONSTANT);
		// Wait until button press
		Button.waitForAnyPress();

		Display.textGrid("Orange steady ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// orange steady
		log.fine("orange");
		BrickStatusLight.on(BrickStatusLight.COLOR_ORANGE, BrickStatusLight.CONSTANT);
		// Wait until button press
		Button.waitForAnyPress();

		Display.textGrid("Red steady    ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// red steady
		log.fine("red");
		BrickStatusLight.on(BrickStatusLight.COLOR_RED, BrickStatusLight.CONSTANT);
		// Wait until button press
		Button.waitForAnyPress();

		Display.textGrid("Green pulse   ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// green blinking
		log.fine("green blink");
		BrickStatusLight.on(BrickStatusLight.COLOR_GREEN, BrickStatusLight.PULSE);
		// Wait until button press
		Button.waitForAnyPress();

		Display.textGrid("Orange pulse  ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// orange blinking
		log.fine("orange blink");
		BrickStatusLight.on(BrickStatusLight.COLOR_ORANGE, BrickStatusLight.PULSE);
		// Wait until button press
		Button.waitForAnyPress();

		Display.textGrid("Red pulse      ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// red blinking
		log.fine("red blink");
		BrickStatusLight.on(BrickStatusLight.COLOR_RED, BrickStatusLight.PULSE);
		// Wait until button press
		Button.waitForAnyPress();

		log.fine("The End");
	}

}
