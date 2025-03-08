package com.tennis.game.domain.model;

import lombok.Getter;
import lombok.Setter;

import static com.tennis.game.domain.model.Score.FIFTEEN;
import static com.tennis.game.domain.model.Score.FORTY;
import static com.tennis.game.domain.model.Score.THIRTY;
import static com.tennis.game.domain.model.Score.ZERO;

@Getter
public class Player {
    private final String name;
    @Setter
    private Score score;

    public Player(String name) {
        this.name = name;
        this.score = ZERO;
    }

    public void nextScore() {
        switch (score) {
            case ZERO -> score =FIFTEEN;
            case FIFTEEN -> score = THIRTY;
            case THIRTY -> score = FORTY;
        }
    }
}
