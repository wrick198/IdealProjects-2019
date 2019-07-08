package com.knowledge_network.auth.security.dao.impl;

import com.knowledge_network.auth.security.dao.KeyAgreementDAO;
import com.knowledge_network.auth.security.entity.KeyAgreement;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by pentonbin on 18-4-3
 */
@Repository
public class KeyAgreementDAOImpl extends BaseHibernateDAO<KeyAgreement> implements KeyAgreementDAO {

    @Override
    public KeyAgreement findByClientSequence(String clientSequence) {
        return findByUnique("clientSequence", clientSequence);
    }

    @Override
    public void createNewKeyAgreement(KeyAgreement keyAgreement) {
        save(keyAgreement);
    }


}
