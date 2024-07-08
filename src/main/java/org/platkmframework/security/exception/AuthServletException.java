package org.platkmframework.security.exception;

import org.platkmframework.annotation.TruslyException;

import jakarta.servlet.ServletException;

@TruslyException
public class AuthServletException extends ServletException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthServletException() {
		super();
	}

	public AuthServletException(String message, Throwable rootCause) {
		super(message, rootCause);
	}

	public AuthServletException(Throwable rootCause) {
		super(rootCause);
	} 

}
