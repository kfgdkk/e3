package com.e3.web.manager.interceptor;

import com.e3.service.user.pojo.ActiveUser;
import com.e3.util.common.ResourcesUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        /*if (url.indexOf("/user/login")>0||url.indexOf("/error")>0){
            return true;
        }*/
        List<String> urls = ResourcesUtil.gekeyList("anonyURL");
        for (String u : urls){
            if (url.indexOf(u)>0){
                return true;
            }
        }
        HttpSession session = request.getSession();
        ActiveUser activeUser = (ActiveUser) session.getAttribute("activeUser");
        if (activeUser!=null){
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
