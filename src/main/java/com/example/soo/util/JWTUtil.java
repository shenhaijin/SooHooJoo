package com.example.soo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.example.common.constant.Constant;
import com.example.soo.exception.AuthException;

import java.util.Date;


/**
 * @Author shenhaijin
 * @Date 2021/1/4 11:49
 * @Description TODO
 * @Version 1.0
 **/
public class JWTUtil {

    public static String createJWT(String userId,String userName) throws Exception {
        Date issued = new Date();
        Date expired = new Date(issued.getTime() + Constant.TOKEN_EXPIRE_TIME);
        String token = JWT.create()
                .withAudience(userId)
//                .withIssuedAt(issued)
//                .withExpiresAt(expired)
                .withClaim(Constant.USER_NAME_KEY, userName)    //载荷，随便写几个都可以
                .sign(Algorithm.HMAC256(Constant.TOKEN_SECRET));
        return token;
    };

    public static String getUserId(String token) throws Exception{
        return JWT.decode(token).getAudience().get(0);
    }

    public static String getUserName(String token) throws Exception{
        return JWT.decode(token).getClaim(Constant.USER_NAME_KEY).asString();
    }

    public static void verifyToken(String token) throws Exception{
        try{
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(Constant.TOKEN_SECRET)).build();
            jwtVerifier.verify(token);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new AuthException("token["+ token + "] 无效");
        }
    }
}
