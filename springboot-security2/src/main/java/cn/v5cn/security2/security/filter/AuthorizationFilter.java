//package cn.v5cn.security2.security.filter;
//
//import cn.v5cn.security2.security.CustomAuthentication;
//import cn.v5cn.security2.security.SimpleGrantedAuthority;
//import cn.v5cn.security2.security.UserContext;
//import cn.v5cn.security2.security.util.AccessType;
//import cn.v5cn.security2.security.util.SecurityConstants;
//import cn.v5cn.security2.security.vo.AuthResVo;
//import com.google.common.collect.ImmutableList;
//import com.google.common.collect.Lists;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.List;
//
///**
// * @author ZYW
// */
//public class AuthorizationFilter implements Filter {
//
//    private static final Log LOGGER = LogFactory.getLog(AuthorizationFilter.class);
////
////    @Autowired
////    private FeginSysUserService feginSysUserService;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        LOGGER.info("初始化过滤器...");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        LOGGER.info("过滤器正在执行...");
//        String userId = ((HttpServletRequest) servletRequest).getHeader(SecurityConstants.USER_ID_IN_HEADER);
//
//        String clientId = ((HttpServletRequest) servletRequest).getHeader(SecurityConstants.CLIENT_ID_IN_HEADER);
//
//        if(StringUtils.isNotEmpty(userId)) {
//            LOGGER.info("client Id: " + clientId);
//            UserContext userContext = new UserContext(Long.valueOf(userId));
//            userContext.setAccessType(AccessType.ACCESS_TYPE_NORMAL);
//
//            List<SimpleGrantedAuthority> authorityList = Lists.newArrayList();
//
//            SimpleGrantedAuthority clientIdAuthority = new SimpleGrantedAuthority();
//
//            if(StringUtils.equals(clientId, SecurityConstants.CLIENT_ID_WEB_APP)) {
//
//                //TODO 根据用户ID查询用户权限
////                AuthResVo authResVo = feginSysUserService.getByUid(Long.valueOf(userId));
//                AuthResVo authResVo = new AuthResVo();
//                authResVo.setUserName("zhangsan");
//                authResVo.setPermissions(ImmutableList.of("PRE_USER_EDIT","PRE_USER_DEL"));
//                userContext.setUserName(authResVo.getUserName());
//                //userContext.setCustomerId(authResVo.getCustomerId());
//                // List<Permission> permissionList = feignAuthClient.getUserPermissions(userId);
////            for (Permission permission : permissionList) {
////                SimpleGrantedAuthority authority = new SimpleGrantedAuthority();
////                authority.setAuthority(permission.getPermission());
////                authorityList.add(authority);
////            }
//                SimpleGrantedAuthority authority1 = new SimpleGrantedAuthority();
//                authority1.setAuthority("user_list");
//                SimpleGrantedAuthority authority2 = new SimpleGrantedAuthority();
//                authority2.setAuthority("user_add");
//
//                authorityList.add(authority1);
//                authorityList.add(authority2);
//
//                clientIdAuthority.setAuthority(SecurityConstants.CLIENT_ID_WEB_APP);
//
//            } else {
//                LOGGER.info("移动端登录：client id：" + clientId);
//
//                clientIdAuthority.setAuthority(SecurityConstants.CLIENT_ID_MOBILE);
//            }
//            authorityList.add(clientIdAuthority);
//
//            CustomAuthentication userAuth = new CustomAuthentication();
//            userAuth.setAuthorities(authorityList);
//
//            userContext.setAuthorities(authorityList);
//            userContext.setAuthentication(userAuth);
//
//            SecurityContextHolder.setContext(userContext);
//        }
//        filterChain.doFilter(servletRequest,servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
