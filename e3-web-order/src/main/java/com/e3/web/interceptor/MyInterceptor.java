package com.e3.web.interceptor;

import com.e3.service.user.pojo.TbUser;
import com.e3.util.common.CookieUtils;
import com.e3.util.common.E3Result;
import com.e3.util.common.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI+"======================");
        String tt_token = CookieUtils.getCookieValue(request, "TT_TOKEN", true);
        if (StringUtils.isBlank(tt_token)){
            response.sendRedirect("http://localhost:8084/page/login?redirect=http://localhost:8086"+requestURI);
            return false;
        }
        String jsonData = HttpClientUtil.doGet("http://localhost:8084/users/token/" + tt_token);
        E3Result result = E3Result.formatToPojo(jsonData, TbUser.class);
        TbUser user = (TbUser) result.getData();
        if (user!=null){
            request.setAttribute("orderUser",user);
            return true;
        }
        response.sendRedirect("http://localhost:8084/page/logen?redirect=http://localhost:8086"+requestURI);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
