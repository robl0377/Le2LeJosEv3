/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import lejos.hardware.Button;
import lejos.hardware.Keys;

/**
 * Brick Buttons Block
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FBrickButton.html
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=editor%2FUsingSensors_BrickButtons.html
 */
public class BrickButtons {
	
	/** the button ids */
	public static final int BB_NONE = 0;
	public static final int BB_LEFT = 1;
	public static final int BB_CENTER = 2;
	public static final int BB_RIGHT = 3;
	public static final int BB_UP = 4;
	public static final int BB_DOWN =5;

	/**
	 * Return which brick button is pressed.
	 * 
	 * @return the LEGO brick button id, one of BB_NONE .. BB_DOWN
	 */
	public static int measure() {
		// get buttons and debounce them, don't play any keyclick sound
		int bb = Button.keys.getButtons();
		if ((bb & Keys.ID_UP) > 0) {
			return BB_UP;
		}
		if ((bb & Keys.ID_ENTER) > 0) {
			return BB_CENTER;
		}
		if ((bb & Keys.ID_DOWN) > 0) {
			return BB_DOWN;
		}
		if ((bb & Keys.ID_RIGHT) > 0) {
			return BB_RIGHT;
		}
		if ((bb & Keys.ID_LEFT) > 0) {
			return BB_LEFT;
		}
		return BB_NONE;
	}
}
