/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.MoveSteeringUnregulated;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the MoveSteering with unregulated motors.
 * NOTE: Be sure the motors can turn for about 270 degrees freely. Start both of
 * them at the middle position.
 * 
 * @author Roland Blochberger
 */
public class MoveSteeringUnregDegreesTest {

	private static Class<?> clazz = MoveSteeringUnregDegreesTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port leftMotorPort = MotorPort.A;
	static final Port rightMotorPort = MotorPort.D;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		// setup logging to file
		// Setup.log2File(clazz);
		log.fine("Starting ...");

		// instantiate the move class
		MoveSteeringUnregulated mst = new MoveSteeringUnregulated(leftMotorPort, rightMotorPort);
		// run the tests
		MoveSteeringUtil.steeringDegreesTest(mst);

		log.fine("The End");
	}

}
