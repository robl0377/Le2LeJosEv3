/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.LargeMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the large motor.
 * NOTE: Be sure the motor(s) can turn for about 270 degrees freely. Start at the
 * middle position.
 * 
 * @author Roland Blochberger
 */
public class LargeMotorDegreesTest {

	private static Class<?> clazz = LargeMotorDegreesTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port[] motorPorts = new Port[] { MotorPort.A, MotorPort.D };

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.info("Starting ...");

		for (Port motorPort : motorPorts) {
			log.info("");
			// instantiate the motor
			LargeMotor mot = new LargeMotor(motorPort);
			log.info("Created Large IMotor at Port " + mot.getPortName());
			// test the motor
			MotorUtil.motorDegreesTest("Large", mot);
		}

		log.info("The End");
	}
}
