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

import java.util.regex.Pattern;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    /**
     * Constructor XSSRequestWrapper
     * @param servletRequest servletRequest
     */
    public XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    /**
     * getParamValues
     * @param parameter parameter
     * @return String[]
     */
    protected String[] getParamValues(String parameter) {
        String[] values2 = getParameterValues(parameter);
        if (values2 == null)
            return null;
        int count = values2.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = checkingXXS(values2[i]);
        }
        return encodedValues;
    }

    /**
     * getParameter
     * @param parameter parameter
     * @return String
     */
    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null)
            return null;
        return checkingXXS(value);
    }

    /**
     * getHeader
     * @param name name
     * @return String
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return checkingXXS(value);
    }

    /**
     * checkingXXS
     * @param value value
     * @return String
     */
    private String checkingXXS(String value) {
        if (value != null) {
            value = value.replaceAll("", "");
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }

    /**
     * getClientRequestInfo
     * @return ClientRequestInfo
     */
    public ClientRequestInfo getClientRequestInfo() {
        return new ClientRequestInfo(super.getRemoteHost(), super.getRemoteUser(), super.getContentType(), super.getHeader("User-Agent "), BASIC_AUTH);
    }
}
