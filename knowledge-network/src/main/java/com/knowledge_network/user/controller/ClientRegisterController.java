package com.knowledge_network.user.controller;

import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.user.service.ClientRegisterService;
import com.knowledge_network.user.service.OAuth2Service;
import com.knowledge_network.user.vo.ClientDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pentonbin on 18-3-29
 * <p>
 * 提供OAuth2.0客户端注册/登记
 */
@RestController
@RequestMapping(value = "/clientRegister")
public class ClientRegisterController {

    @Autowired
    private OAuth2Service oauth2Service;
    @Autowired
    private ClientRegisterService clientRegisterService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseResult<String> clientRegister(@RequestBody ClientDetailsVO clientDetailsVO) {
        Assert.hasText(clientDetailsVO.getClientId());
        Assert.hasText(clientDetailsVO.getScope());

        String clientId = clientDetailsVO.getClientId();
        String scope = clientDetailsVO.getScope();

        oauth2Service.checkScope(scope);
        clientRegisterService.register(clientDetailsVO);
        return ResponseResult.newSucceedInstance("Succeed", null);
    }
}
