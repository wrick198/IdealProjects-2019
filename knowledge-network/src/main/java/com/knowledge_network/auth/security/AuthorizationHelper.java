package com.knowledge_network.auth.security;


import com.knowledge_network.user.entity.UserRole;
import com.knowledge_network.user.service.inner.InnerUserRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pentonbin on 17-12-2.
 * <p>
 * 权限相关工具类，包含了数据库中所有的表、系统所有的用户类型以及表的相关操作
 * 业务上可以使用该类来获取与权限相关的功能
 */
public class AuthorizationHelper {

    @Autowired
    private InnerUserRolePermissionService userRolePermissionService;

    private static AuthorizationHelper instance;

    private AuthorizationHelper() {
    }

    public static AuthorizationHelper getInstance() {
        if (instance == null) {
            synchronized (AuthorizationHelper.class) {
                if (instance == null) {
                    instance = new AuthorizationHelper();
                }
            }
        }
        return instance;
    }

    /**
     * 分别对应创建create、更新update、读取retrieve、删除delete
     */
    public static enum CURD {
        CREATE("CREATE"), UPDATE("UPDATE"), RETRIEVE("RETRIEVE"), DELETE("DELETE");

        private String opt;

        CURD(String opt) {
            this.opt = opt;
        }

        public String getOpt() {
            return opt;
        }
    }

    /**
     * 数据库的表，每个表会有对应的增删改查操作
     */
    public static enum TABLE {

        CLASS("CLASS"),
        COURSE("COURSE"),
        COURSE_CLICKS("COURSE_CLICKS"),
        KNOWLEDGE_NETWORK_MANAGER("KNOWLEDGE_NETWORK_MANAGER"),
        KNOWLEDGE_POINT("KNOWLEDGE_POINT"),
        KNOWLEDGE_POINT_CLICKS("KNOWLEDGE_POINT_CLICKS"),
        KNOWLEDGE_POINT_DIFFICULT("KNOWLEDGE_POINT_DIFFICULT"),
        KNOWLEDGE_POINT_IMPORTANCE("KNOWLEDGE_POINT_IMPORTANCE"),
        LEARNING_PATH("LEARNING_PATH"),
        LEARNING_PLAN("LEARNING_PLAN"),
        PARENT("PARENT"),
        RESOURCE("RESOURCE"),
        RESOURCE_CLICKS("RESOURCE_CLICKS"),
        RESOURCE_TYPE("RESOURCE_TYPE"),
        SCHOOL("SCHOOL"),
        STUDENT("STUDENT"),
        SUBJECT("SUBJECT"),
        SYSTEM_MANAGER("SYSTEM_MANAGER"),
        TAG("TAG"),
        TEACHER("TEACHER"),
        TEACHER_TYPE("TEACHER_TYPE"),
        TEACHING_PLAN("TEACHING_PLAN"),
        USER("USER"),
        USER_PERMISSION("USER_PERMISSION"),
        USER_ROLE("USER_ROLE");

        private String name;

        TABLE(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static final String[] ALL_CURD = {
            CURD.CREATE.toString(),
            CURD.UPDATE.toString(),
            CURD.RETRIEVE.toString(),
            CURD.DELETE.toString()
    };

    public static final String[] ALL_TALBES = {
            TABLE.CLASS.toString(),
            TABLE.COURSE.toString(),
            TABLE.COURSE_CLICKS.toString(),
            TABLE.KNOWLEDGE_NETWORK_MANAGER.toString(),
            TABLE.KNOWLEDGE_POINT.toString(),
            TABLE.KNOWLEDGE_POINT_CLICKS.toString(),
            TABLE.KNOWLEDGE_POINT_DIFFICULT.toString(),
            TABLE.KNOWLEDGE_POINT_IMPORTANCE.toString(),
            TABLE.LEARNING_PATH.toString(),
            TABLE.LEARNING_PLAN.toString(),
            TABLE.PARENT.toString(),
            TABLE.RESOURCE.toString(),
            TABLE.RESOURCE_CLICKS.toString(),
            TABLE.RESOURCE_TYPE.toString(),
            TABLE.SCHOOL.toString(),
            TABLE.STUDENT.toString(),
            TABLE.SUBJECT.toString(),
            TABLE.SYSTEM_MANAGER.toString(),
            TABLE.TAG.toString(),
            TABLE.TEACHER.toString(),
            TABLE.TEACHER_TYPE.toString(),
            TABLE.TEACHING_PLAN.toString(),
            TABLE.USER.toString(),
            TABLE.USER_PERMISSION.toString(),
            TABLE.USER_ROLE.toString(),
    };

    public static final String[] ALL_ROLES = {
            UserRole.STUDENT,
            UserRole.PARENT,
            UserRole.TEACHER,
            UserRole.KNOWLEDGE_NETWORK_MANAGER,
            UserRole.SYSTEM_MANAGER
    };

    public static final String[] ALL_ROLES_NAME = {
            "学生",
            "家长",
            "教师",
            "知识网络管理员",
            "系统管理员"
    };

    /**
     * 获取权限字段的工具方法
     *
     * @param table 数据库表名
     * @param opt   对应的操作
     * @return 对应的权限
     */
    public String generatePermission(TABLE table, CURD opt) {
        if (table == null || opt == null) {
            return null;
        }
        if (userRolePermissionService.containPermission(table.getName() + "_" + opt.getOpt())) {
            return table.getName() + "_" + opt.getOpt();
        }
        return null;
    }
}
