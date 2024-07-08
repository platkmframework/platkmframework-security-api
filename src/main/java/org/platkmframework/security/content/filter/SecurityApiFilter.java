/*******************************************************************************
 * Copyright(c) 2023 the original author Eduardo Iglesias Taylor.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	 https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * 	Eduardo Iglesias Taylor - initial API and implementation
 *******************************************************************************/
package org.platkmframework.security.content.filter;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.platkmframework.content.ObjectContainer;
import org.platkmframework.security.content.SessionConstants;
import org.platkmframework.security.content.XSSRequestWrapper;
import org.platkmframework.security.content.handler.AuthenticationSecurityHandler;
import org.platkmframework.security.content.handler.LoginSecurityHandler;
import org.platkmframework.security.content.handler.LogoutSecurityHandler;
import org.platkmframework.security.content.handler.RedirectionSecurityHandler;
import org.platkmframework.security.exception.AuthSecurityException;
import org.platkmframework.security.exception.AuthServletException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 **/
public class SecurityApiFilter implements Filter {
	
	@Override
	public void init(FilterConfig arg0) throws ServletException { /* document why this method is empty */ }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		XSSRequestWrapper   req  = new XSSRequestWrapper((HttpServletRequest) request);
		HttpServletResponse resp = (HttpServletResponse) response;
		
		try {
			
			if(StringUtils.isNotBlank(req.getPathInfo())) {
				if(req.getPathInfo().endsWith(SessionConstants.C_LOGIN_PATH)){
					
					List<?> list = ObjectContainer.instance().getListObjectByInstance(LoginSecurityHandler.class);
					if(list == null || list.isEmpty())  throw new ServletException("Debe crear una implementación de la interface " + LoginSecurityHandler.class.getName());
					LoginSecurityHandler loginSecurityHandler = (LoginSecurityHandler) list.get(0);
	            	loginSecurityHandler.login(req, resp);	
	            	  
				}else if(req.getPathInfo().endsWith(SessionConstants.C_LOGOUT_PATH)){
					
					List<?> list = ObjectContainer.instance().getListObjectByInstance(LogoutSecurityHandler.class);
					if(list == null || list.isEmpty())  throw new ServletException("Debe crear una implementación de la interface " + LoginSecurityHandler.class.getName());
					LogoutSecurityHandler logoutSecurityHandler = (LogoutSecurityHandler) list.get(0);
					logoutSecurityHandler.logout(req, resp);	
				
				}else if(req.getPathInfo().endsWith(SessionConstants.C_REDIRECTION_PATH)){
					
					List<?> list = ObjectContainer.instance().getListObjectByInstance(RedirectionSecurityHandler.class);
					if(list == null || list.isEmpty())  throw new ServletException("Debe crear una implementación de la interface " + LoginSecurityHandler.class.getName());
					RedirectionSecurityHandler redirectionSecurityHandler = (RedirectionSecurityHandler) list.get(0);
					redirectionSecurityHandler.redirection(req, resp);	
				
				}else{
					
					List<?> list = ObjectContainer.instance().getListObjectByInstance(AuthenticationSecurityHandler.class);
					if(list != null && !list.isEmpty()) {
						AuthenticationSecurityHandler authenticationSecurityHandler = (AuthenticationSecurityHandler) list.get(0);
						authenticationSecurityHandler.authentication(req, resp); 
						chain.doFilter(req, resp);
					}else
						chain.doFilter(req, resp);
	            }
			}else chain.doFilter(req, resp);
			
		}catch(AuthSecurityException e) {
			throw new AuthServletException(e);
		} 
 
	}
	
	
	@Override
	public void destroy() { /* document why this method is empty */ }
}
