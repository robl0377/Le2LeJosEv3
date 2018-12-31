/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.MediumMotor;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the medium motor.
 * NOTE: Be sure the motor can run freely.
 * 
 * @author Roland Blochberger
 */
public class MediumMotorTest {

	private static Class<?> clazz = MediumMotorTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port gripMotorPort = MotorPort.A;

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// instantiate the motor
		MediumMotor mot = new MediumMotor(gripMotorPort);
		log.fine("Created Medium Motor at Port " + mot.getPortName());

		// -----------------------------------------------
		Display.textGrid("Medium Motor", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("OnForSeconds ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// run motor backward for 0.9 seconds and brake afterward
		log.fine("Motor start");
		mot.motorOnForSeconds(-75, 0.9F, true);
		log.fine("Motor stop");

		// Wait 0.9 seconds
		Wait.time(0.9F);

		// run motor forward for 0.9 seconds and brake afterward
		log.fine("Motor start");
		mot.motorOnForSeconds(75, 0.9F, true);
		log.fine("Motor stop");

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		Display.textGrid("OnForDegrees ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// run motor backward for 1.5 rotations and brake afterward
		int degs = mot.measureDegrees();
		int dege = 0;
		log.fine("Motor started at " + degs + "degr.");
		mot.motorOnForRotationsDegrees(-75, 1, 180, true);
		dege = mot.measureDegrees();
		log.fine("Motor stopped at " + dege + "degr.: rotated: " + (dege - degs) + "degr.");

		// Wait 0.9 seconds
		Wait.time(0.9F);

		// run motor forward for 1.5 rotations and brake afterward
		degs = mot.measureDegrees();
		log.fine("Motor started at " + degs + "degr.");
		mot.motorOnForRotationsDegrees(75, 1, 180, true);
		dege = mot.measureDegrees();
		log.fine("Motor stopped at " + dege + "degr.: rotated: " + (dege - degs) + "degr.");

		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		Display.textGrid("OnForDegrees Seq.", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// run motor for +90 degrees and -90 degrees immediately one after another
		degs = mot.measureDegrees();
		log.fine("Motor started at " + degs + "degr.");
		mot.motorOnForDegrees(100, 90, true);
		mot.motorOnForDegrees(-100, 90, true);
		dege = mot.measureDegrees();
		log.fine("Motor stopped at " + dege + "degr.: rotated: " + (dege - degs) + "degr.");
		
		// Wait until button press
		Button.waitForAnyPress();

		log.fine("The End");
	}

}
