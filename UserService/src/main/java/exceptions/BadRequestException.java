package exceptions;

import java.io.Serializable;

public class BadRequestException extends Exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException() {

	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Exception e) {
		super(message, e);
	}
}
