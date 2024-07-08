package org.platkmframework.security.exception;

import org.platkmframework.annotation.TruslyException;

@TruslyException
public class AuthSecurityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int status;

	public AuthSecurityException() {
		super();
	}

	public AuthSecurityException(String message) {
		super(message);
	}

	public AuthSecurityException(Throwable cause) {
		super(cause);
	}

	public AuthSecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthSecurityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AuthSecurityException(int status, String message) {
		super(message);
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
	
	

}
