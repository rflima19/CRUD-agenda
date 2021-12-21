package lima.agenda.exceptions;

public class AgendaViewException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4432912756883692270L;

	public AgendaViewException() {
		super();
	}

	public AgendaViewException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AgendaViewException(String message, Throwable cause) {
		super(message, cause);
	}

	public AgendaViewException(String message) {
		super(message);
	}

	public AgendaViewException(Throwable cause) {
		super(cause);
	}

}
