package com.example.soo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


/**
 * @Author shenhaijin
 * @Date 2021/1/4 11:49
 * @Description TODO
 * @Version 1.0
 **/
public class JWTUtil {
    public static String createJWT(String userName,String passWord) throws Exception {
        String token = JWT.create().withAudience(userName).sign(Algorithm.HMAC256(passWord));
        return token;
    };

    public static String decodeToken(String token) throws Exception{
        String userName = JWT.decode(token).getAudience().get(0);
        return userName;
    }
}
