package com.tennis.game.model;

import lombok.Getter;

@Getter
public class TennisGame {
    private final Player playerA;
    private final Player playerB;
    private Player winner;
    private Player advantage;

    public TennisGame() {
        this.playerA = new Player("A");
        this.playerB = new Player("B");
    }

    public String getScore() {
        if(winner != null) return "Player " + winner.getName() + " wins the game";
        if(advantage != null) {
            return (advantage == playerA) ? "Player A : advantage / Player B : 40" : "Player A : 40 / Player B : advantage";
        }
        return "Player A : " + playerA.getScore().getPoints() + " / Player B : " + playerB.getScore().getPoints();
    }

    void pointScoredBy(Player player) {
        if(winner != null) return;

        var opponent = getOpponent(player);
        if(player.getScore() == Score.FORTY && opponent.getScore() == Score.FORTY) {
            if(advantage == player) {
                winner = player;
                return;
            } else if(advantage == opponent) {
                advantage = null;
                return;
            } else {
                advantage = player;
                return;
            }
        }

        if(player.getScore() == Score.FORTY) {
            winner = player;
            return;
        }
        player.nextScore();
    }

    private Player getOpponent(Player player) {
        return (player == playerA) ? playerB : playerA;
    }

}


