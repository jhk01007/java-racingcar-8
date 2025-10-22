package racingcar.controller;

import racingcar.dto.CarRacingRequestDto;
import racingcar.dto.CarRacingResponseDto;
import racingcar.mapper.CarRacingMapper;
import racingcar.service.CarRacingService;
import racingcar.view.CarRacingInputView;
import racingcar.view.CarRacingOutputView;

public class CarRacingController {

    private final CarRacingInputView carRacingInputView;
    private final CarRacingOutputView carRacingOutputView;
    private final CarRacingService carRacingService;

    public CarRacingController(CarRacingInputView carRacingInputView, CarRacingOutputView carRacingOutputView, CarRacingService carRacingService) {
        this.carRacingInputView = carRacingInputView;
        this.carRacingOutputView = carRacingOutputView;
        this.carRacingService = carRacingService;
    }

    public void start() {
        // 입력
        String carNames = carRacingInputView.readCarNames();
        String roundCount = carRacingInputView.readRoundCount();

        // 레이스 시작
        CarRacingRequestDto requestDto = CarRacingMapper.toRequestDto(carNames, roundCount);
        CarRacingResponseDto responseDto = carRacingService.start(requestDto);

        // 레이스 결과 출력
        carRacingOutputView.writeResult(responseDto);
    }
}
