package com.example.monitoring.interfaces.api.livekitWebhook;

import com.example.monitoring.domain.livekitWebhook.WebhookCommand;
import com.example.monitoring.domain.livekitWebhook.WebhookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/livekit/webhook")
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookService webhookService;

    @PostMapping("")
    public ResponseEntity<Void> handleWebhook(
            @RequestBody WebhookV1Dto.request request,
            @RequestHeader(value = "Authorization", required = false) String signatureHeader
    ) {
        log.info("✅ Received webhook event: {}", request);
        log.info("Signature Header: {}", signatureHeader);

        log.debug("✅ Received webhook event: {}", request);
        log.debug("Signature Header: {}", signatureHeader);

        // TODO: request -> JSON 직렬화 후 verifySignature 호출
        // if (!webhookService.verifySignature(requestJson, signatureHeader)) {
        //     return ResponseEntity.status(401).build();
        // }

        WebhookCommand command = WebhookCommand.toCommand(request);

        webhookService.handleEvent(command);

        return ResponseEntity.ok().build();
    }

}
