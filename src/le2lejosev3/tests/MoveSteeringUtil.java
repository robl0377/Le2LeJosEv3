/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Logger;

import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.IMoveSteering;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;

/**
 * Reusable MoveSteering Tests.
 * 
 * @author Roland Blochberger
 */
public class MoveSteeringUtil {

	private static final Logger log = Logger.getLogger(MoveSteeringUtil.class.getName());

	/**
	 * MoveSteering test for motors that can turn about 270 degrees.
	 * 
	 * @param mst the MoveSteering instance
	 */
	static void steeringDegreesTest(IMoveSteering mst) {
		// reset motor rotation
		mst.rotationResetLeft();
		mst.rotationResetRight();

		int degls = 0;
		int degle = 0;
		int degle2 = 0;
		int degrs = 0;
		int degre = 0;
		int degre2 = 0;
		int degxs = 0;

		// -----------------------------------------------
		log.info("--- OnForDegrees sngl.");
		Display.textGrid("Move Steering", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("OnForDegrees sngl. ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);

		degxs = 75;
		// run motor backward for degxs degrees and brake afterward
		degls = mst.measureDegreesLeft();
		degrs = mst.measureDegreesRight();
		mst.motorsOnForDegrees(0, -65, degxs, true);
		degle = mst.measureDegreesLeft();
		degre = mst.measureDegreesRight();
		// run motor forward to middle position
		mst.motorsOnForDegrees(0, 65, degxs, true);
		degle2 = mst.measureDegreesLeft();
		degre2 = mst.measureDegreesRight();
		log.info("Motors started at " + degls + " degr., " + degrs + " degr.");
		log.info("Motors stopped at " + degle + " degr., " + degre + " degr.");
		log.info("Motors stopped at " + degle2 + " degr., " + degre2 + " degr.");
		log.info(" Expected Rotation: back and fore " + degxs + " degr. each");

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		log.info("");
		log.info("--- OnForDegrees mult.");
		Display.textGrid("Move Steering", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("OnForDegrees mult. ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// run motor forward and backward several times and brake afterward
		int[] dgx = new int[] {  15,  34,  49,  67, 130,  92 };
		int[] pwr = new int[] { +65, -65, +65, -65, +65, -65 };
		int[] dgls = new int[dgx.length];
		int[] dgle = new int[dgx.length];
		int[] dgrs = new int[dgx.length];
		int[] dgre = new int[dgx.length];
		for (int i = 0; i < dgx.length; i++) {
			dgls[i] = mst.measureDegreesLeft();
			dgrs[i] = mst.measureDegreesRight();
			mst.motorsOnForDegrees(0, pwr[i], dgx[i], true);
			dgle[i] = mst.measureDegreesLeft();
			dgre[i] = mst.measureDegreesRight();
		}
		log.info("Motor started at " + dgls[0] + " degr., " + dgrs[0] + " degr.");
		degxs = 0;
		for (int i = 0; i < dgx.length; i++) {
			log.info("Motor stopped at " + dgle[i] + " degr, " + dgre[i] + " degr.");
			log.info(" Actual Rotation   : " + Math.abs(dgle[i] - dgls[i]) + " degr., " + Math.abs(dgre[i] - dgrs[i]) + " degr.");
			log.info(" Expected Rotation : " + (dgx[i]) + " degr.");
			degxs += dgx[i] * (pwr[i] >= 0 ? +1 : -1);
		}
		log.info(    " Exp Total Rotation: " + degxs + " degr.");

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		log.info("");
		log.info("--- OnForSeconds");
		Display.textGrid("Move Steering", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("OnForSeconds ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);

		degxs = 0;
		// run motors backward for 0.2 seconds and brake afterward
		log.info("Motors start");
		degls = mst.measureDegreesLeft();
		degrs = mst.measureDegreesRight();
		mst.motorsOnForSeconds(0, -40, 0.2F, true);
		degle = mst.measureDegreesLeft();
		degre = mst.measureDegreesRight();
		// run motors forward for 0.2 seconds and brake afterward
		mst.motorsOnForSeconds(0, 40, 0.2F, true);
		degle2 = mst.measureDegreesLeft();
		degre2 = mst.measureDegreesRight();
		log.info("Motors stop");
		log.info("Motors started at " + degls + " degr., " + degrs + " degr.");
		log.info("Motors stopped at " + degle + " degr., " + degre + " degr.");
		log.info("Motors stopped at " + degle2 + " degr., " + degre2 + " degr.");
		log.info(" Total Rotation   : " + (degle2 - degls) + " degr., " + (degre2 - degrs) + " degr.");
		log.info(" Expected Rotation: " + degxs + " degr., " + degxs + " degr.");

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();
	}

	/**
	 * MoveSteering test for free running motors.
	 * 
	 * @param mst the MoveSteering instance
	 */
	static void steeringTest(IMoveSteering mst) {
		// -----------------------------------------------
		log.info("--- OnForSeconds");
		Display.textGrid("Move Steering", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("OnForSeconds ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Run left & right motors forward for 2.5 seconds then brake
		log.fine("OnFor 2.5 seconds");
		mst.motorsOnForSeconds(0, 75, 2.5F, true);
		log.fine("done");
		// reset rotation
		mst.rotationResetLeft();
		mst.rotationResetRight();

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		log.info("--- OnForRotations");
		Display.textGrid("Move Steering", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("OnForRotations ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Run left & right motors left for 3 rotations then brake
		log.fine("OnFor 3 rotations");
		mst.motorsOnForRotations(-25, 75, 3, true);
		log.fine("done");
		// measure rotations
		log.fine("Rotations left : " + mst.measureRotationsLeft());
		log.fine("Rotations right: " + mst.measureRotationsRight());

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		log.info("--- OnForDegrees");
		Display.textGrid("Move Steering", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("OnForDegrees ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Run left & right motors right on the spot for 180 degrees then don't brake
		log.fine("OnFor 180 degrees");
		mst.motorsOnForDegrees(100, 75, 180, false);
		log.fine("done");
		// measure total degrees
		log.fine("Degrees left : " + mst.measureDegreesLeft());
		log.fine("Degrees right: " + mst.measureDegreesRight());

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		log.info("--- On");
		Display.textGrid("Move Steering", true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("On 2 sec.", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Run left & right motors left
		log.fine("On");
		mst.motorsOn(-50, 75);
		log.fine("done");
		// measure power levels
		log.fine("Power left : " + mst.measureCurrentPowerLeft());
		log.fine("Power right: " + mst.measureCurrentPowerRight());

		// Wait 2 seconds
		Wait.time(2F);
		// stop motors without brake
		log.fine("Off");
		mst.motorsOff(false);
		log.fine("done");

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();
	}
}
