package com.tennis.game.domain.usecase;

import com.tennis.game.domain.DomainService;
import com.tennis.game.domain.model.Player;
import com.tennis.game.domain.service.ITennisGameService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@DomainService
public class TennisGameUseCase implements ITennisGameUseCase {

    private final ITennisGameService tennisGameService;

    @Override
    public List<String> playGame(String input) {
        tennisGameService.resetGame();
        var result = new ArrayList<String>();
        for (char c : input.toCharArray()) {
            Player player = (c == 'A') ? tennisGameService.getPlayerA() : tennisGameService.getPlayerB();
            tennisGameService.pointScoredBy(player);
            String score = tennisGameService.getScore();
            if (score != null) {
                result.add(score);
            }
        }
        return result;
    }
}
