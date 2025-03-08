package com.tennis.game.adapter.secondary;

import com.tennis.game.domain.model.Player;
import com.tennis.game.domain.model.TennisGame;
import com.tennis.game.domain.service.ITennisGameService;
import org.springframework.stereotype.Service;

@Service
public class TennisGameService implements ITennisGameService {

    private TennisGame tennisGame = new TennisGame();

    @Override
    public void pointScoredBy(Player player) {
        tennisGame.pointScoredBy(player);
    }

    @Override
    public String getScore() {
        return tennisGame.getScore();
    }

    @Override
    public Player getPlayerA() {
        return tennisGame.getPlayerA();
    }

    @Override
    public Player getPlayerB() {
        return tennisGame.getPlayerB();
    }
    @Override
    public void resetGame() {
        this.tennisGame = new TennisGame();
    }
}
