package com.tennis.game.domain.usecase;

import com.tennis.game.domain.events.TennisScorePublisher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TennisGameUseCaseTest {

    @Mock
    private TennisScorePublisher tennisScorePublisher;

    @InjectMocks
    private TennisGameUseCase tennisGameUseCase;

    private static final List<String> MOCK_SCORE = List.of(
            "Player A : 15 / Player B : 0",
            "Player A : 15 / Player B : 15",
            "Player A : 30 / Player B : 15",
            "Player A : 30 / Player B : 30",
            "Player A : 40 / Player B : 30",
            "Player A : 40 / Player B : 40",
            "Player A : advantage / Player B : 40",
            "Player A : 40 / Player B : 40",
            "Player A : 40 / Player B : advantage",
            "Player B wins the game"
    );


    @Test
    void shouldPlayGameAndReturnScores() {

        // Given
        String input = "ABABABABBB";

        // When
        var result = tennisGameUseCase.playGame(input);

        // Then
        Assertions.assertThat(result).containsExactlyElementsOf(MOCK_SCORE);

    }
}

