package com.tennis.game.domain.model;

import lombok.Getter;

@Getter
public class TennisGame {
    private boolean gameEnded = false;
    private final Player playerA;
    private final Player playerB;
    private Player winner;
    private Player advantage;

    private static final String PLAYER_A = "A";
    private static final String PLAYER_B = "B";
    private static final String WINS_MESSAGE = "Player %s wins the game";
    private static final String SIMPLE_SCORE_MESSAGE = "Player A : %s / Player B : %s";
    private static final String PLAYER_A_ADVANTAGE = "Player A : advantage / Player B : 40";
    private static final String PLAYER_B_ADVANTAGE = "Player A : 40 / Player B : advantage";

    public TennisGame() {
        this.playerA = new Player(PLAYER_A);
        this.playerB = new Player(PLAYER_B);
    }

    public String getScore() {
        if (winner != null && !gameEnded) {
            gameEnded = true;
            return String.format(WINS_MESSAGE, winner.getName());
        }
        if (advantage != null && !gameEnded) {
            return (advantage == playerA) ? PLAYER_A_ADVANTAGE : PLAYER_B_ADVANTAGE;
        }
        if (!gameEnded) {
            return String.format(SIMPLE_SCORE_MESSAGE, playerA.getScore().getPoints(), playerB.getScore().getPoints());
        }
        return null;
    }

    public void pointScoredBy(Player player) {

        if (gameEnded) return;
        if (winner != null) return;

        var opponent = getOpponent(player);
        if (player.getScore() == Score.FORTY && opponent.getScore() == Score.FORTY) {
            if (advantage == player) {
                winner = player;
                return;
            } else if (advantage == opponent) {
                advantage = null;
                return;
            } else {
                advantage = player;
                return;
            }
        }

        if (player.getScore() == Score.FORTY) {
            winner = player;
            return;
        }
        player.nextScore();
    }

    private Player getOpponent(Player player) {
        return player.getName().equals(playerA.getName()) ? playerB : playerA;
    }

}


