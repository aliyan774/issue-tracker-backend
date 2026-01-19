package com.example.issue_tracker_backend.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private Long expiration;
    
    private Key getSigningKey() {
      byte[] keyBytes = secret.getBytes();
      // Ensure the key is at least 32 bytes for HS256
      if (keyBytes.length < 32) {
        keyBytes = Keys.hmacShaKeyFor(new byte[32]).getEncoded();
      }
      return new SecretKeySpec(keyBytes, 0, keyBytes.length, SignatureAlgorithm.HS256.getJcaName());
    }
    
    public String extractEmail(String token) {
      return extractClaim(token, Claims::getSubject);
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
      try {
        return Jwts.parser()
          .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
          .build()
          .parseSignedClaims(token)
          .getPayload();
      } catch (Exception e) {
        throw new RuntimeException("Invalid JWT token: " + e.getMessage(), e);
      }
    }
    
    public String generateToken(String email, Long userId, String role) {
      Map<String, Object> claims = new HashMap<>();
      claims.put("userId", userId);
      claims.put("role", role);
      return createToken(claims, email);
    }
    
    private String createToken(Map<String, Object> claims, String subject) {
      return Jwts.builder()
        .claims(claims)
        .subject(subject)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
    }
    
    public Boolean validateToken(String token, String email) {
      final String extractedEmail = extractEmail(token);
      return (extractedEmail.equals(email) && !isTokenExpired(token));
    }
    
    private Boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
    }
    
    private Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
    }
}
