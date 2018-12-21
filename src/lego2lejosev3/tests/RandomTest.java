/**
 * 
 */
package lego2lejosev3.tests;

import java.util.logging.Logger;

import lego2lejosev3.logging.Setup;
import lego2lejosev3.pblocks.Random;

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
		// setup logging to file
		Setup.log2File(clazz);
		log.fine("Starting ...");

		// random number test
		int count = 100;
		log.fine("loop count: " + count);
		float low = 0.1F;
		float high = 5.4F;
		float[] numbers = new float[count];
		log.fine("start numeric loop");
		for (int i = 0; i < count; i++) {
			// generate random number between the bounds
			numbers[i] = Random.numeric(low, high);
		}
		log.fine("end numeric loop");
		float avgn = 0;
		StringBuffer nvals = new StringBuffer();
		for (int i = 0; i < count; i++) {
			avgn += numbers[i];
			if (i > 0) {
				nvals.append(", ");
			}
			nvals.append(numbers[i]);
		}
		avgn /= count;
		// calculate the averages
		log.fine("numeric: random average: " + avgn + ", linear average: " + ((high - low) / 2F));
		log.finest("numeric values: " + nvals);

		// logic test
		int prob = 75;
		boolean[] bools = new boolean[count];
		log.fine("start logic loop");
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
		log.fine("logic: # of Trues: " + numTrue + ", # of Falses: " + numFalse + ", specified probability for True: " + prob);
		log.finest("numeric values: " + lvals);

		log.fine("The End");
	}

}
