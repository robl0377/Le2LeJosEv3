/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;

/**
 * Test for the logging support.
 * 
 * @author Roland Blochberger
 */
public class LoggingTest {

	private static Class<?> clazz = LoggingTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	// all logging levels sorted by level value descending
	private static final Level[] allLevels = new Level[] { Level.SEVERE, Level.WARNING, Level.INFO, Level.CONFIG,
			Level.FINE, Level.FINER, Level.FINEST };

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// setup logging to file with root logger Level.FINE
		Setup.log2File(clazz);
		log.info("Starting ...");

		for (Level rtlvl : allLevels) {
			// set new root logger level
			Setup.setRootLoggerLevel(rtlvl);
			log.log(rtlvl, "Root Logger Level: " + Setup.getRootLoggerLevel());

			// write log messages
			for (Level lvl : allLevels) {
				log.log(lvl, "Log Message on level {0}.", lvl.getName() );
			}
		}

		log.info("The End");
	}

}
