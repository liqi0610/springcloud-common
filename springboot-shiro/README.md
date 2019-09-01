##注解 功能
* @RequiresGuest	        只有游客可以访问
* @RequiresAuthentication	需要登录才能访问
* @RequiresUser	            已登录的用户或“记住我”的用户能访问
* @RequiresRoles	        已登录的用户需具有指定的角色才能访问
* @RequiresPermissions	    已登录的用户需具有指定的权限才能访问

## shiro提供和多个默认的过滤器，我们可以用这些过滤器来配置控制指定url的权限：

配置缩写	对应的过滤器	功能

* anon	_AnonymousFilter_	指定url可以匿名访问
* authc	_FormAuthenticationFilter_	指定url需要form表单登录，默认会从请求中获取username、password,rememberMe等参数并尝试登录，如果登录不了就会跳转到loginUrl配置的路径。我们也可以用这个过滤器做默认的登录逻辑，但是一般都是我们自己在控制器写登录逻辑的，自己写的话出错返回的信息都可以定制嘛。
* authcBasic	_BasicHttpAuthenticationFilter_	指定url需要basic登录
* logout	_LogoutFilter_	登出过滤器，配置指定url就可以实现退出功能，非常方便
* noSessionCreation	_NoSessionCreationFilter_	禁止创建会话
* perms	_PermissionsAuthorizationFilter_	需要指定权限才能访问
* port	_PortFilter_	需要指定端口才能访问
* rest	_HttpMethodPermissionFilter_	将http请求方法转化成相应的动词来构造一个权限字符串，这个感觉意义不大，有兴趣自己看源码的注释
* roles	_RolesAuthorizationFilter_	需要指定角色才能访问
* ssl	_SslFilter_	需要https请求才能访问
* user	_UserFilter_	需要已登录或“记住我”的用户才能访问