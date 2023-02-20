package com.example.psb_click.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class Base {

    @Value("${variable.partnerId}")
    private Integer partner_id;
    @Value("${variable.partnerKey}")
    private String partner_key;

    public String getServiceKey() throws NoSuchAlgorithmException {
        long unixTimestamp = (new Date()).getTime() / 1000;
        String serviceKey = partner_id+";"+Base.Hash(unixTimestamp+partner_key)+";"+unixTimestamp;
        return serviceKey;
    }

    static String Hash(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        byte[] digest = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
        BigInteger no = new BigInteger(1, digest);
        String hashtext = no.toString(16);

        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

}
