/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Change;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.InfraredSensor;
import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

/**
 * Test for the wait infrared sensor.
 * 
 * @author Roland Blochberger
 */
public class WaitInfraredSensorTest {

	private static Class<?> clazz = WaitInfraredSensorTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port irSensorPort = SensorPort.S4;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// instantiate the ir sensor
		InfraredSensor irs = new InfraredSensor(irSensorPort);

		// -----------------------------------------------
		// infrared wait for proximity change
		Display.textGrid("Infrared Sensor", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Proximity Change:", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press ENTER", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// run until button is pressed
		float value = 0;
		while (Button.ENTER.isUp()) {
			value = irs.waitChangeProximity(Change.CHANGE_ANY, 3F);
			Display.textGrid("Prox: " + value + "         ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		log.fine("The End");
	}
}
