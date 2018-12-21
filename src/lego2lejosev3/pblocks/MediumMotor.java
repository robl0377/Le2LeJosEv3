/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package lego2lejosev3.pblocks;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;


/**
 * Medium Motor and Motor Rotation Blocks.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FMediumMotor.html
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FRotationSensor.html
 */
public class MediumMotor extends RegulatedMotor {

	private Port motorPort;

	/**
	 * Constructor.
	 * 
	 * @param motorPort
	 */
	public MediumMotor(Port motorPort) {
		super(new EV3MediumRegulatedMotor(motorPort));
		this.motorPort = motorPort;
	}

	/**
	 * @return the name of the motor port; or null if not available.
	 */
	public String getPortName() {
		return (this.motorPort != null) ? this.motorPort.getName() : null;
	}
}
