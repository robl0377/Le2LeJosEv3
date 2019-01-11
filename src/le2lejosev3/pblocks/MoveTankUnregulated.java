/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import lejos.hardware.port.Port;

/**
 * Move Tank Block.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FMoveTank.html
 */
public class MoveTankUnregulated extends MoveBaseUnregulated implements IMoveTank {

	private static final Logger log = Logger.getLogger(MoveTankUnregulated.class.getName());

	/**
	 * Constructor.
	 * 
	 * @param leftMotorPort  the left motor port.
	 * @param rightMotorPort the right motor port.
	 */
	public MoveTankUnregulated(Port leftMotorPort, Port rightMotorPort) {
		super(leftMotorPort, rightMotorPort);
	}

	/**
	 * Constructor.
	 * 
	 * @param leftMotor  the left UnregulatedMotor.
	 * @param rightMotor the right UnregulatedMotor.
	 */
	public MoveTankUnregulated(UnregulatedMotor leftMotor, UnregulatedMotor rightMotor) {
		super(leftMotor, rightMotor);
	}

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	@Override
	public void motorsOn(int powerLeft, int powerRight) {
		int[] motorPower = calcPower(powerLeft, powerRight);
		super.motorsOn(motorPower[0], motorPower[1]);
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
	@Override
	public void motorsOnForSeconds(int powerLeft, int powerRight, float period, boolean brake) {
		if (period > 0) {
			int[] motorPower = calcPower(powerLeft, powerRight);
			// let motors run for the specified period then brake or float
			super.motorsOnForSeconds(motorPower[0], motorPower[1], period, brake);
		}
	}

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotations(int powerLeft, int powerRight, int rotations, boolean brake) {
		motorsOnForRotationsDegrees(powerLeft, powerRight, rotations, 0, brake);
	}

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotations(int powerLeft, int powerRight, float rotations, boolean brake) {
		motorsOnForRotationsDegrees(powerLeft, powerRight, rotations, 0, brake);
	}

	/**
	 * let left and right motors run for the specified number of degrees.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param degrees    number of degrees (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	@Override
	public void motorsOnForDegrees(int powerLeft, int powerRight, int degrees, boolean brake) {
		motorsOnForRotationsDegrees(powerLeft, powerRight, 0, degrees, brake);
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
	@Override
	public void motorsOnForRotationsDegrees(int powerLeft, int powerRight, int rotations, int degrees, boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			int[] motorPower = calcPower(powerLeft, powerRight);
			// do the rotation then brake or float
			super.motorsOnForRotationsDegrees(motorPower[0], motorPower[1], rotations, degrees, brake);
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
	@Override
	public void motorsOnForRotationsDegrees(int powerLeft, int powerRight, float rotations, int degrees, boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			int[] motorPower = calcPower(powerLeft, powerRight);
			// do the rotation then brake or float
			super.motorsOnForRotationsDegrees(motorPower[0], motorPower[1], rotations, degrees, brake);
		}
	}

	/**
	 * calculate motor power.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @return array of 2 int: powerLeft and powerRight.
	 */
	protected int[] calcPower(int powerLeft, int powerRight) {
		// limit the power
		if (powerLeft < -100) {
			powerLeft = -100;
		} else if (powerLeft > 100) {
			powerLeft = 100;
		}
		if (powerRight < -100) {
			powerRight = -100;
		} else if (powerRight > 100) {
			powerRight = 100;
		}
		if (log.isLoggable(Level.FINEST)) {
			log.finest("left: " + powerLeft + ", right: " + powerRight);
		}
		int[] motorPower = new int[2];
		motorPower[0] = powerLeft;
		motorPower[1] = powerRight;
		return motorPower;
	}
}
