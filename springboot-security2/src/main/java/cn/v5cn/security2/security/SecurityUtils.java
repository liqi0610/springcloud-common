package cn.v5cn.security2.security;

import org.springframework.security.core.context.SecurityContextHolder;


public final class SecurityUtils {

    public static String getCurrentUserUsername() {
        UserContext userContext = (UserContext) SecurityContextHolder.getContext();
        return userContext.getUserName();
    }

    public static Long getCurrentUserId() {
        UserContext userContext = (UserContext) SecurityContextHolder.getContext();
        return userContext.getUserId();
    }

    public static Long getCustomerId() {
        UserContext userContext = (UserContext) SecurityContextHolder.getContext();
        return userContext.getCustomerId();
    }
}
