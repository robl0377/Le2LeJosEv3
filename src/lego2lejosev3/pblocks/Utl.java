/**
 * LeJOS Implementation of LEGO Mindstorms Programming Blocks
 */
package lego2lejosev3.pblocks;

import lejos.hardware.Button;

/**
 * Utilities and the Wait Time Block; The sensor specific Time modes are located
 * in the respective Sensor classes.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FWait.html
 */
public class Utl {

	/**
	 * Wait for a time in seconds.
	 * 
	 * @param period the waiting time in seconds.
	 */
	public static void waitTime(float period) {
		waitTimeMs(Math.round(period * 1000F), null);
	}

	/**
	 * Wait for a time in milliseconds.
	 * 
	 * @param period       the waiting time in milliseconds.
	 * @param whileWaiting a Runnable task to do once while waiting.
	 */
	public static void waitTimeMs(long period, Runnable whileWaiting) {
		// start time
		long ts = System.currentTimeMillis();
		long td = 0;
		if (whileWaiting != null) {
			whileWaiting.run();
		}
		// wait until time expired
		while (Button.ESCAPE.isUp()) {
			// calc remaining time
			td = (System.currentTimeMillis() - ts) - period;
			if (td >= 0L) {
				// time expired: leave loop
				break;
			}
			// wait until time expired
			if (td <= 3L) {
				// nearly done: just wait it out
				Thread.yield();

			} else {
				// sleep 80% of rest period
				try {
					Thread.sleep((8L * td) / 10L);
				} catch (InterruptedException e) {
					// leave loop
					break;
				}
			}
		}
	}
}
