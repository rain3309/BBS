package com.rain.bbs.security;


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Encodes {

    public static String encodehex(byte[] input){
        return Hex.encodeHexString(input);
    }

    public static byte[] decodehex(String input){
        try {
           return  Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new IllegalArgumentException();
        }
    }
}
