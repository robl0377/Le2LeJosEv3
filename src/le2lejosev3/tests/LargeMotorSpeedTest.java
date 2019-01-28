/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.LargeMotor;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

/**
 * Test for the measured power of a motor.
 * NOTE: Be sure the motor can run freely.
 * 
 * @author Roland Blochberger
 */
public class LargeMotorSpeedTest {

	private static Class<?> clazz = LargeMotorSpeedTest.class;
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
			// run test sequence for regulated motor
			measureSpeedTest(powers, new TLargeMotor(motorPort));
		}

		log.fine("The End");
	}

	/**
	 * test the regulated motor speed values.
	 * 
	 * @param powers the array of power values to use.
	 * @param motor  the motor under test.
	 */
	private static void measureSpeedTest(int[] powers, TLargeMotor motor) {
		log.fine("");
		float pwr = 0;
		float apwr = 0;
		float amrs = 0;
		float arrs = 0;
		float asrs = 0;
		int cnt = 10;
		for (int power : powers) {
			String banner = "Regul. Motor " + motor.getPortName();

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
				arrs += motor.getRegulatorRotationSpeed();
				asrs += motor.getMotorRotationSpeed();
				Display.textGrid("Power " + pwr + "      ", false, 0, 4, Display.COLOR_BLACK, Display.FONT_NORMAL);
			}
			log.fine("Average measured power: " + (apwr / cnt));
			log.fine("Average measured rotation speed: " + (amrs / cnt));
			log.fine("Average regulator rotation speed: " + (arrs / cnt));
			log.fine("Average setup rotation speed: " + (asrs / cnt));
			motor.measureRotationSpeedTim();
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
 * Test Subclass of LargeMotor to expose protected methods.
 */
class TLargeMotor extends LargeMotor {

	private static final Logger log = Logger.getLogger(TLargeMotor.class.getName());

	/**
	 * Constructor.
	 * 
	 * @param motorPort
	 */
	public TLargeMotor(Port motorPort) {
		super(motorPort);
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
	 * measure current rotation speed with timing.
	 * 
	 * @return the rotation speed in degrees / second.
	 */
	public float measureRotationSpeedTim() {
		int sdeg, dif;
		long etim, stim = System.currentTimeMillis();
		// measure the degrees per 100ms
		sdeg = motor.getTachoCount();
		// wait about 100ms
		Delay.msDelay(99L);
		etim = System.currentTimeMillis();
		dif = motor.getTachoCount();
		log.finest("measure speed period: " + (etim - stim));
		dif = Math.abs(dif - sdeg);
		return 10F * dif;
	}

	/**
	 * get current rotation speed from regulator.
	 * 
	 * @return the rotation speed in degrees / second.
	 */
	@Override
	public float getRegulatorRotationSpeed() {
		return motor.getRotationSpeed();
	}

	/**
	 * get current rotation speed from motor.
	 * 
	 * @return the rotation speed in degrees / second.
	 */
	@Override
	public float getMotorRotationSpeed() {
		return motor.getSpeed();
	}

	/**
	 * get motor maximum rotation speed.
	 * 
	 * @return the rotation speed in degrees / second.
	 */
	@Override
	public float getMaxRotationSpeed() {
		return motor.getMaxSpeed();
	}
}
