package com.knowledge_network.user.service;

import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Teacher;
import com.knowledge_network.user.vo.TeacherInfoVO;

import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
public interface TeacherService {

    /**
     * 根据教师用户id获取教师对象
     *
     * @param id 教师用户id
     * @return 教师对象
     */
    public Teacher getTeacherById(Integer id);

    /**
     * 更新教师的个人信息，将同步到数据库
     *
     * @param teacher       需要更新的教师用户
     * @param teacherInfoVO 需要更新的教师信息
     */
    void updateTeacherInfo(Teacher teacher, TeacherInfoVO teacherInfoVO);

    /**
     * 初始化教师的个人信息，将不同步到数据库
     *
     * @param teacher       需要更新的教师用户
     * @param teacherInfoVO 需要更新的教师信息
     */
    void initTeacherInfo(Teacher teacher, TeacherInfoVO teacherInfoVO);

    /**
     * 创建新的教师用户
     *
     * @param teacher 教师用户
     */
    void createTeacher(Teacher teacher);

    /**
     * 获取所有家长分页
     *
     * @param start 起始下标
     * @param rows  每一页行数
     * @return 分页的家长用户
     */
    List<Teacher> getAllTeacherPage(int start, int rows);

    /**
     * 获取所有教师人数
     *
     * @return 人数
     */
    long getAllTeacherCount();

    /**
     * 根据条件获取学生分页
     *
     * @param start      起始下标
     * @param rows       每一页行数
     * @param conditions 条件
     * @return
     */
    List<Teacher> getTeacherPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据条件获取学生人数
     *
     * @param conditions 条件
     * @return
     */
    long getTeacherCountByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 根据用户名查询教师
     *
     * @param name
     * @return
     */
    Teacher getTeacherByUserName(String name);
}
