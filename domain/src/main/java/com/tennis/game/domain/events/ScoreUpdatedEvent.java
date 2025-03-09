package com.tennis.game.domain.events;

public record ScoreUpdatedEvent(String score) implements DomainEvent {
}