package com.javanorth.spring.springbootmultidatasource.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

public class DecryptUtil {

    public static String decryptStr(String encryptStr){
        StandardPBEStringEncryptor encrypt = new StandardPBEStringEncryptor();
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("region");
        encrypt.setConfig(config);

        //解密
        String plainText = encrypt.decrypt(encryptStr);
        LogUtil.info(DecryptUtil.class, "plain text: {}", plainText);
        return plainText;

    }

}
