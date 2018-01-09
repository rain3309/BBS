package com.rain.bbs.controller;

import com.rain.bbs.domain.User;
import com.rain.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(HttpServletRequest request,Model model){

        return "user_list";

    }

    @RequestMapping(value = "save",method = RequestMethod.GET)
    public String save(Model model){
        User user = new User();
        user.setUsername("susan");
        user.setBirthDay(new Date());
        user.setEmail("xxxx@163.com");
        user.setGender("M");
        user.setPhone("133XXXXXXXX");
        user.setType("normal");
        User bean = userService.save(user);
        model.addAttribute("user",bean);
        return "user_list";
    }

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public String delete(){

        //userService.delete()
        return "user_list";
    }
}
