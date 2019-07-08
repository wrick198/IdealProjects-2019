package com.knowledge_network.auth.security;

import com.knowledge_network.auth.security.anno.AuthorizationPermission;
import com.knowledge_network.auth.user.KnowledgeNetworkUserDetails;
import com.knowledge_network.auth.user.LoginUserHolder;
import com.knowledge_network.support.common.ResponseErrorEnum;
import com.knowledge_network.support.common.ResponseResult;
import com.knowledge_network.support.utils.StringUtils;
import com.knowledge_network.user.entity.UserRole;
import com.knowledge_network.user.entity.UserRoleRelationship;
import com.knowledge_network.user.service.UserService;
import com.knowledge_network.user.service.inner.InnerUserRolePermissionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pentonbin on 17-12-2
 * <p>
 * 权限访问拦截器，拦截处理URL请求的Controller方法。
 * <p>
 * 1. 对于需要权限控制的功能，在对应的Controller方法中添加注解{@link AuthorizationPermission}，
 * 并在permissions字段中指定所需的权限
 * 2. {@link AuthorizationPermission#checkCurrentLoginUser()}若为true，表示指定请求的用户是否为当前登录的用户。若为true，则必须在请求URL中添加userId，且使用{@link org.springframework.web.bind.annotation.PathVariable}在参数列表中进行注解
 * 3. 请求用户与当前登录用户不一致，或没有相关权限将返回"/error/404.jsp"页面
 */
@Aspect
@Component
public class AuthorizationWatchDog {

    private Logger logger = LoggerFactory.getLogger(AuthorizationWatchDog.class);

    @Autowired
    private InnerUserRolePermissionService innerUserRolePermissionService;
    @Autowired
    private UserService userService;

    @Around("@annotation(authenticationPermission)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, AuthorizationPermission authenticationPermission) throws Throwable {

        RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) reqAttr).getRequest();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 返回类型
        Class returnType = signature.getReturnType();

        // 获取当前登录的用户
        KnowledgeNetworkUserDetails loginUser = LoginUserHolder.getInstance().getCurrentLoginUser();
        // 检查请求url中的用户是否为当前登录用户
        if (authenticationPermission.checkCurrentLoginUser()) {
            String userId = null;
            // 获取参数名称以及值
            String[] paramsName = signature.getParameterNames();
            Object[] paramsArgs = joinPoint.getArgs();

            for (int i = 0; i < paramsName.length; i++) {
                if (paramsName[i].equalsIgnoreCase("userId")) {
                    userId = (String) paramsArgs[i];
                    break;
                }
            }
            if (loginUser == null || userId == null ||
                    (StringUtils.isNumeric(userId) && !Integer.valueOf(userId).equals(loginUser.getUserId()))) {
                // 请求url中用户id与当前登录用户不一致，返回404
                return checkResult(returnType);
            }
        }

        Collection<UserRole> userRoles = new ArrayList<>();
        Collection<UserRoleRelationship> relationships = userService.getUserById(loginUser.getUserId()).getUserRoleRelationships();
        for (UserRoleRelationship relationship : relationships) {
            userRoles.add(relationship.getUserRole());
        }
        String[] permissions = authenticationPermission.permissions();
        boolean hasPermission = true;
        // for log
        String lastPermission = "";

        for (String permission : permissions) {
            boolean has = false;
            for (UserRole userRole : userRoles) {
                if (innerUserRolePermissionService.userRoleHasPermission(userRole, permission)) {
                    has = true;
                    break;
                }
            }
            if (!has) {
                hasPermission = false;
                lastPermission = permission;
                break;
            }
        }

        if (!hasPermission) {
            logger.warn("DENY: user[userId=" + loginUser.getUserId() + "] require permission[\"" + lastPermission + "\"] to" +
                    " access " + signature.getMethod().getDeclaringClass().toString() + "#" + signature.getMethod().getName());
            return checkResult(returnType);
        }

        // 有相关权限，由Controller处理返回
        return joinPoint.proceed();
    }

    private Object checkResult(Class returnType) {
        if (returnType.equals(ResponseResult.class)) {
            return ResponseResult.newErrorInstance(ResponseErrorEnum.NOT_FOUND, null);
        }
        return null;
    }
}
