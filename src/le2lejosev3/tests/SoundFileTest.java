/**
 * 
 */
package le2lejosev3.tests;

import java.io.File;
import java.io.FilenameFilter;
import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.Sound;
import lejos.hardware.Button;

/**
 * Test for playing a sound file.
 * 
 * @author Roland Blochberger
 */
public class SoundFileTest {

	private static Class<?> clazz = SoundFileTest.class;
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

		// determine which sound files are there
		File sndDir = new File(Sound.SOUND_DIR);
		String[] soundFiles = sndDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				log.fine("found file " + dir.getAbsolutePath() + File.separator + name);
				return name.toLowerCase().endsWith(".wav");
			}
		});
		if (soundFiles == null || soundFiles.length == 0) {
			log.fine("No sound files found");

		} else {

			for (String soundFile : soundFiles) {
				// display filename and hint
				Display.textGrid("Playing:", true, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
				Display.textGrid(soundFile, false, 2, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
				Display.textGrid("Press Button", false, 0, 5, Display.COLOR_BLACK, Display.FONT_NORMAL);

				// play sound file once and wait until done
				log.fine("Playing " + soundFile);
				Sound.playFile(soundFile, 100, Sound.WAIT);
				log.fine("Playing done");

				// Wait until button press
				Button.waitForAnyPress();
			}
		}
		log.fine("The End");
	}

}
