package com.e3.web.interceptor;

import com.e3.service.user.api.UserService;
import com.e3.service.user.pojo.TbUser;
import com.e3.util.common.CookieUtils;
import com.e3.util.common.E3Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService  userServcie;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN", true);
        if (StringUtils.isBlank(token)){
            return true;
        }
        E3Result result = userServcie.getBytokenUser(token);
        TbUser user = (TbUser) result.getData();
        if (user!=null){
            request.setAttribute("users",user);
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
