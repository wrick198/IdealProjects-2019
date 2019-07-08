package com.knowledge_network.user.service.impl;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.exceptions.UserNotFoundException;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.JsonMapper;
import com.knowledge_network.user.dao.UserDAO;
import com.knowledge_network.user.entity.*;
import com.knowledge_network.user.service.*;
import com.knowledge_network.user.service.inner.InnerUserRolePermissionService;
import com.knowledge_network.user.vo.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by pentonbin on 17-12-2
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ParentService parentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private KnowledgeNetworkManagerService knowledgeNetworkManagerService;
    @Autowired
    private SystemManagerService systemManagerService;
    @Autowired
    private InnerUserRolePermissionService innerUserRolePermissionService;
    @Autowired
    private UserRoleRelationshipService userRoleRelationshipService;

    @Override
    public User getUserById(Integer id) {
        return userDAO.findUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        Asserts.hasText(username, ResponseErrorEnum.USERNAME_NOT_NULL);
        return userDAO.findUserByUsername(username);
    }

    @Override
    public UserInfoVO getUserInfo(String userId) throws UserNotFoundException {
        Asserts.notNull(userId, ResponseErrorEnum.USER_ID_NOT_NULL);

        Integer id = Integer.parseInt(userId);
        User user = getUserById(id);
        if (user == null || user.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        UserRole userRole = user.getInitialRole();
        UserInfoVO userInfoVO = null;
        if (userRole.getRole().equalsIgnoreCase(UserRole.STUDENT)) {
            userInfoVO = new StudentInfoVO((Student) user, true);
        } else if (userRole.getRole().equalsIgnoreCase(UserRole.PARENT)) {
            userInfoVO = new ParentInfoVO((Parent) user, true);
        } else if (userRole.getRole().equalsIgnoreCase(UserRole.TEACHER)) {
            userInfoVO = new TeacherInfoVO((Teacher) user);
        } else if (userRole.getRole().equalsIgnoreCase(UserRole.KNOWLEDGE_NETWORK_MANAGER)) {
            userInfoVO = new KnowledgeNetworkManagerInfoVO((KnowledgeNetworkManager) user);
        } else if (userRole.getRole().equalsIgnoreCase(UserRole.SYSTEM_MANAGER)) {
            userInfoVO = new SystemManagerInfoVO((SystemManager) user);
        }
        return userInfoVO;
    }

    @Override
    public void updateUserPassword(User user, String newPassword) {
        Asserts.hasText(newPassword, ResponseErrorEnum.USER_NEW_PASSWORD_NOT_NULL);
        user.setPassword(newPassword);
        userDAO.updateUser(user);
    }

    @Override
    public void updateUserInfo(User user, UserInfoVO userInfoVO) {
        initUserInfo(user, userInfoVO);
        userDAO.updateUser(user);
    }

    @Override
    public void initUserInfo(User user, UserInfoVO userInfoVO) {
        user.setRealName(userInfoVO.getRealName());
        user.setSex(userInfoVO.getSex());
        user.setImageUrl(userInfoVO.getImageUrl());
        user.setBirthday(userInfoVO.getBirthday());
        user.setDescription(userInfoVO.getDescription());
        user.setAddress(userInfoVO.getAddress());
    }

    @Override
    public List<User> getAllUserPage(int start, int rows) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "logoff", User.USER_NON_LOGOFF));
        return getUserPageByConditions(start, rows, conditions);
    }

    @Override
    public List<User> getUserPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions) {
        if (start < 0 || rows <= 0) {
            return new ArrayList<>();
        }
        // 排序：按照注册时间降序排列
        Order order = Order.desc("registerDatetime");
        // 筛选条件：conditions
        if (conditions != null && conditions.size() > 0) {
            return userDAO.findUserByConditionsOrderPage(start, rows, order, conditions);
        }
        return userDAO.findUserByOrderPage(start, rows, order);
    }

    @Override
    public void createUser(User user) {
        if (user != null) {
            user.setRegisterDatetime(new Timestamp(System.currentTimeMillis()));
            user.setEnable(User.USER_ENABLE);
            user.setLogoff(User.USER_NON_LOGOFF);
            userDAO.updateUser(user);
        }
    }

    @Override
    public void register(String userRole, String username, String password, Map<String, Object> datas) {
        Asserts.hasText(username, ResponseErrorEnum.USERNAME_NOT_NULL);
        Asserts.hasText(password, ResponseErrorEnum.USER_PASSWORD_NOT_NULL);
        Asserts.hasText(userRole, ResponseErrorEnum.USER_ROLE_NOT_NULL);

        User user = null;
        if (userRole.equalsIgnoreCase(UserRole.STUDENT)) {
            user = new Student();
        } else if (userRole.equalsIgnoreCase(UserRole.PARENT)) {
            user = new Parent();
        } else if (userRole.equalsIgnoreCase(UserRole.TEACHER)) {
            user = new Teacher();
            Asserts.hasText((String) datas.get("certificateNum"), ResponseErrorEnum.TEACHER_CERT_NUM_NOT_NULL);
            ((Teacher) user).setCertificateNum((String) datas.get("certificateNum"));
        } else {
            throw new IllegalArgumentException("userRole NOT supported");
        }
        if (user != null) {
            user.setUsername(username);
            user.setPassword(password);
            // TODO 系统默认头像
            user.setImageUrl("https://pic1.zhimg.com/80/15eeebc5e0606cb465cb5b01ab89062f_hd.jpg");
            createUser(user);

            UserRoleRelationship relationship = null;
            Collection<UserRole> allUserRoles = innerUserRolePermissionService.getAllUserRoles();
            for (UserRole role : allUserRoles) {
                if (role.getRole().equalsIgnoreCase(userRole)) {
                    relationship = new UserRoleRelationship(user, role, UserRoleRelationship.USER_ROLE_INITIAL_ROLE);
                    userRoleRelationshipService.save(relationship);
                    break;
                }
            }
        }
    }

    @Override
    public long getAllUserCount() {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "logoff", User.USER_NON_LOGOFF));
        return getUserCountByConditions(conditions);
    }

    @Override
    public long getUserCountByConditions(List<BaseHibernateDAO.Condition> conditions) {
        return userDAO.getUserCountByConditions(conditions);
    }

    @Override
    public void updateUserInfo(User user, String json) {
        Asserts.hasText(json, ResponseErrorEnum.BAD_REQUEST);

        UserRole userRole = user.getInitialRole();
        if (userRole.getRole().equalsIgnoreCase(UserRole.STUDENT)) {
            studentService.updateStudentInfo((Student) user, JsonMapper.json2Obj(json, StudentInfoVO.class));
        } else if (userRole.getRole().equalsIgnoreCase(UserRole.PARENT)) {
            parentService.updateParentInfo((Parent) user, JsonMapper.json2Obj(json, ParentInfoVO.class));
        } else if (userRole.getRole().equalsIgnoreCase(UserRole.TEACHER)) {
            teacherService.updateTeacherInfo((Teacher) user, JsonMapper.json2Obj(json, TeacherInfoVO.class));
        } else if (userRole.getRole().equalsIgnoreCase(UserRole.KNOWLEDGE_NETWORK_MANAGER)) {
            knowledgeNetworkManagerService.updateKnowledgeNetworkManagerInfo((KnowledgeNetworkManager) user,
                    JsonMapper.json2Obj(json, KnowledgeNetworkManagerInfoVO.class));
        } else if (userRole.getRole().equalsIgnoreCase(UserRole.SYSTEM_MANAGER)) {
            systemManagerService.updateSystemManager((SystemManager) user,
                    JsonMapper.json2Obj(json, SystemManagerInfoVO.class));
        }
    }

    @Override
    public void disableUser(Integer id) {
        Asserts.notNull(id, ResponseErrorEnum.USER_ID_NOT_NULL);

        User user = getUserById(id);
        if (user != null) {
            user.setEnable(User.USER_DISABLE);
        }
        userDAO.updateUser(user);
    }

    @Override
    public void enableUser(Integer id) {
        Asserts.notNull(id, ResponseErrorEnum.USER_ID_NOT_NULL);

        User user = getUserById(id);
        if (user != null) {
            user.setEnable(User.USER_ENABLE);
        }
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        Asserts.notNull(id, ResponseErrorEnum.USER_ID_NOT_NULL);

        User user = getUserById(id);
        if (user != null) {
            user.setLogoff(User.USER_LOGOFF);
        }
        userDAO.updateUser(user);
    }

    @Override
    public boolean checkUsernameExists(String username) {
        Asserts.hasText(username, ResponseErrorEnum.USERNAME_NOT_NULL);

        return getUserByUsername(username) != null;
    }

    @Override
    public Integer getUserIdByUsername(String username) {
        Asserts.hasText(username, ResponseErrorEnum.USERNAME_NOT_NULL);

        User user = getUserByUsername(username);
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    @Override
    public void removeUser(User user) {
        userDAO.removeUser(user);
    }
}
