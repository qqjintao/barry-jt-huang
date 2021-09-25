package com.example.component;

import cn.hutool.json.JSONUtil;
import com.example.CommonResult;
import com.example.model.LoginUser;
import com.example.untils.StringUtils;
import com.example.util.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author barry.jt.huang
 */
public class RestLogoutSuccessHandler implements LogoutSuccessHandler
{
    @Autowired
    private TokenService jwtTokenUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        LoginUser loginUser = jwtTokenUtil.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            jwtTokenUtil.delLoginUser(loginUser.getToken());
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.success(authentication.getName()+"登出成功！")));
        response.getWriter().flush();
    }
}