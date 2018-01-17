package com.rain.bbs.security;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

public class Cryptos {

    private static final Charset CHARSET = Charset.forName("UTF-8");

    public static byte[] utf8encode(CharSequence string){

        try {
            ByteBuffer bytes = CHARSET.newEncoder().encode(CharBuffer.wrap(string));
            byte[] bytesCopy = new byte[bytes.limit()];
            System.arraycopy(bytes.array(),0,bytesCopy,0,bytes.limit());
            return bytesCopy;
        } catch (CharacterCodingException e) {
            throw new IllegalArgumentException("Encoding failed",e);
        }
    }
}
