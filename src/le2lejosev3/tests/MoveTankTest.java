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
 * NOTE: Be sure the motors can run freely.
 * 
 * @author Roland Blochberger
 */
public class MoveTankTest {

	private static Class<?> clazz = MoveTankTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port leftMotorPort = MotorPort.A;
	static final Port rightMotorPort = MotorPort.D;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// instantiate the move class
		MoveTank mtt = new MoveTank(leftMotorPort, rightMotorPort);
		// run the tests
		MoveTankUtil.tankTest(mtt);

		log.fine("The End");
	}

}
