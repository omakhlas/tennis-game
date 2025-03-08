package com.tennis.game.domain.service;

import com.tennis.game.domain.model.Player;

public interface ITennisGameService {

    void pointScoredBy(Player player);
    String getScore();

    Player getPlayerA();
    Player getPlayerB();

    public void resetGame();

}
