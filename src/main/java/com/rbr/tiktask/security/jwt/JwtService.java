package com.rbr.tiktask.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
    
    private final JwtProperties jwtProperties;

    public String generate(String email) {

        Date now = new Date();

        Date expires = new Date(now.getTime() + jwtProperties.getExpiration());

        return Jwts
                   .builder()
                   .setSubject(email)
                   .setIssuedAt(now)
                   .setExpiration(expires)
                   .signWith(Keys.hmacShaKeyFor(
                       jwtProperties
                           .getSecret()
                           .getBytes(StandardCharsets.UTF_8)
                   ))
                   .compact();

    }

    public String extractSubject(String token) {

        return Jwts
                   .parserBuilder()
                   .setSigningKey(
                       Keys.hmacShaKeyFor(
                           jwtProperties
                               .getSecret()
                               .getBytes(StandardCharsets.UTF_8)
                       )
                   )
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();

    }

}
