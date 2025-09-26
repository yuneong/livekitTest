package com.example.monitoring.interfaces.api.livekitToken;

import com.example.monitoring.domain.livekitToken.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/livekit/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService livekitService;

    @GetMapping("")
    public ResponseEntity<Map<String, String>> createToken(
            @RequestParam String room,
            @RequestParam String identity
    ) {
        String token = livekitService.createToken(room, identity);

        return ResponseEntity.ok(Map.of("token", token));
    }

}
