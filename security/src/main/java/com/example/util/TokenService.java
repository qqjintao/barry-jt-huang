package com.example.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.example.constant.Constants;
import com.example.model.LoginUser;
import com.example.redis.RedisCache;
import com.example.untils.ServletUtils;
import com.example.untils.ip.AddressUtils;
import com.example.untils.ip.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author barry.jt.huang
 */
@Component
public class TokenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);
    protected static final long MILLIS_SECOND = 1000;
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;
    /**
     * 操作间隔时间 10min
     */
    protected static final long INTERVAL_TIME = 10 * 60;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private RedisCache redisCache;

    /**
     * 获取登录用户个人信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            Claims claims = getClaimsFromToken(token);
            // 解析对应的权限以及用户信息
            if (null != claims) {
                String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
                String userKey = getTokenKey(uuid);
                LoginUser user = redisCache.getCacheObject(userKey);
                return user;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 获取请求token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (StringUtils.isNotEmpty(token) && token.startsWith(tokenHead)) {
            token = token.replace(tokenHead, "");
        }
        return token;
    }

    private String getTokenKey(String uuid) {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }


    /**
     * 刷新令牌有效期
     */
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expiration * MILLIS_MINUTE);
        String userKey = getTokenKey(loginUser.getToken());
        redisCache.setCacheObject(userKey, loginUser, expiration, TimeUnit.MINUTES);
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * MILLIS_MINUTE);
    }


    /**
     * 验证token是否还有效
     */
    public boolean validateToken(HttpServletRequest request, LoginUser loginUser) {
        String token = getToken(request);
        return !isTokenExpired(token, request, loginUser);
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token, HttpServletRequest request, LoginUser loginUser) {
        Date expiredDate = getExpiredDateFromToken(token);
        Boolean bo = expiredDate.before(new Date());
        if (!bo) {
            refreshHeadToken(request, loginUser);
        }
        return bo;
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(HttpServletRequest request,LoginUser loginUser) {
        String token = getToken(request);
        Claims claims = getClaimsFromToken(token);
        if (null != loginUser && StringUtils.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
            generateToken(claims);
        }
    }

    /**
     * 根据用户信息生成token
     */
    public String generateToken(LoginUser loginUser) {
        String token = UUID.fastUUID().toString();
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshToken(loginUser);
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        claims.put(Constants.JWT_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 设置用户代理信息
     */
    public void setUserAgent(LoginUser loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        loginUser.setIpaddr(ip);
        loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 当原来的token没过期时是可以刷新的
     */
    public void refreshHeadToken(HttpServletRequest request, LoginUser loginUser) {
        String token = getToken(request);
        Claims claims = getClaimsFromToken(token);
        //如果token在10分钟之内刚刷新过，返回原token
        if (!tokenRefreshJustBefore(token, INTERVAL_TIME)) {
            refreshToken(loginUser);
            generateToken(claims);
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     *
     * @param token 原token
     * @param time  指定时间（秒）
     */
    private boolean tokenRefreshJustBefore(String token, Long time) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(Constants.JWT_CREATED, Date.class);
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        if (refreshDate.after(created) && refreshDate.before(DateUtil.offsetSecond(created, time.intValue()))) {
            return true;
        }
        return false;
    }
}

