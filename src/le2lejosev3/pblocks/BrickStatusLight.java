/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

//import java.util.logging.Level;
//import java.util.logging.Logger;

import lejos.hardware.LED;
import lejos.hardware.ev3.LocalEV3;

/**
 * Brick Status Light Block
 * 
 * @author Roland Blochvberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FLED.html
 */
public class BrickStatusLight {

	// private static final Logger log =
	// Logger.getLogger(BrickStatusLight.class.getName());

	/** the brick status light colors */
	public static final int COLOR_GREEN = 0;
	public static final int COLOR_ORANGE = 1;
	public static final int COLOR_RED = 2;

	/** the brick status light pulse status */
	public static final boolean CONSTANT = false;
	public static final boolean PULSE = true;

	// the LED instance
	private static final LED led = LocalEV3.get().getLED();
	static {
		if (led != null) {
			// handle resources correctly before exiting
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					// turn the light and any blinking off
					// if (log.isLoggable(Level.FINEST)) {
					// log.finest("off");
					// }
					led.setPattern(0);
				}
			}));
		}
	}

	/**
	 * Turns the Brick Status Light on.
	 * 
	 * @param color the color of the light, one of COLOR_GREEN, COLOR_ORANGE,
	 *              COLOR_RED.
	 * @param pulse set true to let the light pulse on and off in a repeating
	 *              pattern; false to let it stay on constantly.
	 */
	public static void on(int color, boolean pulse) {
		led.setPattern(calcPattern(color, pulse));
	}

	/**
	 * Turn the Brick Status Light off.
	 */
	public static void off() {
		// if (log.isLoggable(Level.FINEST)) {
		// log.fine("off");
		// }
		led.setPattern(0);
	}

	/**
	 * Set the Brick Status Light to the standard green blinking pattern that
	 * indicates a program is running on the EV3 Brick.
	 */
	public static void reset() {
		on(COLOR_GREEN, PULSE);
	}

	/**
	 * calculate the color and pulse to the EV3 LED pattern.
	 * 
	 * @param color the color of the light, one of COLOR_GREEN, COLOR_ORANGE,
	 *              COLOR_RED.
	 * @param pulse set true to let the light pulse on and off in a repeating
	 *              pattern; false to let it stay on constantly.
	 * @return the pattern
	 */
	private static int calcPattern(int color, boolean pulse) {
		int pattern = 0;
		switch (color) {
		case COLOR_GREEN:
			pattern = 1;
			break;
		case COLOR_ORANGE:
			pattern = 3;
			break;
		case COLOR_RED:
			pattern = 2;
			break;
		}
		if (pulse) {
			pattern += 3; // 3 * EV3LED.PATTERN_BLINK
			// pattern += 6; // 3 * EV3LED.PATTERN_HEARTBEAT
		}
		// if (log.isLoggable(Level.FINEST)) {
		// log.finest("on pattern: " + Integer.toBinaryString(pattern));
		// }
		return pattern;
	}

}
