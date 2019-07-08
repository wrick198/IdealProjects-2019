package com.knowledge_network.user.service.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.SystemManagerDAO;
import com.knowledge_network.user.entity.SystemManager;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.SystemManagerService;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.vo.SystemManagerInfoVO;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
@Service
public class SystemManagerServiceImpl implements SystemManagerService {

    @Autowired
    private SystemManagerDAO systemManagerDAO;
    @Autowired
    private UserService userService;

    @Override
    public SystemManager getSystemManagerById(Integer id) {
        return systemManagerDAO.findSystemManagerById(id);
    }

    @Override
    public void updateSystemManager(SystemManager systemManager, SystemManagerInfoVO systemManagerInfoVO) {
        initSystemManager(systemManager, systemManagerInfoVO);
        systemManagerDAO.updateSystemManager(systemManager);
    }

    @Override
    public void initSystemManager(SystemManager systemManager, SystemManagerInfoVO systemManagerInfoVO) {
        // 系统管理员没有额外的信息
        userService.initUserInfo(systemManager, systemManagerInfoVO);
    }

    @Override
    public void createSystemManager(SystemManager systemManager) {
        if (systemManager != null) {
            systemManager.setRegisterDatetime(new Timestamp(System.currentTimeMillis()));
            systemManager.setEnable(User.USER_ENABLE);
            systemManager.setLogoff(User.USER_NON_LOGOFF);
            systemManagerDAO.createSystemManager(systemManager);
        }
    }

    @Override
    public List<SystemManager> getAllSystemManagerPage(int start, int rows) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "logoff", User.USER_NON_LOGOFF));
        return getSystemManagerPageByConditions(start, rows, conditions);
    }

    @Override
    public long getAllSystemManagerCount() {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "logoff", User.USER_NON_LOGOFF));
        return getSystemManagerCountByConditions(conditions);
    }

    @Override
    public List<SystemManager> getSystemManagerPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions) {
        if (start < 0 || rows <= 0) {
            return new ArrayList<>();
        }
        // 排序：按照注册时间降序排列
        Order order = Order.desc("registerDatetime");
        // 筛选条件：conditions
        if (conditions != null && conditions.size() > 0) {
            return systemManagerDAO.findSystemManagerByConditionsOrderPage(start, rows, order, conditions);
        }
        return systemManagerDAO.findSystemManagerByOrderPage(start, rows, order);
    }

    @Override
    public long getSystemManagerCountByConditions(List<BaseHibernateDAO.Condition> conditions) {
        return systemManagerDAO.getSystemManagerCountByConditions(conditions);
    }
}
