package com.knowledge_network.quize.dao;

import com.knowledge_network.quize.entity.HasDoneQuize;
import com.knowledge_network.quize.entity.Quize;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.user.entity.Student;

import java.util.List;

/**
 * ** Created by gongjiangtao on 2018/5/2
 * 做题记录DAO接口
 **/
public interface HasDoneQuizeDAO {
    /**
     * 通过id查询做题记录
     *
     * @param id 待查id
     * @return id匹配的做题记录
     */
    HasDoneQuize findHasDoneQuizeById(Integer id);

    /**
     * 通过学生获取做题记录
     *
     * @param student 待查学生
     * @return 匹配的做题记录
     */
    List<HasDoneQuize> findHasDoneQuizeByStudent(Student student);

    /**
     * 通过试题获取做题记录
     *
     * @param quize 待查试题
     * @return 匹配的错题记录
     */
    List<HasDoneQuize> findHasDoneQuizeByQuize(Quize quize);

    /**
     * 根据条件查询做题记录（学生、试题）
     *
     * @param conditions 条件
     * @return 符合条件的做题记录
     */
    HasDoneQuize findByConditions(List<BaseHibernateDAO.Condition> conditions);

    /**
     * 更新做题记录
     *
     * @param hasDoneQuize 待更新的做题记录
     */
    void updateHasDoneQuize(HasDoneQuize hasDoneQuize);

    /**
     * 删除错题记录
     *
     * @param hasDoneQuize 待删除的做题记录
     */
    void deleteHasDoneQuize(HasDoneQuize hasDoneQuize);
}
