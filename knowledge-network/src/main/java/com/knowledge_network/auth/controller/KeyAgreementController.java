package com.knowledge_network.auth.controller;

import com.knowledge_network.auth.security.AgreedDigitalSignatureKeys;
import com.knowledge_network.auth.security.ds.DigitalSignatureSigner;
import com.knowledge_network.auth.security.ds.impl.ECDSASigner;
import com.knowledge_network.auth.security.entity.KeyAgreement;
import com.knowledge_network.auth.security.entity.SymmetricEncryption;
import com.knowledge_network.auth.security.ka.KeyAgreementCalculator;
import com.knowledge_network.auth.security.ka.KeyAgreementGenerator;
import com.knowledge_network.auth.security.ka.impl.Curve25519Calculator;
import com.knowledge_network.auth.security.ka.impl.Curve25519Generator;
import com.knowledge_network.auth.security.service.KeyAgreementService;
import com.knowledge_network.auth.security.service.SymmetricEncryptionService;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.StringUtils;
import com.knowledge_network.user.vo.web.ClientKeyAgreementVO;
import com.knowledge_network.user.vo.web.ServerKeyAgreementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by pentonbin on 18-4-3
 * <p>
 * 处理客户端密钥协商
 */
@RestController
@RequestMapping(value = "/keyAgreement")
public class KeyAgreementController {

    @Autowired
    private KeyAgreementService keyAgreementService;
    @Autowired
    private SymmetricEncryptionService symmetricEncryptionService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseResult<ServerKeyAgreementVO> keyAgreement(HttpServletRequest request, @RequestBody ClientKeyAgreementVO keyAgreementVO) {
        // 客户端唯一序号
        String clientSequence = request.getHeader("clientSequence");
        // 客户端密钥协商公钥
        String clientPublicKey = keyAgreementVO.getClientPublicKey();

        KeyAgreementGenerator generator = new Curve25519Generator();
        // 服务端随机生成密钥协商密钥对
        byte[] serverPublicKey = generator.getPublicKey();
        byte[] serverPrivateKey = generator.getPrivateKey();
        KeyAgreement ka = null;
        if (keyAgreementService.containClientSequence(clientSequence)) {
            ka = keyAgreementService.getByClientSequence(clientSequence);
        } else {
            ka = new KeyAgreement();
        }

        ka.setClientSequence(clientSequence);
        ka.setClientPublicKey(clientPublicKey);
        ka.setServerPublicKey(StringUtils.encodeHexStr(serverPublicKey));
        ka.setServerPrivateKey(StringUtils.encodeHexStr(serverPrivateKey));
        ka.setCreateTime(new Timestamp(System.currentTimeMillis()));
        ka.setClientId(keyAgreementVO.getClientId());
        // 保存到数据库
        keyAgreementService.createNewKeyAgreement(ka);

        // 计算密钥协商公钥的签名
        DigitalSignatureSigner signer = new ECDSASigner();
        // 生成签名
        byte[] sign = signer.calculateSignature(StringUtils.decodeHexStr(AgreedDigitalSignatureKeys.PRIVATE_KEY), serverPublicKey);

        // 返回数据格式
        ServerKeyAgreementVO serverKeyAgreementVO = new ServerKeyAgreementVO();
        serverKeyAgreementVO.setServerPublicKey(StringUtils.encodeHexStr(serverPublicKey));
        serverKeyAgreementVO.setSignature(StringUtils.encodeHexStr(sign));

        // 计算对称加密共享密钥
        KeyAgreementCalculator calculator = new Curve25519Calculator();
        byte[] sharedKey = calculator.calculateSharedSecret(StringUtils.decodeHexStr(clientPublicKey), serverPrivateKey);
        SymmetricEncryption se = null;
        if (symmetricEncryptionService.containClientSequence(clientSequence)) {
            se = symmetricEncryptionService.getByClientSequence(clientSequence);
        } else {
            se = new SymmetricEncryption();
        }
        se.setClientSequence(clientSequence);
        se.setKey(StringUtils.encodeHexStr(sharedKey));
        se.setCreateTime(new Timestamp(System.currentTimeMillis()));
        se.setTimestampSeq(System.currentTimeMillis());
        se.setClientId(keyAgreementVO.getClientId());

        // 保存到数据库
        symmetricEncryptionService.createNewSharedKey(se);

        return ResponseResult.newSucceedInstance(null, serverKeyAgreementVO);
    }
}
