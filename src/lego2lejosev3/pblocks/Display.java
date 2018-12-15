/**
 * LeJOS Implementation of LEGO Mindstorms Programming Blocks
 */
package lego2lejosev3.pblocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.Image;

/**
 * Display Block.
 * 
 * @author Roland Blochberger
 * @see https://ev3-help-online.api.education.lego.com/Education/en-us/page.html?Path=blocks%2FLEGO%2FDisplay.html
 */
public class Display {

	private static final Logger log = Logger.getLogger(Display.class.getName());

	/** the text font styles */
	public static final int FONT_NORMAL = 0; // LeJOS uses a 10x16 pixel font
	public static final int FONT_BOLD = 1; // LeJOS uses a 10x16 pixel font
	public static final int FONT_LARGE = 2; // LeJOS uses a 20x32 pixel font
	public static final int FONT_SMALL = 3; // LeJOS uses a 6x8 pixel font

	/** the text colors; the background is inverted. */
	public static final boolean COLOR_BLACK = false;
	public static final boolean COLOR_WHITE = true;

	// private static final TextLCD tLcd = BrickFinder.getDefault().getTextLCD();
	private static final GraphicsLCD gLcd = LocalEV3.get().getGraphicsLCD();

	/** the base directory for image files on the EV3 brick. */
	public static final String IMAGE_DIR = "/home/lejos/lib/";

	/**
	 * Display a number on the pixel display.
	 * 
	 * @param number the number to display.
	 * @param clrScr set true to clear the screen before writing content; false to
	 *               leave the screen as is.
	 * @param x      the x-coordinate (0..177, 0 = left edge of the LCD).
	 * @param y      the y-coordinate (0..127, 0 = upper edge of the LCD).
	 * @param color  the text color, one of COLOR_BLACK or COLOR_WHITE.
	 * @param font   the font, one of FONT_NORMAL, FONT_BOLD, FONT_LARGE,
	 *               FONT_SMALL; LeJOS does not support the bold font and LEGO
	 *               does not support the small font.
	 */
	public static void textPixels(int number, boolean clrScr, int x, int y, boolean color, int font) {
		textPixels(String.valueOf(number), clrScr, x, y, color, font);
	}

	/**
	 * Display a number on the pixel display.
	 * 
	 * @param number the number to display.
	 * @param clrScr set true to clear the screen before writing content; false to
	 *               leave the screen as is.
	 * @param x      the x-coordinate (0..177, 0 = left edge of the LCD).
	 * @param y      the y-coordinate (0..127, 0 = upper edge of the LCD).
	 * @param color  the text color, one of COLOR_BLACK or COLOR_WHITE.
	 * @param font   the font, one of FONT_NORMAL, FONT_BOLD, FONT_LARGE,
	 *               FONT_SMALL; LeJOS does not support the bold font and LEGO
	 *               does not support the small font.
	 */
	public static void textPixels(float number, boolean clrScr, int x, int y, boolean color, int font) {
		textPixels(String.valueOf(number), clrScr, x, y, color, font);
	}

	/**
	 * Display a text on the pixel display.
	 * 
	 * @param text   the text to display.
	 * @param clrScr set true to clear the screen before writing content; false to
	 *               leave the screen as is.
	 * @param x      the x-coordinate (0..177, 0 = left edge of the LCD).
	 * @param y      the y-coordinate (0..127, 0 = upper edge of the LCD).
	 * @param color  the text color, one of COLOR_BLACK or COLOR_WHITE.
	 * @param font   the font, one of FONT_NORMAL, FONT_BOLD, FONT_LARGE,
	 *               FONT_SMALL; LeJOS does not support the bold font and LEGO
	 *               does not support the small font.
	 */
	public static void textPixels(String text, boolean clrScr, int x, int y, boolean color, int font) {
		if (clrScr) {
			gLcd.clear();
			gLcd.refresh();
		}
		// set the font
		Font fnt = null;
		switch (font) {
		case FONT_LARGE:
			fnt = Font.getLargeFont();
			break;
		case FONT_SMALL:
			fnt = Font.getSmallFont();
			break;
		case FONT_NORMAL:
		case FONT_BOLD:
		default:
			fnt = Font.getDefaultFont();
			break;
		}
		gLcd.setFont(fnt);
		// draw the string
		gLcd.drawString(text, x, y, GraphicsLCD.LEFT | GraphicsLCD.TOP, color);
		gLcd.refresh();
	}

