package com.rain.bbs.shiro;

import com.rain.bbs.domain.User;
import com.rain.bbs.service.OperationLogService;
import com.rain.bbs.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmsAuthenticationFilter extends FormAuthenticationFilter {

    private String successUrl = null;

    //重写onPreHandel方法 判断是否已经登录 是不是登陆请求
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        boolean isAllowed = isAccessAllowed(request, response, mappedValue);
        if(isAllowed && isLoginRequest(request,response)){
            issueSuccessRedirect(request,response);
            return false;
        }
        return isAllowed || onAccessDenied(request,response);
    }

    //重写issueSuccessRedirect方法  设置登陆成功之后跳转的请求
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest hsr = (HttpServletRequest) request;
        HttpServletResponse hsp = (HttpServletResponse) response;

        if(StringUtils.isBlank(successUrl)){
            //清除缓存
            WebUtils.getAndClearSavedRequest(request);
            //设置登陆成功之后要跳转的链接
            WebUtils.issueRedirect(request,response,"/success");
            //同上
            //WebUtils.redirectToSavedRequest(request, response, "/success");
        }

    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {

        AuthenticationToken token = createToken(request, response);

        String username = (String) token.getPrincipal();
        User user = userService.getByUsernmae(username);
        HttpServletRequest hsr = (HttpServletRequest) request;
        String ip = hsr.getRemoteAddr();
        try {
            Subject subject = getSubject(request, response);
            subject.login(token);
            operationLogService.loginSuccess(ip,user.getId());
            return onLoginSuccess(token,subject,request,response);
        } catch (AuthenticationException e) {
            Object credentials = token.getCredentials();
            String password = null;
            if(credentials instanceof char[]){
                password = new String((char[])credentials);
            }
            operationLogService.loginFail(ip,user.getId(),password);
            return onLoginFailure(token,e,request,response);
        }
    }

    //登陆成功
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //登陆成功后要做的操作
        return super.onLoginSuccess(token, subject, request, response);
    }

    //登陆失败
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        //登录失败后的操作
        return super.onLoginFailure(token, e, request, response);
    }

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    private OperationLogService operationLogService;
    @Autowired
    public void setOperationLogService(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }
}
