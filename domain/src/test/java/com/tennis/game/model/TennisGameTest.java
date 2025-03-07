package com.tennis.game.model;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TennisGameTest {

    @Test
    public void should_test_initial_score_when_the_game_starts() {
        TennisGame game = new TennisGame();
        var score = game.getScore();
        Assertions.assertThat(score).isEqualTo("Player A : 0 / Player B : 0");
    }
}
