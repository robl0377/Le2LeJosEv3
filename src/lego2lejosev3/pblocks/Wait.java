/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package lego2lejosev3.pblocks;

import lejos.utility.Delay;

/**
 * Wait Time Block.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FWait.html
 */
public class Wait {

	/**
	 * Wait for a time in seconds.
	 * 
	 * @param period the waiting time in seconds.
	 */
	public static void time(float period) {
		Delay.msDelay(Math.round(period * 1000F));
	}

	/**
	 * Wait for a time in seconds and measure it.
	 * 
	 * @param period the waiting time in seconds.
	 * @return the number of milliseconds actually waited.
	 */
	public static long timeM(float period) {
		// start time
		long ts = System.currentTimeMillis();
		Delay.msDelay(Math.round(period * 1000F));
		// return elapsed time
		return System.currentTimeMillis() - ts;
	}

}
