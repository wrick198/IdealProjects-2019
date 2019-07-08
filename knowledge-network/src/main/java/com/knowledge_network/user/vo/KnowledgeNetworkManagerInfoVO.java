package com.knowledge_network.user.vo;

import com.knowledge_network.user.entity.KnowledgeNetworkManager;

/**
 * Created by pentonbin on 17-12-14
 * <p>
 * 知识网络管理员页面数据对象
 */
public class KnowledgeNetworkManagerInfoVO extends TeacherInfoVO {

    public KnowledgeNetworkManagerInfoVO() {
    }

    public KnowledgeNetworkManagerInfoVO(KnowledgeNetworkManager knowledgeNetworkManager) {
        super(knowledgeNetworkManager);
    }
}
