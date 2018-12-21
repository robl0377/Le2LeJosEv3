/**
 * 
 */
package lego2lejosev3.tests;

import java.util.logging.Logger;

import lego2lejosev3.logging.Setup;
import lego2lejosev3.pblocks.Timer;
import lego2lejosev3.pblocks.Wait;

/**
 * Test for the Timer and Wait blocks.
 * 
 * @author Roland Blochberger
 */
public class TimerTest {

	private static Class<?> clazz = TimerTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file
		Setup.log2File(clazz);
		log.fine("Starting ...");

		// Timer and Wait test
		float[] periods = new float[] { 0.001F, 0.005F, 0.01F, 0.05F, 0.1F, 0.34F, 0.72F, 1F, 1.2F, 1.8F, 2.1F, 2.5F,
				2.95F, 3.12F };
		long[] waitTimes = new long[periods.length];
		float[] measureTimes = new float[periods.length];
		int tid = 0;
		long wt = 0;
		log.fine("loop count: " + periods.length);
		for (int i = 0; i < periods.length; i++) {
			tid = (i % 8) + 1;
			Timer.reset(tid);
			wt = Wait.timeM(periods[i]);
			measureTimes[i] = Timer.measure(tid);
			waitTimes[i] = wt;
		}
		StringBuffer vals = new StringBuffer();
		for (int i = 0; i < periods.length; i++) {
			vals.append("\nExpected: " + periods[i] + ", Measured: " + measureTimes[i] + ", Waited: "
					+ (waitTimes[i] / 1000F));
		}
		log.fine("Values: " + vals);

		log.fine("The End");
	}

}
