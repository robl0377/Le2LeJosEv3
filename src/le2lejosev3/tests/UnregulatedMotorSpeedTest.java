/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.UnregulatedMotor;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;

/**
 * Test for the measured power of a motor.
 * NOTE: Be sure the motor can run freely.
 * 
 * @author Roland Blochberger
 */
public class UnregulatedMotorSpeedTest {

	private static Class<?> clazz = UnregulatedMotorSpeedTest.class;
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

		// array of power values to test
		int[] powers = new int[] { 10, 30, 50, 65, 85, 100, 108, 115 };
		// array of motor ports to test
		Port[] motorPorts = new Port[] { MotorPort.B, MotorPort.C };

		for (Port motorPort : motorPorts) {
			// run test sequence for unregulated motor
			measureSpeedTest(powers, new TUnregulatedMotor(motorPort));
		}

		log.fine("The End");
	}

	/**
	 * test the unregulated motor speed values.
	 * 
	 * @param powers the array of power values to use.
	 * @param motor  the motor under test.
	 */
	private static void measureSpeedTest(int[] powers, TUnregulatedMotor motor) {
		log.fine("");
		float pwr = 0;
		float apwr = 0;
		float amrs = 0;
		int cnt = 10;
		for (int power : powers) {
			String banner = "Unreg. Motor " + motor.getPortName();

			log.info("--- Current Power: " + power + " (" + banner + ") max. speed " + motor.getMaxRotationSpeed());
			Display.textGrid(banner, true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
			Display.textGrid("Power " + power + "  ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// Motor on with specified power
			log.fine("Motor on");
			motor.motorOn(power);
			// Wait 0.05 seconds
			Wait.time(0.05F);
			apwr = 0;
			amrs = 0;
			// calculate averages of 10 samples
			for (int i = 0; Button.ESCAPE.isUp() && (i < cnt); i++) {
				pwr = motor.measureCurrentPower();
				apwr += pwr;
				amrs += motor.measureRotationSpeed();
				Display.textGrid("Power " + pwr + "      ", false, 0, 4, Display.COLOR_BLACK, Display.FONT_NORMAL);
			}
			log.fine("Average measured power: " + (apwr / cnt));
			log.fine("Average measured rotation speed: " + (amrs / cnt));
			// motor stop with brake
			motor.motorOff(true);
			log.fine("Motor off");
		}

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();
	}
}

/**
 * Test Subclass of UnregulatedMotor to expose protected methods.
 */
class TUnregulatedMotor extends UnregulatedMotor {

	/**
	 * Constructor.
	 * 
	 * @param motorPort
	 */
	public TUnregulatedMotor(Port motorPort) {
		super(motorPort);
		super.setMediumMotor(false);
	}

	/**
	 * measure current rotation speed.
	 * 
	 * @return the rotation speed in degrees / second.
	 */
	@Override
	public float measureRotationSpeed() {
		return super.measureRotationSpeed();
	}

	/**
	 * get motor maximum rotation speed.
	 * 
	 * @return the rotation speed in degrees / second.
	 */
	@Override
	public float getMaxRotationSpeed() {
		return super.getMaxRotationSpeed();
	}
}
