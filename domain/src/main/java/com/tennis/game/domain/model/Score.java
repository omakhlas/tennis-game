package com.tennis.game.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Score {
    ZERO(0),
    FIFTEEN(15),
    THIRTY(30),
    FORTY(40);
    private final int points;
}
