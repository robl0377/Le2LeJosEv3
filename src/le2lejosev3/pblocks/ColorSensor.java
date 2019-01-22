/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;

/**
 * Color Sensor Block.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FColorSensor.html
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=editor%2FUsingSensors_Color.html
 */
public class ColorSensor {

	/** The color codes used in Color mode */
	public static final int COLOR_NONE = 0;
	public static final int COLOR_BLACK = 1;
	public static final int COLOR_BLUE = 2;
	public static final int COLOR_GREEN = 3;
	public static final int COLOR_YELLOW = 4;
	public static final int COLOR_RED = 5;
	public static final int COLOR_WHITE = 6;
	public static final int COLOR_BROWN = 7;

	private Port sensorPort = null;
	private EV3ColorSensor sensor = null;
	private SampleProvider sp = null;
	private float[] sample = null;

	/**
	 * Constructor.
	 * 
	 * @param sensorPort
	 */
	public ColorSensor(Port sensorPort) {
		this.sensorPort = sensorPort;
		sensor = new EV3ColorSensor(this.sensorPort);
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
	 * Fetch and return the color code from the sensor.
	 * 
	 * @return the color code, one of COLOR_NONE .. COLOR_BROWN.
	 */
	public int measureColor() {
		return convColorId(sensor.getColorID());
	}

	/**
	 * convert the LeJOS color code back to the LEGO one.
	 * 
	 * @param lejosColorId the color id from LeJOS
	 * @return the LEGO programming block color number
	 */
	protected int convColorId(int lejosColorId) {
		switch (lejosColorId) {
		case Color.BLACK:
			return COLOR_BLACK;
		case Color.BLUE:
			return COLOR_BLUE;
		case Color.GREEN:
			return COLOR_GREEN;
		case Color.YELLOW:
			return COLOR_YELLOW;
		case Color.RED:
			return COLOR_RED;
		case Color.WHITE:
			return COLOR_WHITE;
		case Color.BROWN:
			return COLOR_BROWN;
		default:
			return COLOR_NONE;
		}
	}

	/**
	 * Fetch and return the reflected light intensity from the sensor (as int value).
	 * 
	 * @return the reflected light intensity (0..100).
	 */
	public int measureReflectedLightIntensity() {
		// switch to reflected light intensity mode
		sp = sensor.getRedMode();
		sample = new float[sp.sampleSize()];
		sp.fetchSample(sample, 0);
		return Math.round(sample[0] * 100F);
	}

	/**
	 * Fetch and return the reflected light intensity from the sensor (as float value).
	 * 
	 * @return the reflected light intensity (0..100).
	 */
	public float measureReflectedLightIntensityF() {
		// switch to reflected light intensity mode
		sp = sensor.getRedMode();
		sample = new float[sp.sampleSize()];
		sp.fetchSample(sample, 0);
		return sample[0] * 100F;
	}

	/**
	 * Fetch and return the ambient light intensity from the sensor (as int value).
	 * 
	 * @return the ambient light intensity (0..100).
	 */
	public int measureAmbientLightIntensity() {
		// switch to ambient light intensity mode
		sp = sensor.getAmbientMode();
		sample = new float[sp.sampleSize()];
		sp.fetchSample(sample, 0);
		return Math.round(sample[0] * 100F);
	}

	/**
	 * Fetch and return the ambient light intensity from the sensor (as float value).
	 * 
	 * @return the ambient light intensity (0..100).
	 */
	public float measureAmbientLightIntensityF() {
		// switch to ambient light intensity mode
		sp = sensor.getAmbientMode();
		sample = new float[sp.sampleSize()];
		sp.fetchSample(sample, 0);
		return sample[0] * 100F;
	}
}
