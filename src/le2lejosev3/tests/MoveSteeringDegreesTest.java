/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.MoveSteering;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the MoveSteering.
 * NOTE: Be sure the motors can turn for about 270 degrees freely. Start both of
 * them at the middle position.
 * 
 * @author Roland Blochberger
 */
public class MoveSteeringDegreesTest {

	private static Class<?> clazz = MoveSteeringDegreesTest.class;
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
		MoveSteering mst = new MoveSteering(leftMotorPort, rightMotorPort);
		// run the tests
		MoveSteeringUtil.steeringDegreesTest(mst);

		log.fine("The End");
	}

}
