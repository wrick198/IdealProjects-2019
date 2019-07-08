package com.knowledge_network.auth.security.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 对称加密的公密钥数据库表
 */
@Entity
@Table(name = "symmetric_encryption")
public class SymmetricEncryption implements Serializable {

    /**
     * 客户端的唯一序号
     */
    private String clientSequence;
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 该客户端与服务端进行通信的共享密钥
     */
    private String sharedKey;
    /**
     * 防重放的时间戳序号
     */
    private Long timestampSeq;
    /**
     * 通信时的时间
     */
    private Timestamp createTime;

    public SymmetricEncryption() {
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
    @Column(name = "shared_key", nullable = false)
    public String getKey() {
        return sharedKey;
    }

    public void setKey(String key) {
        this.sharedKey = key;
    }

    @Basic
    @Column(name = "timestame_seq", nullable = false)
    public Long getTimestampSeq() {
        return timestampSeq;
    }

    public void setTimestampSeq(Long timestampSeq) {
        this.timestampSeq = timestampSeq;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SymmetricEncryption that = (SymmetricEncryption) o;

        return clientSequence.equals(that.clientSequence);
    }

    @Override
    public int hashCode() {
        return clientSequence.hashCode();
    }
}
