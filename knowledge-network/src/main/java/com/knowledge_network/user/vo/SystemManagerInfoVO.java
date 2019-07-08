package com.knowledge_network.user.vo;

import com.knowledge_network.user.entity.SystemManager;

/**
 * Created by pentonbin on 17-12-14
 * <p>
 * 系统管理员的页面数据对象
 */
public class SystemManagerInfoVO extends UserInfoVO {

    public SystemManagerInfoVO() {
    }

    public SystemManagerInfoVO(SystemManager systemManager) {
        super(systemManager);
    }
}
