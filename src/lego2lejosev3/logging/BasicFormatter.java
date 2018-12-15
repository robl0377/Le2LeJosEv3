/**
 * LeJOS Implementation of LEGO Mindstorms Programming Blocks
 */
package lego2lejosev3.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Logging Formatter
 * 
 * @author Roland Blochberger
 */
public class BasicFormatter extends Formatter {

	private Date dat = new Date();
	private final static String DateTimeFormat = "yyyy-MM-dd hh:mm:ss.SSS";
	private SimpleDateFormat DateTimeFormatter = new SimpleDateFormat(DateTimeFormat);
	private static final String NL = System.getProperty("line.separator");

	/**
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 */
	@Override
	public synchronized String format(LogRecord record) {
		StringBuffer sb = new StringBuffer();
		// Minimize memory allocations here.
		// add date & time
		dat.setTime(record.getMillis());
		sb.append(DateTimeFormatter.format(dat));
		sb.append(' ');

		// add the level
		sb.append(record.getLevel().getName().substring(0, 4));
		sb.append(": ");

		// add the classname
		String name = null;
		if (record.getSourceClassName() != null) {
			name = record.getSourceClassName();
		} else {
			name = record.getLoggerName();
		} // extract the simple classname (strip off package name)
		int p = name.lastIndexOf('.');
		if (p >= 0) {
			name = name.substring(p + 1);
		}
		sb.append(name);
		sb.append(' ');

		// add the method name
		if (record.getSourceMethodName() != null) {
			sb.append(record.getSourceMethodName());
			sb.append(' ');
		}

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
