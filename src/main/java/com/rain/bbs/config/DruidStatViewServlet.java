package com.rain.bbs.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/druid/*",initParams = {
        @WebInitParam(name = "allow",value = "127.0.0.1",description = "允许访问的IP"),
        @WebInitParam(name = "deny",value = "192.168.10.21",description = "黑名单"),
        @WebInitParam(name = "loginUsername",value = "susan",description = "Druid控制台登陆名"),
        @WebInitParam(name = "loginPassword",value = "123456",description = "登录密码"),
        @WebInitParam(name = "resetEnable",value = "false")
})
public class DruidStatViewServlet extends StatViewServlet{
}
