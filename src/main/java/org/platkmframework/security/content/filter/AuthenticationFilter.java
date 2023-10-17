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

import org.platkmframework.comon.service.exception.ServiceException;
import org.platkmframework.content.ioc.ObjectContainer;
import org.platkmframework.security.content.XSSRequestWrapper;
import org.platkmframework.security.content.handler.AuthenticationSecurityHandler;

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
public class AuthenticationFilter implements Filter {
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		XSSRequestWrapper   req  = new XSSRequestWrapper((HttpServletRequest) request);
		HttpServletResponse resp = (HttpServletResponse) response;
		
		try {
			
            List<?> list = ObjectContainer.instance().getListObjectByInstance(AuthenticationSecurityHandler.class);
            if(list == null || list.isEmpty()) {
            	
            	chain.doFilter(req, resp);
            	
            }else {
            	
            	AuthenticationSecurityHandler authenticationSecurityHandler = (AuthenticationSecurityHandler) list.get(0);
            	authenticationSecurityHandler.authentication(req, resp); 
	            chain.doFilter(req, resp);
	             
            }
		}catch(ServiceException e) {
			throw new ServletException(e);
		} 
 
	}
	
	
	@Override
	public void destroy() {}
}
