package org.platkmframework.security.exception;

import org.platkmframework.annotation.TruslyException;
import jakarta.servlet.ServletException;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
@TruslyException
public class AuthServletException extends ServletException {

    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor AuthServletException
     */
    public AuthServletException() {
        super();
    }

    /**
     * Constructor AuthServletException
     * @param message message
     * @param rootCause rootCause
     */
    public AuthServletException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    /**
     * Constructor AuthServletException
     * @param rootCause rootCause
     */
    public AuthServletException(Throwable rootCause) {
        super(rootCause);
    }
}
