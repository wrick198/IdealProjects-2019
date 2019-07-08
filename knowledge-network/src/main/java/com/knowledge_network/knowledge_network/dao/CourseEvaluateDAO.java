package com.knowledge_network.knowledge_network.dao;

import com.knowledge_network.knowledge_network.entity.CourseEvaluate;
import com.knowledge_network.knowledge_network.vo.CourseEvaluateInfoVO;
import com.knowledge_network.support.base.BaseHibernateDAO;
import org.hibernate.criterion.Order;

import java.util.List;

public interface CourseEvaluateDAO {
    /**
     * 根据id获取课程评价
     *
     * @param id
     * @return
     */
    CourseEvaluate findCourseEvaluateById(Integer id);

    /**
     * 添加/更新数据库中的课程评价信息
     *
     * @param courseEvaluate
     */
    void updateCourseEvaluate(CourseEvaluate courseEvaluate);

    /**
     * 删除课程评价信息
     *
     * @param courseEvaluate
     */
    void deleteCourseEvaluate(CourseEvaluate courseEvaluate);

    /**
     * 根据顺序不加筛选地显示课程评价信息（管理员用）
     * @param start
     * @param row
     * @param order
     * @return
     */
    List<CourseEvaluate> findCourseEvaluatesByOrderPage(int start, int row, Order order);

    /**
     * 根据顺序且显示当前课程下的课程评价信息（用户通用）
     * @param start
     * @param row
     * @param order
     * @param condition
     * @return
     */
    List<CourseEvaluate> findCourseEvaluatesByConditionOrderPage(int start, int row, Order order, List<BaseHibernateDAO.Condition> condition);
}
