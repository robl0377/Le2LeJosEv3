/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.MoveTank;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the move tank.
 * NOTE: Be sure the motors can run freely.
 * 
 * @author Roland Blochberger
 */
public class MoveTankTest {

	private static Class<?> clazz = MoveTankTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port leftMotorPort = MotorPort.B;
	static final Port rightMotorPort = MotorPort.C;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// instantiate the move class
		MoveTank mst = new MoveTank(leftMotorPort, rightMotorPort);

		// Run left & right motors forward for 2.5 seconds then brake
		log.fine("OnFor 2.5 seconds");
		mst.motorsOnForSeconds(75, 75, 2.5F, true);
		log.fine("done");

		// Wait 2 seconds
		Wait.time(2F);

		// Run left & right motors left for 3 rotations then brake
		log.fine("OnFor 3 rotations");
		mst.motorsOnForRotations(37, 75, 3, true);
		log.fine("done");

		// Wait 2 seconds
		Wait.time(2F);

		// Run left & right motors right on the spot for 180 degrees then don't brake
		log.fine("OnFor 180 degrees");
		mst.motorsOnForDegrees(75, -75, 180, false);
		log.fine("done");

		// Wait 2 seconds
		Wait.time(2F);

		// Run left & right motors left
		log.fine("On");
		mst.motorsOn(0, 75);
		log.fine("done");
		// Wait 2 seconds
		Wait.time(2F);
		// stop motors without brake
		log.fine("Off");
		mst.motorsOff(false);
		log.fine("done");

		log.fine("The End");
	}

}
