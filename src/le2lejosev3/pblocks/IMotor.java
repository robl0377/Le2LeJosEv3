package le2lejosev3.pblocks;

import lejos.hardware.port.Port;

/**
 * IMotor Interface.
 * Defines the common methods for all motor classes.
 * 
 * @author Roland Blochberger
 */
public interface IMotor extends IMotorRotation {

	/**
	 * @return the motorPort; or null if not available.
	 */
	public Port getPort();

	/**
	 * @return the name of the motor port; or null if not available.
	 */
	public String getPortName();

	/**
	 * let motor run indefinitely and return immediately.
	 * 
	 * @param power set power percentage (0..100); + forward; - backward.
	 */
	public void motorOn(int power);

	/**
	 * let motor run indefinitely and return immediately.
	 * 
	 * @param power set power percentage (0..100); + forward; - backward.
	 */
	public void motorOn(float power);

	/**
	 * let motor run the specified period in seconds.
	 * 
	 * @param power  set power percentage (0..100); + forward; - backward.
	 * @param period the waiting time in seconds (> 0).
	 * @param brake  set true to brake at the end of movement; set false to remove
	 *               power but do not brake.
	 */
	public void motorOnForSeconds(int power, float period, boolean brake);

	/**
	 * let motor run the specified period in seconds.
	 * 
	 * @param power  set power percentage (0..100); + forward; - backward.
	 * @param period the waiting time in seconds (> 0).
	 * @param brake  set true to brake at the end of movement; set false to remove
	 *               power but do not brake.
	 */
	public void motorOnForSeconds(float power, float period, boolean brake);

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
	public void motorOnForDegrees(int power, int degrees, boolean brake);

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
	public void motorOnForDegrees(float power, int degrees, boolean brake);

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
	public void motorOnForRotations(int power, int rotations, boolean brake);

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
	public void motorOnForRotations(float power, int rotations, boolean brake);

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
	public void motorOnForRotations(int power, float rotations, boolean brake);

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
	public void motorOnForRotations(float power, float rotations, boolean brake);

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
	public void motorOnForRotationsDegrees(int power, int rotations, int degrees, boolean brake);

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
	public void motorOnForRotationsDegrees(float power, int rotations, int degrees, boolean brake);

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
	public void motorOnForRotationsDegrees(int power, float rotations, int degrees, boolean brake);

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
	public void motorOnForRotationsDegrees(float power, float rotations, int degrees, boolean brake);

	/**
	 * stop motor.
	 * 
	 * @param brake set true to brake at the end of movement; set false to remove
	 *              power but do not brake.
	 */
	public void motorOff(boolean brake);

	/**
	 * stop the motor and wait until done, then close resources and remove the
	 * reference to the motor instance.
	 * Note1: this will automatically run at a program's end.
	 * Note2: close an existing motor class before creating a new motor class on the
	 * same motor port; for example: close a regulated motor before creating an
	 * unregulated one on the same port.
	 */
	public void close();
}
