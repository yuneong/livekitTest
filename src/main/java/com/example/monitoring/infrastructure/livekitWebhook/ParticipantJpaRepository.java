package com.example.monitoring.infrastructure.livekitWebhook;

import com.example.monitoring.domain.livekitWebhook.ParticipantHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantJpaRepository extends JpaRepository<ParticipantHistory, Long> {

}
