/**
 * 
 */
package le2lejosev3.pblocks;

/**
 * IMoveRotation Interface.
 * Defines the common motor rotation methods for all move classes.
 * 
 * @author Roland Blochberger
 */
public interface IMoveRotation {

	/**
	 * Motor Rotation Block: reset the left motor's rotation to zero.
	 */
	public void rotationResetLeft();

	/**
	 * Motor Rotation Block: reset the right motor's rotation to zero.
	 */
	public void rotationResetRight();

	/**
	 * Motor Rotation Block: measure the current degrees the left motor turned since
	 * the last reset.
	 * 
	 * @return the degrees.
	 */
	public int measureDegreesLeft();

	/**
	 * Motor Rotation Block: measure the current degrees the right motor turned
	 * since the last reset.
	 * 
	 * @return the degrees.
	 */
	public int measureDegreesRight();

	/**
	 * Motor Rotation Block: measure the number of rotations the left motor turned
	 * since the last reset.
	 * 
	 * @return the rotations.
	 */
	public float measureRotationsLeft();

	/**
	 * Motor Rotation Block: measure the number of rotations the right motor turned
	 * since the last reset.
	 * 
	 * @return the rotations.
	 */
	public float measureRotationsRight();

	/**
	 * Motor Rotation Block: measure the current power level of the left motor.
	 * 
	 * @return the current power level (0..100).
	 */
	public int measureCurrentPowerLeft();

	/**
	 * Motor Rotation Block: measure the current power level of the right motor.
	 * 
	 * @return the current power level (0..100).
	 */
	public int measureCurrentPowerRight();

}
