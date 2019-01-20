/**
 * 
 */
package le2lejosev3.pblocks;

/**
 * IMoveTank Interface.
 * Defines the common methods for all move tank classes.
 * 
 * @author Roland Blochberger
 */
public interface IMoveTank extends IMoveRotation {

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	public void motorsOn(int powerLeft, int powerRight);

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	public void motorsOn(float powerLeft, int powerRight);

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	public void motorsOn(int powerLeft, float powerRight);

	/**
	 * let left and right motors run indefinitely and return immediately.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 */
	public void motorsOn(float powerLeft, float powerRight);

	/**
	 * let left and right motors run the specified period in seconds.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param period     the waiting time in seconds (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForSeconds(int powerLeft, int powerRight, float period, boolean brake);

	/**
	 * let left and right motors run the specified period in seconds.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param period     the waiting time in seconds (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForSeconds(float powerLeft, int powerRight, float period, boolean brake);

	/**
	 * let left and right motors run the specified period in seconds.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param period     the waiting time in seconds (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForSeconds(int powerLeft, float powerRight, float period, boolean brake);

	/**
	 * let left and right motors run the specified period in seconds.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param period     the waiting time in seconds (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForSeconds(float powerLeft, float powerRight, float period, boolean brake);

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForRotations(int powerLeft, int powerRight, int rotations, boolean brake);

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForRotations(float powerLeft, int powerRight, int rotations, boolean brake);

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForRotations(int powerLeft, float powerRight, int rotations, boolean brake);

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForRotations(float powerLeft, float powerRight, int rotations, boolean brake);

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForRotations(int powerLeft, int powerRight, float rotations, boolean brake);

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForRotations(float powerLeft, int powerRight, float rotations, boolean brake);

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForRotations(int powerLeft, float powerRight, float rotations, boolean brake);

	/**
	 * let left and right motors run for the specified number of rotations.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param rotations  number of rotations (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForRotations(float powerLeft, float powerRight, float rotations, boolean brake);

	/**
	 * let left and right motors run for the specified number of degrees.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param degrees    number of degrees (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForDegrees(int powerLeft, int powerRight, int degrees, boolean brake);

	/**
	 * let left and right motors run for the specified number of degrees.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param degrees    number of degrees (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForDegrees(float powerLeft, int powerRight, int degrees, boolean brake);

	/**
	 * let left and right motors run for the specified number of degrees.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param degrees    number of degrees (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForDegrees(int powerLeft, float powerRight, int degrees, boolean brake);

	/**
	 * let left and right motors run for the specified number of degrees.
	 * 
	 * @param powerLeft  set power percentage (0..100); + forward; - backward.
	 * @param powerRight set power percentage (0..100); + forward; - backward.
	 * @param degrees    number of degrees (> 0).
	 * @param brake      set true to brake at the end of movement; set false to
	 *                   remove power but do not brake.
	 */
	public void motorsOnForDegrees(float powerLeft, float powerRight, int degrees, boolean brake);

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
	public void motorsOnForRotationsDegrees(int powerLeft, int powerRight, int rotations, int degrees, boolean brake);

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
	public void motorsOnForRotationsDegrees(float powerLeft, int powerRight, int rotations, int degrees, boolean brake);

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
	public void motorsOnForRotationsDegrees(int powerLeft, float powerRight, int rotations, int degrees, boolean brake);

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
	public void motorsOnForRotationsDegrees(float powerLeft, float powerRight, int rotations, int degrees, boolean brake);

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
	public void motorsOnForRotationsDegrees(int powerLeft, int powerRight, float rotations, int degrees, boolean brake);

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
	public void motorsOnForRotationsDegrees(float powerLeft, int powerRight, float rotations, int degrees, boolean brake);

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
	public void motorsOnForRotationsDegrees(int powerLeft, float powerRight, float rotations, int degrees, boolean brake);

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
	public void motorsOnForRotationsDegrees(float powerLeft, float powerRight, float rotations, int degrees, boolean brake);

	/**
	 * stop left and right motors.
	 * 
	 * @param brake set true to brake at the end of movement; set false to
	 *              remove power but do not brake.
	 */
	public void motorsOff(boolean brake);

}
