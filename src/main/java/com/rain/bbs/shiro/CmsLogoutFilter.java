package com.rain.bbs.shiro;

import org.apache.shiro.web.filter.authc.LogoutFilter;

public class CmsLogoutFilter extends LogoutFilter{

    @Override
    public String getRedirectUrl() {
        System.out.println(super.getRedirectUrl());
        return super.getRedirectUrl();
    }
}
