/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the unregulated motor.
 * NOTE: Be sure the motor can run freely.
 * 
 * @author Roland Blochberger
 */
public class UnregulatedMotorTest {

	private static Class<?> clazz = UnregulatedMotorTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port[] motorPorts = new Port[] { MotorPort.B, MotorPort.C };

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		for (Port motorPort : motorPorts) {
			log.info("");
			// instantiate the motor
			UnregulatedMotor mot = new UnregulatedMotor(motorPort);
			log.fine("Created Unregulated IMotor at Port " + mot.getPortName());
			MotorUtil.motorTest("Unreg.", mot, false);
			MotorUtil.motorTest("Unreg.", mot, true);
		}
		log.fine("The End");
	}
}
