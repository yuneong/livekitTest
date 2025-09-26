package com.example.monitoring.domain.livekitToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TokenService {

    @Value("${livekit.api.key}")
    private String API_KEY;

    @Value("${livekit.api.secret}")
    private String API_SECRET;

    public String createToken(String roomName, String participantName) {
        if (roomName == null || roomName.isEmpty() || participantName == null || participantName.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "roomName and identity are required");
        }

//        Map<String, Object> claims = new HashMap<>();
//        claims.put("room", roomName);
//        claims.put("roomJoin", true);
//        claims.put("roomCreate", true);
//        claims.put("canPublish", true);
//        claims.put("canSubscribe", true);
//        claims.put("canPublishData", true);
//
//        long now = System.currentTimeMillis();
//
//        SecretKey secretKey = Keys.hmacShaKeyFor(API_SECRET.getBytes(StandardCharsets.UTF_8));
//
//        log.info("API_KEY={}, API_SECRET={}", API_KEY, API_SECRET);
//
//        return Jwts.builder()
//                .setHeaderParam("kid", API_KEY)   // key id
//                .setIssuer(API_KEY)
//                .setSubject(participantName)
//                .addClaims(claims)
//                .setIssuedAt(new Date(now))
//                .setExpiration(new Date(now + 3600_000)) // 1시간 유효
//                .signWith(secretKey, SignatureAlgorithm.HS256)
//                .compact();

        long now = System.currentTimeMillis();

        SecretKey secretKey = Keys.hmacShaKeyFor(API_SECRET.getBytes(StandardCharsets.UTF_8));

        Map<String, Object> videoGrants = new HashMap<>();
        videoGrants.put("room", roomName);
        videoGrants.put("roomJoin", true);
        videoGrants.put("roomCreate", true);
        videoGrants.put("canPublish", true);
        videoGrants.put("canSubscribe", true);
        videoGrants.put("canPublishData", true);

        Map<String, Object> claims = new HashMap<>();
        claims.put("video", videoGrants);

        return Jwts.builder()
                .setHeaderParam("kid", API_KEY)
                .setIssuer(API_KEY)
                .setSubject(participantName)
                .addClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 3600_000))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

}
