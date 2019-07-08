package com.knowledge_network.quize.service;

import com.knowledge_network.quize.entity.HasDoneQuize;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Student;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/2
 * 学生已做试题业务层接口
 **/
public interface HasDoneQuizeService {
    /**
     * 通过id获取做题记录
     *
     * @param id 待查id
     * @return 与id匹配的做题记录
     */
    HasDoneQuize getHasDoneQuizeById(Integer id);

    /**
     * 获取某学生的做题记录
     *
     * @param student 待查学生
     * @return 该学生的做题记录
     */
    List<HasDoneQuize> getHasDoneQuizeByStudent(Student student);

    /**
     * 查看做过某题的学生做题记录
     *
     * @param quize 待查试题
     * @return quize的被做记录
     */
    List<HasDoneQuize> getHasDoneQuizeByQuize(Quize quize);

    /**
     * 根据条件获取做题记录（学生、试题）
     *
     * @param conditions 条件
     * @return 符合条件的做题记录
     */
    HasDoneQuize getHasDoneQuizeByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 更新做题次数
     *
     * @param hasDoneQuize
     */
    void updatePracticeTimePlusOne(HasDoneQuize hasDoneQuize);

    /**
     * 更新做错次数
     *
     * @param hasDoneQuize
     */
    void updateWrongTimePlusOne(HasDoneQuize hasDoneQuize);

    /**
     * 更新正确次数
     *
     * @param hasDoneQuize
     */
    void updateRightTimePlusOne(HasDoneQuize hasDoneQuize);

    /**
     * 添加一条做题记录
     *
     * @param hasDoneQuize
     */
    void addHasDoneQuizeRecord(HasDoneQuize hasDoneQuize);

    /**
     * 删除做题记录
     *
     * @param hasDoneQuize
     */
    void deleteHasDoneQuizeRecord(HasDoneQuize hasDoneQuize);
}
