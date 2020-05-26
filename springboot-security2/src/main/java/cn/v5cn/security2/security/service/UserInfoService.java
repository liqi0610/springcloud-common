package cn.v5cn.security2.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.v5cn.security2.security.entity.UserDetailsImpl;

/**
 * @author ZYW
 * @version 1.0
 * @date 2020-05-26 21:53
 */
@Service
public class UserInfoService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return new UserDetailsImpl("zhangsan","$2a$10$5uqHclwPzmdLJbkjfsoJIu4BjHP.UCbB7p4Ok1zwzsRA7X.VasqQ.",null);
    }
}
