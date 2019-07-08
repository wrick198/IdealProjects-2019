package com.knowledge_network.auth.security.service;

import com.knowledge_network.auth.security.entity.KeyAgreement;

/**
 * Created by pentonbin on 18-4-3
 */
public interface KeyAgreementService {

    /**
     * 根据客户端唯一序号获取密钥协商对象
     *
     * @param clientSequence
     * @return
     */
    KeyAgreement getByClientSequence(String clientSequence);

    /**
     * 是否包含某个客户端唯一序号
     *
     * @param clientSequence
     * @return
     */
    boolean containClientSequence(String clientSequence);

    /**
     * 持久化密钥协商对象
     *
     * @param keyAgreement
     */
    void createNewKeyAgreement(KeyAgreement keyAgreement);
}
