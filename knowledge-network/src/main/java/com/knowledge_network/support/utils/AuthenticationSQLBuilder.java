package com.knowledge_network.support.utils;

import com.knowledge_network.auth.security.AuthorizationHelper;
import com.knowledge_network.auth.security.AuthorizationHelper.CURD;
import com.knowledge_network.auth.security.AuthorizationHelper.TABLE;
import com.knowledge_network.knowledge_network.controller.ResourceController;
import com.knowledge_network.user.entity.UserPermission;
import com.knowledge_network.user.entity.UserRole;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by pentonbin on 17-12-2.
 * <p>
 * 系统初始化前的工具类，使用该类来生成sql脚本，执行main()方法即可
 */
public class AuthenticationSQLBuilder {

    private static final String ROLE_SCRIPT_FILE_NAME = "init_role.sql";
    private static final String PERMISSION_SCRIPT_FILE_NAME = "init_permission.sql";
    private static final String ROLE_PERMISSION_MAPPING_SCRIPT_FILE_NAME = "init_role_permission_mapping.sql";
    private static final String RESOURCE_TYPE_SCRIPT_FILE_NAME = "init_resource_type.sql";

    private static final String TABLE_USER_ROLE_NAME = "user_role";
    private static final String COLUMN_USER_ROLE_ROLE = "role";
    private static final String COLUMN_USER_ROLE_NAME = "name";

    private static final String TABLE_USER_PERMISSION_NAME = "user_permission";
    private static final String COLUMN_USER_PERMISSION_NAME = "permission";

    private static final String TABLE_ROLE_PERMISSION_MAPPING_NAME = "user_role_has_user_permission";
    private static final String COLUMN_ROLE_PERMISSION_MAPPING_ROLE_NAME = "user_role_id";
    private static final String COLUMN_ROLE_PERMISSION_MAPPING_PERMISSION_NAME = "user_permission_id";

    private static final String TABLE_RESOURCE_TYPE_NAME = "resource_type";
    private static final String COLUMN_RESOURCE_TYPE_NAME = "type";

    private static final String SCRIPT_FOLDER =
            "src" + File.separator +
                    "main" + File.separator +
                    "resources" + File.separator +
                    "META-INF" + File.separator +
                    "sql";

    private static List<UserPermission> initPermissions(String[] tables, String[] curd) {
        if (tables == null || tables.length == 0
                || curd == null || curd.length == 0) {
            return null;
        }
        List<UserPermission> allPermissions = new ArrayList<>();
        for (int i = 0; i < tables.length; ++i) {
            for (int j = 0; j < curd.length; ++j) {
                UserPermission p = new UserPermission();
                p.setPermission(tables[i] + "_" + curd[j]);
                allPermissions.add(p);
            }
        }
        return allPermissions;
    }

    private static List<String> buildPermissionSQL(List<UserPermission> permissions) {
        if (permissions == null || permissions.size() == 0) {
            return null;
        }
        List<String> sqls = new ArrayList<>();
        for (UserPermission permission : permissions) {
            sqls.add(
                    String.format("INSERT INTO `" + TABLE_USER_PERMISSION_NAME +
                                    "`(`" + COLUMN_USER_PERMISSION_NAME + "`) VALUES ('%s');",
                            permission.getPermission()));
        }
        return sqls;
    }

    private static List<String> buildRoleSQL(String[] roles, String[] names) {
        if (roles == null || roles.length == 0 || names == null || names.length == 0 ||
                names.length != roles.length) {
            return null;
        }
        List<String> sqls = new ArrayList<>();
        for (int i = 0; i < roles.length; ++i) {
            sqls.add(String.format("INSERT INTO `" + TABLE_USER_ROLE_NAME +
                    "`(`" + COLUMN_USER_ROLE_ROLE + "`, `" + COLUMN_USER_ROLE_NAME + "`) VALUES ('%s', '%s');", roles[i], names[i]));
        }
        return sqls;
    }

