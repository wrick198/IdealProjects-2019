package com.knowledge_network.user.service.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.dao.KnowledgeNetworkManagerDAO;
import com.knowledge_network.user.entity.KnowledgeNetworkManager;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.KnowledgeNetworkManagerService;
import com.knowledge_network.user.service.TeacherService;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.vo.KnowledgeNetworkManagerInfoVO;
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
public class KnowledgeNetworkManagerServiceImpl implements KnowledgeNetworkManagerService {

    @Autowired
    private KnowledgeNetworkManagerDAO knowledgeNetworkManagerDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;

    @Override
    public KnowledgeNetworkManager getKnowledgeNetworkManagerById(Integer id) {
        return knowledgeNetworkManagerDAO.findKnowledgeNetworkManagerById(id);
    }

    @Override
    public void updateKnowledgeNetworkManagerInfo(KnowledgeNetworkManager knowledgeNetworkManager,
                                                  KnowledgeNetworkManagerInfoVO knowledgeNetworkManagerInfoVO) {
        initKnowledgeNetworkManagerInfo(knowledgeNetworkManager, knowledgeNetworkManagerInfoVO);
        knowledgeNetworkManagerDAO.updateKnowledgeNetworkManager(knowledgeNetworkManager);
    }

    @Override
    public void initKnowledgeNetworkManagerInfo(KnowledgeNetworkManager knowledgeNetworkManager, KnowledgeNetworkManagerInfoVO knowledgeNetworkManagerInfoVO) {
        // 知识网络专家没有教师以外其他信息
        teacherService.initTeacherInfo(knowledgeNetworkManager, knowledgeNetworkManagerInfoVO);
    }

    @Override
    public void createKnowledgeNetworkManager(KnowledgeNetworkManager knowledgeNetworkManager) {
        if (knowledgeNetworkManager != null) {
            knowledgeNetworkManager.setRegisterDatetime(new Timestamp(System.currentTimeMillis()));
            knowledgeNetworkManager.setEnable(User.USER_ENABLE);
            knowledgeNetworkManager.setLogoff(User.USER_NON_LOGOFF);
            knowledgeNetworkManagerDAO.createKnowledgeNetworkManager(knowledgeNetworkManager);
        }
    }

    @Override
    public List<KnowledgeNetworkManager> getAllKnowledgeNetworkManagerPage(int start, int rows) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "logoff", User.USER_NON_LOGOFF));
        return getKnowledgeNetworkManagerPageByConditions(start, rows, conditions);
    }

    @Override
    public long getAllKnowledgeNetworkManagerCount() {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "logoff", User.USER_NON_LOGOFF));
        return getKnowledgeNetworkManagerCountByConditions(conditions);
    }

    @Override
    public List<KnowledgeNetworkManager> getKnowledgeNetworkManagerPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions) {
        if (start < 0 || rows <= 0) {
            return new ArrayList<>();
        }
        // 排序：按照注册时间降序排列
        Order order = Order.desc("registerDatetime");
        // 筛选条件：conditions
        if (conditions != null && conditions.size() > 0) {
            return knowledgeNetworkManagerDAO.findKnowledgeNetworkManagerByConditionsOrderPage(start, rows, order, conditions);
        }
        return knowledgeNetworkManagerDAO.findKnowledgeNetworkManagerByOrderPage(start, rows, order);
    }

    @Override
    public long getKnowledgeNetworkManagerCountByConditions(List<BaseHibernateDAO.Condition> conditions) {
        return knowledgeNetworkManagerDAO.getKnowledgeNetworkManagerCountByConditions(conditions);
    }
}
