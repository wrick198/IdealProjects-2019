package com.knowledge_network.auth.security.anno;

import com.knowledge_network.auth.security.AuthorizationWatchDog;

import java.lang.annotation.*;

/**
 * Created by pentonbin on 17-12-2
 * <p>
 * 权限控制注解
 * <p>
 * 只能使用在Controller的处理方法上，可以查看{@link AuthorizationWatchDog}
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorizationPermission {

    /**
     * 执行该方法所需的权限字段
     *
     * @return 权限
     */
    String[] permissions() default {};

    /**
     * 检查请求url中的用户是否为当前登录用户
     *
     * @return true表示要求检查，false表示不需要检查
     */
    boolean checkCurrentLoginUser() default true;
}
