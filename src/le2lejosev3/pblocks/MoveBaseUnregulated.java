/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import lejos.hardware.Button;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.Port;

/**
 * Common code for MoveTankUnregulated and MoveSteeringUnregulated.
 * 
 * @author Roland Blochberger
 */
public class MoveBaseUnregulated {

	private static final Logger log = Logger.getLogger(MoveBase.class.getName());

	private Port leftMotorPort = null;
	private Port rightMotorPort = null;

	private UnregulatedMotor leftMotor = null;
	private UnregulatedMotor rightMotor = null;

	/**
	 * Constructor.
	 * 
	 * @param leftMotorPort
	 * @param rightMotorPort
	 */
	public MoveBaseUnregulated(Port leftMotorPort, Port rightMotorPort) {
		this.leftMotorPort = leftMotorPort;
		this.rightMotorPort = rightMotorPort;
		// instantiate the motor objects
		leftMotor = new UnregulatedMotor(this.leftMotorPort);
		rightMotor = new UnregulatedMotor(this.rightMotorPort);
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
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	protected void setPower(int powerLeft, int powerRight) {
		leftMotor.setPower(powerLeft);
		rightMotor.setPower(powerRight);
	}
	
	/**
	 * Start the motors.
	 * Since the power level determines the direction of rotation, only forward is
	 * needed here.
	 */
	protected void startMotors() {
		leftMotor.forward();
		rightMotor.forward();
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
		// get start tacho count of the motors
		int lmstc = leftMotor.getTachoCount();
		int rmstc = rightMotor.getTachoCount();

		// determine which motor has the bigger power and thus should be monitored for
		// the degrees
		boolean lal = (Math.abs(powerLeft) >= Math.abs(powerRight));
		// calculate the degrees to turn
		int degrs = (rotations * 360) + degrees;
		// calculate the degrees to turn to
		int lmetc = (powerLeft > 0) ? (lmstc + degrs) : (lmstc - degrs);
		int rmetc = (powerRight > 0) ? (rmstc + degrs) : (rmstc - degrs);
		if (log.isLoggable(Level.FINEST)) {
			log.finest(
					"lmstc: " + lmstc + ", rmstc: " + rmstc + ", lal: " + (lal ? "L" : "R") + ", degrs: " + degrs);
		}

		// start motors
		leftMotor.forward();
		rightMotor.forward();

		int lmtc = 0; // newest sample
		int rmtc = 0; // newest sample
		int pdg = degrs; // pending degrees

		while (Button.ESCAPE.isUp()) {
			if (lal) {
				// get current degrees
				lmtc = leftMotor.getTachoCount();
				// get pending degrees to rotate
				pdg = (powerLeft > 0) ? (lmetc - lmtc) : (lmtc - lmetc);
			} else {
				// get current degrees
				rmtc = rightMotor.getTachoCount();
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
				Thread.yield();

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

	/**
	 * stop left and right motors.
	 * 
	 * @param brake set true to brake at the end of movement; set false to
	 *              remove power but do not brake.
	 */
	public void motorsOff(boolean brake) {
		if (brake) {
			leftMotor.stop();
			rightMotor.stop();
		} else {
			leftMotor.flt();
			rightMotor.flt();
		}
	}
}
