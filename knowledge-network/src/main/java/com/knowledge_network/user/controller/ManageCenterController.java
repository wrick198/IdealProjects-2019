package com.knowledge_network.user.controller;

import com.knowledge_network.auth.security.anno.AuthorizationPermission;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.exceptions.UserNotFoundException;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.IOUtils;
import com.knowledge_network.support.utils.JsonMapper;
import com.knowledge_network.support.utils.VOUtils;
import com.knowledge_network.user.entity.*;
import com.knowledge_network.user.service.*;
import com.knowledge_network.user.service.inner.InnerUserRolePermissionService;
import com.knowledge_network.user.vo.*;
import com.knowledge_network.user.vo.web.ConditionVO;
import com.knowledge_network.user.vo.web.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by pentonbin on 17-12-23
 * <p>
 * 系统管理员管理中心的Controller
 */
@RestController
@RequestMapping(value = "/admin/manageCenter")
public class ManageCenterController {

    @Autowired
    private UserService userService;
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

    /**
     * 获取当前系统的用户列表（分页）
     * 支持添加角色进行筛选（不添加默认则不进行筛选）
     *
     * @param pageVO 分页数据
     * @return 分页的用户
     */
    @AuthorizationPermission(permissions = {"USER_CREATE", "USER_RETRIEVE", "USER_UPDATE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/userManagement", method = RequestMethod.PUT)
    public ResponseResult<ListVO<? extends UserInfoVO>> userManagement(@RequestBody PageVO pageVO) {
        Asserts.notNull(pageVO, ResponseErrorEnum.BAD_REQUEST);
        Asserts.notNull(pageVO.getStart(), ResponseErrorEnum.OTHER_START_NOT_NULL);
        Asserts.notNull(pageVO.getRows(), ResponseErrorEnum.OTHER_ROWS_NOT_NULL);

        String userRole = null;
        if (pageVO.getConditions() != null && pageVO.getConditions().size() > 0) {
            // 只支持用户角色userRole的筛选
            for (ConditionVO conditionVO : pageVO.getConditions()) {
                if (conditionVO.getProperty().equalsIgnoreCase("userRole")
                        && conditionVO.getOp().equalsIgnoreCase(BaseHibernateDAO.ConditionType.EQ.getOp())) {
                    userRole = (String) conditionVO.getValue();
                    break;
                }
            }
        }
        long totalCount = 0;
        List<? extends User> users = null;
        List<UserInfoVO> userInfos = new ArrayList<>();
        if (userRole == null) { // 不需要根据角色进行筛选
            users = userService.getAllUserPage(pageVO.getStart(), pageVO.getRows());
            totalCount = userService.getAllUserCount();
        } else { // 根据角色进行筛选
            if (userRole.equalsIgnoreCase(UserRole.STUDENT)) {
                users = studentService.getAllStudentPage(pageVO.getStart(), pageVO.getRows());
                totalCount = studentService.getAllStudentCount();
            } else if (userRole.equalsIgnoreCase(UserRole.PARENT)) {
                users = parentService.getAllParentPage(pageVO.getStart(), pageVO.getRows());
                totalCount = parentService.getAllParentCount();
            } else if (userRole.equalsIgnoreCase(UserRole.TEACHER)) {
                users = teacherService.getAllTeacherPage(pageVO.getStart(), pageVO.getRows());
                totalCount = teacherService.getAllTeacherCount();
            } else if (userRole.equalsIgnoreCase(UserRole.KNOWLEDGE_NETWORK_MANAGER)) {
                users = knowledgeNetworkManagerService.getAllKnowledgeNetworkManagerPage(pageVO.getStart(), pageVO.getRows());
                totalCount = knowledgeNetworkManagerService.getAllKnowledgeNetworkManagerCount();
            } else if (userRole.equalsIgnoreCase(UserRole.SYSTEM_MANAGER)) {
                users = systemManagerService.getAllSystemManagerPage(pageVO.getStart(), pageVO.getRows());
                totalCount = systemManagerService.getAllSystemManagerCount();
            }
        }
        if (users != null) {
            for (User user : users) {
                userInfos.add(new UserInfoVO(user));
            }
        }
        return ResponseResult.newSucceedInstance(null,
                new ListVO<UserInfoVO>(totalCount, pageVO.getStart(), userInfos.size(), userInfos));
    }

    /**
     * 添加新用户
     *
     * @param request 请求
     * @return
     */
    @AuthorizationPermission(permissions = {"USER_CREATE", "USER_RETRIEVE", "USER_UPDATE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseResult<String> addUser(HttpServletRequest request) {
        String json = IOUtils.readDataFromHttpServletRequest(request);
        Map<String, Object> jsonMap = JsonMapper.json2Map(json);

        Asserts.hasText((String) jsonMap.get("userRole"), ResponseErrorEnum.USER_ROLE_NOT_NULL);
        String userRole = (String) jsonMap.get("userRole");

        User user = null;
        if (userRole.equalsIgnoreCase(UserRole.STUDENT)) {
            StudentInfoVO studentInfoVO = JsonMapper.json2Obj(json, StudentInfoVO.class);
            user = new Student();
            studentService.initStudentInfo((Student) user, studentInfoVO);

        } else if (userRole.equalsIgnoreCase(UserRole.PARENT)) {
            user = new Parent();
            ParentInfoVO parentInfoVO = JsonMapper.json2Obj(json, ParentInfoVO.class);
            parentService.initParentInfo((Parent) user, parentInfoVO);

        } else if (userRole.equalsIgnoreCase(UserRole.TEACHER)) {
            user = new Teacher();
            TeacherInfoVO teacherInfoVO = JsonMapper.json2Obj(json, TeacherInfoVO.class);
            teacherService.initTeacherInfo((Teacher) user, teacherInfoVO);

        } else if (userRole.equalsIgnoreCase(UserRole.KNOWLEDGE_NETWORK_MANAGER)) {
            user = new KnowledgeNetworkManager();
            KnowledgeNetworkManagerInfoVO knowledgeNetworkManagerInfoVO = JsonMapper.json2Obj(json, KnowledgeNetworkManagerInfoVO.class);
            knowledgeNetworkManagerService.initKnowledgeNetworkManagerInfo((KnowledgeNetworkManager) user,
                    knowledgeNetworkManagerInfoVO);

        } else if (userRole.equalsIgnoreCase(UserRole.SYSTEM_MANAGER)) {
            user = new SystemManager();
            SystemManagerInfoVO systemManagerInfoVO = JsonMapper.json2Obj(json, SystemManagerInfoVO.class);
            systemManagerService.initSystemManager((SystemManager) user, systemManagerInfoVO);
        } else {
            throw new IllegalArgumentException("userRole NOT supported");
        }

        if (user != null) {
            Asserts.hasText((String) jsonMap.get("username"), ResponseErrorEnum.USERNAME_NOT_NULL);
            Asserts.hasText((String) jsonMap.get("password"), ResponseErrorEnum.USER_PASSWORD_NOT_NULL);
            user.setUsername((String) jsonMap.get("username"));
            user.setPassword((String) jsonMap.get("password"));
            userService.createUser(user);
            // 设置相应相应的角色

            Collection<UserRole> allUserRoles = innerUserRolePermissionService.getAllUserRoles();
            if (userRole.equalsIgnoreCase(UserRole.KNOWLEDGE_NETWORK_MANAGER)) { // 创建知识网络专家
                UserRole teacherRole = null;
                UserRole knowledgeNetworkManagerRole = null;
                for (UserRole role : allUserRoles) {
                    if (role.getRole().equalsIgnoreCase(UserRole.TEACHER)) {
                        teacherRole = role;
                    } else if (role.getRole().equalsIgnoreCase(UserRole.KNOWLEDGE_NETWORK_MANAGER)) {
                        knowledgeNetworkManagerRole = role;
                    }
                }
                userRoleRelationshipService.save( // 初始化角色为教师
                        new UserRoleRelationship(user, teacherRole, UserRoleRelationship.USER_ROLE_INITIAL_ROLE));
                userRoleRelationshipService.save( // 拥有知识网络专家的角色
                        new UserRoleRelationship(user, knowledgeNetworkManagerRole, UserRoleRelationship.USER_ROLE_NON_INITIAL_ROLE));
            } else {
                for (UserRole role : allUserRoles) { // 其他角色
                    if (role.getRole().equalsIgnoreCase(userRole)) {
                        UserRoleRelationship relationship = new UserRoleRelationship(user, role, UserRoleRelationship.USER_ROLE_INITIAL_ROLE);
                        userRoleRelationshipService.save(relationship);
                        break;
                    }
                }
            }
            return ResponseResult.newSucceedInstance("Created Succeed", null);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.CREATE_USER_FAILED, null);
    }

    /**
     * 修改用户信息（不能修改username、id、registerDatetime、enable、logoff、phone、email）
     * 实现与{@link UserSettingsController#updateUserInfo(String, HttpServletRequest)} 一致
     *
     * @param request
     * @return
     */
    @AuthorizationPermission(permissions = {"USER_CREATE", "USER_RETRIEVE", "USER_UPDATE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/editUser/{userId}", method = RequestMethod.PUT)
    public ResponseResult<String> editUser(@PathVariable String userId, HttpServletRequest request) throws UserNotFoundException {
        Integer id = Integer.parseInt(userId);
        User user = userService.getUserById(id);
        if (user == null || user.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        userService.updateUserInfo(user, IOUtils.readDataFromHttpServletRequest(request));

        return ResponseResult.newSucceedInstance("Personal Information Updated", null);
    }

    /**
     * 获取用户个人信息，实现与{@link UserSettingsController#userInfoOverview(String)} 一致
     *
     * @param userId 用户id
     * @return
     * @throws UserNotFoundException
     */
    @AuthorizationPermission(permissions = {"USER_CREATE", "USER_RETRIEVE", "USER_UPDATE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/userInfo/{userId}", method = RequestMethod.GET)
    public ResponseResult<? extends UserInfoVO> userInfo(@PathVariable String userId) throws UserNotFoundException {
        UserInfoVO userInfo = userService.getUserInfo(userId);
        return ResponseResult.newSucceedInstance(null, userInfo);
    }

    /**
     * 禁用某个用户
     *
     * @param userInfoVOs 用户id的列表
     * @return
     */
    @AuthorizationPermission(permissions = {"USER_CREATE", "USER_RETRIEVE", "USER_UPDATE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public ResponseResult<String> disableUsers(@RequestBody List<? extends UserInfoVO> userInfoVOs) {
        Asserts.notNull(userInfoVOs, ResponseErrorEnum.BAD_REQUEST);

        for (UserInfoVO userInfoVO : userInfoVOs) {
            userService.disableUser(userInfoVO.getId());
        }
        return ResponseResult.newSucceedInstance("Disabled Users", null);
    }

    /**
     * 取消禁用某个用户
     *
     * @param userInfoVOs 用户id的列表
     * @return
     */
    @AuthorizationPermission(permissions = {"USER_CREATE", "USER_RETRIEVE", "USER_UPDATE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    public ResponseResult<String> enableUsers(@RequestBody List<? extends UserInfoVO> userInfoVOs) {
        for (UserInfoVO userInfoVO : userInfoVOs) {
            userService.enableUser(userInfoVO.getId());
        }
        return ResponseResult.newSucceedInstance("Enabled Users", null);
    }

    /**
     * 将用户从系统中删除
     *
     * @param userInfoVOs 用户id列表
     * @return
     */
    @AuthorizationPermission(permissions = {"USER_CREATE", "USER_RETRIEVE", "USER_UPDATE", "USER_DELETE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/logoff", method = RequestMethod.POST)
    public ResponseResult<String> deleteUsers(@RequestBody List<? extends UserInfoVO> userInfoVOs) {
        for (UserInfoVO userInfoVO : userInfoVOs) {
            userService.deleteUser(userInfoVO.getId());
        }
        return ResponseResult.newSucceedInstance("Deleted Users", null);
    }

    /**
     * 根据用户名搜索用户
     *
     * @param pageVO
     * @return
     */
    @AuthorizationPermission(permissions = {"USER_CREATE", "USER_RETRIEVE", "USER_UPDATE", "USER_DELETE"}, checkCurrentLoginUser = false)
    @RequestMapping(value = "/search", method = RequestMethod.PUT)
    public ResponseResult<ListVO<? extends UserInfoVO>> searchUser(@RequestBody PageVO pageVO) {
        Asserts.notNull(pageVO, ResponseErrorEnum.BAD_REQUEST);
        Asserts.notEmpty(pageVO.getConditions(), ResponseErrorEnum.CONDITION_NOT_NULL);

        ListVO<UserInfoVO> listVO = new ListVO<>();
        if (pageVO.getConditions().get(0).getProperty().equalsIgnoreCase("username")
                && pageVO.getConditions().get(0).getOp().equalsIgnoreCase(BaseHibernateDAO.ConditionType.LIKE.getOp())) {
            BaseHibernateDAO.Condition condition = VOUtils.conditionVO2Condition(pageVO.getConditions().get(0));
            List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
            conditions.add(condition);

            List<User> users = userService.getUserPageByConditions(pageVO.getStart(), pageVO.getRows(), conditions);
            long count = userService.getUserCountByConditions(conditions);

            List<UserInfoVO> userInfoVOS = new ArrayList<>();
            for (User user : users) {
                userInfoVOS.add(new UserInfoVO(user));
            }

            listVO.setItems(userInfoVOS);
            listVO.setStart(pageVO.getStart());
            listVO.setNum(users.size());
            listVO.setTotalNum(count);

            return ResponseResult.newSucceedInstance(null, listVO);
        }
        return ResponseResult.newErrorInstance(ResponseErrorEnum.BAD_REQUEST, null);
    }
}