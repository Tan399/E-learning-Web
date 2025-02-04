package com.example.ProjectElearning.Service;


import com.example.ProjectElearning.Model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;


@Service
public class JwtService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(JwtService.class);

    @Value("${spring.app.jwtSecret}")
    public  String jwtSecret;

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);

    }
    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user){
        String username=extractUsername(token);
        return (username.equals(user.getUsername())&&!isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public  <T> T extractClaim(String token, Function<Claims, T> resolver){
        Claims  claims=extractAllClaims(token);
        return  resolver.apply(claims);

    }

    private Claims extractAllClaims(String token){
        return  Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload();
    }


    public String generateToken(User user){
        return Jwts.builder().subject(user.getUsername()).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(key())
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public  String getJwtFromHeader(HttpServletRequest request){
        String BearerToken=request.getHeader("Authorization");
        if(BearerToken!=null&&BearerToken.startsWith("Bearer ")){
            return BearerToken.substring(7);
        }

        return null;
    }

    public boolean validateJwtToken(String authToken) {
        try{
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }



}
