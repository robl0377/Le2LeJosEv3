/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.TouchSensor;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

/**
 * Test for the touch sensor.
 * 
 * @author Roland Blochberger
 */
public class TouchSensorTest {

	private static Class<?> clazz = TouchSensorTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	// the sensor configuration
	static final Port touchSensorPort = SensorPort.S1;

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
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
			Wait.time(0.01F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		// -----------------------------------------------
		// wait until pressed
		Display.textGrid("Pressed? ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		boolean status = false;
		String statusText = null;
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			status = touchSensor.compareState(TouchSensor.PRESSED);
			statusText = (status ? "PRESSED" : "     ");
			Display.textGrid("Status: " + status + "     ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.01F);
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
			status = touchSensor.compareState(TouchSensor.BUMPED);
			statusText = (status ? "BUMPED" : "     ");
			Display.textGrid("Status: " + statusText + "     ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value, but show bumped state for 1 second
			Wait.time(status ? 1F : 0.1F);
		}

		log.fine("The End");
	}

}
