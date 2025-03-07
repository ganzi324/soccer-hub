package com.ganzi.soccerhub.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganzi.soccerhub.auth.exception.InvalidTokenException;
import com.ganzi.soccerhub.common.property.JwtProviderProperties;
import com.ganzi.soccerhub.user.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class JwtAuthProvider {
    public static final String AUDIENCE = "userId";
    public static final String USERNAME = "name";
    public static final String AUTHORITY = "auth";

    private static final String SUBJECT_ACCESS_TOKEN = "accessToken";
    private static final String SUBJECT_REFRESH_TOKEN = "refreshToken";

    private final JwtProviderProperties properties;
    private final Key key;
    private final ObjectMapper objectMapper;

    public JwtAuthProvider(JwtProviderProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        byte[] keyBytes = Decoders.BASE64.decode(properties.getSecretKey());
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.objectMapper = objectMapper;
    }

    public String generateAccessToken(Map<String, Object> claims) {
        return generateToken(SUBJECT_ACCESS_TOKEN, claims, getAccessTokenExpiredAt());
    }

    public String generateRefreshToken(Map<String, Object> claims) {
        return generateRefreshToken(claims, getRefreshTokenExpiresAt());
    }

    public String generateRefreshToken(Map<String, Object> claims, Instant expiresAt) {
        return generateToken(SUBJECT_REFRESH_TOKEN, claims, expiresAt);
    }

    public SessionUser getSessionUser(String token) {
        Claims claims = parseClaims(token);

        return new SessionUser(
                claims.get(AUDIENCE, Long.class),
                claims.get(USERNAME, String.class),
                JwtClaimConverter.namesToRoles(objectMapper.convertValue(claims.get(AUTHORITY), new TypeReference<>() {}))
        );
    }

    public Map<String, Object> getClaimsByUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(AUDIENCE, user.getId().get().value());
        claims.put(USERNAME, user.getName());
        claims.put(AUTHORITY, JwtClaimConverter.roleNamesFromUserRoles(Set.of(user.getUserRole())));

        return claims;
    }

    public Instant getRefreshTokenExpiresAt() {
        return Instant.now().plusSeconds(properties.getRefreshExpiredTime());
    }

    private Instant getAccessTokenExpiredAt() {
        return Instant.now().plusSeconds(properties.getAccessExpiredTime());
    }

    private String generateToken(String subject, Map<String, Object> claims, Instant expiresAt) {
        return Jwts.builder()
                .header()
                .add(createHeaders())
                .and()
                .subject(subject)
                .claim("iss", "off")
                .claims(claims)
                .expiration(Date.from(expiresAt))
                .issuedAt(new Date())
                .signWith(key)
                .compact();
    }

    private Jws<Claims> parseToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token);
        } catch (SecurityException | MalformedJwtException | IllegalArgumentException e) {
            log.info(e.getMessage());
            throw new InvalidTokenException();
        } catch (ExpiredJwtException e) {
            log.info("Token Expiration. (User Id : {})", e.getClaims().get(AUDIENCE));
            throw new InvalidTokenException();
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported jwt Unsecured JWSs.");
            throw new InvalidTokenException();
        }
    }

    private Claims parseClaims(String token) {
        return parseToken(token).getPayload();
    }

    private static Map<String, Object> createHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        return headers;
    }
}
