package com.tennis.game.domain.usecase;

import com.tennis.game.domain.model.Player;
import com.tennis.game.domain.service.ITennisGameService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TennisGameUseCaseTest {

    @Mock
    private ITennisGameService tennisGameService;

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
            "Player B wins the game"
    );


    @Test
    void shouldPlayGameAndReturnScores() {

        // Given
        String input = "ABABABABB";

        when(tennisGameService.getPlayerA()).thenReturn(new Player("A"));
        AtomicInteger counter = new AtomicInteger();
        when(tennisGameService.getScore()).thenAnswer(invocation -> MOCK_SCORE.get(counter.getAndIncrement()));

        // When
        var result = tennisGameUseCase.playGame(input);

        // Then
        Assertions.assertThat(result).containsExactlyElementsOf(MOCK_SCORE);

        verify(tennisGameService, times(9)).pointScoredBy(any());
        verify(tennisGameService, times(9)).getScore();
    }
}

