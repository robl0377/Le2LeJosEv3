/**
 * 
 */
package lego2lejosev3.tests;

import java.io.File;
import java.io.FilenameFilter;
import java.util.logging.Logger;

import lego2lejosev3.logging.Setup;
import lego2lejosev3.pblocks.Display;
import lejos.hardware.Button;

/**
 * Test for playing a file.
 * 
 * @author Roland Blochberger
 */
public class DisplayImage {

	private static Class<?> clazz = DisplayImage.class;
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
		// determine which image files are there
		File imgDir = new File(Display.IMAGE_DIR);
		String[] imageFiles = imgDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				log.fine("found file " + dir.getAbsolutePath() + File.separator + name);
				return name.toLowerCase().endsWith(".lni");
			}
		});
		if (imageFiles == null || imageFiles.length == 0) {
			log.fine("No image files found");

		} else {

			for (String imageFile : imageFiles) {

				// display image file on top left corner
				log.fine("Display image " + imageFile);
				Display.image(imageFile, true, 0, 0);
				log.fine("Display image done");

				// Wait until button press
				Button.waitForAnyPress();
			}
		}
		log.fine("The End");
	}

}
