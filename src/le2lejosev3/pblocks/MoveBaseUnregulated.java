/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import lejos.hardware.Button;
import lejos.hardware.port.Port;

/**
 * Common code for MoveTankUnregulated and MoveSteeringUnregulated.
 * 
 * @author Roland Blochberger
 */
public class MoveBaseUnregulated implements IMoveRotation {

	private static final Logger log = Logger.getLogger(MoveBase.class.getName());

	private Port leftMotorPort = null;
	private Port rightMotorPort = null;

	private UnregulatedMotor leftMotor = null;
	private UnregulatedMotor rightMotor = null;

	/**
	 * Constructor.
	 * 
	 * @param leftMotorPort  the left motor port.
	 * @param rightMotorPort the right motor port.
	 */
	public MoveBaseUnregulated(Port leftMotorPort, Port rightMotorPort) {
		this.leftMotorPort = leftMotorPort;
		this.rightMotorPort = rightMotorPort;
		// instantiate the motor objects
		leftMotor = new UnregulatedMotor(this.leftMotorPort);
		rightMotor = new UnregulatedMotor(this.rightMotorPort);
	}

	/**
	 * Constructor.
	 * 
	 * @param leftMotor  the left UnregulatedMotor.
	 * @param rightMotor the right UnregulatedMotor.
	 */
	public MoveBaseUnregulated(UnregulatedMotor leftMotor, UnregulatedMotor rightMotor) {
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
	public UnregulatedMotor getLeftMotor() {
		return leftMotor;
	}

	/**
	 * @return the rightMotor
	 */
	public UnregulatedMotor getRightMotor() {
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
			motorsOff(brake);
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
	 * stop left and right motors.
	 * 
	 * @param brake set true to brake at the end of movement; set false to
	 *              remove power but do not brake.
	 */
	public void motorsOff(boolean brake) {
		leftMotor.motorOff(brake);
		rightMotor.motorOff(brake);
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
	public int measureCurrentPowerLeft() {
		return leftMotor.measureCurrentPower();
	}

	/**
	 * Motor Rotation Block: measure the current power level of the right motor.
	 * 
	 * @return the current power level (0..100).
	 */
	@Override
	public int measureCurrentPowerRight() {
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
	protected void rotateMotors(int powerLeft, int powerRight, float rotations, int degrees, boolean brake) {
		// setup motors
		setPower(powerLeft, powerRight);
		// get start tacho count of the motors
		int lmstc = leftMotor.measureDegrees();
		int rmstc = rightMotor.measureDegrees();

		// determine which motor has the bigger power and thus should be monitored for
		// the degrees
		boolean lal = (Math.abs(powerLeft) >= Math.abs(powerRight));
		// calculate the degrees to turn
		int degrs = Math.round(rotations * 360F) + degrees;
		// calculate the degrees to turn to
		int lmetc = (powerLeft > 0) ? (lmstc + degrs) : (lmstc - degrs);
		int rmetc = (powerRight > 0) ? (rmstc + degrs) : (rmstc - degrs);
		if (log.isLoggable(Level.FINEST)) {
			log.finest("lmstc: " + lmstc + ", rmstc: " + rmstc + ", lal: " + (lal ? "L" : "R") + ", degrs: " + degrs);
		}

		// start motors
		startMotors(powerLeft, powerRight);

		int lmtc = 0; // newest sample
		int rmtc = 0; // newest sample
		int pdg = degrs; // pending degrees

		while (Button.ESCAPE.isUp()) {
			if (lal) {
				// get current degrees
				lmtc = leftMotor.measureDegrees();
				// get pending degrees to rotate
				pdg = (powerLeft > 0) ? (lmetc - lmtc) : (lmtc - lmetc);
			} else {
				// get current degrees
				rmtc = rightMotor.measureDegrees();
				// get pending degrees to rotate
				pdg = (powerRight > 0) ? (rmetc - rmtc) : (rmtc - rmetc);
			}
			if (log.isLoggable(Level.FINEST)) {
				log.finest("lmtc: " + lmtc + "; rmtc: " + rmtc + "; pdg: " + pdg);
			}

			// check degrees reached
			if (pdg <= 0) {
				break;
			}
			// wait until motors have reached their number of degrees
			if (pdg < 10) {
				// nearly done: just wait it out
				;

			} else {
				// wait by sleeping between samples
				try {
					Thread.sleep(1L);
				} catch (InterruptedException e) {
					// leave loop
					break;
				}
			}
		}
		// switch motors off
		motorsOff(brake);
	}
}
