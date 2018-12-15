/**
 * 
 */
package lego2lejosev3.pblocks;

import lejos.hardware.port.Port;

/**
 * Large Motor and Motor Rotation Block
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FMotor.html
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FRotationSensor.html
 */
public class LargeMotor extends MediumMotor {

	/**
	 * Constructor.
	 * 
	 * @param motorPort
	 */
	public LargeMotor(Port motorPort) {
		super(motorPort);
	}

}
