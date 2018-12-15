/**
 * LeJOS Implementation of LEGO Mindstorms Programming Blocks
 */
package lego2lejosev3.logging;

import java.io.File;
import java.net.URLDecoder;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Roland Blochberger
 */
public class Setup {

	/**
	 * configure logging to write into a file; the logger level is ALL.
	 * 
	 * @param clazz       the main class of the program.
	 * @param loggerLevel the level of the root logger.
	 */
	public static void log2File(Class<?> clazz) {
		log2File(clazz, Level.ALL);
	}

	/**
	 * configure logging to write into a file.
	 * 
	 * @param clazz       the main class of the program.
	 * @param loggerLevel the level of the root logger.
	 */
	public static void log2File(Class<?> clazz, Level loggerLevel) {
		// init java.util.logging
		Logger logger = Logger.getLogger("");
		logger.setLevel(loggerLevel);
		FileHandler fileTxt;
		try {
			for (Handler handler : logger.getHandlers()) {
				// remove existing handler
				logger.removeHandler(handler);
				// use the customized console formatter for all handlers
				// handler.setFormatter(new ConsoleFormatter());
				// handler.setLevel(Level.INFO);
				// handler.setEncoding("UTF-8");
			}
			// directory of .jar file
			String dir = getInstallDir(clazz);
			// log file name
			String fname = dir + File.separator + clazz.getSimpleName() + "%g%u.log";
			// file handler: log file name, log file size limit, log file generations, do
			// not append
			fileTxt = new FileHandler(fname, 2 * 1024 * 1024, 3, false);
			fileTxt.setFormatter(new BasicFormatter());
			fileTxt.setLevel(Level.ALL);
			fileTxt.setEncoding("UTF-8");
			logger.addHandler(fileTxt);

		} catch (Exception e) {
			System.err.println("Cannot initialize logging");
			e.printStackTrace();
		}
	}

	/**
	 * obtain the installation directory at runtime.
	 * 
	 * @param clazz the main class of the program.
	 * @return the installation directory; or null if not found.
	 * @throws Exception
	 */
	public static String getInstallDir(Class<?> clazz) throws Exception {
		String installDir = null;
		String path = clazz.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		String decodedPath = URLDecoder.decode(path, "UTF-8");
		File instDir = new File(decodedPath);
		if (instDir.getName().endsWith(".jar")) {
			// we are running from a jar file: take parent directory
			instDir = instDir.getParentFile();
		}
		// get the absolute path
		installDir = instDir.getAbsolutePath();
		return installDir;
	}
}
