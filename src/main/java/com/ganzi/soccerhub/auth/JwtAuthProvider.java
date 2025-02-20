package com.ganzi.soccerhub.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganzi.soccerhub.auth.exception.InvalidTokenException;
import com.ganzi.soccerhub.common.property.JwtProviderProperties;
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

@Slf4j
@Component
public class JwtAuthProvider {
    public static final String AUDIENCE = "userId";
    public static final String EMAIL = "email";
    public static final String AUTHORITY = "auth";

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
        return Jwts.builder()
                .header()
                .add(createHeaders())
                .and()
                .subject("accessToken")
                .claim("iss", "off")
                .claims(claims)
                .expiration(Date.from(Instant.now().plusSeconds(properties.getAccessExpiredTime())))
                .issuedAt(new Date())
                .signWith(key)
                .compact();
    }

    public SessionUser getSessionUser(String token) {
        Claims claims = parseClaims(token);

        return new SessionUser(
                claims.get(AUDIENCE, Long.class),
                claims.get(EMAIL, String.class),
                JwtClaimConverter.namesToRoles(objectMapper.convertValue(claims.get(AUTHORITY), new TypeReference<>() {}))
        );
    }

    private Jws<Claims> parseToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) key)
                    .build()
                    .parseSignedClaims(token);
        } catch (SecurityException | MalformedJwtException e) {
            throw new InvalidTokenException();
        } catch (ExpiredJwtException e) {
            // expired
            throw new InvalidTokenException();
        } catch (UnsupportedJwtException e) {
            // unsupported
            throw new InvalidTokenException();
        } catch (IllegalArgumentException e) {
            // empty
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
