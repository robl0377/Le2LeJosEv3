/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import lejos.hardware.ev3.EV3;
import lejos.hardware.port.Port;

/**
 * Move Steering Block using Unregulated Motors.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FMove.html
 * @see EV3 Move Steering Block Explained,
 *      https://communities.theiet.org/blogs/698/1706
 */
public class MoveSteeringUnregulated extends MoveBaseUnregulated implements IMoveSteering {

	private static final Logger log = Logger.getLogger(MoveSteeringUnregulated.class.getName());

	/**
	 * Constructor.
	 * 
	 * @param leftMotorPort  the left motor port.
	 * @param rightMotorPort the right motor port.
	 */
	public MoveSteeringUnregulated(Port leftMotorPort, Port rightMotorPort) {
		super(leftMotorPort, rightMotorPort);
	}

	/**
	 * Constructor.
	 * 
	 * @param leftMotor  the left UnregulatedMotor.
	 * @param rightMotor the right UnregulatedMotor.
	 */
	public MoveSteeringUnregulated(UnregulatedMotor leftMotor, UnregulatedMotor rightMotor) {
		super(leftMotor, rightMotor);
	}

	/**
	 * create a new MoveTankUnregulated instance with the unregulated motors of this instance.
	 * 
	 * @return a new MoveTankUnregulated instance.
	 */
	public MoveTankUnregulated createMoveTank() {
		return new MoveTankUnregulated(super.getLeftMotor(), super.getRightMotor());
	}

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left; 100
	 *                 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 */
	@Override
	public void motorsOn(int steering, int power) {
		int[] motorPower = calcPower(steering, power);
		super.motorsOn(motorPower[0], motorPower[1]);
	}

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left; 100
	 *                 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 */
	@Override
	public void motorsOn(float steering, int power) {
		int[] motorPower = calcPower(steering, power);
		super.motorsOn(motorPower[0], motorPower[1]);
	}

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left; 100
	 *                 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 */
	@Override
	public void motorsOn(int steering, float power) {
		int[] motorPower = calcPower(steering, power);
		super.motorsOn(motorPower[0], motorPower[1]);
	}

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left; 100
	 *                 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 */
	@Override
	public void motorsOn(float steering, float power) {
		int[] motorPower = calcPower(steering, power);
		super.motorsOn(motorPower[0], motorPower[1]);
	}

	/**
	 * let left and right motors run the specified period in seconds.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left; 100
	 *                 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 * @param period   the waiting time in seconds (> 0).
	 * @param brake    set true to brake at the end of movement; set false to
	 *                 remove power but do not brake.
	 */
	@Override
	public void motorsOnForSeconds(int steering, int power, float period, boolean brake) {
		if (period > 0) {
			int[] motorPower = calcPower(steering, power);
			// let motors run for the specified period then brake or float
			super.motorsOnForSeconds(motorPower[0], motorPower[1], period, brake);
		}
	}

	/**
	 * let left and right motors run the specified period in seconds.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left; 100
	 *                 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 * @param period   the waiting time in seconds (> 0).
	 * @param brake    set true to brake at the end of movement; set false to
	 *                 remove power but do not brake.
	 */
	@Override
	public void motorsOnForSeconds(float steering, int power, float period, boolean brake) {
		if (period > 0) {
			int[] motorPower = calcPower(steering, power);
			// let motors run for the specified period then brake or float
			super.motorsOnForSeconds(motorPower[0], motorPower[1], period, brake);
		}
	}

	/**
	 * let left and right motors run the specified period in seconds.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left; 100
	 *                 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 * @param period   the waiting time in seconds (> 0).
	 * @param brake    set true to brake at the end of movement; set false to
	 *                 remove power but do not brake.
	 */
	@Override
	public void motorsOnForSeconds(int steering, float power, float period, boolean brake) {
		if (period > 0) {
			int[] motorPower = calcPower(steering, power);
			// let motors run for the specified period then brake or float
			super.motorsOnForSeconds(motorPower[0], motorPower[1], period, brake);
		}
	}

