package com.knowledge_network.user.service;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.SystemManager;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.vo.SystemManagerInfoVO;

import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
public interface SystemManagerService {

    /**
     * 根据系统管理员用户id获取系统管理员用户
     *
     * @param id 系统管理员用户id
     * @return 系统管理员用户
     */
    SystemManager getSystemManagerById(Integer id);

    /**
     * 更新系统管理员的个人信息，将同步到数据库
     *
     * @param systemManager       需要更新的系统管理员对象
     * @param systemManagerInfoVO 需要更新的信息
     */
    void updateSystemManager(SystemManager systemManager, SystemManagerInfoVO systemManagerInfoVO);


    /**
     * 初始化系统管理员的个人信息，将不同步到数据库
     *
     * @param systemManager       需要更新的系统管理员对象
     * @param systemManagerInfoVO 需要更新的信息
     */
    void initSystemManager(SystemManager systemManager, SystemManagerInfoVO systemManagerInfoVO);

    /**
     * 创建系统管理员用户
     *
     * @param systemManager 系统管理员用户
     */
    void createSystemManager(SystemManager systemManager);

    /**
     * 获取所有系统管理员分页
     *
     * @param start 起始下标
     * @param rows  每一页行数
     * @return 分页的管理员
     */
    List<? extends User> getAllSystemManagerPage(int start, int rows);

    /**
     * 获取所有系统管理员人数
     *
     * @return 人数
     */
    long getAllSystemManagerCount();

    /**
     * 根据条件获取系统管理员分页
     *
     * @param start      起始下标
     * @param rows       每一页行数
     * @param conditions 条件
     * @return
     */
    List<SystemManager> getSystemManagerPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据条件获取系统管理员人数
     *
     * @param conditions 条件
     * @return
     */
    long getSystemManagerCountByConditions(List<BaseHibernateDAO.Condition> conditions);
}
