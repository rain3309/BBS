package com.rain.bbs.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CmsAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("executeLogin");
        return false;
    }
}
