/**
 * LeJOS Implementation of LEGO Mindstorms Programming Blocks
 */
package lego2lejosev3.pblocks;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

/**
 * Gyro Sensor Block.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FGyro.html
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=editor%2FUsingSensors_Gyro.html
 */
public class GyroSensor {

	private Port sensorPort = null;
	private EV3GyroSensor sensor = null;
	private SampleProvider sp = null;
	private float[] sample = null;

	/** the modes of the EV3GyroSensor */
	private static final int RATE_MODE = 0;
	private static final int ANGLE_MODE = 1;
	private static final int ANGLE_RATE_MODE = 2;

	// the switch delay of the EV3GyroSensor is not used
	// private static final int SWITCHDELAY = 200;

	/**
	 * Constructor.
	 * 
	 * @param sensorPort
	 */
	public GyroSensor(Port sensorPort) {
		this.sensorPort = sensorPort;
		sensor = new EV3GyroSensor(this.sensorPort);
		if (sensor != null) {
			// handle resources correctly before exiting
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					// close resources
					sensor.close();
				}
			}));
		}
	}

	/**
	 * Fetch and return the rotation rate from the sensor.
	 * 
	 * @return the rotation rate in degrees/second.
	 */
	public float measureRate() {
		if (sensor.getCurrentMode() != RATE_MODE) {
			// switch to rate mode
			sp = sensor.getRateMode();
			sample = new float[sp.sampleSize()];
			// wait a little bit
			// Thread.sleep(SWITCHDELAY);
		}
		sp.fetchSample(sample, 0);
		// negate to compensate the multiplication by toSI (= -1) in EV3GyroSensor
		return -sample[0];
	}

	/**
	 * Fetch and return the rotation angle from the sensor.
	 * 
	 * @return the rotation angle in degrees.
	 */
	public float measureAngle() {
		if (sensor.getCurrentMode() != ANGLE_MODE) {
			// switch to angle mode
			sp = sensor.getAngleMode();
			sample = new float[sp.sampleSize()];
			// wait a little bit
			// Thread.sleep(SWITCHDELAY);
		}
		sp.fetchSample(sample, 0);
		// negate to compensate the multiplication by toSI (= -1) in EV3GyroSensor
		return -sample[0];
	}

	/**
	 * Fetch and return the rotation angle and rate from the sensor.
	 * 
	 * @return array comprising the rotation ratein degrees/second and the angle in
	 *         degrees.
	 */
	public float[] measureAngleRate() {
		if (sensor.getCurrentMode() != ANGLE_RATE_MODE) {
			// switch to angle and rate mode
			sp = sensor.getAngleAndRateMode();
			sample = new float[sp.sampleSize()];
			// wait a little bit
			// Thread.sleep(SWITCHDELAY);
		}
		sp.fetchSample(sample, 0);
		// negate to compensate the multiplication by toSI (= -1) in EV3GyroSensor
		for (int i = 0; i < sample.length; i++) {
			sample[i] = -sample[i];
		}
		return sample;
	}

	/**
	 * Reset.
	 * resets the rotation angle of the sensor to 0.
	 */
	public void reset() {
		sensor.reset();
	}
}
