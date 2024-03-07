package com.lzj.admin.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DencryptBzUtil {

    private static final byte[] IV_BYTES = "0102030405060708".getBytes();
    private static final char[] CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final byte[] IA = new byte[256];


    /**
     * 解密测试方法
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String s = decrypt("~if4e7qicQU5nfZ7ClODCFg==~VgLZ37rsVa7l3JgpVgLZ37rs3JgpxK05Q0Yb3JgpVa7l~", "YclLHTlUkgAtwORk");
        System.out.println(s);
    }

    /**
     * 解密方法
     * @param content  待解密的密文
     * @param decryptKey 解密的密钥
     * @return
     * @throws Exception
     */
   public static String decrypt(String content, String decryptKey) throws Exception {
        if (!isCiphertext(content)) {
            throw new IllegalArgumentException("非合法密文!!!");
        } else {
            String[] split = content.split("~");
            String text = split[1];
            return aesDecrypt(text, decryptKey);
        }
    }

   public  static boolean isCiphertext(String content) {
        String[] split = content.split("~");
        if (split.length != 3) {
            return false;
        } else {
            boolean flag = content.startsWith("~") && content.endsWith("~");
            if (!flag) {
                return false;
            } else if (content.length() != content.getBytes().length) {
                return false;
            } else {
                int textLen = split[2].length();
                return textLen % 4 == 0;
            }
        }
    }

   

    private static String aesDecrypt(String content, String decryptKey) throws Exception {
        byte[] bytes = aesUtil(decryptKey, decode(content.getBytes("UTF-8")), 2);
        return new String(bytes, "UTF-8");
    }

    public static byte[] aesUtil(String key, byte[] content, int mode) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException {
        byte[] bytes = key.getBytes();
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(bytes);
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(256, random);
        SecretKey secretKey = kgen.generateKey();
        IvParameterSpec iv = new IvParameterSpec(IV_BYTES);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, new SecretKeySpec(secretKey.getEncoded(), "AES"), iv);
        return cipher.doFinal(content);
    }

    private static byte[] decode(byte[] inbuf) {
        int size = inbuf.length / 4 * 3;
        if (size == 0) {
            return inbuf;
        } else {
            if (inbuf[inbuf.length - 1] == 61) {
                --size;
                if (inbuf[inbuf.length - 2] == 61) {
                    --size;
                }
            }

            byte[] outbuf = new byte[size];
            int inpos = 0;
            int outpos = 0;

            for(size = inbuf.length; size > 0; size -= 4) {
                byte a = IA[inbuf[inpos++] & 255];
                byte b = IA[inbuf[inpos++] & 255];
                outbuf[outpos++] = (byte)(a << 2 & 252 | b >>> 4 & 3);
                if (inbuf[inpos] == 61) {
                    return outbuf;
                }

                a = b;
                b = IA[inbuf[inpos++] & 255];
                outbuf[outpos++] = (byte)(a << 4 & 240 | b >>> 2 & 15);
                if (inbuf[inpos] == 61) {
                    return outbuf;
                }

                a = b;
                b = IA[inbuf[inpos++] & 255];
                outbuf[outpos++] = (byte)(a << 6 & 192 | b & 63);
            }

            return outbuf;
        }
    }

    static {
        int i;
        for(i = 0; i < 255; ++i) {
            IA[i] = -1;
        }

        for(i = 0; i < CA.length; ++i) {
            IA[CA[i]] = (byte)i;
        }

    }
}
