/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

/**
 * Medium Motor and Motor Rotation Blocks.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FMediumMotor.html
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FRotationSensor.html
 */
public class MediumMotor extends RegulatedMotor implements IMotor {

	/**
	 * Constructor.
	 * handles the motor resources correctly before exiting
	 * 
	 * @param motorPort
	 */
	public MediumMotor(Port motorPort) {
		super(motorPort, new EV3MediumRegulatedMotor(motorPort));
	}
}
