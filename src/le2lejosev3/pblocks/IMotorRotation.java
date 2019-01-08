/**
 * 
 */
package le2lejosev3.pblocks;

/**
 * IMotorRotation Interface.
 * Defines the common motor rotation methods for all motor classes.
 * 
 * @author Roland Blochberger
 */
public interface IMotorRotation {

	/**
	 * IMotor Rotation Block: reset the motor's rotation to zero.
	 */
	public void rotationReset();

	/**
	 * IMotor Rotation Block: measure the current degrees turned since the last
	 * reset.
	 * 
	 * @return the degrees.
	 */
	public int measureDegrees();

	/**
	 * IMotor Rotation Block: measure the number of rotations turned since the last
	 * reset.
	 * 
	 * @return the rotations.
	 */
	public float measureRotations();

	/**
	 * IMotor Rotation Block: measure the current power level of the motor.
	 * 
	 * @return the current power level.
	 */
	public int measureCurrentPower();

}
