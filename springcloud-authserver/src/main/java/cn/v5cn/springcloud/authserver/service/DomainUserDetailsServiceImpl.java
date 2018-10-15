package cn.v5cn.springcloud.authserver.service;

import cn.v5cn.springcloud.authserver.domain.SysUser;
import cn.v5cn.springcloud.authserver.dto.UserInfoDTO;
import cn.v5cn.springcloud.authserver.mapper.SysUserOauthMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * @author zhuyanwei
 */
@Service("userDetailsService")
public class DomainUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserOauthMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userInfo = (User)authentication.getPrincipal();

        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name",username);
        List<SysUser> userList = sysUserMapper.selectList(wrapper);
        if(userList == null || userList.isEmpty()) {
            throw new UsernameNotFoundException("用户" + username + "不存在!");
        }

        if(userList.size() > 1) {
            throw new UsernameNotFoundException("用户" + username + "不唯一!");
        }

        SysUser user = userList.get(0);

//        List<AuthMarkingDTO> auths = sysUserMapper.findAuthMarkingByUserId(user.getId());
        Set<GrantedAuthority> authorities = Sets.newHashSet();
//        if(auths != null && !auths.isEmpty()) {
//            for(AuthMarkingDTO authMarking : auths) {
//                authorities.add(new SimpleGrantedAuthority("111"));
//                authorities.add(new SimpleGrantedAuthority("222"));
//            }
//        }

        /**
         * 增加系统用户登陆日志
         */
        return new UserInfoDTO(String.valueOf(user.getId()),user.getName(),user.getLoginName(),user.getPwd(),authorities);
    }


}
