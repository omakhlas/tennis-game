package com.tennis.game.adapter.primary;

import com.tennis.game.infra.api.TennisGameApi;
import com.tennis.game.domain.usecase.ITennisGameUseCase;
import com.tennis.game.infra.api.model.TennisGameRequestApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TennisGameController implements TennisGameApi {

    private final ITennisGameUseCase useCase;

    @Override
    public ResponseEntity<List<String>> playSet(TennisGameRequestApiDto request) {
        return ResponseEntity.ok(useCase.playGame(request.getInput()));
    }

}
