package org.platkmframework.security.exception;

import org.eclipse.jetty.http.HttpStatus;
import org.platkmframework.annotation.TruslyException;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
@TruslyException
public class AuthSecurityException extends Exception {

    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo status
     */
    private int status = HttpStatus.UNAUTHORIZED_401;

    /**
     * Constructor AuthSecurityException
     */
    public AuthSecurityException() {
        super();
    }

    /**
     * Constructor AuthSecurityException
     * @param message message
     */
    public AuthSecurityException(String message) {
        super(message);
    }

    /**
     * Constructor AuthSecurityException
     * @param cause cause
     */
    public AuthSecurityException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor AuthSecurityException
     * @param message message
     * @param cause cause
     */
    public AuthSecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor AuthSecurityException
     * @param message message
     * @param cause cause
     * @param enableSuppression enableSuppression
     * @param writableStackTrace writableStackTrace
     */
    public AuthSecurityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Constructor AuthSecurityException
     * @param status status
     * @param message message
     */
    public AuthSecurityException(int status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * getStatus
     * @return int
     */
    public int getStatus() {
        return status;
    }
}
