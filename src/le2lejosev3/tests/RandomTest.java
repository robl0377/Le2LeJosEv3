/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Random;

/**
 * Test for the random block.
 * 
 * @author Roland Blochberger
 */
public class RandomTest {

	private static Class<?> clazz = RandomTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// random float number test
		int count = 100;
		log.fine("loop count: " + count);
		log.fine("");

		float flow = 0.1F;
		float fhigh = 5.4F;
		float[] fnumbers = new float[count];
		log.fine("start float numeric loop (" + flow + ".." + fhigh + ")");
		for (int i = 0; i < count; i++) {
			// generate random number between the bounds
			fnumbers[i] = Random.numeric(flow, fhigh);
		}
		log.fine("end float numeric loop");
		float avgn = 0;
		StringBuffer nvals = new StringBuffer();
		for (int i = 0; i < count; i++) {
			avgn += fnumbers[i];
			if (i > 0) {
				nvals.append(", ");
			}
			nvals.append(fnumbers[i]);
		}
		avgn /= count;
		// calculate the averages
		log.fine("float numeric: random average: " + avgn + ", linear average: " + (((fhigh - flow) / 2F) + flow));
		log.finest("float numeric values: " + nvals);
		log.fine("");

		// random int number test
		int ilow = 1;
		int ihigh = 3;
		int[] numbers = new int[count];
		log.fine("start int numeric loop (" + ilow + ".." + ihigh + ")");
		for (int i = 0; i < count; i++) {
			// generate random number between the bounds
			numbers[i] = Random.numeric(ilow, ihigh);
		}
		log.fine("end int numeric loop");
		avgn = 0;
		nvals = new StringBuffer();
		for (int i = 0; i < count; i++) {
			avgn += numbers[i];
			if (i > 0) {
				nvals.append(", ");
			}
			nvals.append(numbers[i]);
		}
		avgn /= count;
		// calculate the averages
		log.fine("int numeric: random average: " + avgn + ", linear average: " + (((ihigh - ilow) / 2F) + ilow));
		log.finest("int numeric values: " + nvals);
		log.fine("");

		// logic test
		int prob = 75;
		boolean[] bools = new boolean[count];
		log.fine("start logic loop, specified probability for True: " + prob);
		for (int i = 0; i < count; i++) {
			// generate random number between the bounds
			bools[i] = Random.logic(prob);
		}
		log.fine("end logic loop");
		int numTrue = 0;
		int numFalse = 0;
		StringBuffer lvals = new StringBuffer();
		for (int i = 0; i < count; i++) {
			if (bools[i]) {
				numTrue++;
			} else {
				numFalse++;
			}
			if (i > 0) {
				lvals.append(", ");
			}
			lvals.append(bools[i]);
		}
		// calculate the averages
		log.fine("logic: # of Trues: " + numTrue + ", # of Falses: " + numFalse);
		log.finest("logic values: " + lvals);

		log.fine("The End");
	}

}
