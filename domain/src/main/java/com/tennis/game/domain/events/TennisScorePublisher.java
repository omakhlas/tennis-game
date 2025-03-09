package com.tennis.game.domain.events;

public interface TennisScorePublisher {
    void publish(DomainEvent event);
}