package com.knowledge_network.auth.oauth2;

import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.entity.UserRoleRelationship;
import com.knowledge_network.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.*;

/**
 * Created by pentonbin on 18-1-8
 * <p>
 * OAuth2 Token的增强器，往返回的Token数据中添加用户id
 */
public class OAuth2UserTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken result = (DefaultOAuth2AccessToken) accessToken;

        Map<String, Object> additionalInformation = new HashMap<String, Object>();

        Object userDetails = authentication.getPrincipal();
        if (userDetails instanceof UserDetails) {
            String username = ((UserDetails) userDetails).getUsername();
            User user = userService.getUserByUsername(username);

            additionalInformation.put("userId", user.getId());
            additionalInformation.put("username", user.getUsername());
            additionalInformation.put("imageUrl", user.getImageUrl());

            Collection<UserRoleRelationship> relationships = user.getUserRoleRelationships();
            List<String> userRole = new ArrayList<>();
            for (UserRoleRelationship relationship : relationships) {
                userRole.add(relationship.getUserRole().getRole());
            }
            additionalInformation.put("userRole", userRole);
        }

        result.setAdditionalInformation(additionalInformation);
        return result;
    }
}
