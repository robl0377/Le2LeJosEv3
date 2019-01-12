/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

/**
 * Helper to run several threads in parallel.
 * 
 * @author Roland Blochberger
 */
public class Parallel {

	/**
	 * Run 2 threads in parallel and wait until both are done.
	 * 
	 * @param thread1 thread 1
	 * @param thread2 thread 2
	 */
	public static void run(Thread thread1, Thread thread2) {
		// start both threads
		thread1.start();
		thread2.start();
		// wait until both threads are done
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// ignore
		}
	}

	/**
	 * Run several threads in parallel and wait until all are done.
	 * 
	 * @param threads array of several threads
	 */
	public static void run(Thread[] threads) {
		// start all threads
		for (Thread thread : threads) {
			thread.start();
		}
		// wait until all threads are done
		try {
			for (Thread thread : threads) {
				thread.join();
			}
		} catch (InterruptedException e) {
			// ignore
		}
	}
}
