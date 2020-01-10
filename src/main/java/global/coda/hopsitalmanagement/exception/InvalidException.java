package global.coda.hopsitalmanagement.exception;


/**
 * The type Invalid exception.
 */
public class InvalidException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new Invalid exception.
	 */
	public InvalidException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new Invalid exception.
	 *
	 * @param message            the message
	 * @param cause              the cause
	 * @param enableSuppression  the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public InvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new Invalid exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public InvalidException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new Invalid exception.
	 *
	 * @param message the message
	 */
	public InvalidException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new Invalid exception.
	 *
	 * @param cause the cause
	 */
	public InvalidException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