	/**
	 * let left and right motors run the specified period in seconds.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left; 100
	 *                 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 * @param period   the waiting time in seconds (> 0).
	 * @param brake    set true to brake at the end of movement; set false to
	 *                 remove power but do not brake.
	 */
	@Override
	public void motorsOnForSeconds(float steering, float power, float period, boolean brake) {
		if (period > 0) {
			int[] motorPower = calcPower(steering, power);
			// let motors run for the specified period then brake or float
			super.motorsOnForSeconds(motorPower[0], motorPower[1], period, brake);
		}
	}

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotations(int steering, int power, int rotations, boolean brake) {
		motorsOnForRotationsDegrees(steering, power, rotations, 0, brake);
	}

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotations(float steering, int power, int rotations, boolean brake) {
		motorsOnForRotationsDegrees(steering, power, rotations, 0, brake);
	}

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotations(int steering, float power, int rotations, boolean brake) {
		motorsOnForRotationsDegrees(steering, power, rotations, 0, brake);
	}

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotations(float steering, float power, int rotations, boolean brake) {
		motorsOnForRotationsDegrees(steering, power, rotations, 0, brake);
	}

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotations(int steering, int power, float rotations, boolean brake) {
		motorsOnForRotationsDegrees(steering, power, rotations, 0, brake);
	}

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotations(float steering, int power, float rotations, boolean brake) {
		motorsOnForRotationsDegrees(steering, power, rotations, 0, brake);
	}

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotations(int steering, float power, float rotations, boolean brake) {
		motorsOnForRotationsDegrees(steering, power, rotations, 0, brake);
	}

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotations(float steering, float power, float rotations, boolean brake) {
		motorsOnForRotationsDegrees(steering, power, rotations, 0, brake);
	}

	/**
	 * let left and right motors run for the specified number of degrees.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left;
	 *                 100 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 * @param degrees  number of degrees (> 0).
	 * @param brake    set true to brake at the end of movement; set false to
	 *                 remove power but do not brake.
	 */
	@Override
	public void motorsOnForDegrees(int steering, int power, int degrees, boolean brake) {
		motorsOnForRotationsDegrees(steering, power, 0, degrees, brake);
	}

	/**
	 * let left and right motors run for the specified number of degrees.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left;
	 *                 100 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 * @param degrees  number of degrees (> 0).
	 * @param brake    set true to brake at the end of movement; set false to
	 *                 remove power but do not brake.
	 */
	@Override
	public void motorsOnForDegrees(float steering, int power, int degrees, boolean brake) {
		motorsOnForRotationsDegrees(steering, power, 0, degrees, brake);
	}

	/**
	 * let left and right motors run for the specified number of degrees.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left;
	 *                 100 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 * @param degrees  number of degrees (> 0).
	 * @param brake    set true to brake at the end of movement; set false to
	 *                 remove power but do not brake.
	 */
	@Override
	public void motorsOnForDegrees(int steering, float power, int degrees, boolean brake) {
		motorsOnForRotationsDegrees(steering, power, 0, degrees, brake);
	}

	/**
	 * let left and right motors run for the specified number of degrees.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left;
	 *                 100 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 * @param degrees  number of degrees (> 0).
	 * @param brake    set true to brake at the end of movement; set false to
	 *                 remove power but do not brake.
	 */
	@Override
	public void motorsOnForDegrees(float steering, float power, int degrees, boolean brake) {
		motorsOnForRotationsDegrees(steering, power, 0, degrees, brake);
	}

