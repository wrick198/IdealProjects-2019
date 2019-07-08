package com.knowledge_network.auth.security.dao;

import com.knowledge_network.auth.security.entity.KeyAgreement;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 密钥协商数据库表DAO接口
 */
public interface KeyAgreementDAO {

    /**
     * 根据客户端唯一序号获取密钥协商对象
     *
     * @param clientSequence
     * @return
     */
    KeyAgreement findByClientSequence(String clientSequence);

    /**
     * 持久化对象
     *
     * @param keyAgreement
     */
    void createNewKeyAgreement(KeyAgreement keyAgreement);
}
