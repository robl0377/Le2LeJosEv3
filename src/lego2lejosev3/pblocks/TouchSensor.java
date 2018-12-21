/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package lego2lejosev3.pblocks;

import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

/**
 * Touch Sensor Block.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FTouchSensor.html
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=editor%2FUsingSensors_Touch.html
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FWait.html#Mode_TouchSensorCompareState
 */
public class TouchSensor {

	/** the touch sensor compare states */
	public static final int RELEASED = 0;
	public static final int PRESSED = 1;
	public static final int BUMPED = 2;

	private Port sensorPort = null;
	private EV3TouchSensor sensor = null;
	private SampleProvider sp = null;
	private float[] sample = null;
	private boolean pressed = false;

	/**
	 * Constructor.
	 * 
	 * @param sensorPort
	 */
	public TouchSensor(Port sensorPort) {
		this.sensorPort = sensorPort;
		sensor = new EV3TouchSensor(this.sensorPort);
		if (sensor != null) {
			// handle resources correctly before exiting
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					// close resources
					sensor.close();
				}
			}));
			// setup touch mode and prepare sample array
			sp = sensor.getTouchMode();
			sample = new float[sp.sampleSize()];
		}
	}

	/**
	 * Fetch and return a sample from the sensor.
	 * 
	 * @return true if the Touch sensor is currently pressed in; or false if not.
	 */
	public boolean measureState() {
		sp.fetchSample(sample, 0);
		return (sample[0] != 0F);
	}

	/**
	 * Fetch and compare the sensor state.
	 * 
	 * @param state the sensor state to compare to; one of RELEASED, PRESSED, or
	 *              BUMPED.
	 * @return true if the the sensor state matches the specified one; false
	 *         otherwise.
	 */
	public boolean compareState(int state) {
		boolean result = false;
		// get current state of sensor
		boolean sample = measureState();
		// check result
		switch (state) {
		case RELEASED:
			result = !sample;
			break;
		case PRESSED:
			result = sample;
			pressed = sample;
			break;
		case BUMPED:
			result = !sample && pressed;
			pressed = !sample;
			break;
		default:
			throw new RuntimeException("Invalid State value: " + state);
		}
		return result;
	}

	/**
	 * Wait and compare sensor state.
	 * 
	 * @param state the sensor state to compare to; one of RELEASED, PRESSED, or
	 *              BUMPED.
	 * @return true if compare successful; false otherwise (e.g. on Escape button
	 *         pressed).
	 */
	public boolean waitCompareState(int state) {
		boolean result = false;
		while (Button.ESCAPE.isUp()) {
			result = false;
			// short delay util next sample
			try {
				Thread.sleep(2L);
			} catch (InterruptedException e) {
				// leave loop
				break;
			}
			// get current state of sensor
			result = compareState(state);
			// leave wait loop if compare is successful
			if (result) {
				break;
			}
		}
		return result;
	}
}
