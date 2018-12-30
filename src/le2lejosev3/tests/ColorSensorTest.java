/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.ColorSensor;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

/**
 * Test for the color sensor.
 * 
 * @author Roland Blochberger
 */
public class ColorSensorTest {

	private static Class<?> clazz = ColorSensorTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	// the sensor configuration
	static final Port colorSensorPort = SensorPort.S3;

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// instantiate the gyro sensor
		ColorSensor colorSensor = new ColorSensor(colorSensorPort);

		Display.textGrid("Color Sensor", true, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("Press ENTER", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// -----------------------------------------------
		// color number display
		Display.textGrid("Color Number    ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
		int value = 0;
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			// get color id value and display it on the LCD
			value = colorSensor.measureColor();
			Display.textGrid("#: " + value + " = " + colorText(value) + "      ", false, 0, 4, Display.COLOR_BLACK,
					Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.2F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		// -----------------------------------------------
		// reflected light intensity display
		Display.textGrid("Reflected Light ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
		int intensity = 0;
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			// get reflected light intensity value and display it on the LCD
			intensity = colorSensor.measureReflectedLightIntensity();
			Display.textGrid("Refl: " + intensity + "        ", false, 0, 4, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.2F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		// -----------------------------------------------
		// ambient light intensity display
		Display.textGrid("Ambient Light   ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			// get ambient light intensity value and display it on the LCD
			intensity = colorSensor.measureAmbientLightIntensity();
			Display.textGrid("Ambi: " + intensity + "        ", false, 0, 4, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.2F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		// -----------------------------------------------
		// color number display again
		Display.textGrid("Color Number    ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// run until button is pressed
		while (Button.ENTER.isUp()) {
			// get color id value and display it on the LCD
			value = colorSensor.measureColor();
			Display.textGrid("#: " + value + " = " + colorText(value) + "      ", false, 0, 4, Display.COLOR_BLACK,
					Display.FONT_NORMAL);

			// wait until next value
			Wait.time(0.2F);
		}
		// wait until button is released again
		while (Button.ENTER.isDown()) {
			Thread.yield();
		}

		log.fine("The End");
	}

	/**
	 * Convert the color number to the respective color name text.
	 * 
	 * @param colorId LEGO color number.
	 * @return the color name text.
	 */
	private static String colorText(int colorId) {
		switch (colorId) {
		case ColorSensor.COLOR_BLACK:
			return "Black";
		case ColorSensor.COLOR_BLUE:
			return "Blue";
		case ColorSensor.COLOR_GREEN:
			return "Green";
		case ColorSensor.COLOR_YELLOW:
			return "Yellow";
		case ColorSensor.COLOR_RED:
			return "Red";
		case ColorSensor.COLOR_WHITE:
			return "White";
		case ColorSensor.COLOR_BROWN:
			return "Brown";
		default:
			return "Unknown";
		}
	}
}
