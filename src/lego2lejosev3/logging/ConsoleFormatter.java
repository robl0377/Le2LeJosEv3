/**
 * LeJOS Implementation of LEGO Mindstorms Programming Blocks
 */
package lego2lejosev3.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Logging Formatter
 * 
 * @author Roland Blochberger
 */
public class ConsoleFormatter extends Formatter {

	private static final String NL = System.getProperty("line.separator");

	/**
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 */
	@Override
	public synchronized String format(LogRecord record) {
		StringBuffer sb = new StringBuffer();

		// append the message
		sb.append(formatMessage(record));
		sb.append(NL);

		// append the throwable's stack trace if necessary
		if (record.getThrown() != null) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				record.getThrown().printStackTrace(pw);
				pw.close();
				sb.append(sw.toString());
			} catch (Exception ex) {
				// ignore
			}
		}
		return sb.toString();
	}
}
