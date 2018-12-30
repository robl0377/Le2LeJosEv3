/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.UltrasonicSensor;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

/**
 * Test for the Ultrasonic Sensor.
 * 
 * @author Roland Blochberger
 */
public class UltrasonicSensorTest {

	private static Class<?> clazz = UltrasonicSensorTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port usSensorPort = SensorPort.S3;

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// instantiate the ultrasonic sensor
		UltrasonicSensor uss = new UltrasonicSensor(usSensorPort);

		// -----------------------------------------------
		// ultrasonic distance display
		Display.textGrid("Ultrasonic Sensor", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Distance:", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press ENTER", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);

		float dist = 0;
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			// get distance values and display them on the LCD
			dist = uss.measureDistanceCentimeters();
			Display.textGrid("Cm: " + dist + "          ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.1F);

			dist = uss.measureDistanceInches();
			Display.textGrid("In: " + dist + "          ", false, 0, 4, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.1F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		// -----------------------------------------------
		// ultrasonic presence display
		Display.textGrid("Ultrasonic Sensor", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Presence/Listen:", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press ENTER", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);

		boolean presence = false;
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			// get presence value and display it on the LCD
			presence = uss.measurePresence();
			Display.textGrid("Presence: " + presence + "   ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.1F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

	}
}
