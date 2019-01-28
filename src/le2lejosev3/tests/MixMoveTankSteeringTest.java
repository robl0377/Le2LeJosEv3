/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.MoveSteering;
import le2lejosev3.pblocks.MoveTank;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test to use both MoveTank and MoveSteering classes with the same motors
 * during the same program.
 * 
 * @author Roland Blochberger
 */
public class MixMoveTankSteeringTest {

	private static Class<?> clazz = MixMoveTankSteeringTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	// the motor ports to test
	private static final Port leftMotorPort = MotorPort.B;
	private static final Port rightMotorPort = MotorPort.C;

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.info("Starting ...");

		// create a MoveTank instance with LargeMotors on the specified ports
		MoveTank tank = new MoveTank(leftMotorPort, rightMotorPort);

		for (int i = 0; i < 3; i++) {
			// do mix move tests
			moveTests(tank);
		}

		log.info("The End");
	}

	/**
	 * mix move tests.
	 * 
	 * @param tank
	 */
	private static void moveTests(MoveTank tank) {
		log.info("");
		// use the MoveTank object
		// half a rotation straight forward
		tank.motorsOnForRotations(40, 40, 0.5F, true);
		// half a rotation straight backward
		tank.motorsOnForRotations(-40, -40, 0.5F, true);

		// create temporary MoveSteering with the same motors of the MoveTank
		MoveSteering steer = tank.createMoveSteering();
		// half a rotation straight forward
		steer.motorsOnForRotations(0, 60, 0.5F, true);
		// half a rotation straight backward
		steer.motorsOnForRotations(0, -60, 0.5F, true);
		// the MoveSteering instance will be removed automatically when returning from
		// this method

		// use the MoveTank object again
		// half a rotation straight forward
		tank.motorsOnForRotations(40, 40, 0.5F, true);
		// half a rotation straight backward
		tank.motorsOnForRotations(-40, -40, 0.5F, true);
	}
}
