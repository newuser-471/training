package com.example.gateway.security;

import com.example.gateway.exception.JwtTokenIncorrectStructureException;
import com.example.gateway.exception.JwtTokenMalformedException;
import com.example.gateway.exception.JwtTokenMissingException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Autowired
    private JwtConfig jwtConfig;

    public String generateToken(String id){
        Claims claims = Jwts.claims().setSubject(id);
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis+ jwtConfig.getValidity()*1000*60;
        Date exp = new Date(expMillis);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }

    public void validateToken(final String header)throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            String[] parts = header.split(" ");
            if(parts.length!=2 || "Bears".equals(parts[0])){
                throw new JwtTokenIncorrectStructureException("Incorrect Authentication Structure");
            }
            Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(parts[1]);
        }catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty.");
        }
    }
}
