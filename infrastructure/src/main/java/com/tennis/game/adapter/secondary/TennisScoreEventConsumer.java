package com.tennis.game.adapter.secondary;

import com.tennis.game.domain.events.ScoreUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
public class TennisScoreEventConsumer {

    @KafkaListener(topics = "tennis-scores", groupId = "${spring.application.name}")
    public void readScores(ScoreUpdatedEvent event) throws InterruptedException {
        Thread.sleep(Duration.of(2, ChronoUnit.SECONDS));
        log.info(event.score());
    }
}