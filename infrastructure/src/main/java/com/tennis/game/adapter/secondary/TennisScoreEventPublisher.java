package com.tennis.game.adapter.secondary;

import com.tennis.game.domain.events.DomainEvent;
import com.tennis.game.domain.events.TennisScorePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TennisScoreEventPublisher implements TennisScorePublisher {

    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
        kafkaTemplate.send("tennis-scores", event);
    }
}