/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.LargeMotor;
import le2lejosev3.pblocks.UnregulatedMotor;
import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
//import lejos.utility.Delay;

/**
 * Test to use both Regulated and Unregulated motor classes on the same
 * motor port during the same program.
 * However only one one motor class uses the motor port at the same time.
 * NOTE: Be sure the motor can run freely.
 * 
 * @author Roland Blochberger
 */
public class MixMotorsTest {

	private static Class<?> clazz = MixMotorsTest.class;
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
		mixMotors(motorPorts);

		log.fine("The End");
	}

	/**
	 * Use both Regulated and Unregulated motor classes on the specified port.
	 * 
	 * @param motorPort
	 */
	private static void mixMotors(Port[] motorPorts) {
		for (int i = 0; i < 6; i++) {
			log.info("");
			log.info("=== Iteration A" + i);

			for (Port motorPort : motorPorts) {
				// test regulated motor
				regulatedMotor(motorPort);
			}
			for (Port motorPort : motorPorts) {
				// test unregulated motor
				unregulatedMotor(motorPort);
			}
		}

		for (int i = 0; i < 6; i++) {
			log.info("");
			log.info("=== Iteration B" + i);

			for (Port motorPort : motorPorts) {
				// test regulated motor
				regulatedMotor(motorPort);
				// test unregulated motor on the same port
				unregulatedMotor(motorPort);
			}
		}

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();
	}

	/**
	 * test regulated motor.
	 * 
	 * @param motorPort
	 */
	private static void regulatedMotor(Port motorPort) {
		// create regulated motor
		LargeMotor rmotor = new LargeMotor(motorPort);
		String banner = "Regul. Motor " + rmotor.getPortName();
		log.info("");
		log.info("--- " + banner);
		Display.textGrid(banner, true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// let motor run with power 40 for 1 second and brake at end
		rmotor.motorOnForSeconds(40, 1, true);
		// close the motor
		rmotor.close();
		log.info("Regul. Motor closed");
		// wait 50ms
		// Delay.msDelay(50);
	}

	/**
	 * test unregulated motor.
	 * 
	 * @param motorPort
	 */
	private static void unregulatedMotor(Port motorPort) {
		// create unregulated motor
		UnregulatedMotor motor = new UnregulatedMotor(motorPort);
		String banner = "Unreg. Motor " + motor.getPortName();
		log.info("");
		log.info("--- " + banner);
		Display.textGrid(banner, true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// let motor run with power 30 for 1 second and brake at end
		motor.motorOnForSeconds(30, 1, true);
		// close the motor
		motor.close();
		log.info("Unreg. Motor closed");
		// wait 50ms
		// Delay.msDelay(50);
	}
}
