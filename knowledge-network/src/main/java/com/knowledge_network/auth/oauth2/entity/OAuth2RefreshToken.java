package com.knowledge_network.auth.oauth2.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by pentonbin on 17-12-23
 * <p>
 * 保存OAuth2认证的用户刷新token
 * 注意：该类只用来创建oauth_refresh_token表，一般请不要使用或修改该类
 */
@Entity
@Table(name = "oauth_refresh_token")
public class OAuth2RefreshToken implements Serializable {

    private String tokenId;
    private Timestamp createTime;
    private Byte[] token;
    private Byte[] authentication;

    public OAuth2RefreshToken() {
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

    @Lob
    @Basic
    @Column(name = "authentication", nullable = false, length = 65536)
    public Byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Byte[] authentication) {
        this.authentication = authentication;
    }
}
