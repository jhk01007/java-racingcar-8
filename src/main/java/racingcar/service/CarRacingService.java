package racingcar.service;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.dto.CarRacingRequestDto;
import racingcar.dto.CarRacingResponseDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 자동차 경주 로직이 담긴 클래스
 */
public class CarRacingService {

    public CarRacingResponseDto start(CarRacingRequestDto requestDto) {

        List<String> carNameList = requestDto.carNameList();
        int roundCount = requestDto.roundCount();

        // 각 차의 현재 위치를 담을 Map 초기화
        Map<String, Integer> carPositions = new HashMap<>(carNameList.size());
        for (String carName : carNameList) {
            carPositions.put(carName, 0); // 현재 위치를 0으로 초기화
        }

        // 레이스 시작
        List<CarRacingResponseDto.RacingRecord> racingRecords = new ArrayList<>();
        for (int curRound = 1; curRound <= roundCount; curRound++) {
            // 각 차에 대한 이동여부 결정
            for (String carName : carNameList) {
                int randomNumber = Randoms.pickNumberInRange(0, 9);
                if (randomNumber >= 4) {
                    // 난수가 4 이상이면 1 만큼 이동
                    carPositions.put(carName, carPositions.get(carName) + 1);
                }
            }

            // 현재 라운드에 대한 기록을 저장
            HashMap<String, Integer> curRoundResult = new HashMap<>(carPositions);
            racingRecords.add(new CarRacingResponseDto.RacingRecord(curRoundResult));
        }

        // 레이스 결과 정산
        Map<String, Integer> raceResult = racingRecords.getLast().carPositions();

        // 가장 큰 position(우승 거리)를 구함
        int maxPosition = raceResult.values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0); // 비어 있을 경우 기본값

        // 해당 우승거리와 같은 차를 뽑아냄
        List<String> winners = raceResult.entrySet().stream()
                .filter(entry -> entry.getValue() == maxPosition)
                .map(Map.Entry::getKey)
                .toList();


        return new CarRacingResponseDto(racingRecords, winners);
    }
}
