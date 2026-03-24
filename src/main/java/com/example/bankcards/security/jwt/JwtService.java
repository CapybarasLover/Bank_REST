package com.example.bankcards.security.jwt;


import com.example.bankcards.dto.JwtAuthenticationDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
public class JwtService {
    private static final Logger LOGGER = LogManager.getLogger(JwtService.class);

    private final String secret = System.getenv("JWT_SECRET");

    private final int expirationTimeToken = 1000 * 60 * 10; // токен действителен 5 минуты
    private final int expirationTimeRefreshToken = 1000 * 60 * 60 * 48; // рефреш токен доступен в течение двух суток

    public JwtAuthenticationDto generateAuthToken(Map<String, Object> claims, String username){
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setToken(createToken(claims, username));
        jwtDto.setRefreshToken(createRefreshToken(claims, username));
        return jwtDto;
    }

    public JwtAuthenticationDto refreshAuthToken(Map<String, Object> claims, String username, String refreshToken){
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setToken(createToken(claims, username));
        jwtDto.setRefreshToken(refreshToken);
        return jwtDto;
    }

    public String getUsernameFromToken(String token){
        Claims payload = Jwts.parser()
                .verifyWith(getSecreteKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return payload.getSubject();
    }

    public boolean validateToken(String token){
        try{
           Jwts.parser()
                   .verifyWith(getSecreteKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
           return true;
        }catch (ExpiredJwtException jwtEx){
            LOGGER.error("ExpiredJwt Exception:", jwtEx);
        }catch (UnsupportedJwtException jwtEx){
            LOGGER.error("UnsupportedJwt Exception:", jwtEx);
        }catch (MalformedJwtException jwtEx){
            LOGGER.error("MalformedJwt Exception:", jwtEx);
        }catch (SecurityException jwtEx){
            LOGGER.error("Security Exception:", jwtEx);
        }
        catch (JwtException jwtEx){
            LOGGER.error("Invalid token:", jwtEx);
        }
        catch (Exception jwtEx){
            LOGGER.error("Unexpected error with jwt:", jwtEx);
        }
        return false;
    }

    public String createToken(Map<String, Object> claims, String subject){
        Date expiryDate =
                Date.from(Instant.ofEpochMilli(System.currentTimeMillis() +
                        expirationTimeToken));
        return Jwts.builder()
                .claims(claims)
                .claims()
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate)
                .and()
                .signWith(getSecreteKey())
                .compact();
    }

    public String createRefreshToken(Map<String, Object> claims, String subject){
        Date expiryDate =
                Date.from(Instant.ofEpochMilli(System.currentTimeMillis() +
                        expirationTimeRefreshToken));
        return Jwts.builder()
                .claims(claims)
                .claims()
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate)
                .and()
                .signWith(getSecreteKey())
                .compact();
    }

    private SecretKey getSecreteKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
