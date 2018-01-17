package com.rain.bbs.security;

import org.apache.commons.lang3.StringUtils;

public abstract class HashCredentialsDigest implements CredentialsDigest {

    @Override
    public String digest(String uncryptPwd, byte[] salt) {

        if(StringUtils.isBlank(uncryptPwd)){
            return null;
        }
        byte[] hashPassword = digest(Cryptos.utf8encode(uncryptPwd), salt);
        return Encodes.encodehex(hashPassword);
    }

    @Override
    public boolean matches(String credentials, String plainCredentials, byte[] salt) {

        if(StringUtils.isBlank(credentials) && StringUtils.isBlank(plainCredentials)){
            return true;
        }
        return StringUtils.equals(credentials,digest(plainCredentials,salt));
    }

    protected abstract byte[] digest(byte[] input,byte[] salt);
}
