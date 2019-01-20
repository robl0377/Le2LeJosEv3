/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.LargeMotor;
import le2lejosev3.pblocks.MoveSteering;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the MoveSteering.
 * NOTE: Be sure the motors can run freely.
 * 
 * @author Roland Blochberger
 */
public class MoveSteeringTest {

	private static Class<?> clazz = MoveSteeringTest.class;
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

		// instantiate the LargeMotor classes
		stim = System.currentTimeMillis();
		LargeMotor leftMotor = new LargeMotor(leftMotorPort);
		etim = System.currentTimeMillis();
		log.fine("Create LargeMotor B: " + (etim - stim) + "ms"); // about 640ms

		stim = System.currentTimeMillis();
		LargeMotor rightMotor = new LargeMotor(rightMotorPort);
		etim = System.currentTimeMillis();
		log.fine("Create LargeMotor C: " + (etim - stim) + "ms"); // about 15ms

		// instantiate the move class
		stim = System.currentTimeMillis();
		MoveSteering mst = new MoveSteering(leftMotor, rightMotor);
		etim = System.currentTimeMillis();
		log.fine("Create MoveSteering: " + (etim - stim) + "ms"); // about 110ms

		// run the tests
		MoveSteeringUtil.steeringTest(mst);

		log.fine("The End");
	}

}
