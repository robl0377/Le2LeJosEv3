/**
 * 
 */
package le2lejosev3.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import le2lejosev3.pblocks.Sound;
import le2lejosev3.pblocks.Wait;
import lejos.hardware.Button;

/**
 * Test for playing notes.
 * 
 * @author Roland Blochberger
 */
public class SoundNoteTest {

	private static Class<?> clazz = SoundNoteTest.class;
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

		// -----------------------------------------------
		// Play standard notes
		// display hint
		Display.textGrid("Press Button", true, 0, 5, Display.COLOR_BLACK, Display.FONT_NORMAL);

		// play all notes from top to bottom
		for (String note : Sound.noteNames) {
			// display frequency
			Display.textGrid("Playing:" + note + "  ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// play tones
			log.fine("Playing " + note);
			Sound.playNote(note, 0.12F, 80, Sound.WAIT);
			log.fine("Playing done");
			Wait.time(0.06F);

			if (Button.ESCAPE.isDown()) {
				break;
			}
		}

		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		// play frequencies from El3ctric Guitar
		int[] freqs = new int[] { 1318, 1174, 987, 880, 783, 659, 587, 493, 440, 392, 329, 293 };
		for (int freq : freqs) {
			// display frequency and hint
			Display.textGrid("Playing:" + freq + "Hz", true, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);
			Display.textGrid("Press Button", false, 0, 5, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// play tones
			log.fine("Playing " + freq + "Hz");
			Sound.playTone(freq, 0.12F, 80, Sound.WAIT);
			log.fine("Playing done");
			Wait.time(0.06F);

			if (Button.ESCAPE.isDown()) {
				break;
			}
		}

		// Wait until button press
		Button.waitForAnyPress();

		// -----------------------------------------------
		// Play notes on LeJOS instruments
		String name = null;
		for (int instrument : new int[] { Sound.PIANO, Sound.FLUTE, Sound.XYLOPHONE }) {
			switch (instrument) {
			case Sound.PIANO:
				name = "PIANO";
				break;
			case Sound.FLUTE:
				name = "FLUTE";
				break;
			case Sound.XYLOPHONE:
				name = "XYLOPHONE";
				break;
			}
			// display instrument and hint
			Display.textGrid(name, true, 0, 2, Display.COLOR_BLACK, Display.FONT_NORMAL);
			Display.textGrid("Press Button", false, 0, 5, Display.COLOR_BLACK, Display.FONT_NORMAL);

			// play octave 5 from top to bottom as a LeJOS instrument
			for (String note : Sound.noteNames) {
				if (note.endsWith("5")) {
					// display frequency
					Display.textGrid("Playing: " + note + "  ", false, 0, 3, Display.COLOR_BLACK, Display.FONT_NORMAL);

					// play tones
					log.fine("Playing " + name + ": " + note);
					Sound.playNote(instrument, note, 0.3F);
					log.fine("Playing done");
					Wait.time(0.1F);
				}

				if (Button.ESCAPE.isDown()) {
					break;
				}
			}

			if (Button.ESCAPE.isDown()) {
				break;
			}

			// Wait until button press
			Button.waitForAnyPress();
		}

		log.fine("The End");
	}

}
