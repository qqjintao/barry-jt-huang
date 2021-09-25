package com.example.service.handle;

import com.example.constant.Constants;
import com.example.model.LoginUser;
import com.example.model.SysUser;
import com.example.service.ISysMenuService;
import com.example.service.ISysUserService;
import com.example.untils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author barry.jt.huang
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysMenuService permissionService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(userName);
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", userName);
            throw new UsernameNotFoundException("登录用户：" + userName + " 不存在");
        } else if (Constants.DELETED.equals(user.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", userName);
            throw new UsernameNotFoundException("对不起，您的账号：" + userName + " 已被删除");
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user) {
        return new LoginUser(user.getId(),user.getUserName(),user.getPassword(), permissionService.getMenuPermission(user.getId()));
    }
}
