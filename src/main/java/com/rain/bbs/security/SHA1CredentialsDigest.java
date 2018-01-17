package com.rain.bbs.security;

public class SHA1CredentialsDigest extends HashCredentialsDigest{

    public static final int HASH_INTERATIONS = 1024;

    @Override
    protected byte[] digest(byte[] input, byte[] salt) {
        return Digests.sha1(input,salt,HASH_INTERATIONS);
    }
}
