package cn.v5cn.security2.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import cn.v5cn.security2.security.jwt.JwtAuthenticationEntryPoint;
import cn.v5cn.security2.security.service.UserInfoService;
import cn.v5cn.security2.security.jwt.JwtRequestFilter;

/**
 * @author ZYW
 * @version 1.0
 * @date 2020-05-26 21:39
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //配置AuthenticationManager，以便它知道从何处加载匹配凭据的用户使用BCryptPasswordEncoder
        auth.userDetailsService(userInfoService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 在这个例子中我们不需要CSRF
        httpSecurity.csrf().disable()
                // 不要验证此特定请求
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                // 所有其他请求都需要经过身份验证
                .anyRequest().authenticated().and()
                // 确保我们使用无状态会话；会话不会用于存储用户的状态。
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 添加一个过滤器来验证每个请求的令牌
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("000000"));
//    }
}
