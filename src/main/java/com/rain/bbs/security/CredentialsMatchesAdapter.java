package com.rain.bbs.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.util.Assert;
import org.apache.shiro.util.ByteSource;

public class CredentialsMatchesAdapter implements CredentialsMatcher {

    private CredentialsDigest credentialsDigest;

    public CredentialsMatchesAdapter(CredentialsDigest credentialsDigest){
        Assert.notNull(credentialsDigest);
        this.credentialsDigest = credentialsDigest;

    }
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String plainCredentials,credentials = null;
        byte[] saltByte = null;
        Object tokenCredentials = authenticationToken.getCredentials();
        if(tokenCredentials == null){
            plainCredentials = null;
        }else if(tokenCredentials instanceof char[]){
            plainCredentials = new String((char[])tokenCredentials);
        }else if(tokenCredentials instanceof  String){
            plainCredentials = (String) tokenCredentials;
        }else{
            throw new IllegalArgumentException("credentials only support String or char[].");
        }

        if(authenticationInfo instanceof SaltedAuthenticationInfo){
            Object salt = ((SaltedAuthenticationInfo) authenticationInfo).getCredentialsSalt();
            if(salt == null){
                saltByte = null;
            }else if(salt instanceof ByteSource){
                saltByte = ((ByteSource)salt).getBytes();
            }else if(salt instanceof byte[]){
                saltByte = (byte[]) salt;
            }else{
                throw new IllegalArgumentException("salt only support byte[].");
            }

            Object infoCredentials = authenticationInfo.getCredentials();
            if(infoCredentials == null){
                credentials = null;
            }else if(infoCredentials instanceof String){
                credentials = (String)infoCredentials;
            }else if(infoCredentials instanceof char[]){
                credentials = new String((char[])infoCredentials);
            }else{
                throw new IllegalArgumentException("credentials only support String or char[]");
            }

        }
        boolean ff = credentialsDigest.matches(credentials,plainCredentials,saltByte);
        return credentialsDigest.matches(credentials,plainCredentials,saltByte);
    }
}
