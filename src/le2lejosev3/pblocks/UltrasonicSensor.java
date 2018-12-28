/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

//import java.util.logging.Logger;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

/**
 * Ultrasonic Sensor Block
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FUltrasonicSensor.html
 */
public class UltrasonicSensor {

	//private static final Logger log = Logger.getLogger(UltrasonicSensor.class.getName());

	private Port sensorPort = null;
	private EV3UltrasonicSensor sensor = null;
	private SampleProvider sp = null;
	private float[] sample = null;

	/** the modes of the EV3UltrasonicSensor */
	private static final int DISTANCE_MODE = 0;
	private static final int LISTEN_MODE = 2;

	/**
	 * @param sensorPort
	 */
	public UltrasonicSensor(Port sensorPort) {
		this.sensorPort = sensorPort;
		sensor = new EV3UltrasonicSensor(this.sensorPort);
		if (sensor != null) {
			// handle resources correctly before exiting
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					// switch off the indicator led
					sensor.disable();
					// close resources
					sensor.close();
				}
			}));
		}
	}

	/**
	 * Measure the distance from the sensor in centimeters.
	 * 
	 * @return the distance in centimeters (0..255).
	 */
	public float measureDistanceCentimeters() {
		if ((sensor.getCurrentMode() != DISTANCE_MODE) || (sp == null)) {
			// switch to distance mode
			sensor.enable();
			sp = sensor.getDistanceMode();
			sample = new float[sp.sampleSize()];
			// wait a little bit
			// Thread.sleep(SWITCHDELAY);
		}
		sp.fetchSample(sample, 0);
		// convert meters to centimeters
		sample[0] *= 100F;
		return sample[0];
	}

	/**
	 * Measure the distance from the sensor in inches.
	 * 
	 * @return the distance in inches (0..100).
	 */
	public float measureDistanceInches() {
		return measureDistanceCentimeters() / 2.54F;
	}

	/**
	 * Listen for other ultrasonic signals.
	 * 
	 * @return true if presence detected; false otherwise.
	 */
	public boolean measurePresence() {
		if ((sensor.getCurrentMode() != LISTEN_MODE) || (sp == null)) {
			// switch to presence/listen mode
			sensor.enable();
			sp = sensor.getListenMode();
			sample = new float[sp.sampleSize()];
			// wait a little bit
			// Thread.sleep(SWITCHDELAY);
		}
		sp.fetchSample(sample, 0);
		return (sample[0] != 0);
	}
}
