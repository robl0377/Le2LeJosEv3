/**
 * 
 */
package lego2lejosev3.tests;

import java.util.logging.Logger;

import lego2lejosev3.logging.Setup;
import lego2lejosev3.pblocks.Display;
import lejos.hardware.Button;

/**
 * Test for displaying shapes.
 * 
 * @author Roland Blochberger
 */
public class DisplayShapes {

	private static Class<?> clazz = DisplayShapes.class;
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

		// display a black filled circle
		Display.shapesCircle(true, 88, 63, 63, true, Display.COLOR_BLACK);
		log.fine("Circle filled done");
		// display a white outlined circle
		Display.shapesCircle(false, 88, 63, 31, false, Display.COLOR_WHITE);
		log.fine("Circle outline done");
		// display white lines across
		Display.shapesLine(false, 50, 20, 130, 100, Display.COLOR_WHITE);
		log.fine("Line done");
		Display.shapesLine(false, 50, 100, 130, 20, Display.COLOR_WHITE);
		log.fine("Line done");

		Button.waitForAnyPress();
		
		// display a black filled rectangle
		log.fine("Rectangle fill start");
		Display.shapesRectangle(true, 15, 10, 177-30, 127-20, true, Display.COLOR_BLACK);
		log.fine("Rectangle fill done");
		// display a white outlined rectangle
		Display.shapesRectangle(false, 30, 20, 177-60, 127-40, false, Display.COLOR_WHITE);
		log.fine("Rectangle outline done");
		// display a white pixel in the center
		Display.shapesPoint(false, 88, 63, Display.COLOR_WHITE);
		log.fine("Pixel done");

		Button.waitForAnyPress();

		// reset the screen (clear it)
		Display.resetScreen();
		
		Button.waitForAnyPress();
		
		log.fine("The End");
	}
}
