package org.scu.judgingsystem;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.scu.judgingsystem.utils.JwtUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JwtTest {
    @Test
    public void testGen(){
        Map<String, String> claims = new HashMap<>();
        claims.put("username", "user1");
        claims.put("password", "pass1");

        //使用JWT工具类，生成身份令牌
        String token = JwtUtils.generateJwt(claims);
        System.out.println(token);

        DecodedJWT decoded = JwtUtils.decodeJwt(token);
        System.out.println(decoded.getClaim("username").asString());
    }

}
