package org.platkmframework.security.content.handler;
 
import org.platkmframework.security.content.XSSRequestWrapper;
import org.platkmframework.security.exception.AuthSecurityException;

import jakarta.servlet.http.HttpServletResponse;
 

public interface LoginSecurityHandler { 
	
	public void login(XSSRequestWrapper req, HttpServletResponse resp) throws AuthSecurityException;
	
}
