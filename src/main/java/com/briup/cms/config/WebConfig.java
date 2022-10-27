package com.briup.cms.config;


import com.briup.cms.web.interception.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web层配置类，提供用户自定义组件对象 解决XXX具体问题
 * @author SDX
 * @create 2022-10-27 9:26
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired//植入我们通过spring创建的bean对象
    private JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/auth/**");
    }

}
