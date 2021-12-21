package lima.agenda.exceptions;

public class AgendaModelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6225734502037602882L;

	public AgendaModelException() {
		super();
	}

	public AgendaModelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AgendaModelException(String message, Throwable cause) {
		super(message, cause);
	}

	public AgendaModelException(String message) {
		super(message);
	}

	public AgendaModelException(Throwable cause) {
		super(cause);
	}

}
