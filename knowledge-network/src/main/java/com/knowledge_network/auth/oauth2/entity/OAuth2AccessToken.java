package com.knowledge_network.auth.oauth2.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by pentonbin on 17-12-23
 * <p>
 * 保存OAuth2认证的用户token
 * 注意：该类只用来创建oauth_access_token表，一般请不要使用或修改该类
 */
@Entity
@Table(name = "oauth_access_token")
public class OAuth2AccessToken implements Serializable {

    private String tokenId;
    private Timestamp createTime;
    private Byte[] token;
    private String authenticationId;
    private String username;
    private String clientId;
    private Byte[] authentication;
    private String refreshToken;

    public OAuth2AccessToken() {
    }

    @Id
    @Column(name = "token_id", nullable = false)
    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Basic(optional = false)
    @Column(name = "create_time", nullable = false, insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Lob
    @Basic
    @Column(name = "token", nullable = false, length = 65536)
    public Byte[] getToken() {
        return token;
    }

    public void setToken(Byte[] token) {
        this.token = token;
    }

    @Basic
    @Column(name = "authentication_id", nullable = false)
    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    @Basic
    @Column(name = "user_name", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "client_id", nullable = false)
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Lob
    @Basic
    @Column(name = "authentication", nullable = false, length = 65536)
    public Byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Byte[] authentication) {
        this.authentication = authentication;
    }

    @Basic
    @Column(name = "refresh_token", nullable = false)
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
