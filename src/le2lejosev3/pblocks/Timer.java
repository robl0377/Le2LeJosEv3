/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import org.jfree.util.Log;

/**
 * Timer Block
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FTimer.html
 */
public class Timer {

	// the number of timers available (the EV3 supports 8 timers)
	private static final int count = 8;
	// the timer start values
	private static long[] startValues = new long[count];
	static {
		// initialize the start values
		long start = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			startValues[i] = start;
		}
	}

	/**
	 * measure the elapsed time.
	 * 
	 * @param timerID the timer to use; 1..8.
	 * @return the time since the last reset (or the beginning of the program) in seconds.
	 */
	public static float measure(int timerID) {
		long stop = System.currentTimeMillis();
		if (timerID > 0 && timerID <= count) {
			return (stop - startValues[timerID - 1]) / 1000F;
		} else {
			Log.warn("invalid timerID " + timerID);
			return 0F;
		}
	}

	/**
	 * Reset the timer specified by the Timer ID. The timer starts timing again
	 * immediately.
	 * 
	 * @param timerID the timer to use; 1..8.
	 */
	public static void reset(int timerID) {
		if (timerID > 0 && timerID <= count) {
			startValues[timerID - 1] = System.currentTimeMillis();

		} else {
			Log.warn("invalid timerID " + timerID);
		}
	}
}
