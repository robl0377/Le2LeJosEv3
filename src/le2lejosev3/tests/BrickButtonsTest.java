/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.BrickButtons;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;

/**
 * Test for the brick buttons.
 * 
 * @author Roland Blochberger
 */
public class BrickButtonsTest {

	private static Class<?> clazz = BrickButtonsTest.class;
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

		Display.textGrid("Brick Buttons", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press ESC to end", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// run until ESCAPE button is pressed
		int button = 0;
		String name = "";
		while (Button.ESCAPE.isUp()) {
			// read brick buttons
			button = BrickButtons.measure();
			switch (button) {
			case BrickButtons.BB_LEFT:
				name = "LEFT";
				break;
			case BrickButtons.BB_CENTER:
				name = "CENTER";
				break;
			case BrickButtons.BB_RIGHT:
				name = "RIGHT";
				break;
			case BrickButtons.BB_UP:
				name = "UP";
				break;
			case BrickButtons.BB_DOWN:
				name = "DOWN";
				break;
			default:
				name = "NONE";
				break;
			}
			Display.textGrid("Button: " + name + "    ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.05F);
		}

		log.fine("The End");
	}

}
