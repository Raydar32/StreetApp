package exceptions;

import java.io.Serializable;

/*
 * 	Eccezione per risorsa non trovata
 * 
 */
public class ResourceNotFoundException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {

	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Exception e) {
		super(message, e);
	}
}
