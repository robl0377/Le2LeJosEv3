/**
 * Java Implementation of LEGO Mindstorms Programming Blocks
 */
package le2lejosev3.pblocks;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import lejos.hardware.Button;

/**
 * Sound Block.
 * 
 * BEWARE: This class uses the EV3Audio class in this same package. Do NOT use
 * the original LeJOS EV3Audio class or the class lejos.hardware.Sound that uses
 * it in the same program as this class!
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FSound.html
 */
public class Sound {

	private static final Logger log = Logger.getLogger(Sound.class.getName());

	/** the play types */
	public static final int WAIT = 0; // play once and wait for completion.
	public static final int ONCE = 1; // play once and return immediately.
	public static final int REPEAT = 2; // play continuously and return immediately.

	/** the LeJOS 'instruments' */
	public static final int PIANO = 0;
	public static final int FLUTE = 1;
	public static final int XYLOPHONE = 2;

	/** the base directory for sound files on the EV3 brick. */
	public static final String SOUND_DIR = "/home/lejos/lib/";
	/** the default extension for sound files that LeJOS can play */
	public static final String DEFAULT_EXT = "wav";

	// array with allowed note names
	public static final String[] noteNames = new String[] { "B6", "A#6", "A6", "G#6", "G6", "F#6", "F6", "E6", "D#6",
			"D6", "C#6", "C6", "B5", "A#5", "A5", "G#5", "G5", "F#5", "F5", "E5", "D#5", "D5", "C#5", "C5", "B4", "A#4",
			"A4", "G#4", "G4", "F#4", "F4", "E4", "D#4", "D4", "C#4", "C4" };
	// array with (rounded) frequencies of the respective note name in the array
	// above
	protected static final int[] noteFreqs = new int[] { 1976, 1865, 1760, 1661, 1568, 1480, 1397, 1319, 1245, 1175,
			1109, 1047, 988, 932, 880, 831, 784, 740, 698, 659, 622, 587, 554, 523, 494, 466, 440, 415, 392, 370, 349,
			330, 311, 294, 277, 262 };

	// create and start the background sound thread
	private static SoundThread soundthread = new SoundThread();
	static {
		soundthread.start();
	}

	/**
	 * Play a sound file.
	 * The sound file can be part of a jar file in the classpath, or reside as
	 * ordinary file on the EV3 Brick.
	 * If you did not pack the sound file into a jar file and added it to the
	 * project's classpath, please upload the sound file (by SCP) to the SOUND_DIR
	 * directory before using this programming block.
	 * The sound file must be mono, from 8kHz to 48kHz, and 8-bit or 16-bit PWM.
	 * 
	 * @param filename the file name only; you can omit the DEFAULT_EXT.
	 * @param volume   the volume (0..100).
	 * @param playType the play type, one of WAIT, ONCE, or REPEAT.
	 */
	public static void playFile(String filename, int volume, int playType) {
		if (filename.lastIndexOf('.') <= 0) {
			// no extension found: append the default sound file extension
			filename = filename + '.' + DEFAULT_EXT;
		}
		InputStream in = Sound.class.getResourceAsStream("/resources/" + filename);
		if (in != null) {
			// resource found:
			// stop background sound immediately
			soundthread.quiet();
			switch (playType) {
			case WAIT:
				// Play sound and wait until done
				if (log.isLoggable(Level.FINEST)) {
					log.log(Level.FINEST, "/resources/{0}", filename);
				}
				int r = ((EV3Audio) EV3Audio.getAudio()).playSample(in, volume);
				if (r < 0) {
					log.warning("playSample(/resources/" + filename + "): Error " + r);
				}
				break;
			case ONCE:
				soundthread.setRes(filename, volume, false);
				break;
			case REPEAT:
				soundthread.setRes(filename, volume, true);
				break;
			default:
				throw new RuntimeException("Invalid play type value: " + playType);
			}

		} else {
			// no resource found: check if file exists in the SOUND_DIR
			File soundFile = new File(SOUND_DIR, filename);
			if (soundFile.canRead()) {
				// file found
				// stop background sound immediately
				soundthread.quiet();
				switch (playType) {
				case WAIT:
					// Play sound and wait until done
					if (log.isLoggable(Level.FINEST)) {
						log.log(Level.FINEST, soundFile.getAbsolutePath());
					}
					// int r = lejos.hardware.Sound.playSample(soundFile, volume);
					int r = EV3Audio.getAudio().playSample(soundFile, volume);
					if (r < 0) {
						log.warning("playSample(" + soundFile.getAbsolutePath() + "): Error " + r);
					}
					break;
				case ONCE:
					soundthread.setFile(soundFile, volume, false);
					break;
				case REPEAT:
					soundthread.setFile(soundFile, volume, true);
					break;
				default:
					throw new RuntimeException("Invalid play type value: " + playType);
				}

			} else {
				log.warning("Cannot read sound file " + soundFile.getAbsolutePath());
			}
		}
	}

