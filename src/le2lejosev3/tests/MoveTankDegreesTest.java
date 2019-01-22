/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.MoveTank;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the MoveTank.
 * NOTE: Be sure the motors can turn for about 270 degrees freely. Start both of
 * them at the middle position.
 * 
 * @author Roland Blochberger
 */
public class MoveTankDegreesTest {

	private static Class<?> clazz = MoveTankDegreesTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port leftMotorPort = MotorPort.B;
	static final Port rightMotorPort = MotorPort.C;

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.info("Starting ...");

		// instantiate the move class
		MoveTank mtt = new MoveTank(leftMotorPort, rightMotorPort);
		// run the tests
		MoveTankUtil.tankDegreesTest(mtt);

		log.info("The End");
	}
}
