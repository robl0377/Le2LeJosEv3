/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import java.util.logging.Logger;

/**
 * Regulated Motor and Motor Rotation Blocks.
 * (common code for LargeMotor and MediumMotor - for internal use only)
 * 
 * @author Roland Blochberger
 */
class RegulatedMotor {

	private static final Logger log = Logger.getLogger(MediumMotor.class.getName());

	// the regulated motor instance
	protected lejos.hardware.motor.BaseRegulatedMotor motor = null;

	/**
	 * Constructor.
	 * 
	 * @param regMotor an instance of either EV3LargeRegulatedMotor or
	 *                 EV3MediumRegulatedMotor.
	 */
	public RegulatedMotor(lejos.hardware.motor.BaseRegulatedMotor regMotor) {
		// store motor object
		motor = regMotor;
		if (motor != null) {
			// handle resources correctly before exiting
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					// stop the motor and wait until done
					motor.stop();
					// close resources
					motor.close();
				}
			}));
		}
	}

	/**
	 * let motor run indefinitely and return immediately.
	 * 
	 * @param power set power percentage (0..100); + forward; - backward.
	 */
	public void motorOn(int power) {
		// setup motor and start it
		setPower(power);
		if (power > 0) {
			motor.forward();
		}
		if (power < 0) {
			motor.backward();
		}
	}

	/**
	 * let motor run the specified period in seconds.
	 * 
	 * @param power  set power percentage (0..100); + forward; - backward.
	 * @param period the waiting time in seconds (> 0).
	 * @param brake  set true to brake at the end of movement; set false to remove
	 *               power but do not brake.
	 */
	public void motorOnForSeconds(int power, float period, boolean brake) {
		// setup motor and start it
		setPower(power);
		log.fine("on for " + period + " sec");
		if (power > 0) {
			motor.forward();
		}
		if (power < 0) {
			motor.backward();
		}
		// wait time in seconds
		Wait.time(period);
		// switch motor off
		motorOff(brake);
	}

	/**
	 * Motor Rotation Block: reset the motor's rotation to zero.
	 */
	public void rotationReset() {
		motor.resetTachoCount();
	}

	/**
	 * Motor Rotation Block: measure the current degrees turned since the last
	 * reset.
	 * 
	 * @return the degrees.
	 */
	public int measureDegrees() {
		return motor.getTachoCount();
	}

	/**
	 * Motor Rotation Block: measure the number of rotations turned since the last
	 * reset.
	 * 
	 * @return the rotations.
	 */
	public float measureRotations() {
		return (motor.getTachoCount() / 360F);
	}

	/**
	 * Motor Rotation Block: measure the current power level of the motor.
	 * 
	 * @return the current power level.
	 */
	public int measureCurrentPower() {
		return getPower();
	}

	/**
	 * let motor run the specified number of degrees.
	 * 
	 * @param power   set power percentage (0..100); + forward; - backward.
	 * @param degrees number of degrees (> 0).
	 * @param brake   set true to brake at the end of movement; set false to remove
	 *                power but do not brake.
	 */
	public void motorOnForDegrees(int power, int degrees, boolean brake) {
		motorOnForRotationsDegrees(power, 0, degrees, brake);
	}

	/**
	 * let motor run the specified number of rotations.
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	public void motorOnForRotations(int power, int rotations, boolean brake) {
		motorOnForRotationsDegrees(power, rotations, 0, brake);
	}

	/**
	 * let motor run the specified number of rotations and degrees.
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations (> 0).
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	public void motorOnForRotationsDegrees(int power, int rotations, int degrees, boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motor power level
			setPower(power);
			// calculate the degrees to turn
			int degrs = (rotations * 360) + degrees;
			if (power < 0) {
				// use negative degrees to turn backward
				degrs = -degrs;
			}
			log.fine("rotate " + degrs + " deg.");
			// start motor and rotate the specified number of degrees and brake afterwards
			// XXX Alas, LeJOS does not expose the hold parameter of the underlaying
			// regulator 'newMove' method. It would correspond to our brake parameter.
			// Instead LeJOS always bakes the motor after rotations.
			motor.rotate(degrs);
			// at least float motor afterwards if specified
			if (!brake) {
				motor.flt();
			}
		}
	}

	/**
	 * stop motor.
	 * 
	 * @param brake set true to brake at the end of movement; set false to remove
	 *              power but do not brake.
	 */
	public void motorOff(boolean brake) {
		if (brake) {
			motor.stop();
		} else {
			motor.flt();
		}
	}

	/**
	 * get current power level.
	 * calculates a power level that corresponds to the current speed.
	 * 
	 * @return the power
	 */
	protected int getPower() {
		return Math.round(100F * motor.getSpeed() / motor.getMaxSpeed());
	}

	/**
	 * set the motor power level.
	 * calculates a speed that corresponds to the power level.
	 * 
	 * @param power the power to set
	 */
	protected void setPower(int power) {
		// calculate the speed for the regulated motor
		// (setSpeed takes the absolute value)
		motor.setSpeed(power * motor.getMaxSpeed() / 100F);
	}

}