	/**
	 * Display a number on the grid display.
	 * 
	 * @param number the number to display.
	 * @param clrScr set true to clear the screen before writing content; false to
	 *               leave the screen as is.
	 * @param column the column (x-coordinate) (0..17, 0 = left edge of the LCD).
	 * @param row    the row (y-coordinate) (0..6, 0 = upper edge of the LCD).
	 * @param color  the text color, one of COLOR_BLACK or COLOR_WHITE.
	 * @param font   the font, one of FONT_NORMAL, FONT_BOLD, FONT_LARGE,
	 *               FONT_SMALL; LeJOS does not support the bold font and LEGO
	 *               does not support the small font.
	 */
	public static void textGrid(int number, boolean clrScr, int column, int row, boolean color, int font) {
		textPixels(String.valueOf(number), clrScr, column * 10, row * 18, color, font);
	}

	/**
	 * Display a number on the grid display.
	 * 
	 * @param number the number to display.
	 * @param clrScr set true to clear the screen before writing content; false to
	 *               leave the screen as is.
	 * @param column the column (x-coordinate) (0..17, 0 = left edge of the LCD).
	 * @param row    the row (y-coordinate) (0..6, 0 = upper edge of the LCD).
	 * @param color  the text color, one of COLOR_BLACK or COLOR_WHITE.
	 * @param font   the font, one of FONT_NORMAL, FONT_BOLD, FONT_LARGE,
	 *               FONT_SMALL; LeJOS does not support the bold font and LEGO
	 *               does not support the small font.
	 */
	public static void textGrid(float number, boolean clrScr, int column, int row, boolean color, int font) {
		textPixels(String.valueOf(number), clrScr, column * 10, row * 18, color, font);
	}

	/**
	 * Display a text on the grid display.
	 * 
	 * @param text   the text to display.
	 * @param clrScr set true to clear the screen before writing content; false to
	 *               leave the screen as is.
	 * @param column the column (x-coordinate) (0..17, 0 = left edge of the LCD).
	 * @param row    the row (y-coordinate) (0..6, 0 = upper edge of the LCD).
	 * @param color  the text color, one of COLOR_BLACK or COLOR_WHITE.
	 * @param font   the font, one of FONT_NORMAL, FONT_BOLD, FONT_LARGE,
	 *               FONT_SMALL; LeJOS does not support the bold font and LEGO
	 *               does not support the small font.
	 */
	public static void textGrid(String text, boolean clrScr, int column, int row, boolean color, int font) {
		textPixels(text, clrScr, column * 10, row * 18, color, font);
	}

	/**
	 * Draw a line on the pixel display.
	 * 
	 * @param clrScr set true to clear the screen before drawing; false to leave the
	 *               screen as is.
	 * @param x1     the x-coordinate of the first point (0..177, 0 = left edge of
	 *               the LCD).
	 * @param y1     the y-coordinate of the first point (0..127, 0 = upper edge of
	 *               the LCD).
	 * @param x2     the x-coordinate of the second point (0..177, 0 = left edge of
	 *               the LCD).
	 * @param y2     the y-coordinate of the second point (0..127, 0 = upper edge of
	 *               the LCD).
	 * @param color  the drawing color, one of COLOR_BLACK or COLOR_WHITE.
	 */
	public static void shapesLine(boolean clrScr, int x1, int y1, int x2, int y2, boolean color) {
		if (clrScr) {
			gLcd.clear();
			gLcd.refresh();
		}
		// set the color
		gLcd.setColor(color == COLOR_BLACK ? GraphicsLCD.BLACK : GraphicsLCD.WHITE);
		// draw the line
		gLcd.drawLine(x1, y1, x2, y2);
		gLcd.refresh();
	}

	/**
	 * Draw a circle on the pixel display.
	 * 
	 * @param clrScr set true to clear the screen before drawing; false to leave the
	 *               screen as is.
	 * @param x      the x-coordinate of the center (0..177, 0 = left edge of the
	 *               LCD).
	 * @param y      the y-coordinate of the center (0..127, 0 = upper edge of the
	 *               LCD).
	 * @param radius the radius in pixels.
	 * @param fill   set true to fill the circle; false to draw the outline only.
	 * @param color  the drawing color, one of COLOR_BLACK or COLOR_WHITE.
	 */
	public static void shapesCircle(boolean clrScr, int x, int y, int radius, boolean fill, boolean color) {
		if (clrScr) {
			gLcd.clear();
			gLcd.refresh();
		}
		// set the color
		gLcd.setColor(color == COLOR_BLACK ? GraphicsLCD.BLACK : GraphicsLCD.WHITE);
		// calculate arc width / height
		int wh = (radius << 1);
		int lx = (x - radius);
		int uy = (y - radius);
		if (fill) {
			// draw and fill the circle
			gLcd.fillArc(lx, uy, wh, wh, 0, 360);
		} else {
			// draw the circle outline only
			gLcd.drawArc(lx, uy, wh, wh, 0, 360);
		}
		gLcd.refresh();
	}

