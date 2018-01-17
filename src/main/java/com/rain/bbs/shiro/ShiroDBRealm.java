package com.rain.bbs.shiro;

import com.rain.bbs.domain.ShiroUser;
import com.rain.bbs.domain.User;
import com.rain.bbs.security.CredentialsDigest;
import com.rain.bbs.security.CredentialsMatchesAdapter;
import com.rain.bbs.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Initializable;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroDBRealm extends AuthorizingRealm implements InitializingBean{
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = (String) token.getPrincipal();
        User user = userService.getByUsernmae(username);
        if(user != null){
            byte[] salt = user.getByteSalt();
            return new SimpleAuthenticationInfo(new ShiroUser(user.getId(),user.getUsername()),user.getPassword(), ByteSource.Util.bytes(salt),getName());
        }else{
            return null;
        }

    }

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialsDigest credentialsDigest;
    @Override
    public void afterPropertiesSet() throws Exception {
        CredentialsMatcher matcher = new CredentialsMatchesAdapter(credentialsDigest);
        setCredentialsMatcher(matcher);
    }
}
