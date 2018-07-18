package org.lyncc.bazinga.rx.bazinga.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

/**
 * @author liguolin
 * @create 2018-07-09 17:45
 **/
public class RsaUtils {

    private static String RSA = "RSA";

    public static void main(String[] args) {

        KeyPair keyPair = generateRSAKeyPair(1024);
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        System.out.println(new String(publicKey.getEncoded()));
//        System.out.println(keyPair.getPrivate().getFormat());
//        System.out.println(keyPair.getPublic().getFormat());
    }


    public static KeyPair generateRSAKeyPair(int keyLength)
    {
        try
        {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(keyLength);
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
