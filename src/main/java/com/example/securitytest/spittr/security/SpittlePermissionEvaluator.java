package com.example.securitytest.spittr.security;

import com.example.securitytest.spittr.Spittle;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;

/**
 * Created by hx on 2019-04-15.
 */
public class SpittlePermissionEvaluator implements PermissionEvaluator {
    private static final GrantedAuthority ADMIN_AUTHORITY = new SimpleGrantedAuthority("ROLE_ADMIN");

    public boolean hasPermission(Authentication authentication, Object target, Object permission) {
        if (target instanceof Spittle) {
            Spittle spittle = (Spittle) target;
            String username = spittle.getSpitter().getUsername();
            if ("delete".equals(permission)) {
                return isAdmin(authentication) || username.equals(authentication.getName());
            }
        }
        throw new UnsupportedOperationException("hasPermission not supported for object <" + target + "> and permission <" + permission + ">");
    }

    public boolean hasPermission(Authentication authentication, Serializable serializable, String targetType, Object permission) {
        throw new UnsupportedOperationException();
    }

    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().contains(ADMIN_AUTHORITY);
    }

    protected MethodSecurityExpressionHandler createExpressionHandle() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new SpittlePermissionEvaluator());
        return expressionHandler;
    }
}