	/**
	 * Draw a rectangle on the pixel display.
	 * 
	 * @param clrScr set true to clear the screen before drawing; false to leave the
	 *               screen as is.
	 * @param x      the x-coordinate of the upper left corner (0..177, 0 = left
	 *               edge of the LCD).
	 * @param y      the y-coordinate of the upper left corner (0..127, 0 = upper
	 *               edge of the LCD).
	 * @param width  the width in pixels.
	 * @param height the heifht in pixels.
	 * @param fill   set true to fill the rectangle; false to draw the outline only.
	 * @param color  the drawing color, one of COLOR_BLACK or COLOR_WHITE.
	 */
	public static void shapesRectangle(boolean clrScr, int x, int y, int width, int height, boolean fill,
			boolean color) {
		if (clrScr) {
			gLcd.clear();
			gLcd.refresh();
		}
		// set the color
		gLcd.setColor(color == COLOR_BLACK ? GraphicsLCD.BLACK : GraphicsLCD.WHITE);
		if (fill) {
			// draw and fill the rectangle
			gLcd.fillRect(x, y, width, height);
		} else {
			// draw the rectangle outline only
			gLcd.drawRect(x, y, width, height);
		}
		gLcd.refresh();
	}

	/**
	 * Draw a pixel on the pixel display.
	 * 
	 * @param clrScr set true to clear the screen before drawing; false to leave the
	 *               screen as is.
	 * @param x      the x-coordinate of the pixel (0..177, 0 = left edge of the
	 *               LCD).
	 * @param y      the y-coordinate of the pixel (0..127, 0 = upper edge of the
	 *               LCD).
	 * @param color  the drawing color, one of COLOR_BLACK or COLOR_WHITE.
	 */
	public static void shapesPoint(boolean clrScr, int x, int y, boolean color) {
		if (clrScr) {
			gLcd.clear();
			gLcd.refresh();
		}
		// draw the pixel
		gLcd.setPixel(x, y, color == COLOR_BLACK ? 1 : 0);
		gLcd.refresh();
	}

	/**
	 * Draw a graphic image file on the pixel display.
	 * Note: The image file must already reside on the EV3 brick. Please upload it
	 * (by SCP) to the IMAGE_DIR directory before using this programming block.
	 * 
	 * @param filename the file name only.
	 * @param clrScr   set true to clear the screen before drawing; false to leave
	 *                 the screen as is.
	 * @param x        the x coordinate of the upper left corner of the image
	 *                 (0..177, 0 = left edge of the LCD).
	 * @param y        the y coordinate of the upper left corner of the image
	 *                 (0..127, 0 = upper edge of the LCD).
	 */
	public static void image(String filename, boolean clrScr, int x, int y) {
		File imageFile = new File(IMAGE_DIR, filename);
		if (imageFile.canRead()) {
			// read the file into an image
			InputStream fis = null;
			try {
				fis = new FileInputStream(imageFile);
				Image img = Image.createImage(fis);
				if (clrScr) {
					gLcd.clear();
					gLcd.refresh();
				}
				gLcd.drawImage(img, x, y, GraphicsLCD.LEFT | GraphicsLCD.TOP);

			} catch (ArrayIndexOutOfBoundsException ie) {
				log.log(Level.WARNING,
						"Error displaying image file " + imageFile.getAbsolutePath() + ": " + ie.toString());

			} catch (Exception ex) {
				log.log(Level.WARNING,
						"Cannot display image file " + imageFile.getAbsolutePath() + ": " + ex.toString(), ex);

			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						// ignore
					}
				}
			}
		} else {
			log.log(Level.WARNING, "Cannot read image file {0}", imageFile.getAbsolutePath());
		}

	}

	/**
	 * Reset screen.
	 * implemented by clear screen.
	 */
	public static void resetScreen() {
		gLcd.clear();
		gLcd.refresh();
	}

}
