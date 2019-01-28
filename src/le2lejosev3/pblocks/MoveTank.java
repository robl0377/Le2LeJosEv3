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
public class MoveTank extends MoveBase implements IMoveTank {

	private static final Logger log = Logger.getLogger(MoveTank.class.getName());

	/**
	 * Constructor.
	 * 
	 * @param leftMotorPort  the left motor port.
	 * @param rightMotorPort the right motor port.
	 */
	public MoveTank(Port leftMotorPort, Port rightMotorPort) {
		super(leftMotorPort, rightMotorPort);
	}

	/**
	 * Constructor.
	 * 
	 * @param leftMotor  the left LargeMotor.
	 * @param rightMotor the right LargeMotor.
	 */
	public MoveTank(LargeMotor leftMotor, LargeMotor rightMotor) {
		super(leftMotor, rightMotor);
	}

	/**
	 * create a new MoveSteering instance with the motors of this instance.
	 * 
	 * @return a new MoveSteering instance.
	 */
	public MoveSteering createMoveSteering() {
		return new MoveSteering(super.getLeftMotor(), super.getRightMotor());
	}

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	@Override
	public void motorsOn(int powerLeft, int powerRight) {
		float[] motorPower = calcPower(powerLeft, powerRight);
		super.motorsOn(motorPower[0], motorPower[1]);
	}

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	@Override
	public void motorsOn(float powerLeft, int powerRight) {
		float[] motorPower = calcPower(powerLeft, powerRight);
		super.motorsOn(motorPower[0], motorPower[1]);
	}

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	@Override
	public void motorsOn(int powerLeft, float powerRight) {
		float[] motorPower = calcPower(powerLeft, powerRight);
		super.motorsOn(motorPower[0], motorPower[1]);
	}

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	@Override
	public void motorsOn(float powerLeft, float powerRight) {
		float[] motorPower = calcPower(powerLeft, powerRight);
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
			// setup motors and start them
			float[] motorPower = calcPower(powerLeft, powerRight);
			// let them run for the specified period then brake or float
			super.motorsOnForSeconds(motorPower[0], motorPower[1], period, brake);
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
	@Override
	public void motorsOnForSeconds(float powerLeft, int powerRight, float period, boolean brake) {
		if (period > 0) {
			// setup motors and start them
			float[] motorPower = calcPower(powerLeft, powerRight);
			// let them run for the specified period then brake or float
			super.motorsOnForSeconds(motorPower[0], motorPower[1], period, brake);
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
	@Override
	public void motorsOnForSeconds(int powerLeft, float powerRight, float period, boolean brake) {
		if (period > 0) {
			// setup motors and start them
			float[] motorPower = calcPower(powerLeft, powerRight);
			// let them run for the specified period then brake or float
			super.motorsOnForSeconds(motorPower[0], motorPower[1], period, brake);
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
	@Override
	public void motorsOnForSeconds(float powerLeft, float powerRight, float period, boolean brake) {
		if (period > 0) {
			// setup motors and start them
			float[] motorPower = calcPower(powerLeft, powerRight);
			// let them run for the specified period then brake or float
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
	public void motorsOnForRotations(float powerLeft, int powerRight, int rotations, boolean brake) {
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
	public void motorsOnForRotations(int powerLeft, float powerRight, int rotations, boolean brake) {
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
	public void motorsOnForRotations(float powerLeft, float powerRight, int rotations, boolean brake) {
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
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	@Override
	public void motorsOnForRotations(float powerLeft, int powerRight, float rotations, boolean brake) {
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
	public void motorsOnForRotations(int powerLeft, float powerRight, float rotations, boolean brake) {
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
	public void motorsOnForRotations(float powerLeft, float powerRight, float rotations, boolean brake) {
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
	 * let left and right motors run for the specified number of degrees.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param degrees    number of degrees (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	@Override
	public void motorsOnForDegrees(float powerLeft, int powerRight, int degrees, boolean brake) {
		motorsOnForRotationsDegrees(powerLeft, powerRight, 0, degrees, brake);
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
	public void motorsOnForDegrees(int powerLeft, float powerRight, int degrees, boolean brake) {
		motorsOnForRotationsDegrees(powerLeft, powerRight, 0, degrees, brake);
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
	public void motorsOnForDegrees(float powerLeft, float powerRight, int degrees, boolean brake) {
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
			float[] motorPower = calcPower(powerLeft, powerRight);
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
	public void motorsOnForRotationsDegrees(float powerLeft, int powerRight, int rotations, int degrees,
			boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			float[] motorPower = calcPower(powerLeft, powerRight);
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
	public void motorsOnForRotationsDegrees(int powerLeft, float powerRight, int rotations, int degrees,
			boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			float[] motorPower = calcPower(powerLeft, powerRight);
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
	public void motorsOnForRotationsDegrees(float powerLeft, float powerRight, int rotations, int degrees,
			boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			float[] motorPower = calcPower(powerLeft, powerRight);
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
	public void motorsOnForRotationsDegrees(int powerLeft, int powerRight, float rotations, int degrees,
			boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			float[] motorPower = calcPower(powerLeft, powerRight);
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
	public void motorsOnForRotationsDegrees(float powerLeft, int powerRight, float rotations, int degrees,
			boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			float[] motorPower = calcPower(powerLeft, powerRight);
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
	public void motorsOnForRotationsDegrees(int powerLeft, float powerRight, float rotations, int degrees,
			boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			float[] motorPower = calcPower(powerLeft, powerRight);
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
	public void motorsOnForRotationsDegrees(float powerLeft, float powerRight, float rotations, int degrees,
			boolean brake) {
		if ((rotations > 0) || (degrees > 0)) {
			// setup motors
			float[] motorPower = calcPower(powerLeft, powerRight);
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
	protected float[] calcPower(float powerLeft, float powerRight) {
		// limit the power
		if (powerLeft < -100F) {
			powerLeft = -100F;
		} else if (powerLeft > 100F) {
			powerLeft = 100F;
		}
		if (powerRight < -100F) {
			powerRight = -100F;
		} else if (powerRight > 100F) {
			powerRight = 100F;
		}
		if (log.isLoggable(Level.FINEST)) {
			log.finest("left: " + powerLeft + ", right: " + powerRight);
		}
		float[] motorPower = new float[2];
		motorPower[0] = powerLeft;
		motorPower[1] = powerRight;
		return motorPower;
	}
}
