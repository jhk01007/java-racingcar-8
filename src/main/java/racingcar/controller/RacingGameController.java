package racingcar.controller;

import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.mapper.CarRacingMapper;
import racingcar.service.CarRacingService;
import racingcar.view.RacingGameInputView;
import racingcar.view.RacingGameOutputView;

public class RacingGameController {

    private final RacingGameInputView racingGameInputView;
    private final RacingGameOutputView racingGameOutputView;
    private final CarRacingService carRacingService;

    public RacingGameController(RacingGameInputView racingGameInputView, RacingGameOutputView racingGameOutputView, CarRacingService carRacingService) {
        this.racingGameInputView = racingGameInputView;
        this.racingGameOutputView = racingGameOutputView;
        this.carRacingService = carRacingService;
    }

    public void start() {
        // 입력
        String carNames = racingGameInputView.readCarNames();
        String roundCount = racingGameInputView.readRoundCount();

        // 레이스 시작
        RacingGameRequestDto requestDto = CarRacingMapper.toRequestDto(carNames, roundCount);
        RacingGameResponseDto responseDto = carRacingService.start(requestDto);

        // 레이스 결과 출력
        racingGameOutputView.writeResult(responseDto);
    }
}
