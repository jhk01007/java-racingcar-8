package racingcar.domain;

import java.util.List;
import java.util.Map;

/**
 * 특정 라운드의 모든 차의 현재 위치를 담고 있는 클래스
 */
public class RacingRecord {

    private Map<String, Integer> carPositions; // Key: 차이름, Value: 현재 위치

    private RacingRecord(Map<String, Integer> carPositions) {
        this.carPositions = carPositions;
    }

    public Map<String, Integer> getCarPositions() {
        return carPositions;
    }

    public static RacingRecord create(Map<String, Integer> carPositions) {
        return new RacingRecord(carPositions);
    }

    public List<String> getTopCarName() {
        // 가장 큰 position(우승 거리)를 구함
        int maxPosition = carPositions.values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0); // 비어 있을 경우 기본값

        // 해당 우승거리와 같은 차를 뽑아냄
        return carPositions.entrySet().stream()
                .filter(entry -> entry.getValue() == maxPosition)
                .map(Map.Entry::getKey)
                .toList();
    }
}
