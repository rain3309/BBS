package com.rain.bbs.config;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(urlPatterns = "/*",filterName = "druidStatFilter",initParams = {
            @WebInitParam(name = "exclusions",value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*",description = "忽略的资源")
})
public class DruidWebStatFilter extends WebStatFilter{
}
