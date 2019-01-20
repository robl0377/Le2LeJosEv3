/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.MoveSteeringUnregulated;
import le2lejosev3.pblocks.UnregulatedMotor;
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// measure the timing
		// get the motor ports
		long etim, stim;
		stim = System.currentTimeMillis();
		Port leftMotorPort = MotorPort.B;
		etim = System.currentTimeMillis();
		log.fine("Get Motorport B: " + (etim - stim) + "ms"); // about 100ms

		stim = System.currentTimeMillis();
		Port rightMotorPort = MotorPort.C;
		etim = System.currentTimeMillis();
		log.fine("Get Motorport C: " + (etim - stim) + "ms"); // about 1ms

		// instantiate the UnregulatedMotor classes
		stim = System.currentTimeMillis();
		UnregulatedMotor leftMotor = new UnregulatedMotor(leftMotorPort);
		etim = System.currentTimeMillis();
		log.fine("Create UnregulatedMotor B: " + (etim - stim) + "ms"); // about 490ms

		stim = System.currentTimeMillis();
		UnregulatedMotor rightMotor = new UnregulatedMotor(rightMotorPort);
		etim = System.currentTimeMillis();
		log.fine("Create UnregulatedMotor C: " + (etim - stim) + "ms"); // about 7ms


		// instantiate the move class
		stim = System.currentTimeMillis();
		MoveSteeringUnregulated mst = new MoveSteeringUnregulated(leftMotor, rightMotor);
		etim = System.currentTimeMillis();
		log.fine("Create MoveSteering: " + (etim - stim) + "ms"); // about 130ms

		// run the tests
		MoveSteeringUtil.steeringTest(mst);

		log.fine("The End");
	}

}
