/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.MoveTankUnregulated;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the MoveTank with unregulated motors.
 * NOTE: Be sure the motors can run freely.
 * 
 * @author Roland Blochberger
 */
public class MoveTankUnregTest {

	private static Class<?> clazz = MoveTankUnregTest.class;
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
		MoveTankUnregulated mtt = new MoveTankUnregulated(leftMotorPort, rightMotorPort);
		// run the tests
		MoveTankUtil.tankTest(mtt);

		log.fine("The End");
	}

}
