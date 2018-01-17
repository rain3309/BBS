package com.rain.bbs.security;

import org.apache.commons.lang3.Validate;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Digests {

    private static SecureRandom random = new SecureRandom();

    private static final String SHA1 = "SHA-1";

    public static byte[] generateSalt(int byteSize){
        Validate.isTrue(byteSize>0,"byteSize argument must be a positive integer (1 or larger)",byteSize);
        byte[] bytes = new byte[byteSize];
        random.nextBytes(bytes);
        return bytes;
    }

    public static byte[] sha1(byte[] input,byte[]salt,int iterations){
        return digest(input,SHA1,salt,iterations);
    }

    /**
     * 对字符串进行散列 支持md5和sha1算法
     * @param input
     * @param algotithm
     * @param salt
     * @param iterations
     * @return
     */
    public static byte[] digest(byte[] input,String algotithm,byte[] salt,int iterations){

        try {
            MessageDigest instance = MessageDigest.getInstance(algotithm);
            if(salt != null){
                instance.update(salt);
            }
            byte[] result = instance.digest(input);

            for (int i = 1; i < iterations; i++) {
                instance.reset();
                result = instance.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException();
        }

    }
}
