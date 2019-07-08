package com.knowledge_network.auth.security.dao.impl;

import com.knowledge_network.auth.security.dao.SymmetricEncryptionDAO;
import com.knowledge_network.auth.security.entity.SymmetricEncryption;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by pentonbin on 18-4-3
 */
@Repository
public class SymmetricEncryptionDAOImpl extends BaseHibernateDAO<SymmetricEncryption> implements SymmetricEncryptionDAO {

    @Override
    public SymmetricEncryption findByClientSequence(String clientSequence) {
        return findByUnique("clientSequence", clientSequence);
    }

    @Override
    public void createNewSharedKey(SymmetricEncryption se) {
        save(se);
    }

    @Override
    public void updateSymmetricEncryption(SymmetricEncryption symmetricEncryption) {
        save(symmetricEncryption);
    }
}
