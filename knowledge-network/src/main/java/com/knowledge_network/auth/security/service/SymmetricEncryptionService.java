package com.knowledge_network.auth.security.service;

import com.knowledge_network.auth.security.entity.SymmetricEncryption;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 与对称加密的密钥表相关的服务接口
 */
public interface SymmetricEncryptionService {

    /**
     * 根据客户端唯一序号获取对称加密对象
     *
     * @param clientSequence
     * @return
     */
    SymmetricEncryption getByClientSequence(String clientSequence);

    /**
     * 保存到数据库
     *
     * @param se
     */
    void createNewSharedKey(SymmetricEncryption se);

    /**
     * 判断是否包含了某个客户端唯一序号
     *
     * @param clientSequence
     * @return
     */
    boolean containClientSequence(String clientSequence);

    /**
     * 更新对称加密的信息
     *
     * @param SymmetricEncryption
     */
    void updateSymmetricEncryption(SymmetricEncryption SymmetricEncryption);
}
