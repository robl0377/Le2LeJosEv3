/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.BrickButtons;
import le2lejosev3.pblocks.Sound;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;

/**
 * Test for interrupting one thread by another one.
 * 
 * @author Roland Blochberger
 */
public class ThreadInterruptTest {

	private static Class<?> clazz = ThreadInterruptTest.class;
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

		// create and start the Stopper thread
		Thread stopper = new Stopper(Thread.currentThread());
		stopper.start();

		String[] sounds = new String[] { "Blue", "Green", "Red", "Yellow" };
		// MAIN Loop
		while (!Thread.currentThread().isInterrupted() && Button.ESCAPE.isUp()) {
			for (String sound : sounds) {
				Sound.playFile(sound, 100, Sound.WAIT);
				if (Button.ESCAPE.isDown()) {
					break;
				}
			}
		}
		log.info("The End");
	}
}

/**
 * Thread to interrupt the main loop if the center brick button was pressed.
 */
class Stopper extends Thread {
	private static final Logger log = Logger.getLogger(Stopper.class.getName());

	private Thread threadToInterrupt;

	/**
	 * Constructor.
	 * 
	 * @param threadToInterrupt the thread to interrupt.
	 */
	public Stopper(Thread threadToInterrupt) {
		this.threadToInterrupt = threadToInterrupt;
		setDaemon(true);
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// Wait until center brick button is pressed
		while (Button.ESCAPE.isUp()) {
			// Wait 20ms
			Wait.time(0.02F);
			// Check if center brick button is pressed
			if (BrickButtons.measure() == BrickButtons.BB_CENTER) {
				break;
			}
		}

		// interrupt the main loop
		threadToInterrupt.interrupt();
		log.info("*** Interrupt Main Loop");
	}
}
