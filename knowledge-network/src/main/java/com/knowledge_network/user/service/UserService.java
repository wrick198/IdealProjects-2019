package com.knowledge_network.user.service;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.exceptions.UserNotFoundException;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.vo.UserInfoVO;

import java.util.List;
import java.util.Map;

/**
 * Created by pentonbin on 17-12-2
 * <p>
 * 提供与用户模块相关的服务接口
 */
public interface UserService {

    /**
     * 根据用户id获取对应的用户。
     * <p>
     * 注意：
     * 1. 如果有明确身份，请使用相应的Service获取用户对象，例如{@link ParentService#getParentById(Integer)}
     * 2. 对于有多重身份的用户对象，请勿使用该接口方法。参考上一点。
     *
     * @param id 用户id
     * @return 查找的用户
     */
    User getUserById(Integer id);

    /**
     * 根据用户名username获取对应的用户
     *
     * @param username 用户名
     * @return 查找的用户
     */
    User getUserByUsername(String username);

    /**
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     * @return
     */
    UserInfoVO getUserInfo(String userId) throws UserNotFoundException;

    /**
     * 修改用户密码
     *
     * @param user        需要修改密码的用户
     * @param newPassword 新的密码
     */
    void updateUserPassword(User user, String newPassword);

    /**
     * 更新用户个人信息（不支持更新具体用户（例如学生、老师等）的信息），将同步到数据库
     * <p>
     * 注意：
     * 1. id、username、registerDatetime不支持修改
     * 2. phone、email使用额外接口更新
     *
     * @param user       需要更新用户信息的用户
     * @param userInfoVO 用户信息
     */
    void updateUserInfo(User user, UserInfoVO userInfoVO);

    /**
     * 初始化用户个人信息（不支持更新具体用户（例如学生、老师等）的信息），不同步到数据库，只进行对象的初始化
     *
     * @param user       需要更新用户信息的用户
     * @param userInfoVO 用户信息
     */
    void initUserInfo(User user, UserInfoVO userInfoVO);

    /**
     * 分页获取所有用户信息
     *
     * @param start 起始下标
     * @param rows  每一页行数
     * @return
     */
    List<User> getAllUserPage(int start, int rows);

    /**
     * 分页获取用户对象
     *
     * @param start      起始下标
     * @param rows       每一页行数
     * @param conditions
     * @return 符合条件的用户
     */
    List<User> getUserPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 创建新用户对象
     *
     * @param user 新用户
     */
    void createUser(User user);

    /**
     * 创建新用户对象
     * 具体用户信息，例如学号、教师证号等，没有进行设置
     *
     * @param userRole 用户角色
     * @param username 用户名
     * @param password 用户密码
     * @param datas    额外的数据
     */
    void register(String userRole, String username, String password, Map<String, Object> datas);

    /**
     * 获取所有用户数量
     *
     * @return
     */
    long getAllUserCount();

    /**
     * 获取筛选条件后的所有用户的总数
     *
     * @param conditions 筛选条件
     * @return 用户总数
     */
    long getUserCountByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据JSON数据更新用户个人信息（支持更新具体用户（例如学生、老师等）的信息）
     *
     * @param user 用户
     * @param json 用户JSON数据
     */
    void updateUserInfo(User user, String json);

    /**
     * 禁用某个用户
     *
     * @param id 用户id
     */
    void disableUser(Integer id);

    /**
     * 取消禁用某个用户
     *
     * @param id 用户id
     */
    void enableUser(Integer id);

    /**
     * 删除某个用户
     * 将用户的状态设置为删除/注销状态
     *
     * @param id 用户id
     */
    void deleteUser(Integer id);

    /**
     * 判断用户名是否已经注册
     *
     * @param username
     * @return true则表示用户名已被注册
     */
    boolean checkUsernameExists(String username);

    /**
     * 根据用户名获取用户id
     *
     * @param username 用户名
     * @return
     */
    Integer getUserIdByUsername(String username);

    /**
     * 谨慎使用该方法：真实删除该用户
     * 如果将某个用户置为删除状态，请调用 {@link UserService#deleteUser(Integer)}
     *
     * @param user
     */
    void removeUser(User user);
}
