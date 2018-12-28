/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

/**
 * Random Block
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FRandom.html
 */
public class Random {

	/**
	 * Generate a random number equally distributed between the specified bounds.
	 * 
	 * @param lowerBound lower bound of the random number.
	 * @param upperBound upper bound of the random number.
	 * @return a random number between the bounds.
	 */
	public static float numeric(float lowerBound, float upperBound) {
		float rnum = lowerBound;
		do {
			// generate a random number including the upper bound
			rnum = (float) (Math.random() * (upperBound + 0.01F - lowerBound) + lowerBound);

		} while ((rnum < lowerBound) || (rnum > upperBound));
		return rnum;
	}

	/**
	 * Generate an integer random number equally distributed between the specified
	 * bounds.
	 * 
	 * @param lowerBound integer lower bound of the random number.
	 * @param upperBound integer upper bound of the random number.
	 * @return an integer random number between the bounds.
	 */
	public static int numeric(int lowerBound, int upperBound) {
		int rnum = lowerBound;
		do {
			// generate a random number including the upper bound
			rnum = (int) Math.round((Math.random() * (upperBound + 0.01F - lowerBound) + lowerBound));

		} while ((rnum < lowerBound) || (rnum > upperBound));
		return rnum;
	}

	/**
	 * Generate true or false with specified probability.
	 * 
	 * @param probabilityOfTrue the probability that a True value will be returned
	 *                          (0..100)
	 * @return true or false.
	 */
	public static boolean logic(int probabilityOfTrue) {
		return ((Math.random() * 100) < probabilityOfTrue);
	}
}
