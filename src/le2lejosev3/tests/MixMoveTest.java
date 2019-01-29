/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.IMoveSteering;
import le2lejosev3.pblocks.IMoveTank;
import le2lejosev3.pblocks.MoveSteering;
import le2lejosev3.pblocks.MoveSteeringUnregulated;
import le2lejosev3.pblocks.MoveTank;
import le2lejosev3.pblocks.MoveTankUnregulated;
import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
//import lejos.utility.Delay;

/**
 * Test to use Regulated and Unregulated Move classes on the same motor ports
 * during the same program.
 * However only one one motor class uses a motor port at the same time.
 * NOTE: Be sure the motors can run freely.
 * 
 * @author Roland Blochberger
 */
public class MixMoveTest {

	private static Class<?> clazz = MixMoveTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// array of motor ports to test
		Port[] motorPorts = new Port[] { MotorPort.B, MotorPort.C };

		// run test sequence for regulated motor
		mixMoves(motorPorts);

		log.fine("The End");
	}

	/**
	 * Use both Regulated and Unregulated MoveTank and MoveSteering classes on the
	 * specified ports.
	 * 
	 * @param motorPort
	 */
	private static void mixMoves(Port[] motorPorts) {
		for (int i = 0; i < 6; i++) {
			log.info("");
			// test regulated MoveTank
			regulatedMoveTank(motorPorts);
			// test unregulated MoveTank
			unregulatedMoveTank(motorPorts);
			// test regulated MoveTank
			regulatedMoveSteering(motorPorts);
			// test unregulated MoveTank
			unregulatedMoveSteering(motorPorts);
		}

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();
	}

	/**
	 * test regulated MoveTank.
	 * 
	 * @param motorPorts
	 */
	private static void regulatedMoveTank(Port[] motorPorts) {
		testMoveTank(new MoveTank(motorPorts[0], motorPorts[1]), "Reg.MoveTank", 40);
	}

	/**
	 * test unregulated MoveTank.
	 * 
	 * @param motorPorts
	 */
	private static void unregulatedMoveTank(Port[] motorPorts) {
		testMoveTank(new MoveTankUnregulated(motorPorts[0], motorPorts[1]), "Unr.MoveTank", 30);
	}

	/**
	 * test regulated MoveSteering.
	 * 
	 * @param motorPorts
	 */
	private static void regulatedMoveSteering(Port[] motorPorts) {
		testMoveSteering(new MoveSteering(motorPorts[0], motorPorts[1]), "Reg.MoveStee", 50);
	}

	/**
	 * test unregulated MoveSteering.
	 * 
	 * @param motorPorts
	 */
	private static void unregulatedMoveSteering(Port[] motorPorts) {
		testMoveSteering(new MoveSteeringUnregulated(motorPorts[0], motorPorts[1]), "Unr.MoveStee", 60);
	}

	/**
	 * test MoveTank.
	 */
	private static void testMoveTank(IMoveTank move, String banner, int power) {
		log.info("");
		log.info("--- " + banner);
		Display.textGrid(banner, true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// let both motors run with power 40 for 1 second and brake at end
		move.motorsOnForSeconds(40, 40, 1, true);
		// close the move
		move.close();
		log.info(banner + " closed");
		// wait 50ms
		// Delay.msDelay(50);
	}

	/**
	 * test MoveSteering.
	 */
	private static void testMoveSteering(IMoveSteering move, String banner, int power) {
		log.info("");
		log.info("--- " + banner);
		Display.textGrid(banner, true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// let both motors run with power 40 for 1 second and brake at end
		move.motorsOnForSeconds(0, 40, 1, true);
		// close the move
		move.close();
		log.info(banner + " closed");
		// wait 50ms
		// Delay.msDelay(50);
	}

}
