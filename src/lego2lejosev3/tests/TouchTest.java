/**
 * 
 */
package lego2lejosev3.tests;

import java.util.logging.Logger;

import lego2lejosev3.logging.Setup;
import lego2lejosev3.pblocks.Display;
import lego2lejosev3.pblocks.TouchSensor;
import lego2lejosev3.pblocks.Utl;
import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

/**
 * Test for the touch sensor.
 * 
 * @author Roland Blochberger
 */
public class TouchTest {

	private static Class<?> clazz = TouchTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	// the sensor configuration
	static final Port touchSensorPort = SensorPort.S3;

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file
		Setup.log2File(clazz);
		log.fine("Starting ...");

		// instantiate the touch sensor
		TouchSensor touchSensor = new TouchSensor(touchSensorPort);

		Display.textGrid("Touch Sensor", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Status", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press ENTER", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		
		// -----------------------------------------------
		// touch status
		boolean value = false;
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			// get touch value and display it on the LCD
			value = touchSensor.measureState();
			Display.textGrid("Touch: " + value + "     ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Utl.waitTime(0.1F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		// -----------------------------------------------
		// wait until pressed
		Display.textGrid("Pressed? ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		String status = null;
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			status = (touchSensor.compareState(TouchSensor.PRESSED) ? "PRESSED" : "     ");
			Display.textGrid("Status: " + status + "     ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Utl.waitTime(0.1F);
		}

		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		// -----------------------------------------------
		// wait until bumped
		Display.textGrid("Bumped? ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// run until button is bumped
		while (Button.ENTER.isUp()) {
			status = (touchSensor.compareState(TouchSensor.BUMPED) ? "BUMPED" : "     ");
			Display.textGrid("Status: " + status + "     ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Utl.waitTime(0.1F);
		}

		log.fine("The End");
	}

}
