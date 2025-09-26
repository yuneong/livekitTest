package com.example.monitoring.interfaces.api.livekitWebhook;

import java.util.Map;

public class WebhookV1Dto {

//    public record request(
//            String event,
//            String room,
//            Map<String, Object> participant
//    ) {}

    public record request(
            String event,
            Room room,
            Participant participant
    ) {
        public record Room(
                String sid,
                String name
        ) {}

        public record Participant(
                String sid,
                String identity,
                Long joinedAt
        ) {}
    }

}
