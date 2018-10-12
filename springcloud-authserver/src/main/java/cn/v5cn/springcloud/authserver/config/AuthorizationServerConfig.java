package cn.v5cn.springcloud.authserver.config;

import cn.v5cn.springcloud.authserver.util.OauthConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcClientDetailsService clientDetailsService(DataSource dataSource) {
        return new JdbcClientDetailsService(dataSource);
    }

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Bean
    public RedisTokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                //允许表单提交
                .allowFormAuthenticationForClients()
                //允许检查Token
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(OauthConstants.CLIENT_ID_MOBILE)
                .scopes(OauthConstants.SCOPES_NSOP)
//                .secret("android")
                .authorizedGrantTypes(OauthConstants.AGT_PASSWORD, OauthConstants.AGT_AUTHORIZATION_CODE, OauthConstants.AGT_REFRESH_TOKEN)
                .and()
                .withClient(OauthConstants.CLIENT_ID_VERIFICATION)
                .scopes(OauthConstants.SCOPES_NSOP)
                .authorizedGrantTypes(OauthConstants.AGT_PASSWORD, OauthConstants.AGT_AUTHORIZATION_CODE, OauthConstants.AGT_REFRESH_TOKEN)
                .and()
                .withClient(OauthConstants.CLIENT_ID_WEBAPP)
                .scopes(OauthConstants.SCOPES_NSOP)
//                .secret("ddd1")
                .authorizedGrantTypes(OauthConstants.AGT_PASSWORD, OauthConstants.AGT_AUTHORIZATION_CODE, OauthConstants.AGT_REFRESH_TOKEN)
                .and()
                .withClient("traffic")
                .scopes("pay")
                //.resourceIds("****")
                .secret("password")
                .authorizedGrantTypes("client_credentials","refresh_token");
    }
}
