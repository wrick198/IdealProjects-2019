package com.knowledge_network.auth.security.service.impl;

import com.knowledge_network.auth.security.dao.SymmetricEncryptionDAO;
import com.knowledge_network.auth.security.entity.SymmetricEncryption;
import com.knowledge_network.auth.security.service.SymmetricEncryptionService;
import com.knowledge_network.support.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pentonbin on 18-4-3
 */
@Service
public class SymmetricEncryptionServiceImpl implements SymmetricEncryptionService {

    @Autowired
    private SymmetricEncryptionDAO symmetricEncryptionDAO;

    @Override
    public SymmetricEncryption getByClientSequence(String clientSequence) {
        if (StringUtils.isNullOrEmpty(clientSequence)) {
            return null;
        }
        return symmetricEncryptionDAO.findByClientSequence(clientSequence);
    }

    @Override
    public void createNewSharedKey(SymmetricEncryption se) {
        if (se != null) {
            symmetricEncryptionDAO.createNewSharedKey(se);
        }
    }

    @Override
    public boolean containClientSequence(String clientSequence) {
        return getByClientSequence(clientSequence) != null;
    }

    @Override
    public void updateSymmetricEncryption(SymmetricEncryption symmetricEncryption) {
        if(symmetricEncryption != null){
            symmetricEncryptionDAO.updateSymmetricEncryption(symmetricEncryption);
        }
    }
}
