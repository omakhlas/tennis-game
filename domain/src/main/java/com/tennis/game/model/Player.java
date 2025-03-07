package com.tennis.game.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Player {
    private final String name;
    @Setter
    private Score score;

    public Player(String name) {
        this.name = name;
        this.score = Score.ZERO;
    }
}
