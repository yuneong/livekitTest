package com.example.monitoring.domain.livekitWebhook;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebhookService {

    private final ParticipantHistoryRepository participantHistoryRepository;

    public void handleEvent(WebhookCommand command) {
        log.debug("command:: "+ command.toString());
        ParticipantHistory participantHistory = ParticipantHistory.create(
                command.participantId(),
                command.room(),
                command.event());

        participantHistoryRepository.save(participantHistory);
    }

}
