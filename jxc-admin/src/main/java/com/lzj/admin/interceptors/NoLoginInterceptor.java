package com.lzj.admin.interceptors;

import com.lzj.admin.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断是否登录
 *
 * @author 乐字节--老李
 * @version 1.0
 */
public class NoLoginInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user= (User) request.getSession().getAttribute("user");

        if(null == user){
            /**
             * 用户未登录 或者 session 过期
             */
            response.sendRedirect("index");
            return false;
        }
        return true;
    }
}
