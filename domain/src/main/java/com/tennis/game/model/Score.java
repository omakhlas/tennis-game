package com.tennis.game.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Score {
    ZERO(0),
    FIFTEEN(15),
    THIRTY(0),
    FORTY(0);
    private final int points;
}
