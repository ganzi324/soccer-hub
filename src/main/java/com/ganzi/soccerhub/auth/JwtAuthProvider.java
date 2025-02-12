package com.ganzi.soccerhub.auth;

import com.ganzi.soccerhub.common.property.JwtProviderProperties;
import com.ganzi.soccerhub.user.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthProvider {

    private final JwtProviderProperties properties;
    private final Key key;

    public JwtAuthProvider(JwtProviderProperties properties) {
        this.properties = properties;
        byte[] keyBytes = Decoders.BASE64.decode(properties.getSecretKey());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .header()
                .add(createHeaders())
                .and()
                .subject("accessToken")
                .claim("iss", "off")
                .claim("aud", user.getEmail())
                .claim("auth", user.getUserRole())
                .expiration(Date.from(Instant.now().plusSeconds(properties.getAccessExpiredTime())))
                .issuedAt(new Date())
                .signWith(key)
                .compact();
    }

    private static Map<String, Object> createHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        return headers;
    }

}
