package com.e3.web.item.controller;

import com.e3.service.user.api.UserService;
import com.e3.service.user.pojo.ActiveUser;
import com.e3.util.common.E3Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("user/login")
    public String login(HttpSession session, String usercode, Model model,String password,String randomcode){
        String validateCode = (String) session.getAttribute("validateCode");
        if (!validateCode.equalsIgnoreCase(randomcode)){
            model.addAttribute("msg","验证码错误");
            return "error";
        }
        E3Result re = userService.authenticat(usercode, password);
        if (re.getStatus()==400){
            model.addAttribute("msg",re.getMsg());
            return "error";
        }
        ActiveUser user = (ActiveUser) re.getData();
        session.setAttribute("activeUser",user);
        return "redirect:/index";
    }
}
