package com.jobtracker.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

        public static String extractUsername(String token) {

    return getClaims(token)
            .getBody()
            .getSubject();
}

    public static boolean validateToken(String token) {

    try {
        getClaims(token);
        return true;
    } catch (Exception e) {
        return false;
    }
}

    private static Jws<Claims> getClaims(String token) {

    return Jwts.parserBuilder()
            .setSigningKey(KEY)
            .build()
            .parseClaimsJws(token);
}

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey12";

    private static final Key KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 86400000)
                )
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }



}