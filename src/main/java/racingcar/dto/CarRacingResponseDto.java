package racingcar.dto;

import java.util.List;
import java.util.Map;

public record CarRacingResponseDto(
        List<RacingRecord> racingRecords,
        List<String> winners
) {

    /**
     * 특정 라운드의 모든 차의 현재 위치를 담고 있는 클래스
     * @param carPositions - 차의 위치
     */
    public record RacingRecord(
            Map<String, Integer> carPositions // Key: 차이름, Value: 현재 위치
    ) {
    }
}
