/**
 * LeJOS Implementation of LEGO Mindstorms Programming Blocks
 */
package lego2lejosev3.pblocks;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

/**
 * Infrared Sensor Block.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FInfraredSensor.html
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=editor%2FUsingSensors_Remote.html
 */
public class InfraredSensor {

	/** the remote control button codes */
	public static final int NONE = 0; // 0 = No button (and Beacon Mode is off)
	public static final int TOP_LEFT = 1; // Button 1
	public static final int BOTTOM_LEFT = 2; // Button 2
	public static final int TOP_RIGHT = 3; // Button 3
	public static final int BOTTOM_RIGHT = 4; // Button 4
	public static final int TOP_BOTH = 5; // Both Button 1 and Button 3
	public static final int TOP_LEFT_BOTTOM_RIGHT = 6; // Both Button 1 and Button 4
	public static final int TOP_RIGHT_BOTTOM_LEFT = 7; // Both Button 2 and Button 3
	public static final int BOTTOM_BOTH = 8; // Both Button 2 and Button 4
	public static final int BEACON = 9; // Beacon Mode is on
	public static final int LEFT_BOTH = 10; // Both Button 1 and Button 2
	public static final int RIGHT_BOTH = 11; // Both Button 3 and Button 4
	/*
	 * LeJOS Button codes:
	 * 1 TOP-LEFT
	 * 2 BOTTOM-LEFT
	 * 3 TOP-RIGHT
	 * 4 BOTTOM-RIGHT
	 * 5 TOP-LEFT + TOP-RIGHT
	 * 6 TOP-LEFT + BOTTOM-RIGHT
	 * 7 BOTTOM-LEFT + TOP-RIGHT
	 * 8 BOTTOM-LEFT + BOTTOM-RIGHT
	 * 9 CENTRE/BEACON
	 * 10 BOTTOM-LEFT + TOP-LEFT
	 * 11 TOP-RIGHT + BOTTOM-RIGHT
	 */

	private Port sensorPort = null;
	private EV3IRSensor sensor = null;
	private SampleProvider sp = null;
	private float[] sample = null;
	private int[] seek = null;

	/** the modes of the EV3GyroSensor */
	private static final int PROXIMITY_MODE = 0;
	private static final int BEACON_MODE = 1;
	// private static final int REMOTE_MODE = 2; // handled inside of LeJOS

	// the switch delay of the EV3GyroSensor is not used
	// private static final int SWITCHDELAY = 250;

	/**
	 * Constructor.
	 * 
	 * @param sensorPort
	 */
	public InfraredSensor(Port sensorPort) {
		this.sensorPort = sensorPort;
		sensor = new EV3IRSensor(this.sensorPort);
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
	 * return the remote control button code.
	 * 
	 * @param channel the remote control channel (1..4).
	 * @return remote control button code, one of TOP_LEFT .. RIGHT_BOTH.
	 */
	public int measureRemote(int channel) {
		if ((channel > 0) && (channel <= EV3IRSensor.IR_CHANNELS)) {
			return sensor.getRemoteCommand(channel - 1);
		} else {
			throw new RuntimeException("Invalid Channel number: " + channel);
		}
	}

	/**
	 * Fetch and return the proximity distance from the sensor.
	 * 
	 * @return the the approximate distance (0..100).
	 */
	public float measureProximity() {
		if (sensor.getCurrentMode() != PROXIMITY_MODE) {
			// switch to distance mode
			sp = sensor.getDistanceMode();
			sample = new float[sp.sampleSize()];
			// wait a little bit
			// Thread.sleep(SWITCHDELAY);
		}
		sp.fetchSample(sample, 0);
		return sample[0];
	}

	/**
	 * Fetch and return the proximity distance and the heading to the beacon.
	 * 
	 * @param channel the remote control channel (1..4).
	 * @return the array of heading (-25..+25), proximity (0..100), and detected (1
	 *         for found or 0 for not found).
	 */
	public int[] measureBeacon(int channel) {
		if ((channel > 0) && (channel <= EV3IRSensor.IR_CHANNELS)) {
			if (sensor.getCurrentMode() != BEACON_MODE) {
				// switch to beacon mode
				sp = sensor.getSeekMode();
				sample = new float[sp.sampleSize()];
				seek = new int[3];
				// wait a little bit
				// Thread.sleep(SWITCHDELAY);
			}
			sp.fetchSample(sample, 0);
			seek[0] = (int)sample[channel - 1];
			seek[1] = (int)sample[channel];
			if (seek[1] > 100) {
				seek[1] = 100;
			}
			seek[2] = (sample[channel] == Float.POSITIVE_INFINITY) ? 0 : 1;
			return seek;

		} else {
			throw new RuntimeException("Invalid Channel number: " + channel);
		}
	}

}
