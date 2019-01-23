/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Logger;

import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.IMotor;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;

/**
 * Reusable Motor Tests.
 * 
 * @author Roland Blochberger
 */
class MotorUtil {

	private static final Logger log = Logger.getLogger(MotorUtil.class.getName());

	/**
	 * Test a single motor.
	 * 
	 * @param motType  the type of motor; one of "Large", "Medium", or "Unreg.".
	 * @param mot      the motor instance to test
	 * @param useFloat set true for floating point values for power
	 */
	static void motorDegreesTest(String motType, IMotor mot, boolean useFloat) {
		String banner = motType + " Motor " + mot.getPortName();
		String ptyp = " (" + (useFloat ? "float" : "int") + ")";

		// reset motor rotation
		mot.rotationReset();

		int degs = 0;
		int dege = 0;
		int dege2 = 0;
		int degxs = 0;

		// -----------------------------------------------
		log.info("");
		log.info("--- OnForDegrees sngl." + ptyp);
		Display.textGrid(banner, true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("OnForDegrees sngl. ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);

		degxs = 75;
		// run motor backward for degxs degrees and brake afterward
		degs = mot.measureDegrees();
		if (useFloat) {
			mot.motorOnForDegrees(-65.0F, degxs, true);
		} else {
			mot.motorOnForDegrees(-65, degxs, true);
		}
		dege = mot.measureDegrees();
		// run motor forward to middle position
		if (useFloat) {
			mot.motorOnForDegrees(65.0F, degxs, true);
		} else {
			mot.motorOnForDegrees(65, degxs, true);
		}
		dege2 = mot.measureDegrees();
		log.info("Motor started at " + degs + " degr.");
		log.info("Motor stopped at " + dege + " degr.");
		log.info("Motor stopped at " + dege2 + " degr.");
		log.info(" Expected Rotation: back and fore " + degxs + " degr. each");

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		log.info("");
		log.info("--- OnForDegrees mult." + ptyp);
		Display.textGrid(banner, true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("OnForDegrees mult. ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// run motor forward and backward several times and brake afterward
		int[] dgx = new int[] { 15, 34, 49, 67, 130, 92 };
		int[] pwr = new int[] { +65, -65, +65, -65, +65, -65 };
		int[] dgs = new int[dgx.length];
		int[] dge = new int[dgx.length];
		for (int i = 0; i < dgx.length; i++) {
			dgs[i] = mot.measureDegrees();
			if (useFloat) {
				mot.motorOnForDegrees((float) pwr[i], dgx[i], true);
			} else {
				mot.motorOnForDegrees(pwr[i], dgx[i], true);
			}
			dge[i] = mot.measureDegrees();
		}
		log.info("Motor started at " + dgs[0] + " degr.");
		degxs = 0;
		for (int i = 0; i < dgx.length; i++) {
			log.info("Motor stopped at " + dge[i] + " degr.");
			log.info(" Actual Rotation   : " + Math.abs(dge[i] - dgs[i]) + " degr.");
			log.info(" Expected Rotation : " + (dgx[i]) + " degr.");
			degxs += dgx[i] * (pwr[i] > 0 ? +1 : -1);
		}
		log.info(" Exp Total Rotation: " + degxs + " degr.");

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		log.info("");
		log.info("--- OnForSeconds" + ptyp);
		Display.textGrid(banner, true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("OnForSeconds ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);

		degxs = 0;
		// run motor backward for 0.2 seconds and brake afterward
		log.info("Motor start");
		degs = mot.measureDegrees();
		if (useFloat) {
			mot.motorOnForSeconds(-40.0F, 0.2F, true);
		} else {
			mot.motorOnForSeconds(-40, 0.2F, true);
		}
		dege = mot.measureDegrees();
		// run motor forward for 0.2 seconds and brake afterward
		if (useFloat) {
			mot.motorOnForSeconds(40.0F, 0.2F, true);
		} else {
			mot.motorOnForSeconds(40, 0.2F, true);
		}
		dege2 = mot.measureDegrees();
		log.info("Motor stop");
		log.info("Motor started at " + degs + " degr.");
		log.info("Motor stopped at " + dege + " degr.");
		log.info("Motor stopped at " + dege2 + " degr.");
		log.info(" Total Rotation   : " + (dege2 - degs) + " degr.");
		log.info(" Expected Rotation: " + degxs + " degr.");

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();
	}

	/**
	 * Test a single motor.
	 * 
	 * @param motType  the type of motor; one of "Large", "Medium", or "Unreg.".
	 * @param mot      the motor instance to test
	 * @param useFloat set true for floating point values for power
	 */
	static void motorTest(String motType, IMotor mot, boolean useFloat) {
		String banner = motType + " Motor " + mot.getPortName();
		String ptyp = " (" + (useFloat ? "float" : "int") + ")";

		// -----------------------------------------------
		log.info("");
		log.info("--- OnForSeconds" + ptyp);
		Display.textGrid(banner, true, 0, 1, Display.COLOR_BLACK, Display.FONT_NORMAL);
		Display.textGrid("OnForSeconds ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// run motor backward for 0.9 seconds and brake afterward
		log.fine("Motor start");
		if (useFloat) {
			mot.motorOnForSeconds(-75.0F, 0.9F, true);
		} else {
			mot.motorOnForSeconds(-75, 0.9F, true);
		}
		log.fine("Motor stop");

		// Wait 0.9 seconds
		Wait.time(0.9F);

		// run motor forward for 0.9 seconds and brake afterward
		log.fine("Motor start");
		if (useFloat) {
			mot.motorOnForSeconds(75.0F, 0.9F, true);
		} else {
			mot.motorOnForSeconds(75, 0.9F, true);
		}
		log.fine("Motor stop");

		Display.textGrid("Press Button", false, 0, 6, Display.COLOR_BLACK, Display.FONT_NORMAL);
		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		log.info("");
		log.info("--- OnForDegrees" + ptyp);
		Display.textGrid("OnForDegrees ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// run motor (backward) for -180 degrees and brake afterward
		int degs = mot.measureDegrees();
		int dege = 0;
		log.fine("Motor started at " + degs + "degr.");
		if (useFloat) {
			mot.motorOnForDegrees(75.0F, -180, true);
		} else {
			mot.motorOnForDegrees(75, -180, true);
		}
		dege = mot.measureDegrees();
		log.fine("Motor stopped at " + dege + "degr.: Rotated: " + (dege - degs) + "degr.");

		// Wait 0.9 seconds
		Wait.time(0.9F);

		// run motor forward for 180 degrees and brake afterward
		degs = mot.measureDegrees();
		log.fine("Motor started at " + degs + "degr.");
		if (useFloat) {
			mot.motorOnForDegrees(75.0F, 180, true);
		} else {
			mot.motorOnForDegrees(75, 180, true);
		}
		dege = mot.measureDegrees();
		log.fine("Motor stopped at " + dege + "degr.: Rotated: " + (dege - degs) + "degr.");

		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		log.info("");
		log.info("--- OnForRotationsDegrees" + ptyp);
		Display.textGrid("OnForRotationsDegrees ", false, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// run motor backward for 1.5 rotations and brake afterward
		degs = mot.measureDegrees();
		dege = 0;
		log.fine("Motor started at " + degs + "degr.");
		if (useFloat) {
			mot.motorOnForRotationsDegrees(-75.0F, 1, 180, true);
		} else {
			mot.motorOnForRotationsDegrees(-75, 1, 180, true);
		}
		dege = mot.measureDegrees();
		log.fine("Motor stopped at " + dege + "degr.: Rotated: " + (dege - degs) + "degr.");

		// Wait 0.9 seconds
		Wait.time(0.9F);

		// run motor forward for 1.5 rotations and brake afterward
		degs = mot.measureDegrees();
		log.fine("Motor started at " + degs + "degr.");
		if (useFloat) {
			mot.motorOnForRotationsDegrees(75.0F, 1, 180, true);
		} else {
			mot.motorOnForRotationsDegrees(75, 1, 180, true);
		}
		dege = mot.measureDegrees();
		log.fine("Motor stopped at " + dege + "degr.: Rotated: " + (dege - degs) + "degr.");

		// Wait until button press
		Button.waitForAnyPress();
	}
}