	/**
	 * Play a tone with the specified frequency and duration.
	 * 
	 * @param frequency the frequency of the tone in Hz (cycles per second);
	 *                  recommended: 250 to 10000.
	 * @param duration  tone duration in seconds.
	 * @param volume    the volume (0..100).
	 * @param playType  the play type, one of WAIT, ONCE, or REPEAT.
	 */
	public static void playTone(int frequency, float duration, int volume, int playType) {
		int durMs = Math.round(duration * 1000);
		// stop background sound immediately
		soundthread.quiet();
		switch (playType) {
		case WAIT:
			// Play tone and wait until done
			// lejos.hardware.Sound.playTone(frequency, durMs, volume);
			EV3Audio.getAudio().playTone(frequency, durMs, volume);
			break;
		case ONCE:
			soundthread.setTone(frequency, durMs, volume, false);
			break;
		case REPEAT:
			soundthread.setTone(frequency, durMs, volume, true);
			break;
		default:
			throw new RuntimeException("Invalid play type value: " + playType);
		}
	}

	/**
	 * Play a musical note.
	 * 
	 * @param note     the note name; Allowed is "C" through "B", followed
	 *                 optionally by "#", followed by "4" through "6"; A-G is a
	 *                 musical note name; 4-6 is an octave number; "#" means "sharp"
	 *                 (one half step higher than the note without "#").
	 * @param duration tone duration in seconds.
	 * @param volume   the volume (0..100).
	 * @param playType the play type, one of WAIT, ONCE, or REPEAT.
	 */
	public static void playNote(String note, float duration, int volume, int playType) {
		playTone(calcFreq(note), duration, volume, playType);
	}

	/**
	 * Play a musical note on a 'LeJOS instrument'.
	 * Play a note with attack, decay, sustain and release shape, which is known as
	 * a ADSR envelope. This function plays a more musical sounding note compared to
	 * playTone(). It uses a set of supplied "instrument" parameters to define the
	 * shape of the note's envelope. (sounds strange)
	 * 
	 * @param instrument the 'LeJOS instrument'; one of PIANO, FLUTE, XYLOPHONE.
	 * @param note       the note name; Allowed is "C" through "B", followed
	 *                   optionally by "#", followed by "4" through "6"; A-G is a
	 *                   musical note name; 4-6 is an octave number; "#" means
	 *                   "sharp" (one half step higher than the note without "#").
	 * @param duration   tone duration in seconds.
	 */
	public static void playNote(int instrument, String note, float duration) {
		int[] instr = null;
		switch (instrument) {
		case PIANO:
			instr = lejos.hardware.Sounds.PIANO;
			break;
		case FLUTE:
			instr = lejos.hardware.Sounds.FLUTE;
			break;
		case XYLOPHONE:
			instr = lejos.hardware.Sounds.XYLOPHONE;
			break;
		default:
			throw new RuntimeException("Invalid instrument value: " + instrument);
		}
		int frequency = calcFreq(note);
		int durMs = Math.round(duration * 1000);
		// stop background sound immediately
		soundthread.quiet();
		// Play tone and wait until done
		// lejos.hardware.Sound.playNote(instr, frequency, durMs);
		EV3Audio.getAudio().playNote(instr, frequency, durMs);
	}

	/**
	 * Stop any sounds playing in the background.
	 */
	public static void stop() {
		soundthread.quiet();
	}

	/**
	 * convert a note name to its frequency.
	 * 
	 * @param note the note name
	 * @return the note frequency; or 0 if invalid note name.
	 */
	public static int calcFreq(String note) {
		note = note.toUpperCase();
		// scan table of allowed note names
		for (int ix = 0; ix < noteNames.length; ix++) {
			if (noteNames[ix] == note) {
				// found note, return its frequency
				return noteFreqs[ix];
			}
		}
		log.warning("Invalid note: " + note);
		return 0;
	}

