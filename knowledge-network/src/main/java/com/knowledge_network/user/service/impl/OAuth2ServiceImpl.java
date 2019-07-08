package com.knowledge_network.user.service.impl;

import com.knowledge_network.support.exceptions.OAuth2ScopeException;
import com.knowledge_network.user.service.OAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pentonbin on 18-3-29
 */
@Service
public class OAuth2ServiceImpl implements OAuth2Service {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Override
    public void validateScope(String clientId, String scope) {
        Set<String> scopes = null;
        if (scope.contains(",")) {
            scopes = new HashSet<>(Arrays.asList(scope.split(",")));
        }
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        Assert.notNull(scopes);
        Assert.notNull(clientDetails);

        Set<String> register = clientDetails.getScope();
        for (String s : scopes) {
            if (!register.contains(s)) {
                throw new OAuth2ScopeException(s);
            }
        }
    }

    @Override
    public void checkScope(String scope) {
        Set<String> scopes = null;
        if (scope.contains(",")) {
            scopes = new HashSet<>(Arrays.asList(scope.split(",")));
        }
        // TODO
    }
}
