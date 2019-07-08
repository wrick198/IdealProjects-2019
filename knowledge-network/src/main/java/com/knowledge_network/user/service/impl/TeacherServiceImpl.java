package com.knowledge_network.user.service.impl;

import com.knowledge_network.knowledge_network.service.SubjectService;
import com.knowledge_network.support.base.BaseHibernateDAO;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.utils.Asserts;
import com.knowledge_network.user.dao.TeacherDAO;
import com.knowledge_network.user.entity.Teacher;
import com.knowledge_network.user.entity.User;
import com.knowledge_network.user.service.SchoolService;
import com.knowledge_network.user.service.TeacherService;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.vo.TeacherInfoVO;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pentonbin on 17-12-14
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDAO teacherDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private SubjectService subjectService;

    @Override
    public Teacher getTeacherById(Integer id) {
        return teacherDAO.findTeacherById(id);
    }

    @Override
    public void updateTeacherInfo(Teacher teacher, TeacherInfoVO teacherInfoVO) {
        initTeacherInfo(teacher, teacherInfoVO);
        teacherDAO.updateTeacher(teacher);
    }

    @Override
    public void initTeacherInfo(Teacher teacher, TeacherInfoVO teacherInfoVO) {
        // 教师证号非空字段
        Asserts.hasText(teacherInfoVO.getCertificateNum(), ResponseErrorEnum.TEACHER_CERT_NUM_NOT_NULL);
        teacher.setCertificateNum(teacherInfoVO.getCertificateNum());

        if (teacherInfoVO.getSchool() != null && teacherInfoVO.getSchool().getId() != null) {
            teacher.setSchool(schoolService.getSchoolById(teacherInfoVO.getSchool().getId()));
        }
        if (teacherInfoVO.getSubject() != null && teacherInfoVO.getSubject().getId() != null) {
            teacher.setSubject(subjectService.getSubjectById(teacherInfoVO.getSubject().getId()));
        }
        userService.initUserInfo(teacher, teacherInfoVO);
    }

    @Override
    public void createTeacher(Teacher teacher) {
        if (teacher != null) {
            teacher.setRegisterDatetime(new Timestamp(System.currentTimeMillis()));
            teacher.setEnable(User.USER_ENABLE);
            teacher.setLogoff(User.USER_NON_LOGOFF);
            teacherDAO.createTeacher(teacher);
        }
    }

    @Override
    public List<Teacher> getAllTeacherPage(int start, int rows) {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "logoff", User.USER_NON_LOGOFF));
        return getTeacherPageByConditions(start, rows, conditions);
    }

    @Override
    public long getAllTeacherCount() {
        List<BaseHibernateDAO.Condition> conditions = new ArrayList<>();
        conditions.add(new BaseHibernateDAO.Condition(BaseHibernateDAO.ConditionType.EQ, "logoff", User.USER_NON_LOGOFF));
        return getTeacherCountByConditions(conditions);
    }

    @Override
    public List<Teacher> getTeacherPageByConditions(int start, int rows, List<BaseHibernateDAO.Condition> conditions) {
        if (start < 0 || rows <= 0) {
            return new ArrayList<>();
        }
        // 排序：按照注册时间降序排列
        Order order = Order.desc("registerDatetime");
        // 筛选条件：conditions
        if (conditions != null && conditions.size() > 0) {
            return teacherDAO.findTeacherByConditionsOrderPage(start, rows, order, conditions);
        }
        return teacherDAO.findTeacherByOrderPage(start, rows, order);
    }

    @Override
    public long getTeacherCountByConditions(List<BaseHibernateDAO.Condition> conditions) {
        return teacherDAO.getTeacherCountByConditions(conditions);
    }

    @Override
    public Teacher getTeacherByUserName(String name) {
        Asserts.notNull(name, ResponseErrorEnum.USERNAME_NOT_NULL);
        return teacherDAO.findTeacherByUsername(name);
    }
}
