/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import lejos.hardware.port.Port;

/**
 * Common code for MoveTank and MoveSteering.
 * 
 * @author Roland Blochberger
 */
public class MoveBase implements IMoveRotation {

	private static final Logger log = Logger.getLogger(MoveBase.class.getName());

	private Port leftMotorPort = null;
	private Port rightMotorPort = null;

	private LargeMotor leftMotor = null;
	private LargeMotor rightMotor = null;

	/**
	 * Constructor.
	 * 
	 * @param leftMotorPort  the left motor port.
	 * @param rightMotorPort the right motor port.
	 */
	public MoveBase(Port leftMotorPort, Port rightMotorPort) {
		this.leftMotorPort = leftMotorPort;
		this.rightMotorPort = rightMotorPort;
		// instantiate the motor objects
		leftMotor = new LargeMotor(this.leftMotorPort);
		rightMotor = new LargeMotor(this.rightMotorPort);
	}

	/**
	 * Constructor.
	 * 
	 * @param leftMotor  the left LargeMotor.
	 * @param rightMotor the right LargeMotor.
	 */
	public MoveBase(LargeMotor leftMotor, LargeMotor rightMotor) {
		this.leftMotor = leftMotor;
		if (leftMotor != null) {
			this.leftMotorPort = leftMotor.getPort();
		}
		this.rightMotor = rightMotor;
		if (rightMotor != null) {
			this.rightMotorPort = rightMotor.getPort();
		}
	}

	/**
	 * @return the leftMotor
	 */
	public LargeMotor getLeftMotor() {
		return leftMotor;
	}

	/**
	 * @return the rightMotor
	 */
	public LargeMotor getRightMotor() {
		return rightMotor;
	}

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	protected void motorsOn(int powerLeft, int powerRight) {
		setPower(powerLeft, powerRight);
		startMotors(powerLeft, powerRight);
	}

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	protected void motorsOn(float powerLeft, float powerRight) {
		setPower(powerLeft, powerRight);
		startMotors(powerLeft, powerRight);
	}

	/**
	 * let left and right motors run the specified period in seconds.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param period     the waiting time in seconds (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	protected void motorsOnForSeconds(int powerLeft, int powerRight, float period, boolean brake) {
		if (period > 0) {
			// setup motors and start them
			setPower(powerLeft, powerRight);
			startMotors(powerLeft, powerRight);
			// wait time in seconds
			Wait.time(period);
			// switch motors off
			leftMotor.motorOff(brake, true);
			rightMotor.motorOff(brake, true);
		}
	}

	/**
	 * let left and right motors run the specified period in seconds.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param period     the waiting time in seconds (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	protected void motorsOnForSeconds(float powerLeft, float powerRight, float period, boolean brake) {
		if (period > 0) {
			// setup motors and start them
			setPower(powerLeft, powerRight);
			startMotors(powerLeft, powerRight);
			// wait time in seconds
			Wait.time(period);
			// switch motors off
			leftMotor.motorOff(brake, true);
			rightMotor.motorOff(brake, true);
		}
	}

	/**
	 * let left and right motors run the specified number of rotations and degrees.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations of the motor (> 0).
	 * @param degrees    number of degrees (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	protected void motorsOnForRotationsDegrees(int powerLeft, int powerRight, float rotations, int degrees,
			boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// do the rotation
			rotateMotors(powerLeft, powerRight, rotations, degrees, brake);
		}
	}

	/**
	 * let left and right motors run the specified number of rotations and degrees.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations of the motor (> 0).
	 * @param degrees    number of degrees (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	protected void motorsOnForRotationsDegrees(float powerLeft, int powerRight, float rotations, int degrees,
			boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// do the rotation
			rotateMotors(powerLeft, powerRight, rotations, degrees, brake);
		}
	}

	/**
	 * let left and right motors run the specified number of rotations and degrees.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations of the motor (> 0).
	 * @param degrees    number of degrees (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	protected void motorsOnForRotationsDegrees(int powerLeft, float powerRight, float rotations, int degrees,
			boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// do the rotation
			rotateMotors(powerLeft, powerRight, rotations, degrees, brake);
		}
	}

	/**
	 * let left and right motors run the specified number of rotations and degrees.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations of the motor (> 0).
	 * @param degrees    number of degrees (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	protected void motorsOnForRotationsDegrees(float powerLeft, float powerRight, float rotations, int degrees,
			boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// do the rotation
			rotateMotors(powerLeft, powerRight, rotations, degrees, brake);
		}
	}

	/**
	 * stop left and right motors.
	 * 
	 * @param brake set true to brake at the end of movement; set false to
	 *              remove power but do not brake.
	 */
	public void motorsOff(boolean brake) {
		leftMotor.motorOff(brake, true);
		rightMotor.motorOff(brake, true);
	}

	/**
	 * Motor Rotation Block: reset the left motor's rotation to zero.
	 */
	@Override
	public void rotationResetLeft() {
		leftMotor.rotationReset();
	}

	/**
	 * Motor Rotation Block: reset the right motor's rotation to zero.
	 */
	@Override
	public void rotationResetRight() {
		rightMotor.rotationReset();
	}

	/**
	 * Motor Rotation Block: measure the current degrees the left motor turned since
	 * the last reset.
	 * 
	 * @return the degrees.
	 */
	@Override
	public int measureDegreesLeft() {
		return leftMotor.measureDegrees();
	}

