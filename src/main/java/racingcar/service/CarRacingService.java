package racingcar.service;

import racingcar.domain.CarRacing;
import racingcar.dto.CarRacingRequestDto;
import racingcar.dto.CarRacingResponseDto;
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
    public CarRacingResponseDto start(CarRacingRequestDto requestDto) {
        // Car 도메인 객체로 변환
        List<Car> cars = convertToDomain(requestDto.carNameList());

        // CarRacing 객체 생성
        CarRacing carRacing = CarRacing.create(cars, requestDto.roundCount());

        // 레이스 시작
        carRacing.startRace();

        return new CarRacingResponseDto(
                CarRacingMapper.toRacingRecordDto(carRacing.getRacingRecords()),
                carRacing.getWinners()
        );
    }

    private static List<Car> convertToDomain(List<String> carNameList) {
        return carNameList.stream()
                .map(CarRacingMapper::toDomain)
                .toList();
    }
}
