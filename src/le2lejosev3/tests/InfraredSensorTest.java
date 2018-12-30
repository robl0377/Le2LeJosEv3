/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.InfraredSensor;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

/**
 * Test for the infrared sensor.
 * 
 * @author Roland Blochberger
 */
public class InfraredSensorTest {

	private static Class<?> clazz = InfraredSensorTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port irSensorPort = SensorPort.S4;

	static int remoteChannel = 2;

	private static String[] buttonNames = new String[] { "NONE", "TOP_LEFT", "BOTTOM_LEFT", "TOP_RIGHT", "BOTTOM_RIGHT",
			"TOP_BOTH", "TOP_LEFT_BOT_RIGHT", "TOP_RIGHT_BOT_LEFT", "BOTTOM_BOTH", "BEACON", "LEFT_BOTH",
			"RIGHT_BOTH" };

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// instantiate the ir sensor
		InfraredSensor irs = new InfraredSensor(irSensorPort);

		// -----------------------------------------------
		// infrared proximity display
		Display.textGrid("Infrared Sensor", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Proximity:", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press ENTER", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);

		float prox = 0;
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			// get ir proximity value and display it on the LCD
			prox = irs.measureProximity();
			Display.textGrid("Prox: " + prox + "         ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.1F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		// -----------------------------------------------
		// infrared beacon display
		Display.textGrid("Infrared Sensor", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Beacon: Ch " + remoteChannel, false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press ENTER", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);

		int[] beac = new int[3];
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			// get beacon heading, proximity, detected status and display it on the LCD
			beac = irs.measureBeacon(remoteChannel);
			Display.textGrid("Head: " + beac[0] + "      ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
			Display.textGrid("Prox: " + beac[1] + "      ", false, 0, 4, Display.COLOR_BLACK, Display.FONT_NORMAL);
			Display.textGrid("Det.: " + beac[2] + "      ", false, 0, 5, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.1F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		// -----------------------------------------------
		// infrared remote display
		Display.textGrid("Infrared Sensor", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Remote: Ch " + remoteChannel, false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press ENTER", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);

		int rbutton = 0;
		String rbname = null;

		// run until button is pressed
		while (Button.ENTER.isUp()) {
			rbutton = irs.measureRemote(remoteChannel);
			if (rbutton >= 0 && rbutton < buttonNames.length) {
				rbname = buttonNames[rbutton];
			} else {
				rbname = "Invalid: " + rbutton;
			}
			Display.textGrid(rbname + "                  ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.01F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		// -----------------------------------------------
		// infrared proximity display
		Display.textGrid("Infrared Sensor", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Proximity:", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press ENTER", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);

		prox = 0;
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			// get ir proximity value and display it on the LCD
			prox = irs.measureProximity();
			Display.textGrid("Prox: " + prox + "         ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.1F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		log.fine("The End");
	}

}
