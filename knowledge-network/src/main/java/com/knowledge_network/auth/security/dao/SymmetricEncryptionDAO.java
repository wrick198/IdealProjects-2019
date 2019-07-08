package com.knowledge_network.auth.security.dao;

import com.knowledge_network.auth.security.entity.SymmetricEncryption;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 对称加密数据表的DAO
 */
public interface SymmetricEncryptionDAO {

    /**
     * 根据客户端唯一序号获取对称加密对象
     *
     * @param clientSequence
     * @return
     */
    SymmetricEncryption findByClientSequence(String clientSequence);

    /**
     * 保存到数据库
     *
     * @param se
     */
    void createNewSharedKey(SymmetricEncryption se);

    /**
     * 更新对称加密数据表数据
     *
     * @param symmetricEncryption
     */
    void updateSymmetricEncryption(SymmetricEncryption symmetricEncryption);
}
