package com.e3.user.controller;

import com.e3.service.user.api.UserService;
import com.e3.service.user.pojo.TbUser;
import com.e3.util.common.CookieUtils;
import com.e3.util.common.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/1/5 0005.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userServcie;

    //校验数据
    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public E3Result checkParam(@PathVariable String param,@PathVariable Integer type){
        return userServcie.checkParam(param,type);
    }

    //用户注册
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    @ResponseBody
    public E3Result  registerUser(TbUser tbUser){
        return   userServcie.registerUser(tbUser);
    }

    //用户登录
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public  E3Result  login(HttpServletRequest  request, HttpServletResponse response, String username, String password){

        E3Result  result = userServcie.login(username,password);
        //把token放到cookie中
        CookieUtils.setCookie(request,response,"TT_TOKEN",result.getData().toString(),true);
        return result;
    }

    //根据token码换取用户信息
    @RequestMapping("/user/token/{token}")
    @ResponseBody
    public Object  getByToken(@PathVariable  String token,String callback){
        E3Result  result = userServcie.getBytokenUser(token);
        MappingJacksonValue  m = new MappingJacksonValue(result);
        m.setJsonpFunction(callback);
        return m;
    }

    //根据token码换取用户信息
    @RequestMapping("/users/token/{token}")
    @ResponseBody
    public E3Result  getByTokens(@PathVariable  String token,String callback){
        E3Result  result = userServcie.getBytokenUser(token);

        return result;
    }

}
