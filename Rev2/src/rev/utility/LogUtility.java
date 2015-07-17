package rev.utility;

/**
 * Utility for logging system status.
 * 
 *
 * 
 */
public class LogUtility {

	/**
	 * Logs a note.
	 * 
	 * @param source
	 *            the source of log.
	 * @param msg
	 *            the log message.
	 */
	public static void makeLog(String source, String msg) {
		System.out.println(System.currentTimeMillis() + "\t[" + source + "] " + msg);
	}
}