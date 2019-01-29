/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;

/**
 * Test the float and double arithmetic speed.
 * 
 * @author Roland Blochberger
 */
public class MathSpeedTest {

	private static Class<?> clazz = MathSpeedTest.class;
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
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
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
		// convert the numbers to long
		long[] lArry = new long[numbersCount];
		for (int i = 0; i < lArry.length; i++) {
			lArry[i] = Math.round(dArry[i]);
		}
		// convert the numbers to int
		int[] iArry = new int[numbersCount];
		for (int i = 0; i < iArry.length; i++) {
			iArry[i] = (int)(lArry[i]);
		}

		long ts, td, tf, tl, ti;
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
		// run long calculations
		lCalc(lArry);
		// store time
		tl = System.currentTimeMillis();
		// run int calculations
		iCalc(iArry);
		// store time
		ti = System.currentTimeMillis();

		// write the result
		log.info("double: " + (td - ts) + "ms");
		log.info("float : " + (tf - td) + "ms");
		log.info("long  : " + (tl - tf) + "ms");
		log.info("int   : " + (ti - tl) + "ms");

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

	/**
	 * Run long calculations.
	 * 
	 * @param lArry the data array.
	 * @return the result.
	 */
	private static long lCalc(long[] lArry) {
		long val = 0L;
		long add = 0L;
		long sub = 0L;
		long mul = 0L;
		long div = 0L;
		for (int i = 0; i < lArry.length; i++) {
			val = lArry[i];
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
	 * Run int calculations.
	 * 
	 * @param iArry the data array.
	 * @return the result.
	 */
	private static int iCalc(int[] iArry) {
		int val = 0;
		int add = 0;
		int sub = 0;
		int mul = 0;
		int div = 0;
		for (int i = 0; i < iArry.length; i++) {
			val = iArry[i];
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
