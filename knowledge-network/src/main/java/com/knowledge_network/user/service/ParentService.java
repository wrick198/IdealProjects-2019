package com.knowledge_network.user.service;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Parent;
import com.knowledge_network.user.entity.Student;
import com.knowledge_network.user.vo.ParentInfoVO;

import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
public interface ParentService {

    /**
     * 根据用户id获取家长对象
     *
     * @param id 家长id
     * @return 家长对象
     */
    Parent getParentById(Integer id);

    /**
     * 更新家长个人信息，将同步到数据库
     *
     * @param parent       需要更新信息的家长
     * @param parentInfoVO 需要更新的信息
     */
    void updateParentInfo(Parent parent, ParentInfoVO parentInfoVO);

    /**
     * 初始化家长个人信息，不同步到数据库
     *
     * @param parent       需要更新信息的家长
     * @param parentInfoVO 需要更新的信息
     */
    void initParentInfo(Parent parent, ParentInfoVO parentInfoVO);

    /**
     * 根据家长用户名获取家长对象
     *
     * @param username 家长用户名
     * @return 家长对象
     */
    Parent getParentByUsername(String username);

    /**
     * 家长关联其孩子
     *
     * @param parent  家长对象
     * @param student 家长关联的孩子
     */
    void addChild(Parent parent, Student student);

    /**
     * 家长移除关联的孩子
     *
     * @param parent  家长对象
     * @param student 移除的孩子
     */
    void removeChild(Parent parent, Student student);

    /**
     * 创建新的家长用户
     *
     * @param parent 新的家长用户
     */
    void createParent(Parent parent);

    /**
     * 获取所有家长分页
     *
     * @param start 起始下标
     * @param rows  每一页行数
     * @return 分页的家长
     */
    List<Parent> getAllParentPage(int start, int rows);

    /**
     * 获取所有家长人数
     *
     * @return 人数
     */
    long getAllParentCount();

    /**
     * 根据条件获取家长分页
     *
     * @param start      起始下标
     * @param rows       每一页行数
     * @param conditions 条件
     * @return
     */
    List<Parent> getParentPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据条件获取家长人数
     *
     * @param conditions 条件
     * @return
     */
    long getParentCountByConditions(List<BaseHibernateDAO.Condition> conditions);
}
