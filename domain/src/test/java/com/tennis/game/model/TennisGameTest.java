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

    @Test
    public void should_return_actual_score_when_the_thePlayers_score_some_points() {
        TennisGame game = new TennisGame();
        game.pointScoredBy(game.getPlayerA());
        Assertions.assertThat(game.getScore()).isEqualTo("Player A : 15 / Player B : 0");
        game.pointScoredBy(game.getPlayerA());
        Assertions.assertThat(game.getScore()).isEqualTo("Player A : 30 / Player B : 0");
        game.pointScoredBy(game.getPlayerA());
        Assertions.assertThat(game.getScore()).isEqualTo("Player A : 40 / Player B : 0");
        game.pointScoredBy(game.getPlayerB());
        Assertions.assertThat(game.getScore()).isEqualTo("Player A : 40 / Player B : 15");
    }

    @Test
    public void playerA_should_win_a_normal_game() {
        TennisGame game = new TennisGame();
        game.pointScoredBy(game.getPlayerA());
        game.pointScoredBy(game.getPlayerA());
        game.pointScoredBy(game.getPlayerA());
        game.pointScoredBy(game.getPlayerA());
        Assertions.assertThat(game.getScore()).isEqualTo("Player A wins the game");
    }
}
