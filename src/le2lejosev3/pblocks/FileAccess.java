/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FileAccess Block.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FFileAccess.html
 */
public class FileAccess {

	private static final Logger log = Logger.getLogger(FileAccess.class.getName());

	/** the base directory for the text files on the EV3 brick. */
	public static final String FILES_DIR = "/home/lejos/programs";
	/** the default extension for the text files */
	public static final String DEFAULT_EXT = "txt";

	// the map of open file descriptions
	private static Hashtable<String, FileDesc> openfiles = new Hashtable<String, FileDesc>();
	static {
		// handle resources correctly before exiting
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				// close any open files at program end
				Iterator<?> ofIterator = openfiles.entrySet().iterator();
				while (ofIterator.hasNext()) {
					@SuppressWarnings("unchecked")
					Map.Entry<String, FileDesc> ofElement = (Map.Entry<String, FileDesc>) ofIterator.next();
					if (doClose((FileDesc) ofElement.getValue())) {
						ofIterator.remove();
					}
				}
			}
		}));
	}

	/**
	 * Read from the file with the specified name.
	 * 
	 * @param filename the file name only; you can omit the DEFAULT_EXT.
	 * @return the contents of the next line to read as a number; or 0 if end of
	 *         file.
	 */
	public static int readInt(String filename) {
		String text = read(filename);
		if ((text == null) || text.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(text);
	}

	/**
	 * Read from the file with the specified name.
	 * 
	 * @param filename the file name only; you can omit the DEFAULT_EXT.
	 * @return the contents of the next line to read as a number; or 0 if end of
	 *         file.
	 */
	public static float readNumeric(String filename) {
		String text = read(filename);
		if ((text == null) || text.isEmpty()) {
			return 0F;
		}
		return Float.parseFloat(text);
	}

	/**
	 * Read from the file with the specified name.
	 * 
	 * @param filename the file name only; you can omit the DEFAULT_EXT.
	 * @return the contents of the next line to read as a text string; or null if
	 *         end of file.
	 */
	public static String read(String filename) {
		String text = null;
		// get associated open file data if they exist
		FileDesc fd = openfiles.get(filename);
		if ((fd == null) || (fd.fr == null) || (fd.br == null)) {
			// file is not open:
			File file = new File(FILES_DIR, appendExt(filename));
			// log.finest("open (read) " + file.getAbsolutePath());
			// create the reader objects
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				if (br != null) {
					// store open file data in map
					fd = new FileDesc(file, fr, br);
					openfiles.put(filename, fd);
					// log.finest("store FileDesc");
				}

			} catch (IOException ex) {
				log.log(Level.WARNING, "Cannot open file: " + file.getAbsolutePath() + ": " + ex.toString(), ex);
			}
		}
		if ((fd != null) && (fd.file != null) && (fd.br != null)) {
			// read next line from the file; or null if end of file
			try {
				text = fd.br.readLine();
				// log.finest("read \"" + text + "\" from " + fd.file.getAbsolutePath());
				if ((text != null) && !text.isEmpty()) {
					text.concat(System.lineSeparator());
				}

			} catch (IOException ex) {
				log.log(Level.WARNING, "Cannot read file: " + fd.file.getAbsolutePath() + ": " + ex.toString(), ex);
			}
		}
		return text;
	}

	/**
	 * Write to the file with the specified name.
	 * 
	 * @param filename the file name only; you can omit the DEFAULT_EXT.
	 * @param number   the number to append to the file in a new line.
	 */
	public static void write(String filename, int number) {
		write(filename, String.valueOf(number));
	}

	/**
	 * Write to the file with the specified name.
	 * 
	 * @param filename the file name only; you can omit the DEFAULT_EXT.
	 * @param number   the number to append to the file in a new line.
	 */
	public static void write(String filename, float number) {
		write(filename, String.valueOf(number));
	}

	/**
	 * Write to the file with the specified name.
	 * 
	 * @param filename the file name only; you can omit the DEFAULT_EXT.
	 * @param text     the text to append to the filein a new line.
	 */
	public static void write(String filename, String text) {
		// get associated open file data if they exist
		FileDesc fd = openfiles.get(filename);
		if ((fd == null) || (fd.fw == null) || (fd.bw == null)) {
			// file is not open:
			File file = new File(FILES_DIR, appendExt(filename));
			// log.finest("open (write) " + file.getAbsolutePath());
			// create the writer objects
			FileWriter fw = null;
			BufferedWriter bw = null;
			try {
				fw = new FileWriter(file, true);
				bw = new BufferedWriter(fw);
				if (bw != null) {
					// store open file data in map
					fd = new FileDesc(file, fw, bw);
					openfiles.put(filename, fd);
					// log.finest("store FileDesc");
				}

			} catch (IOException ex) {
				log.log(Level.WARNING, "Cannot open file: " + file.getAbsolutePath() + ": " + ex.toString(), ex);
			}
		}
		if ((fd != null) && (fd.file != null) && (fd.bw != null)) {
			// append the text to the file
			try {
				fd.bw.append(text);
				fd.bw.newLine();
				// log.finest("appended \"" + text + "\" to " + fd.file.getAbsolutePath());

			} catch (IOException ex) {
				log.log(Level.WARNING, "Cannot write file: " + fd.file.getAbsolutePath() + ": " + ex.toString(), ex);
			}
		}
	}

	/**
	 * close the file with the specified name.
	 * 
	 * @param filename the file name only; you can omit the DEFAULT_EXT.
	 */
	public static void close(String filename) {
		// get associated open file data if they exist
		FileDesc fd = openfiles.get(filename);
		if (doClose(fd)) {
			openfiles.remove(filename);
		}
	}

	/**
	 * Delete the file with the specified name.
	 * 
	 * @param filename the file name only; you can omit the DEFAULT_EXT.
	 */
	public static void delete(String filename) {
		// close file if it is in the open file data map
		close(filename);
		// delete the file if it exists
		File file = new File(FILES_DIR, appendExt(filename));
		// log.finest("delete " + file.getAbsolutePath());
		if (file.canWrite()) {
			// file exists and is writable
			if (!file.delete()) {
				// cannot delete the file
				log.log(Level.WARNING, "Cannot delete file " + file.getAbsolutePath());
				// } else {
				// log.finest("deleted " + file.getAbsolutePath());
			}
		}
	}

	/**
	 * append the default file extension if necessary.
	 * 
	 * @param filename the file name only; you can omit the DEFAULT_EXT.
	 * @return the filename with extension.
	 */
	static String appendExt(String filename) {
		if (filename.lastIndexOf('.') <= 0) {
			// no extension found: append the default file extension
			return filename + '.' + DEFAULT_EXT;
		}
		return filename;
	}

	/**
	 * close the file with the specified file data.
	 * 
	 * @param fd the file data.
	 */
	static boolean doClose(FileDesc fd) {
		if ((fd != null) && (fd.file != null)) {
			try {
				if (fd.bw != null) {
					fd.bw.flush();
					fd.bw.close();
					// this logging will probably not be written in the shutdown hook
					// log.finest("closed (write) " + fd.file.getAbsolutePath());
				}
				if (fd.br != null) {
					fd.br.close();
					// this logging will probably not be written in the shutdown hook
					// log.finest("closed (read) " + fd.file.getAbsolutePath());
				}

			} catch (IOException ex) {
				// this logging will probably not be written in the shutdown hook
				log.log(Level.WARNING, "Cannot close file: " + fd.file.getAbsolutePath() + ": " + ex.toString(), ex);
			}
			return true;
		}
		return false;
	}
}

/**
 * Data holder for file related references.
 */
class FileDesc {

	File file = null;
	FileReader fr = null;
	BufferedReader br = null;
	FileWriter fw = null;
	BufferedWriter bw = null;

	/**
	 * Constructor.
	 * 
	 * @param file
	 * @param fw
	 * @param bw
	 */
	public FileDesc(File file, FileWriter fw, BufferedWriter bw) {
		this.file = file;
		this.fw = fw;
		this.bw = bw;
	}

	/**
	 * Constructor.
	 * 
	 * @param file
	 * @param fr
	 * @param br
	 */
	public FileDesc(File file, FileReader fr, BufferedReader br) {
		this.file = file;
		this.fr = fr;
		this.br = br;
	}
}
