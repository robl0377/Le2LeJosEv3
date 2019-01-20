/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import lejos.hardware.port.Port;

/**
 * Regulated Motor and Motor Rotation Blocks.
 * (common code for LargeMotor and MediumMotor - for internal use only)
 * 
 * @author Roland Blochberger
 */
class RegulatedMotor implements IMotor {

	private static final Logger log = Logger.getLogger(RegulatedMotor.class.getName());

	// the motor port
	protected Port motorPort;
	// the regulated motor instance
	protected lejos.hardware.motor.BaseRegulatedMotor motor = null;

	/**
	 * Constructor.
	 * handles the motor resources correctly before exiting
	 * 
	 * @param motorPort the motor port.
	 * @param regMotor  an instance of either EV3LargeRegulatedMotor or
	 *                  EV3MediumRegulatedMotor.
	 */
	protected RegulatedMotor(Port motorPort, lejos.hardware.motor.BaseRegulatedMotor regMotor) {
		// store motor port and motor instance
		this.motorPort = motorPort;
		motor = regMotor;
		if (motor != null) {
			// limit the acceleration (maximum is 6000)
			motor.setAcceleration(2000);
			// handle resources correctly before exiting
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					close();
				}
			}));
		}
	}

	/**
	 * stop the motor and wait until done, then close resources and remove the
	 * reference to the motor instance.
	 */
	protected void close() {
		if (motor != null) {
			// stop the motor and wait until done
			motor.stop();
			// close resources
			motor.close();
			motor = null;
		}
	}

	/**
	 * @return the motor
	 */
	lejos.hardware.motor.BaseRegulatedMotor getMotor() {
		return motor;
	}

	/**
	 * @return the motorPort; or null if not available.
	 */
	@Override
	public Port getPort() {
		return motorPort;
	}

	/**
	 * @return the name of the motor port; or null if not available.
	 */
	@Override
	public String getPortName() {
		return (this.motorPort != null) ? this.motorPort.getName() : null;
	}

	/**
	 * let motor run indefinitely and return immediately.
	 * 
	 * @param power set power percentage (0..100); + forward; - backward.
	 */
	@Override
	public void motorOn(int power) {
		// setup motor and start it
		setPower(power);
		start(power);
	}

	/**
	 * let motor run indefinitely and return immediately.
	 * 
	 * @param power set power percentage (0..100); + forward; - backward.
	 */
	@Override
	public void motorOn(float power) {
		// setup motor and start it
		setPower(power);
		start(power);
	}

	/**
	 * let motor run indefinitely unregulated and return immediately.
	 * 
	 * @param power set power percentage (0..100); + forward; - backward.
	 */
	public void UnregulatedOn(int power) {
		// TODO implement unregulated motor on somehow
		setPower(power);
		start(power);
	}

	/**
	 * let motor run indefinitely unregulated and return immediately.
	 * 
	 * @param power set power percentage (0..100); + forward; - backward.
	 */
	public void UnregulatedOn(float power) {
		// TODO implement unregulated motor on somehow
		setPower(power);
		start(power);
	}

	/**
	 * let motor run the specified period in seconds.
	 * 
	 * @param power  set power percentage (0..100); + forward; - backward.
	 * @param period the waiting time in seconds (> 0).
	 * @param brake  set true to brake at the end of movement; set false to remove
	 *               power but do not brake.
	 */
	@Override
	public void motorOnForSeconds(int power, float period, boolean brake) {
		if (log.isLoggable(Level.FINEST)) {
			log.log(Level.FINEST, "on for {0} sec", period);
		}
		// setup motor and start it
		setPower(power);
		start(power);
		// wait time in seconds
		Wait.time(period);
		// switch motor off
		motorOff(brake);
	}

	/**
	 * let motor run the specified period in seconds.
	 * 
	 * @param power  set power percentage (0..100); + forward; - backward.
	 * @param period the waiting time in seconds (> 0).
	 * @param brake  set true to brake at the end of movement; set false to remove
	 *               power but do not brake.
	 */
	@Override
	public void motorOnForSeconds(float power, float period, boolean brake) {
		if (log.isLoggable(Level.FINEST)) {
			log.log(Level.FINEST, "on for {0} sec", period);
		}
		// setup motor and start it
		setPower(power);
		start(power);
		// wait time in seconds
		Wait.time(period);
		// switch motor off
		motorOff(brake);
	}

	/**
	 * let motor run the specified number of degrees.
	 * 
	 * @param power   set power percentage (0..100); + forward; - backward.
	 * @param degrees number of degrees (> 0).
	 * @param brake   set true to brake at the end of movement; set false to remove
	 *                power but do not brake.
	 */
	@Override
	public void motorOnForDegrees(int power, int degrees, boolean brake) {
		motorOnForRotationsDegrees(power, 0, degrees, brake);
	}

	/**
	 * let motor run the specified number of degrees.
	 * 
	 * @param power   set power percentage (0..100); + forward; - backward.
	 * @param degrees number of degrees (> 0).
	 * @param brake   set true to brake at the end of movement; set false to remove
	 *                power but do not brake.
	 */
	@Override
	public void motorOnForDegrees(float power, int degrees, boolean brake) {
		motorOnForRotationsDegrees(power, 0, degrees, brake);
	}

	/**
	 * let motor run the specified number of rotations.
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations; it seems that the LEGO Programming
	 *                  block also accepts a negative number of rotations for
	 *                  backward movement.
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorOnForRotations(int power, int rotations, boolean brake) {
		motorOnForRotationsDegrees(power, rotations, 0, brake);
	}

	/**
	 * let motor run the specified number of rotations.
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations; it seems that the LEGO Programming
	 *                  block also accepts a negative number of rotations for
	 *                  backward movement.
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorOnForRotations(float power, int rotations, boolean brake) {
		motorOnForRotationsDegrees(power, rotations, 0, brake);
	}

	/**
	 * let motor run the specified number of rotations.
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations; it seems that the LEGO Programming
	 *                  block also accepts a negative number of rotations for
	 *                  backward movement.
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorOnForRotations(int power, float rotations, boolean brake) {
		motorOnForRotationsDegrees(power, rotations, 0, brake);
	}

	/**
	 * let motor run the specified number of rotations.
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations; it seems that the LEGO Programming
	 *                  block also accepts a negative number of rotations for
	 *                  backward movement.
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorOnForRotations(float power, float rotations, boolean brake) {
		motorOnForRotationsDegrees(power, rotations, 0, brake);
	}

	/**
	 * let motor run the specified number of rotations and degrees.
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations; it seems that the LEGO Programming
	 *                  block also accepts a negative number of rotations for
	 *                  backward movement.
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorOnForRotationsDegrees(int power, int rotations, int degrees, boolean brake) {
		motorOnForRotationsDegrees(power, rotations, degrees, brake, false);
	}

	/**
	 * let motor run the specified number of rotations and degrees.
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations; it seems that the LEGO Programming
	 *                  block also accepts a negative number of rotations for
	 *                  backward movement.
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorOnForRotationsDegrees(float power, int rotations, int degrees, boolean brake) {
		motorOnForRotationsDegrees(power, rotations, degrees, brake, false);
	}

	/**
	 * let motor run the specified number of rotations and degrees.
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations; it seems that the LEGO Programming
	 *                  block also accepts a negative number of rotations for
	 *                  backward movement.
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorOnForRotationsDegrees(int power, float rotations, int degrees, boolean brake) {
		motorOnForRotationsDegrees(power, rotations, degrees, brake, false);
	}

	/**
	 * let motor run the specified number of rotations and degrees.
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations; it seems that the LEGO Programming
	 *                  block also accepts a negative number of rotations for
	 *                  backward movement.
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorOnForRotationsDegrees(float power, float rotations, int degrees, boolean brake) {
		motorOnForRotationsDegrees(power, rotations, degrees, brake, false);
	}

	/**
	 * let motor run the specified number of rotations and degrees (wait until turn is complete).
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations; it seems that the LEGO Programming
	 *                  block also accepts a negative number of rotations for
	 *                  backward movement.
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 * @param immediateReturn true means don't wait for motor stop; false otherwise.
	 */
	public void motorOnForRotationsDegrees(int power, float rotations, int degrees, boolean brake, boolean immediateReturn) {
		motorOnForRotationsDegrees(power, rotations, degrees, brake, false);
	}

	/**
	 * let motor run the specified number of rotations and degrees (wait until turn is complete).
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations; it seems that the LEGO Programming
	 *                  block also accepts a negative number of rotations for
	 *                  backward movement.
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 * @param immediateReturn true means don't wait for motor stop; false otherwise.
	 */
	public void motorOnForRotationsDegrees(float power, float rotations, int degrees, boolean brake, boolean immediateReturn) {
		if (rotations < 0) {
			// set reverse power and positive rotations
			power = -Math.abs(power);
			rotations = Math.abs(rotations);
		}
		if ((rotations > 0) || (degrees > 0)) {
			// setup motor power level
			setPower(power);
			// calculate the degrees to turn
			int degrs = Math.round(rotations * 360F) + degrees;
			if (log.isLoggable(Level.FINEST)) {
				log.log(Level.FINEST, "rotate {0} deg.", degrs);
			}
			if (power < 0) {
				// use negative degrees to turn backward
				degrs = -degrs;
			}
			// start motor and rotate the specified number of degrees and brake afterwards.
			// XXX Alas, LeJOS does not expose the hold parameter of the underlaying
			// regulator 'newMove' method. It would correspond to our brake parameter.
			// Instead LeJOS always brakes the motor after rotations.
			motor.rotate(degrs);
			// at least float motor afterwards if specified
			if (!brake) {
				motor.flt(immediateReturn);
			}
		}
	}

	/**
	 * stop motor.
	 * 
	 * @param brake set true to brake at the end of movement; set false to remove
	 *              power but do not brake.
	 */
	@Override
	public void motorOff(boolean brake) {
		motorOff(brake, false);
	}

	/**
	 * stop motor.
	 * 
	 * @param brake set true to brake at the end of movement; set false to remove
	 *              power but do not brake.
	 * @param immediateReturn true means don't wait for motor stop; false otherwise.
	 */
	public void motorOff(boolean brake, boolean immediateReturn) {
		if (brake) {
			motor.stop(immediateReturn);
		} else {
			motor.flt(immediateReturn);
		}
	}

	/**
	 * Motor Rotation Block: reset the motor's rotation to zero.
	 */
	@Override
	public void rotationReset() {
		motor.resetTachoCount();
	}

	/**
	 * Motor Rotation Block: measure the current degrees turned since the last
	 * reset.
	 * 
	 * @return the degrees.
	 */
	@Override
	public int measureDegrees() {
		return motor.getTachoCount();
	}

	/**
	 * Motor Rotation Block: measure the number of rotations turned since the last
	 * reset.
	 * 
	 * @return the rotations.
	 */
	@Override
	public float measureRotations() {
		return (motor.getTachoCount() / 360F);
	}

	/**
	 * Motor Rotation Block: measure the current power level of the motor.
	 * 
	 * @return the current power level.
	 */
	@Override
	public float measureCurrentPower() {
		return getPower();
	}

	/**
	 * get current power level.
	 * calculates a power level that corresponds to the current speed.
	 * 
	 * @return the power 0..100.
	 */
	protected float getPower() {
		return 100F * motor.getSpeed() / motor.getMaxSpeed();
	}

	/**
	 * set the motor power level.
	 * calculates a speed that corresponds to the power level.
	 * 
	 * @param power the power to set, 0..100.
	 */
	protected void setPower(int power) {
		// calculate the speed for the regulated motor
		// (setSpeed takes the absolute value)
		motor.setSpeed(power * motor.getMaxSpeed() / 100F);
	}

	/**
	 * set the motor power level.
	 * calculates a speed that corresponds to the power level.
	 * 
	 * @param power the power to set, 0..100.
	 */
	protected void setPower(float power) {
		// calculate the speed for the regulated motor
		// (setSpeed takes the absolute value)
		motor.setSpeed(power * motor.getMaxSpeed() / 100F);
	}

	/**
	 * start the motor.
	 * 
	 * @param power set power direction; + forward; 0 stop; - backward.
	 */
	protected void start(int power) {
		if (power > 0) {
			motor.forward();
		}
		if (power < 0) {
			motor.backward();
		}
	}
	/**
	 * start the motor.
	 * 
	 * @param power set power direction; + forward; 0 stop; - backward.
	 */
	protected void start(float power) {
		if (power > 0) {
			motor.forward();
		}
		if (power < 0) {
			motor.backward();
		}
	}
}
