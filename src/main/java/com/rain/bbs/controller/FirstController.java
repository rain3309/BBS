package com.rain.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/first")
public class FirstController {

    @RequestMapping("/index")
    public String index(){

        return "index";
    }
}
