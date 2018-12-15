/**
 * 
 */
package lego2lejosev3.tests;

import java.util.logging.Logger;

import lego2lejosev3.logging.Setup;
import lego2lejosev3.pblocks.Display;
import lejos.hardware.Button;

/**
 * Test for displaying a text.
 * 
 * @author Roland Blochberger
 */
public class DisplayText {

	private static Class<?> clazz = DisplayText.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file
		Setup.log2File(clazz);
		log.fine("Starting ...");

		// display text
		Display.textPixels("EV3", true, 70, 50, Display.COLOR_BLACK, Display.FONT_LARGE);

		Button.waitForAnyPress();

		// display an integer number
		Display.textPixels(53, true, 80, 50, Display.COLOR_WHITE, Display.FONT_LARGE);

		Button.waitForAnyPress();

		// display a float number
		Display.textPixels(7.2F, true, 80, 50, Display.COLOR_BLACK, Display.FONT_NORMAL);

		Button.waitForAnyPress();

		// display small text
		Display.textPixels("LEGO 2 LeJOS EV3", true, 60, 50, Display.COLOR_BLACK, Display.FONT_SMALL);

		Button.waitForAnyPress();

		// display all ASCII characters and the German umlauts
		Display.textPixels("`1234567890-=qwer", true, 0, 0, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textPixels("tyuiop[]\\asdfghjk", false, 0, 20, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textPixels("l;'/zxcvbnmäöüß,.", false, 0, 40, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textPixels("~@#$%^&*()_+QWER", false, 0, 60, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textPixels("TYUIOP{}|ASDFGHJK", false, 0, 80, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textPixels("L:\"?ZXCVBNMÄÖÜ<>", false, 0, 100, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// XXX characters displayed as spaces: äöüßÄÖÜ

		Button.waitForAnyPress();

		// display text
		Display.textGrid("EV3", true, 7, 3, Display.COLOR_BLACK, Display.FONT_LARGE);

		Button.waitForAnyPress();

		// display an integer number
		Display.textGrid(53, true, 8, 5, Display.COLOR_WHITE, Display.FONT_LARGE);

		Button.waitForAnyPress();

		// display a float number at the bottom right corner
		Display.textGrid(7.2F, true, 15, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);

		Button.waitForAnyPress();

		// display all ASCII characters in a grid
		Display.textGrid("`1234567890-=qwer", true, 0, 0, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("tyuiop[]\\asdfghjk", false, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("l;'/zxcvbnm,.", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("~@#$%^&*()_+QWER", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("TYUIOP{}|ASDFGHJK", false, 0, 4, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("L:\"?ZXCVBNM<>", false, 0, 5, Display.COLOR_BLACK, Display.FONT_NORMAL);

		Button.waitForAnyPress();
		log.fine("The End");
	}

}
