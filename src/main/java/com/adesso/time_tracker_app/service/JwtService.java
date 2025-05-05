package com.adesso.time_tracker_app.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Secret key (256-bit for HS256)
    private static final String SECRET_KEY = "d348f99e45ae9e3789a602c17e85c657d348f99e45ae9e3789a602c17e85c657";
    private final Key signingKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    private static final long expirationTime = 3600000;  // 1 hour expiration

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // Generate token with claims
    public String generateToken(Map<String, Object> extraClaims, String username) {

        JwtBuilder jwtBuilder = Jwts
                .builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey());

        return jwtBuilder.compact();
    }

    // Generate token without extra claims
    public String generateToken(String username) {
        return generateToken(Map.of(), username);
    }

    // Extract username
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    // Extract any claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseToken(token);
        return claimsResolver.apply(claims);
    }

    // Check if token is valid
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username)) && !isTokenExpired(token);
    }

    // Check if expired
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    // Parse the JWT
    private Claims parseToken(String token) {
        JwtParser jwtParser=Jwts.parser()
                .verifyWith((SecretKey) signingKey)
                .build();

        return jwtParser.parseSignedClaims(token)
                .getPayload();
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        Claims claims = parseToken(token);
        return (List<String>) claims.get("roles");
    }

}
