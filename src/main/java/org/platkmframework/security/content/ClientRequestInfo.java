package org.platkmframework.security.content;

import java.io.Serializable;

/**
 *   Author:
 *     Eduardo Iglesias
 *   Contributors:
 *   	Eduardo Iglesias - initial API and implementation
 */
public class ClientRequestInfo implements Serializable {

    /**
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo remoteHost
     */
    private String remoteHost;

    /**
     * Atributo remoteUser
     */
    private String remoteUser;

    /**
     * Atributo contentType
     */
    private String contentType;

    /**
     * Atributo userAgent
     */
    private String userAgent;

    /**
     * Atributo remoteAddr
     */
    private String remoteAddr;

    /**
     * Constructor ClientRequestInfo
     */
    public ClientRequestInfo() {
        super();
    }

    /**
     * Constructor ClientRequestInfo
     * @param remoteHost remoteHost
     * @param remoteUser remoteUser
     * @param contentType contentType
     * @param userAgent userAgent
     * @param remoteAddr remoteAddr
     */
    public ClientRequestInfo(String remoteHost, String remoteUser, String contentType, String userAgent, String remoteAddr) {
        super();
        this.remoteHost = remoteHost;
        this.remoteUser = remoteUser;
        this.contentType = contentType;
        this.userAgent = userAgent;
        this.remoteAddr = remoteAddr;
    }

    /**
     * getRemoteHost
     * @return String
     */
    public String getRemoteHost() {
        return remoteHost;
    }

    /**
     * setRemoteHost
     * @param remoteHost remoteHost
     */
    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    /**
     * getRemoteUser
     * @return String
     */
    public String getRemoteUser() {
        return remoteUser;
    }

    /**
     * setRemoteUser
     * @param remoteUser remoteUser
     */
    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    /**
     * getContentType
     * @return String
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * setContentType
     * @param contentType contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * getUserAgent
     * @return String
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * setUserAgent
     * @param userAgent userAgent
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * getRemoteAddr
     * @return String
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }

    /**
     * setRemoteAddr
     * @param remoteAddr remoteAddr
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
}
