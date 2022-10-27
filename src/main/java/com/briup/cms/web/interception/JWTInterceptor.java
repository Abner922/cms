package com.briup.cms.web.interception;


import com.briup.cms.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1.对用户所提交的token信息进行验证
 * 对/auth/**
 * @author SDX
 * @create 2022-10-26 11:11
 */

@Component // 创建一个拦截器对象
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取用户的请求头信息
        String token = request.getHeader("token");

        //2.当用户未提供时，web提示用户token无效
        if (token == null) {
            throw new RuntimeException("token无效，请重新登录");
        }
        //3.用户提供 但是token无效 超时，不合法的字符串
        JwtUtil.checkSign(token);

        return true;
    }
}
