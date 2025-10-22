package racingcar.service;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.dto.CarRacingRequestDto;
import racingcar.dto.CarRacingResponseDto;
import racingcar.mapper.CarRacingMapper;
import racingcar.model.Car;

import java.util.*;

/**
 * 자동차 경주 로직을 담당하는 클래스
 */
public class CarRacingService {

    /**
     * 레이스를 처리한다.
     * @param requestDto - 자동차 정보와 라운드 수가 담긴 DTO
     * @return 라운드별 과정과 최종 우승자가 담긴 DTO
     */
    public CarRacingResponseDto start(CarRacingRequestDto requestDto) {

        // Car 도메인 객체로 변환
        List<Car> cars = convertToDomain(requestDto.carNameList());

        // 각 자동차의 현재 위치를 담을 Map 초기화
        Map<String, Integer> carPositions = initCarPositionMap(cars);

        // 레이스 시작
        List<CarRacingResponseDto.RacingRecord> racingRecords = startRace(requestDto.roundCount(), cars, carPositions);

        // 레이스 결과 정산
        List<String> winners = processRaceResult(racingRecords);

        return new CarRacingResponseDto(racingRecords, winners);
    }

    private static List<Car> convertToDomain(List<String> carNameList) {
        return carNameList.stream()
                .map(CarRacingMapper::toDomain)
                .toList();
    }

    private static Map<String, Integer> initCarPositionMap(List<Car> cars) {
        Map<String, Integer> carPositions = new LinkedHashMap<>(cars.size());
        for (Car car : cars) {
            carPositions.put(car.getName(), 0); // 현재 위치를 0으로 초기화
        }
        return carPositions;
    }

    private static List<CarRacingResponseDto.RacingRecord> startRace(int roundCount, List<Car> cars, Map<String, Integer> carPositions) {
        List<CarRacingResponseDto.RacingRecord> racingRecords = new ArrayList<>();
        for (int curRound = 1; curRound <= roundCount; curRound++) {
            // 각 차에 대한 이동여부 결정
            for (Car car : cars) {
                int randomNumber = Randoms.pickNumberInRange(0, 9);
                if (randomNumber >= 4) {
                    // 난수가 4 이상이면 1 만큼 이동
                    carPositions.put(car.getName(), carPositions.get(car.getName()) + 1);
                }
            }

            // 현재 라운드에 대한 기록을 저장
            HashMap<String, Integer> curRoundResult = new HashMap<>(carPositions);
            racingRecords.add(new CarRacingResponseDto.RacingRecord(curRoundResult));
        }
        return racingRecords;
    }

    private static List<String> processRaceResult(List<CarRacingResponseDto.RacingRecord> racingRecords) {

        Map<String, Integer> raceResult = racingRecords.getLast().carPositions();

        // 가장 큰 position(우승 거리)를 구함
        int maxPosition = raceResult.values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0); // 비어 있을 경우 기본값

        // 해당 우승거리와 같은 차를 뽑아냄
        return raceResult.entrySet().stream()
                .filter(entry -> entry.getValue() == maxPosition)
                .map(Map.Entry::getKey)
                .toList();
    }

}
