/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package lego2lejosev3.pblocks;

import lejos.hardware.port.Port;
import lejos.hardware.port.UARTPort;
import lejos.hardware.sensor.SensorMode;

/**
 * EV3 Infra Red sensor.
 * (Fixed version of the lejos.hardware.sensor.EV3IRSensor class)
 * 
 * @author Roland Blochberger
 */
public class EV3IRSensor extends lejos.hardware.sensor.EV3IRSensor {

	/**
	 * Constructor.
	 * 
	 * @param port
	 */
	public EV3IRSensor(UARTPort port) {
		super(port);
	}

	/**
	 * Constructor.
	 * 
	 * @param port
	 */
	public EV3IRSensor(Port port) {
		super(port);
	}

	/**
	 * initialize the modes array
	 */
	protected void init() {
		setModes(new SensorMode[] { new DistanceMode(), new SeekMode() });
	}

	/**
	 * Distance mode handler class.
	 */
	private class DistanceMode implements SensorMode {
		private static final float toSI = 1f;

		@Override
		public int sampleSize() {
			return 1;
		}

		@Override
		public void fetchSample(float[] sample, int offset) {
			switchMode(IR_PROX, SWITCH_DELAY);
			int raw = ((int) port.getByte() & 0xff);
			// just do not manipulate the raw sensor value for proximity
			sample[offset] = raw * toSI;
		}

		@Override
		public String getName() {
			return "Distance";
		}
	}

	/**
	 * Seek mode handler class.
	 */
	private class SeekMode implements SensorMode {
		private static final float toSI = 1f;
		byte[] seekVals = new byte[8];

		@Override
		public int sampleSize() {
			return 8;
		}

		@Override
		public void fetchSample(float[] sample, int offset) {
			switchMode(IR_SEEK, SWITCH_DELAY);
			port.getBytes(seekVals, 0, seekVals.length);
			for (int i = 0; i < seekVals.length; i += 2) {
				int raw = (int) seekVals[i + 1] & 0xff;
				if (raw == 128) {
					// beacon bearing
					sample[offset++] = 0;
					// beacon proximity
					sample[offset++] = Float.POSITIVE_INFINITY;
				} else {
					// beacon bearing
					sample[offset++] = seekVals[i] * toSI;
					// beacon proximity
					sample[offset++] = (int) seekVals[i + 1] & 0xff;
				}
			}
		}

		@Override
		public String getName() {
			return "Seek";
		}
	}
}
