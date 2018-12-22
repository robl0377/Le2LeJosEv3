/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

/**
 * Large Motor and Motor Rotation Block
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FMotor.html
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FRotationSensor.html
 */
public class LargeMotor extends RegulatedMotor {

	private Port motorPort;

	/**
	 * Constructor.
	 * 
	 * @param motorPort
	 */
	public LargeMotor(Port motorPort) {
		super(new EV3LargeRegulatedMotor(motorPort));
		this.motorPort = motorPort;
	}

	/**
	 * @return the name of the motor port; or null if not available.
	 */
	public String getPortName() {
		return (this.motorPort != null) ? this.motorPort.getName() : null;
	}
}
