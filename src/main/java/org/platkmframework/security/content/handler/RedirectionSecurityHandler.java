package org.platkmframework.security.content.handler;

import org.platkmframework.security.content.XSSRequestWrapper;
import org.platkmframework.security.exception.AuthSecurityException;
import jakarta.servlet.http.HttpServletResponse;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public interface RedirectionSecurityHandler {

    /**
     * redirection
     * @param req req
     * @param resp resp
     * @throws AuthSecurityException AuthSecurityException
     */
    public void redirection(XSSRequestWrapper req, HttpServletResponse resp) throws AuthSecurityException;
}
