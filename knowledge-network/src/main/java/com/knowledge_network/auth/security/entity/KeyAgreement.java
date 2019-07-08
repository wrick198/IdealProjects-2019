package com.knowledge_network.auth.security.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 密钥协商的公密钥数据库表
 */
@Entity
@Table(name = "key_agreement")
public class KeyAgreement implements Serializable {

    /**
     * 客户端唯一序号
     */
    private String clientSequence;
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 客户端密钥协商的公钥
     */
    private String clientPublicKey;
    /**
     * 服务端对应的密钥协商公钥
     */
    private String serverPublicKey;
    /**
     * 服务端对应的密钥协商私钥
     */
    private String serverPrivateKey;
    /**
     * 通信时的时间
     */
    private Timestamp createTime;

    public KeyAgreement() {
    }

    @Id
    @Column(name = "client_sequence", nullable = false, unique = true)
    public String getClientSequence() {
        return clientSequence;
    }

    public void setClientSequence(String clientSequence) {
        this.clientSequence = clientSequence;
    }

    @Basic
    @Column(name = "client_id")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "client_pub_key", nullable = false)
    public String getClientPublicKey() {
        return clientPublicKey;
    }

    public void setClientPublicKey(String clientPublicKey) {
        this.clientPublicKey = clientPublicKey;
    }

    @Basic
    @Column(name = "server_pub_key", nullable = false)
    public String getServerPublicKey() {
        return serverPublicKey;
    }

    public void setServerPublicKey(String serverPublicKey) {
        this.serverPublicKey = serverPublicKey;
    }

    @Basic
    @Column(name = "server_pri_key", nullable = false)
    public String getServerPrivateKey() {
        return serverPrivateKey;
    }

    public void setServerPrivateKey(String serverPrivateKey) {
        this.serverPrivateKey = serverPrivateKey;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        return clientSequence.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        KeyAgreement keyAgreement = (KeyAgreement) obj;

        if (!clientSequence.equalsIgnoreCase(keyAgreement.getClientSequence())) return false;

        return true;
    }
}
