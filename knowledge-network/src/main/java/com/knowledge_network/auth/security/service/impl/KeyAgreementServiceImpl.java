package com.knowledge_network.auth.security.service.impl;

import com.knowledge_network.auth.security.dao.KeyAgreementDAO;
import com.knowledge_network.auth.security.entity.KeyAgreement;
import com.knowledge_network.auth.security.service.KeyAgreementService;
import com.knowledge_network.support.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pentonbin on 18-4-3
 */
@Service
public class KeyAgreementServiceImpl implements KeyAgreementService {

    @Autowired
    private KeyAgreementDAO keyAgreementDAO;

    @Override
    public KeyAgreement getByClientSequence(String clientSequence) {
        if (StringUtils.isNullOrEmpty(clientSequence)) {
            return null;
        }
        return keyAgreementDAO.findByClientSequence(clientSequence);
    }

    @Override
    public boolean containClientSequence(String clientSequence) {
        return keyAgreementDAO.findByClientSequence(clientSequence) != null;
    }

    @Override
    public void createNewKeyAgreement(KeyAgreement keyAgreement) {
        if (keyAgreement == null) {
            return;
        }
        keyAgreementDAO.createNewKeyAgreement(keyAgreement);
    }
}
