/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import java.util.logging.Level;
import java.util.logging.Logger;

import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.robotics.SampleProvider;

/**
 * Infrared Sensor Block.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FInfraredSensor.html
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=editor%2FUsingSensors_Remote.html
 */
public class InfraredSensor implements Change {

	private static final Logger log = Logger.getLogger(InfraredSensor.class.getName());

	/** the remote control button codes */
	public static final int NONE = 0; // 0 = No Command Button (and Beacon Mode is off)
	public static final int TOP_LEFT = 1; // Command Button 1
	public static final int BOTTOM_LEFT = 2; // Command Button 2
	public static final int TOP_RIGHT = 3; // Command Button 3
	public static final int BOTTOM_RIGHT = 4; // Command Button 4
	public static final int TOP_BOTH = 5; // Both Command Button 1 and Command Button 3
	public static final int TOP_LEFT_BOTTOM_RIGHT = 6; // Both Command Button 1 and Command Button 4
	public static final int TOP_RIGHT_BOTTOM_LEFT = 7; // Both Command Button 2 and Command Button 3
	public static final int BOTTOM_BOTH = 8; // Both Command Button 2 and Command Button 4
	public static final int BEACON = 9; // Beacon Mode is on
	public static final int LEFT_BOTH = 10; // Both Command Button 1 and Command Button 2
	public static final int RIGHT_BOTH = 11; // Both Command Button 3 and Command Button 4
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
	private boolean isRemote = false;

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
	 * Fetch and return the proximity distance from the sensor.
	 * 
	 * @return the the approximate distance (0..100).
	 */
	public float measureProximity() {
		if ((sensor.getCurrentMode() != PROXIMITY_MODE) || (sp == null) || isRemote) {
			// switch to proximity mode
			isRemote = false;
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
	 * @return array comprising of heading (-25..+25) in the 0th element, proximity
	 *         (0..100) in the 1st element, and detected (1 for found or 0 for not
	 *         found) in the 2nd element.
	 */
	public int[] measureBeacon(int channel) {
		if ((channel > 0) && (channel <= EV3IRSensor.IR_CHANNELS)) {
			if ((sensor.getCurrentMode() != BEACON_MODE) || (sp == null) || isRemote) {
				// switch to beacon mode
				isRemote = false;
				sp = sensor.getSeekMode();
				sample = new float[sp.sampleSize()];
				seek = new int[3];
				// wait a little bit
				// Thread.sleep(SWITCHDELAY);
			}
			sp.fetchSample(sample, 0);
			int ix = (channel - 1) * 2;
			seek[0] = (int) sample[ix++];
			seek[1] = (int) sample[ix];
			if (seek[1] > 100) {
				seek[1] = 100;
			}
			seek[2] = (sample[ix] == Float.POSITIVE_INFINITY) ? 0 : 1;
			return seek;

		} else {
			throw new RuntimeException("Invalid Channel number: " + channel);
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
			isRemote = true;
			return sensor.getRemoteCommand(channel - 1);

		} else {
			throw new RuntimeException("Invalid Channel number: " + channel);
		}
	}

	/**
	 * Wait for Change of Infrared Proximity.
	 * 
	 * @param direction the change direction, one of CHANGE_INCREASE,
	 *                  CHANGE_DECREASE, CHANGE_ANY.
	 * @param amount    the amount of change (> 0).
	 * @return the current proximity value after the change.
	 */
	public float waitChangeProximity(int direction, float amount) {
		if ((direction == CHANGE_INCREASE) || (direction == CHANGE_DECREASE) || (direction == CHANGE_ANY)) {
			// get initial proximity
			float iniVal = measureProximity();
			if (log.isLoggable(Level.FINEST)) {
				log.finest("iniVal: " + iniVal);
			}
			float curVal = iniVal;
			boolean change = false;
			while (Button.ESCAPE.isUp()) {
				// short delay until next sample
				try {
					Thread.sleep(1L);
				} catch (InterruptedException e) {
					// leave loop
					break;
				}
				// get current state of sensor
				curVal = measureProximity();
				// compare
				switch (direction) {
				case CHANGE_INCREASE:
					change = (curVal >= (iniVal + amount));
					break;
				case CHANGE_DECREASE:
					change = (curVal <= (iniVal - amount));
					break;
				case CHANGE_ANY:
					change = ((curVal >= (iniVal + amount)) || (curVal <= (iniVal - amount)));
					break;
				}
				if (log.isLoggable(Level.FINEST)) {
					log.finest("curVal: " + curVal + ", change: " + change);
				}
				// leave wait loop if change occurred
				if (change) {
					break;
				}
			}
			return curVal;

		} else {
			throw new RuntimeException("Invalid Change direction: " + direction);
		}
	}
}
