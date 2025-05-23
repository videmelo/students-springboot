package br.com.studentmanagement.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    public static String md5(String password) throws NoSuchAlgorithmException {

        // Converte a password para ser CRIPTOGRAFADA
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, messageDigest.digest(password.getBytes()));
        return hash.toString(16);
    }
}
