/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the unregulated motor.
 * NOTE: Be sure the motor can turn for about 270 degrees freely. Start at the
 * middle position.
 * 
 * @author Roland Blochberger
 */
public class UnregulatedMotorDegreesTest {

	private static Class<?> clazz = UnregulatedMotorDegreesTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port[] motorPorts = new Port[] { MotorPort.B, MotorPort.C };

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		// Setup.log2File(clazz, Level.ALL);
		// setup logging to file
		Setup.log2File(clazz);
		log.info("Starting ...");

		for (Port motorPort : motorPorts) {
			log.info("");
			// instantiate the motor
			UnregulatedMotor mot = new UnregulatedMotor(motorPort);
			log.info("Created Unregulated IMotor at Port " + mot.getPortName());
			// test the motor
			MotorUtil.motorDegreesTest("Unreg.", mot, false);
			MotorUtil.motorDegreesTest("Unreg.", mot, true);
		}

		log.info("The End");
	}

}
