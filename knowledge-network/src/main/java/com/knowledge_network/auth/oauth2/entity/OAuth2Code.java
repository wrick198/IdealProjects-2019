package com.knowledge_network.auth.oauth2.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pentonbin on 18-3-21
 * <p>
 * 保存授权码授权模式中的授权码
 * 注意：该类只用来创建oauth_code表，一般请不要使用或修改该类
 */
@Entity
@Table(name = "oauth_code")
public class OAuth2Code implements Serializable {


    private String code;
    private Byte[] authentication;

    public OAuth2Code() {
    }

    @Id
    @Column(name = "code", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
