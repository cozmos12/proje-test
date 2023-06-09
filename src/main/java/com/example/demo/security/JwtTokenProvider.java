package com.example.demo.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.net.Authenticator;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${questapp.app.secret}")
    private String APP_SECRET;
    @Value("${questapp.expires.secret}")
    private Long EXPIRES_IN;

    public String generateJwtToken(Authentication auth){
        JwtUserDetails userDetails=(JwtUserDetails) auth.getPrincipal();
        Date expireData =new Date(new Date().getTime()+EXPIRES_IN);
        return Jwts.builder().
                setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireData)
                .signWith(SignatureAlgorithm.HS512,APP_SECRET).compact();
    }
    public String generateJwtTokenByUserId(Long userId) {
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(userId))
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
    }

    Long getUserIdFromJwt(String token){
        Claims claims=Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJwt(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJwt(token);
            return !isTokenExpired(token);
        }catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException |
                IllegalArgumentException e){
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration=Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJwt(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
