package com.tennis.game.domain.usecase;

import com.tennis.game.domain.DomainService;
import com.tennis.game.domain.events.ScoreUpdatedEvent;
import com.tennis.game.domain.events.TennisScorePublisher;
import com.tennis.game.domain.model.Player;
import com.tennis.game.domain.model.TennisGame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@DomainService
public class TennisGameUseCase implements ITennisGameUseCase {

    private final TennisScorePublisher tennisScorePublisher;

    @Override
    public List<String> playGame(String input) {
        log.info("Starting the game");
        if (!input.matches("[AB]+")) {
            throw new IllegalArgumentException("Invalid character : Only 'A' and 'B' are allowed");
        }
        var tennisGame = new TennisGame();
        var result = new ArrayList<String>();
        for (char c : input.toCharArray()) {
            Player player = (c == 'A') ? tennisGame.getPlayerA() : tennisGame.getPlayerB();
            tennisGame.pointScoredBy(player);
            String score = tennisGame.getScore();
            if (score != null) {
                log.info("Current score: {}", score);
                ScoreUpdatedEvent event = new ScoreUpdatedEvent(score);
                tennisScorePublisher.publish(event);
                result.add(score);
            }
        }
        return result;
    }
}