	/**
	 * Motor Rotation Block: measure the current degrees the right motor turned
	 * since the last reset.
	 * 
	 * @return the degrees.
	 */
	@Override
	public int measureDegreesRight() {
		return rightMotor.measureDegrees();
	}

	/**
	 * Motor Rotation Block: measure the number of rotations the left motor turned
	 * since the last reset.
	 * 
	 * @return the rotations.
	 */
	@Override
	public float measureRotationsLeft() {
		return (leftMotor.measureDegrees() / 360F);
	}

	/**
	 * Motor Rotation Block: measure the number of rotations the right motor turned
	 * since the last reset.
	 * 
	 * @return the rotations.
	 */
	@Override
	public float measureRotationsRight() {
		return (rightMotor.measureDegrees() / 360F);
	}

	/**
	 * Motor Rotation Block: measure the current power level of the left motor.
	 * 
	 * @return the current power level (0..100).
	 */
	@Override
	public float measureCurrentPowerLeft() {
		return leftMotor.measureCurrentPower();
	}

	/**
	 * Motor Rotation Block: measure the current power level of the right motor.
	 * 
	 * @return the current power level (0..100).
	 */
	@Override
	public float measureCurrentPowerRight() {
		return rightMotor.measureCurrentPower();
	}

	/**
	 * Set the power level for both motors.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	protected void setPower(int powerLeft, int powerRight) {
		leftMotor.setPower(powerLeft);
		rightMotor.setPower(powerRight);
	}

	/**
	 * Set the power level for both motors.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	protected void setPower(float powerLeft, float powerRight) {
		leftMotor.setPower(powerLeft);
		rightMotor.setPower(powerRight);
	}

	/**
	 * Start the motors forward or backward.
	 * 
	 * @param powerLeft  set power direction; + forward; 0 stop; - backward.
	 * @param powerRight set power direction; + forward; 0 stop; - backward.
	 */
	protected void startMotors(int powerLeft, int powerRight) {
		leftMotor.start(powerLeft);
		rightMotor.start(powerRight);
	}

	/**
	 * Start the motors forward or backward.
	 * 
	 * @param powerLeft  set power direction; + forward; 0 stop; - backward.
	 * @param powerRight set power direction; + forward; 0 stop; - backward.
	 */
	protected void startMotors(float powerLeft, float powerRight) {
		leftMotor.start(powerLeft);
		rightMotor.start(powerRight);
	}

	/**
	 * Rotate.
	 * The motor with the bigger power is monitored for the degrees and the other
	 * motor is simply switched on. Afer reaching the specified degrees, the other
	 * motor is switched off. If both motors get the same power setting, both are
	 * rotated equally.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations of the motor (> 0).
	 * @param degrees    number of degrees (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	protected void rotateMotors(float powerLeft, float powerRight, float rotations, int degrees, boolean brake) {
		// calculate the degrees to turn
		int degrs = Math.round(rotations * 360F) + degrees;
		if (log.isLoggable(Level.FINEST)) {
			log.log(Level.FINEST, "rotate {0} deg.", degrs);
		}
		// setup motor power level
		leftMotor.setPower(powerLeft);
		rightMotor.setPower(powerRight);
		// determine which motor has the bigger power and thus should be monitored for
		// the degrees
		float apl = Math.abs(powerLeft);
		float apr = Math.abs(powerRight);
		if (apl > apr) {
			// switch right motor on
			rightMotor.start(powerRight);
			// rotate left motor and then brake or float it (wait for motor stop)
			// use negative degrees to turn backward
			// XXX Alas, LeJOS does not expose the hold parameter of the underlaying
			// regulator 'newMove' method. It would correspond to our brake parameter.
			// Instead LeJOS always brakes the motor after rotations.
			leftMotor.getMotor().rotate(((powerLeft < 0) ? -degrs : degrs), false);
			// brake or float right motor afterwards (don't wait for motor stop)
			rightMotor.motorOff(brake, true);
			// at least float motor afterwards if specified
			if (!brake) {
				leftMotor.getMotor().flt(true);
			}
		}
		if (apl < apr) {
			// switch left motor on
			leftMotor.start(powerLeft);
			// rotate right motor and then brake or float it (wait for motor stop)
			// use negative degrees to turn backward
			// XXX Alas, LeJOS does not expose the hold parameter of the underlaying
			// regulator 'newMove' method. It would correspond to our brake parameter.
			// Instead LeJOS always brakes the motor after rotations.
			rightMotor.getMotor().rotate(((powerRight < 0) ? -degrs : degrs), false);
			// brake or float left motor afterwards (don't wait for motor stop)
			leftMotor.motorOff(brake, true);
			// at least float right motor afterwards if specified
			if (!brake) {
				rightMotor.getMotor().flt(true);
			}

		} else if (apl == apr) {
			// rotate left motor, use negative degrees to turn backward  (don't wait for motor stop)
			leftMotor.getMotor().rotate(((powerLeft < 0) ? -degrs : degrs), true);
			// rotate right motor, use negative degrees to turn backward  (wait for motor stop)
			rightMotor.getMotor().rotate(((powerRight < 0) ? -degrs : degrs), false);
			// at least float motors afterwards if specified
			if (!brake) {
				leftMotor.getMotor().flt(true);
				rightMotor.getMotor().flt(false);
			}
		}
	}
}
