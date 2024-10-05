package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtils {
    public static final Long JWT_TTL=60*60*1000L;
    public static final String JWT_KEY="whatisthis";

    public static String getUUID(){
        String token= UUID.randomUUID().toString().replaceAll("-","");
        return token;
    }
    public static String createJWT(String subject,Long ttlMills){
        JwtBuilder builder=getJwtBuilder(subject,ttlMills,getUUID());
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMills, String uuid) {
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        SecretKey secretKey=generalKey();
        long nowMills=System.currentTimeMillis();
        Date now=new Date(nowMills);
        if (ttlMills==null){
            ttlMills=JwtUtils.JWT_TTL;
        }
        long expMills=nowMills+ttlMills;
        Date expDate=new Date(expMills);
        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("XX")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm,secretKey)
                .setExpiration(expDate);
    }
    public static String createJWT(String id,String subject,Long ttlMills){
        JwtBuilder builder=getJwtBuilder(subject,ttlMills,id);
        return builder.compact();
    }
    public static SecretKey generalKey(){
        byte[]encoderedKey= Base64.getDecoder().decode(JwtUtils.JWT_KEY);
        SecretKey secretKey=new SecretKeySpec(encoderedKey,0,encoderedKey.length,"AES");
        return secretKey;
    }

    public static Claims parseJWT(String jwt)throws Exception{
        SecretKey secretKey=generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
