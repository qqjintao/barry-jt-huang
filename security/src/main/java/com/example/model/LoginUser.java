package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.dialect.helper.HsqldbDialect;
import lombok.Data;
import org.apache.commons.collections4.set.CompositeSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.logging.Handler;
import java.util.stream.Collectors;

/**
 * @author barry.jt.huang
 */
@Data
public class LoginUser implements UserDetails {
    private static final long serialVersionUID = 232246154657919099L;
    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * id
     */
    private long id;

    /**
     * 帳戶
     */
    private String userName;

    /**
     * 密碼
     */
    private String password;

    public LoginUser() {
    }

    public LoginUser(long id,String userName,String password, Set<String> permissions) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        if(null!=permissions){
            Iterator<String> iterator = permissions.iterator();
            while (iterator.hasNext()){
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(iterator.next()));
            }
        }
        return simpleGrantedAuthorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
