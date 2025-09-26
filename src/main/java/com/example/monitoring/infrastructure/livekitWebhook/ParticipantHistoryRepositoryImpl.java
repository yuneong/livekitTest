package com.example.monitoring.infrastructure.livekitWebhook;

import com.example.monitoring.domain.livekitWebhook.ParticipantHistory;
import com.example.monitoring.domain.livekitWebhook.ParticipantHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipantHistoryRepositoryImpl implements ParticipantHistoryRepository {

    private final ParticipantJpaRepository participantJpaRepository;

    @Override
    public ParticipantHistory save(ParticipantHistory participantHistory) {
        return participantJpaRepository.save(participantHistory);
    }

}
