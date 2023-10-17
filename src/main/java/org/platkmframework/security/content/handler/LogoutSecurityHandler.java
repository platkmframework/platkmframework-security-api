package org.platkmframework.security.content.handler;

import org.platkmframework.comon.service.exception.ServiceException;
import org.platkmframework.security.content.XSSRequestWrapper;

import jakarta.servlet.http.HttpServletResponse;

public interface LogoutSecurityHandler { 
	
	public void logout(XSSRequestWrapper req, HttpServletResponse resp)throws ServiceException;
	
}
