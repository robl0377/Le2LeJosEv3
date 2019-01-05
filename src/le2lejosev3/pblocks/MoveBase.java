/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

/**
 * Common code for MoveTank and MoveSteering.
 * 
 * @author Roland Blochberger
 */
public class MoveBase {

	private static final Logger log = Logger.getLogger(MoveBase.class.getName());

	private Port leftMotorPort = null;
	private Port rightMotorPort = null;

	private EV3LargeRegulatedMotor leftMotor = null;
	private EV3LargeRegulatedMotor rightMotor = null;

	/**
	 * Constructor.
	 * 
	 * @param leftMotorPort
	 * @param rightMotorPort
	 */
	public MoveBase(Port leftMotorPort, Port rightMotorPort) {
		this.leftMotorPort = leftMotorPort;
		this.rightMotorPort = rightMotorPort;
		// instantiate the motor objects
		leftMotor = new EV3LargeRegulatedMotor(this.leftMotorPort);
		rightMotor = new EV3LargeRegulatedMotor(this.rightMotorPort);
		if ((leftMotor != null) || (rightMotor != null)) {
			// handle resources correctly before exiting
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					// stop the motors and wait until done, then close resources
					if (leftMotor != null) {
						leftMotor.stop();
						leftMotor.close();
					}
					if (rightMotor != null) {
						rightMotor.stop();
						rightMotor.close();
					}
				}
			}));
		}
	}

	/**
	 * Set the power level for both motors.
	 * calculates a speed that corresponds to the power level.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	protected void setPower(int powerLeft, int powerRight) {
		// calculate the speed for the regulated motor
		// (setSpeed takes the absolute value)
		leftMotor.setSpeed(powerLeft * leftMotor.getMaxSpeed() / 100F);
		rightMotor.setSpeed(powerRight * rightMotor.getMaxSpeed() / 100F);
	}

	/**
	 * Start the motors forward or backward.
	 * 
	 * @param powerLeft  set power direction; + forward; 0 stop; - backward.
	 * @param powerRight set power direction; + forward; 0 stop; - backward.
	 */
	protected void startMotors(int powerLeft, int powerRight) {
		if (powerLeft > 0) {
			leftMotor.forward();
		}
		if (powerLeft < 0) {
			leftMotor.backward();
		}
		if (powerRight > 0) {
			rightMotor.forward();
		}
		if (powerRight < 0) {
			rightMotor.backward();
		}
	}

	/**
	 * Rotate.
	 * The motor with the bigger power is monitored for the degrees and the other
	 * motor is simply switched on. Afer reaching the specified degrees, the other
	 * motor is switched off.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations of the motor (> 0).
	 * @param degrees    number of degrees (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	protected void rotateMotors(int powerLeft, int powerRight, int rotations, int degrees, boolean brake) {
		// calculate the degrees to turn
		int degrs = (rotations * 360) + degrees;
		if (log.isLoggable(Level.FINEST)) {
			log.finest("rotate " + degrs + " deg.");
		}
		// determine which motor has the bigger power and thus should be monitored for
		// the degrees
		if (Math.abs(powerLeft) >= Math.abs(powerRight)) {
			// switch right motor on
			if (powerRight > 0) {
				rightMotor.forward();
			}
			if (powerRight < 0) {
				rightMotor.backward();
			}
			// rotate left motor
			if (powerLeft < 0) {
				// use negative degrees to turn backward
				degrs = -degrs;
			}
			// start left motor and rotate the specified number of degrees and brake
			// afterwards.
			// XXX Alas, LeJOS does not expose the hold parameter of the underlaying
			// regulator 'newMove' method. It would correspond to our brake parameter.
			// Instead LeJOS always brakes the motor after rotations.
			leftMotor.rotate(degrs);
			if (brake) {
				rightMotor.stop(true);
			} else {
				rightMotor.flt(true);
				// at least float motor after rotation if specified
				leftMotor.flt(true);
			}

		} else {
			// switch left motor on
			if (powerLeft > 0) {
				leftMotor.forward();
			}
			if (powerLeft < 0) {
				leftMotor.backward();
			}
			// rotate right motor
			if (powerRight < 0) {
				// use negative degrees to turn backward
				degrs = -degrs;
			}
			// start right motor and rotate the specified number of degrees and brake
			// afterwards.
			// XXX Alas, LeJOS does not expose the hold parameter of the underlaying
			// regulator 'newMove' method. It would correspond to our brake parameter.
			// Instead LeJOS always brakes the motor after rotations.
			rightMotor.rotate(degrs);
			if (brake) {
				leftMotor.stop();
			} else {
				leftMotor.flt(true);
				// at least float motor after rotation if specified
				rightMotor.flt();
			}
		}
	}

	/**
	 * stop left and right motors.
	 * 
	 * @param brake set true to brake at the end of movement; set false to
	 *              remove power but do not brake.
	 */
	public void motorsOff(boolean brake) {
		if (brake) {
			leftMotor.stop(true);
			rightMotor.stop(true);
		} else {
			leftMotor.flt(true);
			rightMotor.flt(true);
		}
	}
}
