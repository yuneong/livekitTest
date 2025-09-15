package com.example.monitoring.interfaces.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/livekit")
public class LivekitTokenController {

    private static final String API_KEY = "devkey";
    private static final String API_SECRET =
            "this_is_a_very_long_secret_key_for_testing_purposes_12345";

    @GetMapping("/token")
    public Map<String, String> createToken(
            @RequestParam(defaultValue = "guest") String identity
    ) {
        long now = System.currentTimeMillis();

        // JWT 클레임 생성
        String token = Jwts.builder()
                .setHeaderParam("kid", API_KEY)   // key id
                .setIssuer(API_KEY)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 3600_000)) // 1시간 유효
                .claim("video", Map.of(
                        "roomJoin", true,
                        "room", "test-room"
                ))
                .setSubject(identity)
                .signWith(SignatureAlgorithm.HS256, API_SECRET.getBytes(StandardCharsets.UTF_8))
                .compact();

        return Map.of("token", token);
    }

}
