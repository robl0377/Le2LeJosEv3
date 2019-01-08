/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.MoveTankUnregulated;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the MoveTank with unregulated motors.
 * NOTE: Be sure the motors can turn for about 270 degrees freely. Start both of
 * them at the middle position.
 * 
 * @author Roland Blochberger
 */
public class MoveTankUnregDegreesTest {

	private static Class<?> clazz = MoveTankUnregDegreesTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port leftMotorPort = MotorPort.A;
	static final Port rightMotorPort = MotorPort.D;

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		// Setup.log2File(clazz, Level.ALL);
		// setup logging to file
		Setup.log2File(clazz);
		log.info("Starting ...");

		// instantiate the move class
		MoveTankUnregulated mtt = new MoveTankUnregulated(leftMotorPort, rightMotorPort);
		// run the tests
		MoveTankUtil.tankDegreesTest(mtt);

		log.info("The End");
	}
}
