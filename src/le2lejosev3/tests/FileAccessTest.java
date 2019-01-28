/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.FileAccess;
import le2lejosev3.pblocks.Random;
import lejos.hardware.Button;

/**
 * Test for the file access.
 * 
 * @author Roland Blochberger
 */
public class FileAccessTest {

	private static Class<?> clazz = FileAccessTest.class;
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

		String[] filenames = new String[] { "PRG0", "PRG1", "PRG2", "PRG3", "PRG4", "PRG5" };

		// be sure the files do not exist at the beginning
		for (String filename : filenames) {
			FileAccess.delete(filename);
		}

		// write file test with non-existing file
		writeFile(filenames[0], false);
		// write file test with existing file
		writeFile(filenames[0], true);

		// write file, it will be closed at the end of the program
		writeFile(filenames[1], false);
		// write file, it will be closed at the end of the program
		writeReadFileInt(filenames[3], true);
		// write file, it will be closed at the end of the program
		writeReadFileNumeric(filenames[5], false);

		// read a file and write to another file
		copyFile(filenames[0], filenames[2], true);
		copyFile(filenames[3], filenames[4], false);

		log.fine("The End");
	}

	/**
	 * write text to a file.
	 * 
	 * @param filename
	 */
	private static void writeFile(String filename, boolean close) {
		long stim;
		// 1. delete the file if it already exists
		stim = System.currentTimeMillis();
		FileAccess.delete(filename);
		stim = System.currentTimeMillis() - stim;
		log.log(Level.FINE, "delete: {0}ms", stim);

		// 2. write lines
		String text;
		for (int i = 0; i < 50; i++) {
			// write a text
			text = String.format("Line %1$2d", i);
			stim = System.currentTimeMillis();
			FileAccess.write(filename, text);
			stim = System.currentTimeMillis() - stim;
			log.log(Level.FINE, "write \"{0}\": {1}ms", new Object[] { text, stim });
		}

		if (close) {
			// 3. close the file
			FileAccess.close(filename);
		}
	}

	/**
	 * write integer data to a file and read them afterwards.
	 * 
	 * @param filename
	 */
	private static void writeReadFileInt(String filename, boolean close) {
		long stim;
		// 1. delete the file if it already exists
		stim = System.currentTimeMillis();
		FileAccess.delete(filename);
		stim = System.currentTimeMillis() - stim;
		log.log(Level.FINE, "delete: {0}ms", stim);

		// 2. write numbers
		int num;
		int[] nums = new int[50];
		float avg = 0;
		for (int i = 0; i < 50; i++) {
			// write a number
			num = Random.numeric(1, 9);
			nums[i] = num;
			avg += num;
			stim = System.currentTimeMillis();
			FileAccess.write(filename, num);
			stim = System.currentTimeMillis() - stim;
			log.log(Level.FINE, "write \"{0}\": {1}ms", new Object[] { num, stim });
		}
		log.log(Level.FINE, "Average number written: {0}", (avg / 50));
		// 3. close the file
		FileAccess.close(filename);

		// read and check the numbers
		String chk;
		for (int i = 0; Button.ENTER.isUp() && (i < 55); i++) {
			stim = System.currentTimeMillis();
			num = FileAccess.readInt(filename);
			stim = System.currentTimeMillis() - stim;
			if (num == 0F) {
				log.log(Level.FINE, "read eof: {0}ms", stim);
				break;
			}
			chk = (num == nums[i] ? "OK" : ("" + num + "!=" + nums[i]));
			log.log(Level.FINE, "read \"{0}\", {2}: {1}ms", new Object[] { num, stim, chk });
		}

		if (close) {
			// 3. close the file
			FileAccess.close(filename);
		}
	}

	/**
	 * write float data to a file and read them afterwards.
	 * 
	 * @param filename
	 */
	private static void writeReadFileNumeric(String filename, boolean close) {
		long stim;
		// 1. delete the file if it already exists
		stim = System.currentTimeMillis();
		FileAccess.delete(filename);
		stim = System.currentTimeMillis() - stim;
		log.log(Level.FINE, "delete: {0}ms", stim);

		// 2. write numbers
		float num;
		float[] nums = new float[50];
		float avg = 0;
		for (int i = 0; i < 50; i++) {
			// write a number
			num = Random.numeric(0.5F, 7.5F);
			nums[i] = num;
			avg += num;
			stim = System.currentTimeMillis();
			FileAccess.write(filename, num);
			stim = System.currentTimeMillis() - stim;
			log.log(Level.FINE, "write \"{0}\": {1}ms", new Object[] { num, stim });
		}
		log.log(Level.FINE, "Average number written: {0}", (avg / 50));
		// 3. close the file
		FileAccess.close(filename);

		// read and check the numbers
		String chk;
		for (int i = 0; Button.ENTER.isUp() && (i < 55); i++) {
			stim = System.currentTimeMillis();
			num = FileAccess.readNumeric(filename);
			stim = System.currentTimeMillis() - stim;
			if (num == 0F) {
				log.log(Level.FINE, "read eof: {0}ms", stim);
				break;
			}
			chk = (num == nums[i] ? "OK" : ("" + num + "!=" + nums[i]));
			log.log(Level.FINE, "read \"{0}\", {2}: {1}ms", new Object[] { num, stim, chk });
		}

		if (close) {
			// 3. close the file
			FileAccess.close(filename);
		}
	}

	/**
	 * copy the contents of readFilename into writefilename.
	 * 
	 * @param readFilename
	 * @param writefilename
	 */
	private static void copyFile(String readFilename, String writefilename, boolean close) {
		long stim;

		// 1. delete the write file if it already exists
		stim = System.currentTimeMillis();
		FileAccess.delete(writefilename);
		stim = System.currentTimeMillis() - stim;
		log.log(Level.FINE, "delete: {0}ms", stim);

		String text = null;
		int i = 0;
		while (Button.ENTER.isUp()) {
			// 2. read a line from text file
			stim = System.currentTimeMillis();
			text = FileAccess.read(readFilename);
			stim = System.currentTimeMillis() - stim;
			log.log(Level.FINE, "read \"{0}\": {1}ms", new Object[] { text, stim });
			i++;
			if ((text == null) || text.isEmpty() || (i > 55)) {
				break;
			}
			// 3. write line to text file
			stim = System.currentTimeMillis();
			FileAccess.write(writefilename, text);
			stim = System.currentTimeMillis() - stim;
			log.log(Level.FINE, "write \"{0}\": {1}ms", new Object[] { text, stim });
		}
		if (close) {
			// 4. close both files
			FileAccess.close(readFilename);
			FileAccess.close(writefilename);
		}
	}

}
