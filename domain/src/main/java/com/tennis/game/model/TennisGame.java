package com.tennis.game.model;

import lombok.Getter;

@Getter
public class TennisGame {
    private final Player playerA;
    private final Player playerB;

    public TennisGame() {
        this.playerA = new Player("A");
        this.playerB = new Player("B");
    }

    public String getScore() {
        return "Player A : " + playerA.getScore().getPoints() + " / Player B : " + playerB.getScore().getPoints();
    }

    void pointScoredBy(Player player) {
        player.nextScore();
    }

}


