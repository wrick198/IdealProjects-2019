package com.knowledge_network.user.controller;

import com.knowledge_network.auth.security.anno.AuthorizationPermission;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.exceptions.UserNotFoundException;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.support.utils.IOUtils;
import com.knowledge_network.user.entity.Parent;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.*;
import com.knowledge_network.user.vo.ParentInfoVO;
import com.knowledge_network.user.vo.StudentInfoVO;
import com.knowledge_network.user.vo.UserInfoVO;
import com.knowledge_network.user.vo.web.UserPasswordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by pentonbin on 17-12-9
 * <p>
 * 用户设置中心的Controller
 */
@RestController
@RequestMapping(value = "/user/settings/")
public class UserSettingsController {

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


    /**
     * 加载用户个人信息
     *
     * @param userId 登录的用户id
     * @return 响应对象
     * @throws UserNotFoundException
     */
    @AuthorizationPermission(permissions = {"USER_RETRIEVE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/info/{userId}", method = RequestMethod.GET)
    public ResponseResult<? extends UserInfoVO> userInfoOverview(@PathVariable String userId) throws UserNotFoundException {
        UserInfoVO userInfoVO = userService.getUserInfo(userId);
        ResponseResult<UserInfoVO> response = ResponseResult.newSucceedInstance(null, userInfoVO);
        return response;
    }

    /**
     * 修改用户密码
     *
     * @param userId       登录用户id
     * @param userPassword 前端json
     * @return 响应对象
     * @throws UserNotFoundException
     */
    @AuthorizationPermission(permissions = {"USER_UPDATE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/resetPassword/{userId}", method = RequestMethod.POST)
    public ResponseResult<String> resetPassword(@PathVariable String userId, @RequestBody UserPasswordVO userPassword)
            throws UserNotFoundException {
        Asserts.notNull(userPassword, ResponseErrorEnum.BAD_REQUEST);
        Asserts.hasText(userPassword.getOldPassword(), ResponseErrorEnum.USER_PASSWORD_NOT_NULL);

        Integer id = Integer.parseInt(userId);
        User loginUser = userService.getUserById(id);
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        if (loginUser.getPassword().equals(userPassword.getOldPassword())) {
            loginUser.setPassword(userPassword.getNewPassword());
            userService.updateUserPassword(loginUser, userPassword.getNewPassword());
            return ResponseResult.newSucceedInstance("Password Updated", null);
        } else {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.USER_RESET_PASSWORD_NOT_PATCH, null);
        }
    }

    /**
     * 修改用户个人信息
     *
     * @param userId  要修改的用户
     * @param request 请求
     * @return
     * @throws UserNotFoundException
     */
    @AuthorizationPermission(permissions = {"USER_UPDATE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/info/{userId}", method = RequestMethod.PUT)
    public ResponseResult<String> updateUserInfo(@PathVariable String userId, HttpServletRequest request)
            throws UserNotFoundException {
        Integer id = Integer.parseInt(userId);
        User loginUser = userService.getUserById(id);
        if (loginUser == null || loginUser.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }

        // 读取JSON数据，此处不适合使用@RequestBody处理
        // 因为@RequestBody注解的对象没办法进行强制类型转换
        String json = IOUtils.readDataFromHttpServletRequest(request);
        userService.updateUserInfo(loginUser, json);

        return ResponseResult.newSucceedInstance("Personal Information Updated", null);
    }

    /**
     * 学生功能：学生添加家长
     *
     * @param userId       学生id
     * @param parentInfoVO 家长对象的信息（其中必须包含家长用户名username）
     * @return
     * @throws UserNotFoundException
     */
    @AuthorizationPermission(permissions = {"STUDENT_UPDATE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/addParent/{userId}", method = RequestMethod.PUT)
    public ResponseResult<String> addParent(@PathVariable String userId, @RequestBody ParentInfoVO parentInfoVO)
            throws UserNotFoundException {
        Integer id = Integer.parseInt(userId);
        Student student = studentService.getStudentById(id);
        if (student == null || student.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        String parentUsername = parentInfoVO.getUsername();
        Parent parent = parentService.getParentByUsername(parentUsername);
        if (parent == null) {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.PARENT_NOT_FOUND, null);
        }

        studentService.addParent(student, parent);
        return ResponseResult.newSucceedInstance("Added Successfully", null);
    }

    /**
     * 学生功能：学生移除关联的家长（至少要包含username或id）
     *
     * @param userId       学生用户id
     * @param parentInfoVO 要移除的家长（必须包含username或id）
     * @return
     * @throws UserNotFoundException
     */
    @AuthorizationPermission(permissions = {"STUDENT_UPDATE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/removeParent/{userId}", method = RequestMethod.PUT)
    public ResponseResult<String> removeParent(@PathVariable String userId, @RequestBody ParentInfoVO parentInfoVO)
            throws UserNotFoundException {
        Integer id = Integer.parseInt(userId);
        Student student = studentService.getStudentById(id);
        if (student == null || student.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        String parentUsername = parentInfoVO.getUsername();
        Integer parentId = parentInfoVO.getId();
        Parent parent = parentService.getParentById(parentId);
        if (parent == null) {
            parent = parentService.getParentByUsername(parentUsername);
            if (parent == null) {
                return ResponseResult.newErrorInstance(ResponseErrorEnum.PARENT_NOT_FOUND, null);
            }
        }

        studentService.removeParent(student, parent);
        return ResponseResult.newSucceedInstance("Removed Successfully", null);
    }

    /**
     * 家长功能：家长获取其关联的孩子
     *
     * @param userId
     * @return
     * @throws UserNotFoundException
     */
    @AuthorizationPermission(permissions = {"STUDENT_RETRIEVE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/myChild/{userId}", method = RequestMethod.GET)
    public ResponseResult<List<StudentInfoVO>> myChild(@PathVariable String userId)
            throws UserNotFoundException {
        Integer id = Integer.parseInt(userId);
        Parent parent = parentService.getParentById(id);
        if (parent == null || parent.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        Collection<Student> students = parent.getStudents();
        List<StudentInfoVO> studentList = new ArrayList<>();
        for (Student student : students) {
            StudentInfoVO infoVO = new StudentInfoVO(student, false);
            studentList.add(infoVO);
        }
        return ResponseResult.newSucceedInstance(null, studentList);
    }

    /**
     * 家长功能：添加关联的孩子对象
     *
     * @param userId        家长用户id
     * @param studentInfoVO 学生信息（必须包含username）
     * @return
     * @throws UserNotFoundException
     */
    @AuthorizationPermission(permissions = {"PARENT_UPDATE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/addChild/{userId}", method = RequestMethod.PUT)
    public ResponseResult<String> addChild(@PathVariable String userId, @RequestBody StudentInfoVO studentInfoVO)
            throws UserNotFoundException {
        Asserts.notNull(studentInfoVO, ResponseErrorEnum.BAD_REQUEST);

        Integer id = Integer.parseInt(userId);
        Parent parent = parentService.getParentById(id);
        if (parent == null || parent.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        String studentUsername = studentInfoVO.getUsername();
        Student student = studentService.getStudentByUsername(studentUsername);
        if (student == null) {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.CHILD_NOT_FOUND, null);
        }

        parentService.addChild(parent, student);
        return ResponseResult.newSucceedInstance("Added Successfully", null);
    }

    /**
     * 家长功能：移除关联的孩子对象
     *
     * @param userId        家长用户id
     * @param studentInfoVO 学生用户信息
     * @return
     */
    @AuthorizationPermission(permissions = {"PARENT_UPDATE"}, checkCurrentLoginUser = true)
    @RequestMapping(value = "/removeChild/{userId}", method = RequestMethod.PUT)
    public ResponseResult<String> removeChild(@PathVariable String userId, @RequestBody StudentInfoVO studentInfoVO)
            throws UserNotFoundException {
        Asserts.notNull(studentInfoVO, ResponseErrorEnum.BAD_REQUEST);

        Integer id = Integer.parseInt(userId);
        Parent parent = parentService.getParentById(id);
        if (parent == null || parent.getLogoff() == User.USER_LOGOFF) {
            throw new UserNotFoundException(userId);
        }
        String studentUsername = studentInfoVO.getUsername();
        Student student = studentService.getStudentByUsername(studentUsername);
        if (student == null) {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.CHILD_NOT_FOUND, null);
        }

        parentService.removeChild(parent, student);
        return ResponseResult.newSucceedInstance("Removed Successfully", null);
    }
}
