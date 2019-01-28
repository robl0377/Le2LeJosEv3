/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

/**
 * Unregulated Motor and Motor Rotation Blocks.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FUnregulatedMotor.html
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FRotationSensor.html
 */
public class UnregulatedMotor implements IMotor {

	private static final Logger log = Logger.getLogger(UnregulatedMotor.class.getName());

	// the motor port
	protected Port motorPort;
	// the unregulated motor instance
	protected lejos.hardware.motor.UnregulatedMotor motor = null;

	// motor blocking timeout in degrees or rotations mode
	protected static final int BLOCK_TIMEOUT = 500;

	// medium motor flag
	// (used for measureCurrentPower() only)
	protected boolean isMediumMotor = false;

	// assumed maximal speed of a EV3 large motor at max. voltage
	// (used for measureCurrentPower() only)
	protected float MAX_SPEED_AT_9V = 175 * 360 / 60;

	/**
	 * Constructor.
	 * handles the motor resources correctly before exiting
	 * 
	 * @param motorPort the motor port.
	 */
	public UnregulatedMotor(Port motorPort) {
		// store motor port
		this.motorPort = motorPort;
		// instantiate the motor object
		motor = new lejos.hardware.motor.UnregulatedMotor(this.motorPort);
		if (motor != null) {
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
	 * Note1: this will automatically run at a program's end.
	 * Note2: close an existing motor class before creating a new motor class on the
	 * same motor port; for example: close a regulated motor before creating an
	 * unregulated one on the same port.
	 */
	public void close() {
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
	lejos.hardware.motor.UnregulatedMotor getMotor() {
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
	 * set the EV3 motor type.
	 * 
	 * @param isMediumMotor set true for MediumMotor; set false for LargeMotor.
	 */
	public void setMediumMotor(boolean isMediumMotor) {
		this.isMediumMotor = isMediumMotor;
		MAX_SPEED_AT_9V = (isMediumMotor ? 260 * 360 / 60 : 175 * 360 / 60);
	}

	/**
	 * @return the EV3 motor type: true for MediumMotor, false for LargeMotor.
	 */
	public boolean isMediumMotor() {
		return isMediumMotor;
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
	 * @param degrees number of degrees; it seems that the LEGO Programming
	 *                block also accepts a negative number of degrees for
	 *                backward movement.
	 * @param brake   set true to brake at the end of movement; set false to remove
	 *                power but do not brake.
	 */
	@Override
	public void motorOnForDegrees(int power, int degrees, boolean brake) {
		motorOnForRotationsDegrees(power, 0F, degrees, brake);
	}

	/**
	 * let motor run the specified number of degrees.
	 * 
	 * @param power   set power percentage (0..100); + forward; - backward.
	 * @param degrees number of degrees; it seems that the LEGO Programming
	 *                block also accepts a negative number of degrees for
	 *                backward movement.
	 * @param brake   set true to brake at the end of movement; set false to remove
	 *                power but do not brake.
	 */
	@Override
	public void motorOnForDegrees(float power, int degrees, boolean brake) {
		motorOnForRotationsDegrees(Math.round(power), 0F, degrees, brake);
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
		motorOnForRotationsDegrees(power, (float) rotations, 0, brake);
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
		motorOnForRotationsDegrees(Math.round(power), (float) rotations, 0, brake);
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
		motorOnForRotationsDegrees(Math.round(power), rotations, 0, brake);
	}

	/**
	 * let motor run the specified number of rotations and degrees.
	 * 
	 * the total degrees to turn the motor is (rotations * 360) + degrees.
	 * it seems that the LEGO Programming block also accepts a negative number of
	 * total degrees for backward movement
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations.
	 * @param degrees   number of degrees.
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorOnForRotationsDegrees(int power, int rotations, int degrees, boolean brake) {
		motorOnForRotationsDegrees(power, (float) rotations, degrees, brake);
	}

	/**
	 * let motor run the specified number of rotations and degrees.
	 * 
	 * the total degrees to turn the motor is (rotations * 360) + degrees.
	 * it seems that the LEGO Programming block also accepts a negative number of
	 * total degrees for backward movement
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations.
	 * @param degrees   number of degrees.
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorOnForRotationsDegrees(float power, int rotations, int degrees, boolean brake) {
		motorOnForRotationsDegrees(Math.round(power), (float) rotations, degrees, brake);
	}

	/**
	 * let motor run the specified number of rotations and degrees.
	 * 
	 * the total degrees to turn the motor is (rotations * 360) + degrees.
	 * it seems that the LEGO Programming block also accepts a negative number of
	 * total degrees for backward movement
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations.
	 * @param degrees   number of degrees.
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorOnForRotationsDegrees(int power, float rotations, int degrees, boolean brake) {
		// calculate the degrees to turn
		int degrs = Math.round(rotations * 360F) + degrees;
		if (degrs < 0) {
			// set reverse power and positive rotations
			power = -Math.abs(power);
			degrs = Math.abs(degrs);
		}
		if (degrs > 0) {
			// setup motor power
			motor.setPower(power);
			// get start tacho count of the motor
			int mstc = motor.getTachoCount();
			// and the current timestamp
			long mstct = System.currentTimeMillis();
			if (log.isLoggable(Level.FINEST)) {
				log.log(Level.FINEST, "rotate {0} deg.", degrs);
			}
			// calculate the degrees to turn to
			int metc = (power > 0) ? (mstc + degrs) : (mstc - degrs);
			if (log.isLoggable(Level.FINEST)) {
				log.finest("mstc: " + mstc + ", degrs: " + degrs + ", metc: " + metc);
			}

			// start motor
			start(power);

			int mtc = 0; // newest sample
			long mtct = 0L; // newest timestamp
			int motc = mstc; // old sample
			long motct = mstct; // old timestamp
			int pdg = degrs; // pending degrees

			while (Button.ESCAPE.isUp()) {
				// get current degrees
				mtc = motor.getTachoCount();
				// and the current timestamp
				mtct = System.currentTimeMillis();
				// get pending degrees to rotate
				pdg = (power > 0) ? (metc - mtc) : (mtc - metc);
				if (log.isLoggable(Level.FINEST)) {
					log.finest("mtc: " + mtc + "; motc: " + motc + "; pdg: " + pdg);
				}

				// check if not rotating any more (blocked)
				if (mtc == motc) {
					// blocked
					if ((mtct - motct) >= BLOCK_TIMEOUT) {
						// blocked too long: leave loop
						break;
					}
				} else {
					// not blocked: store degrees and timestamp for later
					motc = mtc;
					motct = mtct;
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
			// switch motor off
			motorOff(brake);
		}
	}

	/**
	 * let motor run the specified number of rotations and degrees.
	 * 
	 * the total degrees to turn the motor is (rotations * 360) + degrees.
	 * it seems that the LEGO Programming block also accepts a negative number of
	 * total degrees for backward movement
	 * 
	 * @param power     set power percentage (0..100); + forward; - backward.
	 * @param rotations number of rotations.
	 * @param degrees   number of degrees.
	 * @param brake     set true to brake at the end of movement; set false to
	 *                  remove power but do not brake.
	 */
	@Override
	public void motorOnForRotationsDegrees(float power, float rotations, int degrees, boolean brake) {
		motorOnForRotationsDegrees(Math.round(power), rotations, degrees, brake);
	}

	/**
	 * stop motor.
	 * 
	 * @param brake set true to brake at the end of movement; set false to remove
	 *              power but do not brake.
	 */
	@Override
	public void motorOff(boolean brake) {
		if (brake) {
			motor.stop();
		} else {
			motor.flt();
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
	 * calculates a power level that corresponds to the current speed.
	 * 
	 * @return the current power level 0..100.
	 */
	@Override
	public float measureCurrentPower() {
		return 100F * measureRotationSpeed() / getMaxRotationSpeed();
	}

	/**
	 * measure current rotation speed.
	 * 
	 * @return the rotation speed in degrees / second.
	 */
	protected float measureRotationSpeed() {
		// measure the degrees per 100ms
		int dif = 0;
		int sdeg = motor.getTachoCount();
		// wait about 100ms
		Delay.msDelay(99L);
		dif = Math.abs(motor.getTachoCount() - sdeg);
		return 10F * dif;
	}

	/**
	 * get maximum speed.
	 * (similar to the BaseRegulatedMotor getMaxSpeed method)
	 * 
	 * @return the maximum rotation speed in degrees / second.
	 */
	protected float getMaxRotationSpeed() {
		// It is generally assumed, that the maximum accurate speed of an EV3 Motor is
		// 100 degree/second * Voltage. We generalise this to other LEGO motors by
		// returning a value
		// that is based on 90% of the maximum free running speed of the motor.
		return LocalEV3.ev3.getPower().getVoltage() * MAX_SPEED_AT_9V / 9.0f * 0.9f;
	}

	/**
	 * get currently set power level.
	 * 
	 * @return the power 0..100.
	 */
	protected int getPower() {
		return motor.getPower();
	}

	/**
	 * set the motor power level.
	 * 
	 * @param power the power to set, 0..100.
	 */
	protected void setPower(int power) {
		// motor.setPower(Math.abs(power));
		motor.setPower(power);
	}

	/**
	 * set the motor power level.
	 * 
	 * @param power the power to set, 0..100.
	 */
	protected void setPower(float power) {
		// motor.setPower(Math.abs(power));
		motor.setPower(Math.round(power));
	}

	/**
	 * start the motor.
	 * 
	 * @param power set power direction; + forward; 0 stop; - backward.
	 */
	protected void start(int power) {
		motor.forward();
	}

	/**
	 * start the motor.
	 * 
	 * @param power set power direction; + forward; 0 stop; - backward.
	 */
	protected void start(float power) {
		motor.forward();
	}
}
