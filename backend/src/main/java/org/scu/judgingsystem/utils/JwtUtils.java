package org.scu.judgingsystem.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils {
    // 签名密钥
    private static final String SECRET = "_scu_";

    /**
     * 生成token
     * @param payload token携带的信息
     * @return token字符串
     */
    public static String generateJwt(Map<String,String> payload){
        // 指定token过期时间为7天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();
        // 构建payload
        payload.forEach(builder::withClaim);
        // 指定过期时间和签名算法
        String token = builder
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SECRET));

        return token;
    }


    /**
     * 解析token
     * @param token token字符串
     * @return 解析后的token
     */
    public static DecodedJWT decodeJwt(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        //DecodedJWT decodedJWT = JWT.decode(token);

        return decodedJWT;
    }
}



