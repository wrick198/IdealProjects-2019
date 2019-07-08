package com.knowledge_network.user.service.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.user.dao.ParentDAO;
import com.knowledge_network.user.entity.Parent;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.ParentService;
import com.knowledge_network.user.service.StudentService;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.vo.ParentInfoVO;
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
public class ParentServiceImpl implements ParentService {

    @Autowired
    private ParentDAO parentDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;

    @Override
    public Parent getParentById(Integer id) {
        return parentDAO.findParentById(id);
    }

    @Override
    public void updateParentInfo(Parent parent, ParentInfoVO parentInfoVO) {
        initParentInfo(parent, parentInfoVO);
        parentDAO.updateParent(parent);
    }

    @Override
    public void initParentInfo(Parent parent, ParentInfoVO parentInfoVO) {
        // 家长没有额外的信息
        userService.initUserInfo(parent, parentInfoVO);
    }

    @Override
    public Parent getParentByUsername(String username) {
        Asserts.hasText(username, ResponseErrorEnum.USERNAME_NOT_NULL);
        return parentDAO.findParentByUsername(username);
    }

    @Override
    public void addChild(Parent parent, Student student) {
        if (parent == null || student == null) {
            return;
        }
        studentService.addParent(student, parent);
    }

    @Override
    public void removeChild(Parent parent, Student student) {
        if (parent == null || student == null) {
            return;
        }
        studentService.removeParent(student, parent);
    }

    @Override
    public void createParent(Parent parent) {
        if (parent != null) {
            parent.setRegisterDatetime(new Timestamp(System.currentTimeMillis()));
            parent.setEnable(User.USER_ENABLE);
            parent.setLogoff(User.USER_NON_LOGOFF);
            parentDAO.createParent(parent);
        }
    }

    @Override
    public List<Parent> getAllParentPage(int start, int rows) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "logoff", User.USER_NON_LOGOFF));
        return getParentPageByConditions(start, rows, conditions);
    }

    @Override
    public long getAllParentCount() {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "logoff", User.USER_NON_LOGOFF));
        return getParentCountByConditions(conditions);
    }

    @Override
    public List<Parent> getParentPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions) {
        if (start < 0 || rows <= 0) {
            return new ArrayList<>();
        }
        // 排序：按照注册时间降序排列
        Order order = Order.desc("registerDatetime");
        // 筛选条件：conditions
        if (conditions != null && conditions.size() > 0) {
            return parentDAO.findParentByConditionsOrderPage(start, rows, order, conditions);
        }
        return parentDAO.findParentByOrderPage(start, rows, order);
    }

    @Override
    public long getParentCountByConditions(List<BaseHibernateDAO.Condition> conditions) {
        return parentDAO.getParentCountByConditions(conditions);
    }
}
