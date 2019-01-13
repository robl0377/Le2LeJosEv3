/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.MoveSteeringUnregulated;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the MoveSteering with unregulated motors.
 * NOTE: Be sure the motors can run freely.
 * 
 * @author Roland Blochberger
 */
public class MoveSteeringUnregTest {

	private static Class<?> clazz = MoveSteeringUnregTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port leftMotorPort = MotorPort.B;
	static final Port rightMotorPort = MotorPort.C;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		// Setup.log2File(clazz, Level.ALL);
		// setup logging to file
		Setup.log2File(clazz);
		log.fine("Starting ...");

		// instantiate the move class
		MoveSteeringUnregulated mst = new MoveSteeringUnregulated(leftMotorPort, rightMotorPort);
		// run the tests
		MoveSteeringUtil.steeringTest(mst);

		log.fine("The End");
	}

}