    private static List<String> buildMappingSQL(Map<String, List<String>> permissionMap) {
        if (permissionMap == null) {
            return null;
        }
        List<String> sqls = new ArrayList<>();

        Iterator<Map.Entry<String, List<String>>> iter = permissionMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, List<String>> next = iter.next();
            String role = next.getKey();
            List<String> permissions = next.getValue();
            for (String permission : permissions) {
                sqls.add(String.format("INSERT INTO `" + TABLE_ROLE_PERMISSION_MAPPING_NAME + "`(`" + COLUMN_ROLE_PERMISSION_MAPPING_ROLE_NAME + "`, `" + COLUMN_ROLE_PERMISSION_MAPPING_PERMISSION_NAME + "`) " +
                                "SELECT `" + TABLE_USER_ROLE_NAME + "`.`id` AS `" + COLUMN_ROLE_PERMISSION_MAPPING_ROLE_NAME + "`,`" + TABLE_USER_PERMISSION_NAME + "`.`id` AS `" + COLUMN_ROLE_PERMISSION_MAPPING_PERMISSION_NAME + "` " +
                                "FROM `" + TABLE_USER_ROLE_NAME + "`, `" + TABLE_USER_PERMISSION_NAME + "` " +
                                "WHERE `" + TABLE_USER_ROLE_NAME + "`.`" + COLUMN_USER_ROLE_ROLE + "` = '%s' AND `" + TABLE_USER_PERMISSION_NAME + "`.`" + COLUMN_USER_PERMISSION_NAME + "` = '%s';",
                        role, permission));
            }
        }
        return sqls;
    }

    private static List<String> buildResourceTypeSQL(String[] resourceTypes) {
        if (resourceTypes == null || resourceTypes.length == 0) {
            return null;
        }

        List<String> sqls = new ArrayList<>();
        for (String type : resourceTypes) {
            sqls.add(String.format("INSERT INTO `" + TABLE_RESOURCE_TYPE_NAME + "`(`" + COLUMN_RESOURCE_TYPE_NAME + "`) VALUES ('%s');",
                    type));
        }
        return sqls;
    }

    private static void buildScript(String fileName, List<String> sqls) {
        if (sqls == null || sqls.size() == 0) {
            System.out.println("数据库脚本[" + fileName + "]创建失败！");
            return;
        }
        String parent = new File("").getAbsolutePath();
        String scriptPath = parent + File.separator + SCRIPT_FOLDER + File.separator + fileName;
        File script = new File(scriptPath);
        if (script.exists()) {
            script.delete();
        }

        FileWriter writer = null;
        try {
            writer = new FileWriter(script);
            for (String sql : sqls) {
                writer.write(sql + "\n");
            }
            System.out.println("数据库脚本[" + fileName + "]创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(writer);
        }

    }

    public static void main(String[] args) {
        String[] curd = AuthorizationHelper.ALL_CURD;
        String[] tables = AuthorizationHelper.ALL_TALBES;
        String[] roles = AuthorizationHelper.ALL_ROLES;
        String[] roleNames = AuthorizationHelper.ALL_ROLES_NAME;
        String[] resourceTypes = ResourceController.ALL_FILE_TYPE.split(",");

        // 生成角色Role的sql脚本
        List<String> roleSqls = buildRoleSQL(roles, roleNames);
        buildScript(ROLE_SCRIPT_FILE_NAME, roleSqls);

        // 生成权限Permission的sql脚本
        List<UserPermission> permissions = initPermissions(tables, curd);
        List<String> permissionSqls = buildPermissionSQL(permissions);
        buildScript(PERMISSION_SCRIPT_FILE_NAME, permissionSqls);

        // 生成角色-权限映射表的sql脚本
        List<String> mappingSqls = buildMappingSQL(PERMISSION_MAP);
        buildScript(ROLE_PERMISSION_MAPPING_SCRIPT_FILE_NAME, mappingSqls);

        // 生成资源类型的sql脚本
        List<String> resourceTypesSql = buildResourceTypeSQL(resourceTypes);
        buildScript(RESOURCE_TYPE_SCRIPT_FILE_NAME, resourceTypesSql);

    }

    private static String generatePermissionString(TABLE table, CURD opt) {
        return table.getName() + "_" + opt.getOpt();
    }

    private static final Map<String, List<String>> PERMISSION_MAP = new HashMap<>();

    static {
        String[] studentPermissions = new String[]{
                generatePermissionString(TABLE.CLASS, CURD.RETRIEVE),
                generatePermissionString(TABLE.COURSE, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_DIFFICULT, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_IMPORTANCE, CURD.RETRIEVE),
                generatePermissionString(TABLE.LEARNING_PATH, CURD.RETRIEVE),
                generatePermissionString(TABLE.LEARNING_PLAN, CURD.RETRIEVE),
                generatePermissionString(TABLE.PARENT, CURD.RETRIEVE),
                generatePermissionString(TABLE.RESOURCE, CURD.RETRIEVE),
                generatePermissionString(TABLE.RESOURCE_TYPE, CURD.RETRIEVE),
                generatePermissionString(TABLE.SCHOOL, CURD.RETRIEVE),
                generatePermissionString(TABLE.STUDENT, CURD.UPDATE),
                generatePermissionString(TABLE.STUDENT, CURD.RETRIEVE),
                generatePermissionString(TABLE.SUBJECT, CURD.RETRIEVE),
                generatePermissionString(TABLE.TAG, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHER, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHER_TYPE, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.RETRIEVE),
                generatePermissionString(TABLE.USER, CURD.UPDATE),
                generatePermissionString(TABLE.USER, CURD.RETRIEVE),
        };
        String[] parentPermissions = new String[]{
                generatePermissionString(TABLE.CLASS, CURD.RETRIEVE),
                generatePermissionString(TABLE.COURSE, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_DIFFICULT, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_IMPORTANCE, CURD.RETRIEVE),
                generatePermissionString(TABLE.LEARNING_PATH, CURD.RETRIEVE),
                generatePermissionString(TABLE.LEARNING_PLAN, CURD.RETRIEVE),
                generatePermissionString(TABLE.PARENT, CURD.UPDATE),
                generatePermissionString(TABLE.PARENT, CURD.RETRIEVE),
                generatePermissionString(TABLE.RESOURCE, CURD.RETRIEVE),
                generatePermissionString(TABLE.RESOURCE_TYPE, CURD.RETRIEVE),
                generatePermissionString(TABLE.SCHOOL, CURD.RETRIEVE),
                generatePermissionString(TABLE.STUDENT, CURD.RETRIEVE),
                generatePermissionString(TABLE.SUBJECT, CURD.RETRIEVE),
                generatePermissionString(TABLE.TAG, CURD.UPDATE),
                generatePermissionString(TABLE.TEACHER, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHER_TYPE, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.RETRIEVE),
                generatePermissionString(TABLE.USER, CURD.UPDATE),
                generatePermissionString(TABLE.USER, CURD.RETRIEVE),
        };
        String[] teacherPermission = new String[]{
                generatePermissionString(TABLE.CLASS, CURD.RETRIEVE),
                generatePermissionString(TABLE.COURSE, CURD.CREATE),
                generatePermissionString(TABLE.COURSE, CURD.UPDATE),
                generatePermissionString(TABLE.COURSE, CURD.RETRIEVE),
                generatePermissionString(TABLE.COURSE, CURD.DELETE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_DIFFICULT, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_IMPORTANCE, CURD.RETRIEVE),
                generatePermissionString(TABLE.LEARNING_PATH, CURD.RETRIEVE),
                generatePermissionString(TABLE.LEARNING_PLAN, CURD.RETRIEVE),
                generatePermissionString(TABLE.PARENT, CURD.RETRIEVE),
                generatePermissionString(TABLE.RESOURCE, CURD.CREATE),
                generatePermissionString(TABLE.RESOURCE, CURD.UPDATE),
                generatePermissionString(TABLE.RESOURCE, CURD.RETRIEVE),
                generatePermissionString(TABLE.RESOURCE, CURD.DELETE),
                generatePermissionString(TABLE.RESOURCE_TYPE, CURD.RETRIEVE),
                generatePermissionString(TABLE.SCHOOL, CURD.RETRIEVE),
                generatePermissionString(TABLE.STUDENT, CURD.RETRIEVE),
                generatePermissionString(TABLE.SUBJECT, CURD.RETRIEVE),
                generatePermissionString(TABLE.TAG, CURD.CREATE),
                generatePermissionString(TABLE.TAG, CURD.UPDATE),
                generatePermissionString(TABLE.TAG, CURD.RETRIEVE),
                generatePermissionString(TABLE.TAG, CURD.DELETE),
                generatePermissionString(TABLE.TEACHER, CURD.UPDATE),
                generatePermissionString(TABLE.TEACHER, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHER_TYPE, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.CREATE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.UPDATE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.DELETE),
                generatePermissionString(TABLE.USER, CURD.UPDATE),
                generatePermissionString(TABLE.USER, CURD.RETRIEVE)
        };
        String[] knmManagerPermissions = new String[]{
                generatePermissionString(TABLE.CLASS, CURD.RETRIEVE),
                generatePermissionString(TABLE.COURSE, CURD.CREATE),
                generatePermissionString(TABLE.COURSE, CURD.UPDATE),
                generatePermissionString(TABLE.COURSE, CURD.RETRIEVE),
                generatePermissionString(TABLE.COURSE, CURD.DELETE),
                generatePermissionString(TABLE.KNOWLEDGE_NETWORK_MANAGER, CURD.UPDATE),
                generatePermissionString(TABLE.KNOWLEDGE_NETWORK_MANAGER, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT, CURD.CREATE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT, CURD.UPDATE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT, CURD.DELETE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_DIFFICULT, CURD.CREATE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_DIFFICULT, CURD.UPDATE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_DIFFICULT, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_DIFFICULT, CURD.DELETE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_IMPORTANCE, CURD.CREATE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_IMPORTANCE, CURD.UPDATE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_IMPORTANCE, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_IMPORTANCE, CURD.DELETE),
                generatePermissionString(TABLE.LEARNING_PATH, CURD.RETRIEVE),
                generatePermissionString(TABLE.LEARNING_PLAN, CURD.RETRIEVE),
                generatePermissionString(TABLE.PARENT, CURD.RETRIEVE),
                generatePermissionString(TABLE.RESOURCE, CURD.CREATE),
                generatePermissionString(TABLE.RESOURCE, CURD.UPDATE),
                generatePermissionString(TABLE.RESOURCE, CURD.RETRIEVE),
                generatePermissionString(TABLE.RESOURCE, CURD.DELETE),
                generatePermissionString(TABLE.RESOURCE_TYPE, CURD.CREATE),
                generatePermissionString(TABLE.RESOURCE_TYPE, CURD.UPDATE),
                generatePermissionString(TABLE.RESOURCE_TYPE, CURD.RETRIEVE),
                generatePermissionString(TABLE.RESOURCE_TYPE, CURD.DELETE),
                generatePermissionString(TABLE.SCHOOL, CURD.RETRIEVE),
                generatePermissionString(TABLE.STUDENT, CURD.RETRIEVE),
                generatePermissionString(TABLE.SUBJECT, CURD.RETRIEVE),
                generatePermissionString(TABLE.TAG, CURD.CREATE),
                generatePermissionString(TABLE.TAG, CURD.UPDATE),
                generatePermissionString(TABLE.TAG, CURD.RETRIEVE),
                generatePermissionString(TABLE.TAG, CURD.DELETE),
                generatePermissionString(TABLE.TEACHER, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHER_TYPE, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.CREATE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.UPDATE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.DELETE),
                generatePermissionString(TABLE.USER, CURD.UPDATE),
                generatePermissionString(TABLE.USER, CURD.RETRIEVE)
        };
        String[] sysManagerPermissions = new String[]{
                generatePermissionString(TABLE.CLASS, CURD.CREATE),
                generatePermissionString(TABLE.CLASS, CURD.UPDATE),
                generatePermissionString(TABLE.CLASS, CURD.RETRIEVE),
                generatePermissionString(TABLE.CLASS, CURD.DELETE),
                generatePermissionString(TABLE.COURSE, CURD.CREATE),
                generatePermissionString(TABLE.COURSE, CURD.UPDATE),
                generatePermissionString(TABLE.COURSE, CURD.RETRIEVE),
                generatePermissionString(TABLE.COURSE, CURD.DELETE),
                generatePermissionString(TABLE.KNOWLEDGE_NETWORK_MANAGER, CURD.CREATE),
                generatePermissionString(TABLE.KNOWLEDGE_NETWORK_MANAGER, CURD.UPDATE),
                generatePermissionString(TABLE.KNOWLEDGE_NETWORK_MANAGER, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_NETWORK_MANAGER, CURD.DELETE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT, CURD.CREATE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT, CURD.UPDATE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT, CURD.DELETE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_DIFFICULT, CURD.CREATE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_DIFFICULT, CURD.UPDATE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_DIFFICULT, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_DIFFICULT, CURD.DELETE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_IMPORTANCE, CURD.CREATE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_IMPORTANCE, CURD.UPDATE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_IMPORTANCE, CURD.RETRIEVE),
                generatePermissionString(TABLE.KNOWLEDGE_POINT_IMPORTANCE, CURD.DELETE),
                generatePermissionString(TABLE.LEARNING_PATH, CURD.CREATE),
                generatePermissionString(TABLE.LEARNING_PATH, CURD.UPDATE),
                generatePermissionString(TABLE.LEARNING_PATH, CURD.RETRIEVE),
                generatePermissionString(TABLE.LEARNING_PATH, CURD.DELETE),
                generatePermissionString(TABLE.LEARNING_PLAN, CURD.CREATE),
                generatePermissionString(TABLE.LEARNING_PLAN, CURD.UPDATE),
                generatePermissionString(TABLE.LEARNING_PLAN, CURD.RETRIEVE),
                generatePermissionString(TABLE.LEARNING_PLAN, CURD.DELETE),
                generatePermissionString(TABLE.PARENT, CURD.CREATE),
                generatePermissionString(TABLE.PARENT, CURD.UPDATE),
                generatePermissionString(TABLE.PARENT, CURD.RETRIEVE),
                generatePermissionString(TABLE.PARENT, CURD.DELETE),
                generatePermissionString(TABLE.RESOURCE, CURD.CREATE),
                generatePermissionString(TABLE.RESOURCE, CURD.UPDATE),
                generatePermissionString(TABLE.RESOURCE, CURD.RETRIEVE),
                generatePermissionString(TABLE.RESOURCE, CURD.DELETE),
                generatePermissionString(TABLE.RESOURCE_TYPE, CURD.CREATE),
                generatePermissionString(TABLE.RESOURCE_TYPE, CURD.UPDATE),
                generatePermissionString(TABLE.RESOURCE_TYPE, CURD.RETRIEVE),
                generatePermissionString(TABLE.RESOURCE_TYPE, CURD.DELETE),
                generatePermissionString(TABLE.SCHOOL, CURD.CREATE),
                generatePermissionString(TABLE.SCHOOL, CURD.UPDATE),
                generatePermissionString(TABLE.SCHOOL, CURD.RETRIEVE),
                generatePermissionString(TABLE.SCHOOL, CURD.DELETE),
                generatePermissionString(TABLE.STUDENT, CURD.CREATE),
                generatePermissionString(TABLE.STUDENT, CURD.UPDATE),
                generatePermissionString(TABLE.STUDENT, CURD.RETRIEVE),
                generatePermissionString(TABLE.STUDENT, CURD.DELETE),
                generatePermissionString(TABLE.SUBJECT, CURD.CREATE),
                generatePermissionString(TABLE.SUBJECT, CURD.UPDATE),
                generatePermissionString(TABLE.SUBJECT, CURD.RETRIEVE),
                generatePermissionString(TABLE.SUBJECT, CURD.DELETE),
                generatePermissionString(TABLE.SYSTEM_MANAGER, CURD.CREATE),
                generatePermissionString(TABLE.SYSTEM_MANAGER, CURD.UPDATE),
                generatePermissionString(TABLE.SYSTEM_MANAGER, CURD.RETRIEVE),
                generatePermissionString(TABLE.SYSTEM_MANAGER, CURD.DELETE),
                generatePermissionString(TABLE.TAG, CURD.CREATE),
                generatePermissionString(TABLE.TAG, CURD.UPDATE),
                generatePermissionString(TABLE.TAG, CURD.RETRIEVE),
                generatePermissionString(TABLE.TAG, CURD.DELETE),
                generatePermissionString(TABLE.TEACHER, CURD.CREATE),
                generatePermissionString(TABLE.TEACHER, CURD.UPDATE),
                generatePermissionString(TABLE.TEACHER, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHER, CURD.DELETE),
                generatePermissionString(TABLE.TEACHER_TYPE, CURD.CREATE),
                generatePermissionString(TABLE.TEACHER_TYPE, CURD.UPDATE),
                generatePermissionString(TABLE.TEACHER_TYPE, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHER_TYPE, CURD.DELETE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.CREATE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.UPDATE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.RETRIEVE),
                generatePermissionString(TABLE.TEACHING_PLAN, CURD.DELETE),
                generatePermissionString(TABLE.USER, CURD.CREATE),
                generatePermissionString(TABLE.USER, CURD.UPDATE),
                generatePermissionString(TABLE.USER, CURD.RETRIEVE),
                generatePermissionString(TABLE.USER, CURD.DELETE),
                generatePermissionString(TABLE.USER_PERMISSION, CURD.CREATE),
                generatePermissionString(TABLE.USER_PERMISSION, CURD.UPDATE),
                generatePermissionString(TABLE.USER_PERMISSION, CURD.RETRIEVE),
                generatePermissionString(TABLE.USER_PERMISSION, CURD.DELETE),
                generatePermissionString(TABLE.USER_ROLE, CURD.CREATE),
                generatePermissionString(TABLE.USER_ROLE, CURD.UPDATE),
                generatePermissionString(TABLE.USER_ROLE, CURD.RETRIEVE),
                generatePermissionString(TABLE.USER_ROLE, CURD.DELETE),
        };
        PERMISSION_MAP.put(UserRole.STUDENT, Arrays.asList(studentPermissions));
        PERMISSION_MAP.put(UserRole.PARENT, Arrays.asList(parentPermissions));
        PERMISSION_MAP.put(UserRole.TEACHER, Arrays.asList(teacherPermission));
        PERMISSION_MAP.put(UserRole.KNOWLEDGE_NETWORK_MANAGER, Arrays.asList(knmManagerPermissions));
        PERMISSION_MAP.put(UserRole.SYSTEM_MANAGER, Arrays.asList(sysManagerPermissions));
    }
}
