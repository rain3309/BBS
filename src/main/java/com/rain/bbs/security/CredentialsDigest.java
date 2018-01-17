package com.rain.bbs.security;

public interface CredentialsDigest {

    public String digest(String uncryptPwd,byte[] salt);

    public boolean matches(String credentials,String plainCredentials,byte[] salt);
}