	/**
	 * Thread to play sounds in the background.
	 */
	static class SoundThread extends Thread {

		private String resname = null;
		private File soundFile = null;
		private int frequency = 0;
		private int durMs = 0;
		private int volume = 0;
		private boolean repeat = false;

		/**
		 * Constructor
		 */
		public SoundThread() {
			this.setDaemon(true);
			this.setPriority(MIN_PRIORITY);
		}

		/**
		 * Set the parameters to play a sound resource.
		 * 
		 * @param resname
		 * @param volume
		 * @param repeat
		 */
		public void setRes(String resname, int volume, boolean repeat) {
			synchronized (this) {
				this.resname = resname;
				this.soundFile = null;
				this.frequency = 0;
				this.durMs = 0;
				this.volume = volume;
				this.repeat = repeat;
			}
		}

		/**
		 * Set the parameters to play a sound file.
		 * 
		 * @param soundFile
		 * @param volume
		 * @param repeat
		 */
		public void setFile(File soundFile, int volume, boolean repeat) {
			synchronized (this) {
				this.resname = null;
				this.soundFile = soundFile;
				this.frequency = 0;
				this.durMs = 0;
				this.volume = volume;
				this.repeat = repeat;
			}
		}

		/**
		 * Set the parameters to play a tone.
		 * 
		 * @param frequency
		 * @param durMs
		 * @param volume
		 * @param repeat
		 */
		public void setTone(int frequency, int durMs, int volume, boolean repeat) {
			synchronized (this) {
				this.resname = null;
				this.soundFile = null;
				this.frequency = frequency;
				this.durMs = durMs;
				this.volume = volume;
				this.repeat = repeat;
			}
		}

		/**
		 * Stop any sound playing in the background.
		 */
		public void quiet() {
			// clear all command data
			synchronized (this) {
				this.resname = null;
				this.soundFile = null;
				this.frequency = 0;
				this.durMs = 0;
				this.volume = 0;
				this.repeat = false;
			}
			if (log.isLoggable(Level.FINEST)) {
				log.log(Level.FINEST, "endPCMPlayback");
			}
			// stop sound
			((EV3Audio) EV3Audio.getAudio()).endPCMPlayback();
		}

		/**
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			String resname = null;
			File soundFile = null;
			int frequency = 0;
			int durMs = 0;
			int volume = 0;
			int r = 0;
			boolean repeat = false;
			while (Button.ESCAPE.isUp()) {
				Thread.yield();

				// copy class variables to local ones
				synchronized (this) {
					resname = this.resname;
					soundFile = this.soundFile;
					frequency = this.frequency;
					durMs = this.durMs;
					volume = this.volume;
					repeat = this.repeat;
				}
				if (resname != null) {
					// play sound resource and wait until done
					InputStream in = Sound.class.getResourceAsStream("/resources/" + resname);
					if (in != null) {
						if (log.isLoggable(Level.FINEST)) {
							log.log(Level.FINEST, "playSample: /resources/{0}", resname);
						}
						// use new method
						r = ((EV3Audio) EV3Audio.getAudio()).playSample(in, volume);
						if (r < 0) {
							log.warning("playSample(/resources/" + resname + "): Error " + r);
						}
					}
					if (in == null || !repeat) {
						synchronized (this) {
							this.resname = null;
						}
					}
				} else if (soundFile != null) {
					// play sound file and wait until done
					// int r = lejos.hardware.Sound.playSample(soundFile, volume);
					if (log.isLoggable(Level.FINEST)) {
						log.log(Level.FINEST, "playSample: {0}", soundFile.getAbsolutePath());
					}
					r = EV3Audio.getAudio().playSample(soundFile, volume);
					if (r < 0) {
						log.warning("playSample(" + soundFile.getAbsolutePath() + "): Error " + r);
					}
					if (!repeat) {
						synchronized (this) {
							this.soundFile = null;
						}
					}
				} else if (frequency != 0) {
					// play tone and wait until done
					// lejos.hardware.Sound.playTone(frequency, durMs, volume);
					EV3Audio.getAudio().playTone(frequency, durMs, volume);
					if (!repeat) {
						synchronized (this) {
							this.frequency = 0;
						}
					}
				}
			}
		}
	}
}
