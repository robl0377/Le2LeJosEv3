/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.Parallel;
import le2lejosev3.pblocks.Sound;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;

/**
 * Test for running several threads in parallel.
 * 
 * @author Roland Blochberger
 */
public class ParallelTest {

	private static Class<?> clazz = ParallelTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.info("Starting ...");

		// run 2 threads in parallel
		Parallel.run(new Thread1(), new Thread2());

		log.info("The End");
	}

	/**
	 * Thread to switch the LCD image every 2.5 seconds.
	 */
	static class Thread1 extends Thread {
		/**
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			String[] imgs = new String[] { "EV3", "Right", "Backward" };
			while (Button.ESCAPE.isUp()) {
				for (String img : imgs) {
					// display image
					Display.image(img, true, 0, 0);
					if (Button.ESCAPE.isDown()) {
						break;
					}
					// wait for 2.5 seconds
					Wait.time(2.5F);
				}
			}
		}
	}

	/**
	 * Thread to play 4 sound files one after the other.
	 */
	static class Thread2 extends Thread {
		/**
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			String[] sounds = new String[] { "Blue", "Green", "Red", "Yellow" };
			while (Button.ESCAPE.isUp()) {
				for (String sound : sounds) {
					Sound.playFile(sound, 100, Sound.WAIT);
					if (Button.ESCAPE.isDown()) {
						break;
					}
				}
			}
		}
	}
}
