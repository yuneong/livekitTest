package com.example.monitoring.domain.livekitWebhook;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "participant_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParticipantHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String participantId;
    private String roomName;
    private String eventType;
    private LocalDateTime eventTime;

    public static ParticipantHistory create(String participantId, String roomName, String eventType) {
        ParticipantHistory history = new ParticipantHistory();

        history.participantId = participantId;
        history.roomName = roomName;
        history.eventType = eventType;
        history.eventTime = LocalDateTime.now();

        return history;
    }
}
