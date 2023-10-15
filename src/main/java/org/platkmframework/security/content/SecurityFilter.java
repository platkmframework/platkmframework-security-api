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
package org.platkmframework.security.content;

import java.io.IOException;
import java.util.List;

import javax.security.auth.login.LoginException;
 
import org.platkmframework.content.ioc.ObjectContainer;

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
public class SecurityFilter implements Filter {
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		XSSRequestWrapper   req  = new XSSRequestWrapper((HttpServletRequest) request);
		HttpServletResponse resp = (HttpServletResponse) response;
		
		try {
			
            List<?> list = ObjectContainer.instance().getListObjectByInstance(BaseSecurityHandler.class);
            if(list == null || list.isEmpty()) {
            	
            	chain.doFilter(req, resp);
            	
            }else {
	            BaseSecurityHandler baseSecurityHandler = (BaseSecurityHandler) list.get(0);
	            
	            if(baseSecurityHandler.isLogingRequest(req)) {
	            	baseSecurityHandler.login(req, resp);
	            }else if(baseSecurityHandler.isLogoutRequest(req)) {
	            	baseSecurityHandler.logout(req, resp);
	            	throw new ServletException("User logout");
	            }else {
	            	baseSecurityHandler.authentication(null, null);
	            	chain.doFilter(req, resp);
	            }
            }
		}catch(LoginException e) {
			throw new ServletException(e);
		} 
 
	}
	
	
	@Override
	public void destroy() {}
}
