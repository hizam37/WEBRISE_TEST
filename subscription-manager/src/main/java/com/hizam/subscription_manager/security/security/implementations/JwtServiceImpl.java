package com.hizam.subscription_manager.security.security.implementations;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtServiceImpl {

    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token,UserDetails userDetails);
    String generateToken(Map<String,Object> extractClaims,UserDetails userDetails);
    boolean isTokenExpired(String token);
    Date extractExpiration(String token);
    <T> T extractClaim(String token, Function<Claims,T> ClaimResolvers);
    Claims extractAllClaims(String token);
    SecretKey getSigningKey();
}
