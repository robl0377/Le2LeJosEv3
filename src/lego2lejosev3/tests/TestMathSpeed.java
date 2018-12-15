/**
 * 
 */
package lego2lejosev3.tests;

import java.util.logging.Logger;

import lego2lejosev3.logging.Setup;

/**
 * Test the float and double arithmetic speed.
 * 
 * @author Roland Blochberger
 */
public class TestMathSpeed {

	private static Class<?> clazz = TestMathSpeed.class;
	private static final Logger log = Logger.getLogger(clazz.getName());
	
	// number of values to use
	private static final int numbersCount = 15000;
	// range of numbers to use
	private static final double numberRange = 10000.0;

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file
		Setup.log2File(clazz);
		log.fine("Starting ...");

		

		// generate some random double numbers in an array
		double[] dArry = new double[numbersCount];
		for (int i = 0; i < dArry.length; i++) {
			dArry[i] = Math.random() * numberRange * (((i & 0x1) == 0) ? +1.0 : -1.0);
		}
		// convert the numbers to float
		float[] fArry = new float[numbersCount];
		for (int i = 0; i < fArry.length; i++) {
			fArry[i] = (float) dArry[i];
		}
		long ts, td, tf;
		// start timer
		ts = System.currentTimeMillis();
		// run double calculations
		dCalc(dArry);
		// store time
		td = System.currentTimeMillis();
		// run float calculations
		fCalc(fArry);
		// store time
		tf = System.currentTimeMillis();

		// write the result
		log.info("double: " + (td - ts) + "ms");
		log.info("float : " + (tf - td) + "ms");

		log.fine("The End");
	}

	/**
	 * Run double calculations.
	 * 
	 * @param dArry the data array.
	 * @return the result.
	 */
	private static double dCalc(double[] dArry) {
		double val = 0.0;
		double add = 0.0;
		double sub = 0.0;
		double mul = 0.0;
		double div = 0.0;
		for (int i = 0; i < dArry.length; i++) {
			val = dArry[i];
			// add all
			add += val;
			// subtract all
			sub -= val;
			// multiply all
			mul *= val;
			// divide all
			div /= val;
		}
		return add + sub + mul + div;
	}

	/**
	 * Run float calculations.
	 * 
	 * @param fArry the data array.
	 * @return the result.
	 */
	private static float fCalc(float[] fArry) {
		float val = 0.0f;
		float add = 0.0f;
		float sub = 0.0f;
		float mul = 0.0f;
		float div = 0.0f;
		for (int i = 0; i < fArry.length; i++) {
			val = fArry[i];
			// add all
			add += val;
			// subtract all
			sub -= val;
			// multiply all
			mul *= val;
			// divide all
			div /= val;
		}
		return add + sub + mul + div;
	}

}
