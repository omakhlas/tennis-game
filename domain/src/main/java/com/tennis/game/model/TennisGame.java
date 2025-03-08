package com.tennis.game.model;

import lombok.Getter;

@Getter
public class TennisGame {
    private final Player playerA;
    private final Player playerB;
    private Player winner;

    public TennisGame() {
        this.playerA = new Player("A");
        this.playerB = new Player("B");
    }

    public String getScore() {
        if(winner != null) return "Player " + winner.getName() + " wins the game";
        return "Player A : " + playerA.getScore().getPoints() + " / Player B : " + playerB.getScore().getPoints();
    }

    void pointScoredBy(Player player) {
        if(winner != null) return;
        if(player.getScore() == Score.FORTY) winner = player;
        player.nextScore();
    }

}


