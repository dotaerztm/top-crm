package com.lzj.admin.config;

import com.lzj.admin.interceptors.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器 - 登录前限制
 * 没有登录 不能访问其他页面
 *
 * @author 乐字节--老李
 * @version 1.0
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public NoLoginInterceptor noLoginInterceptor(){
        return new NoLoginInterceptor();
    }

    /**
     * addPathPatterns : 拦截的路径
     * excludePathPatterns ： 放行的路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index","/user/login","/test",
                        "/css/**","/error/**","/images/**","/js/**","/lib/**",
                        "/image/*","/video/*","/text/*","/imageAndVideo/*",
                        "/campaign/*","/student/*",
                        "/appletIndex/*","/appletCircle/*","/appletMyself/*");
    }
}
