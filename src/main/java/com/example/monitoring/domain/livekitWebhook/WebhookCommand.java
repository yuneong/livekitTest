package com.example.monitoring.domain.livekitWebhook;

import com.example.monitoring.interfaces.api.livekitWebhook.WebhookV1Dto;

public record WebhookCommand(
        String event,
        String room,
        String participantId
) {

    public static WebhookCommand toCommand(WebhookV1Dto.request request) {
        String participantId = request.participant() != null
                ? request.participant().identity()
                : "unknown";

        String roomName = request.room() != null
                ? request.room().name()
                : "unknown";

        return new WebhookCommand(
                request.event(),
                roomName,
                participantId
        );
    }
}
