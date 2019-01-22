/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.MediumMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the medium motor.
 * NOTE: Be sure the motor can run freely.
 * 
 * @author Roland Blochberger
 */
public class MediumMotorTest {

	private static Class<?> clazz = MediumMotorTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	static final Port motorPort = MotorPort.A;

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// instantiate the motor
		MediumMotor mot = new MediumMotor(motorPort);
		log.fine("Created Medium IMotor at Port " + mot.getPortName());

		MotorUtil.motorTest("Medium", mot, false);
		MotorUtil.motorTest("Medium", mot, true);

		log.fine("The End");
	}
}
