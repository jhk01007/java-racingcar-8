package racingcar.service;

import racingcar.domain.RacingGame;
import racingcar.domain.RacingCar;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.mapper.CarRacingMapper;
import racingcar.domain.Car;
import java.util.*;

/**
 * 자동차 경주 로직을 담당하는 클래스
 */
public class CarRacingService {

    /**
     * 레이스를 처리한다.
     *
     * @param requestDto - 자동차 정보와 라운드 수가 담긴 DTO
     * @return 라운드별 과정과 최종 우승자가 담긴 DTO
     */
    public RacingGameResponseDto start(RacingGameRequestDto requestDto) {
        // Car 도메인 객체로 변환
        List<Car> cars = convertToDomain(requestDto.carNameList());

        // RacingCar 도메인 객체 생성
        List<RacingCar> racingCars = createRacingCar(cars);

        // RacingGame 객체 생성
        RacingGame racingGame = RacingGame.create(racingCars, requestDto.roundCount());

        // 레이스 시작
        racingGame.startRace();

        return new RacingGameResponseDto(
                CarRacingMapper.toRacingRecordDto(racingGame.getRacingRecords()),
                racingGame.getWinners()
        );
    }

    private static List<Car> convertToDomain(List<String> carNameList) {
        return carNameList.stream()
                .map(CarRacingMapper::toDomain)
                .toList();
    }

    private static List<RacingCar> createRacingCar(List<Car> cars) {
        return cars.stream()
                .map(car -> RacingCar.create(car, 0))
                .toList();
    }
}
