/**
 * ****************************************************************************
 *  Copyright(c) 2023 the original author Eduardo Iglesias Taylor.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  	 https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  Contributors:
 *  	Eduardo Iglesias Taylor - initial API and implementation
 * *****************************************************************************
 */
package org.platkmframework.security.content;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class SecurityContent {

    /**
     * Atributo secuirtyThreadLocal
     */
    private ThreadLocal<AuthenPrincipal> secuirtyThreadLocal = new ThreadLocal<>();

    /**
     * Atributo securityContent
     */
    private static SecurityContent securityContent;

    
    /**
     * Security Content
     */
    private SecurityContent() {
		super(); 
	}

	/**
     * instance
     * @return SecurityContent
     */
    public static SecurityContent instance() {
        if (securityContent == null)
            securityContent = new SecurityContent();
        return securityContent;
    }

    /**
     * setPrincipal
     * @param authenPrincipal authenPrincipal
     */
    public synchronized void setPrincipal(AuthenPrincipal authenPrincipal) {
        secuirtyThreadLocal.set(authenPrincipal);
    }

    /**
     * clear
     */
    public synchronized void clear() {
        //secuirtyThreadLocal.remove();
    }

    /**
     * getPrincipal
     * @return AuthenPrincipal
     */
    public AuthenPrincipal getPrincipal() {
        return secuirtyThreadLocal.get();
    }
}
