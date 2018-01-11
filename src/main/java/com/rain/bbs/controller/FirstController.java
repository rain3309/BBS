package com.rain.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstController {

    @RequestMapping("/")
    public String login(){

        return "view/login";
    }
    @RequestMapping("/index")
    public String index(){

        return "index";
    }

}