	/**
	 * let left and right motors run the specified number of rotations and degrees.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations of the motor (> 0).
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotationsDegrees(int steering, int power, int rotations, int degrees, boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			int[] motorPower = calcPower(steering, power);
			setPower(motorPower[0], motorPower[1]);
			// do the rotation then brake or float
			rotateMotors(motorPower[0], motorPower[1], rotations, degrees, brake);
		}
	}

	/**
	 * let left and right motors run the specified number of rotations and degrees.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations of the motor (> 0).
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotationsDegrees(float steering, int power, int rotations, int degrees, boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			int[] motorPower = calcPower(steering, power);
			setPower(motorPower[0], motorPower[1]);
			// do the rotation then brake or float
			rotateMotors(motorPower[0], motorPower[1], rotations, degrees, brake);
		}
	}

	/**
	 * let left and right motors run the specified number of rotations and degrees.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations of the motor (> 0).
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotationsDegrees(int steering, float power, int rotations, int degrees, boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			int[] motorPower = calcPower(steering, power);
			setPower(motorPower[0], motorPower[1]);
			// do the rotation then brake or float
			rotateMotors(motorPower[0], motorPower[1], rotations, degrees, brake);
		}
	}

	/**
	 * let left and right motors run the specified number of rotations and degrees.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations of the motor (> 0).
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotationsDegrees(float steering, float power, int rotations, int degrees, boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			int[] motorPower = calcPower(steering, power);
			setPower(motorPower[0], motorPower[1]);
			// do the rotation then brake or float
			rotateMotors(motorPower[0], motorPower[1], rotations, degrees, brake);
		}
	}

	/**
	 * let left and right motors run the specified number of rotations and degrees.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations of the motor (> 0).
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotationsDegrees(int steering, int power, float rotations, int degrees, boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			int[] motorPower = calcPower(steering, power);
			setPower(motorPower[0], motorPower[1]);
			// do the rotation then brake or float
			rotateMotors(motorPower[0], motorPower[1], rotations, degrees, brake);
		}
	}

	/**
	 * let left and right motors run the specified number of rotations and degrees.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations of the motor (> 0).
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotationsDegrees(float steering, int power, float rotations, int degrees, boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			int[] motorPower = calcPower(steering, power);
			setPower(motorPower[0], motorPower[1]);
			// do the rotation then brake or float
			rotateMotors(motorPower[0], motorPower[1], rotations, degrees, brake);
		}
	}

	/**
	 * let left and right motors run the specified number of rotations and degrees.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations of the motor (> 0).
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotationsDegrees(int steering, float power, float rotations, int degrees, boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			int[] motorPower = calcPower(steering, power);
			setPower(motorPower[0], motorPower[1]);
			// do the rotation then brake or float
			rotateMotors(motorPower[0], motorPower[1], rotations, degrees, brake);
		}
	}

	/**
	 * let left and right motors run the specified number of rotations and degrees.
	 * 
	 * @param steering  set amount of steering (0..100); + for right; - for left;
	 *                  100 means turn on the spot.
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations of the motor (> 0).
	 * @param degrees   number of degrees (> 0).
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotationsDegrees(float steering, float power, float rotations, int degrees, boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			int[] motorPower = calcPower(steering, power);
			setPower(motorPower[0], motorPower[1]);
			// do the rotation then brake or float
			rotateMotors(motorPower[0], motorPower[1], rotations, degrees, brake);
		}
	}

	/**
	 * calculate motor power.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left;
	 *                 100 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 * @retrun array of 2 int: powerLeft and powerRight.
	 */
	protected int[] calcPower(int steering, int power) {
		return calcPower((float)steering, power);
	}

	/**
	 * calculate motor power.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left;
	 *                 100 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 * @retrun array of 2 int: powerLeft and powerRight.
	 */
	protected int[] calcPower(float steering, int power) {
		// limit the steering
		if (steering < -100F) {
			steering = -100F;
		} else if (steering > 100F) {
			steering = 100F;
		}
		// limit the power
		if (power < -100) {
			power = -100;
		} else if (power > 100) {
			power = 100;
		}
		// right turn (+0..+50): reduce power of the right motor; left motor full power.
		// right turn (+51..+100): reverse the power of the right motor; left motor full
		// power.
		// left turn (-0..-50): reduce power of the left motor; right motor full power.
		// left turn (-51..-100): reverse the power of the left motor; right motor full
		// power.
		int lmpwr = power;
		int rmpwr = power;
		if (steering > 0) {
			// right turn
			rmpwr = Math.round(power * (1F - steering / 50F));
		} else if (steering < 0) {
			// left turn
			lmpwr = Math.round(power * (1F + steering / 50F));
		}
		if (log.isLoggable(Level.FINEST)) {
			log.finest("steering: " + steering + ", power: " + power + " -> left: " + lmpwr + ", right: " + rmpwr);
		}
		int[] motorPower = new int[2];
		motorPower[0] = lmpwr;
		motorPower[1] = rmpwr;
		return motorPower;
	}
	
	/**
	 * calculate motor power.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left;
	 *                 100 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 * @retrun array of 2 int: powerLeft and powerRight.
	 */
	protected int[] calcPower(int steering, float power) {
		return calcPower(steering, Math.round(power));
	}

	/**
	 * calculate motor power.
	 * 
	 * @param steering set amount of steering (0..100); + for right; - for left;
	 *                 100 means turn on the spot.
	 * @param power    set power percentage (0..100); + forward; - backward.
	 * @retrun array of 2 int: powerLeft and powerRight.
	 */
	protected int[] calcPower(float steering, float power) {
		return calcPower(steering, Math.round(power));
	}
}
