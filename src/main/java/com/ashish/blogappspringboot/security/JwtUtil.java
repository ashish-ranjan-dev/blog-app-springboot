package com.ashish.blogappspringboot.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String jwtSecret = "dnsaerr3432n5nfd4jfnrj"; //TODO : dont store in git

    private final Algorithm signingAlgorithm = Algorithm.HMAC256(jwtSecret);

    public String createJwt(Long userId){
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date()) // TODO: set expiration date
                .sign(signingAlgorithm);
    }

    public Long retrieveUserId(String jwt){
        var decodedJwt = JWT.decode(jwt);
        return Long.valueOf(decodedJwt.getSubject());
    }
}
