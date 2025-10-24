package racingcar.mapper;

import racingcar.domain.RacingRecord;
import racingcar.dto.RacingGameRequestDto;
import racingcar.dto.RacingGameResponseDto;
import racingcar.domain.Car;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 자동차 경주에 관해서 객체 매핑을 담당하는 클래스
 */
public class CarRacingMapper {

    public static Car toDomain(String carName) {
        return Car.create(carName);
    }

    /**
     * 문자열 형태의 자동차 목록, 라운드 수를 CarRacingRequestDto로 변환
     *
     * @param carNameList - 자동차 목록
     * @param roundCount  - 라운드 수
     */
    public static RacingGameRequestDto toRequestDto(String carNameList, String roundCount) {
        return new RacingGameRequestDto(
                mapCarNameList(carNameList), mapRoundCount(roundCount));
    }

    /**
     * RacingRecord 도메인 리스트를 RacingRecordDto로 변환
     *
     * @param racingRecords - RacingRecord 도메인 리스트
     */
    public static List<RacingGameResponseDto.RacingRecordDto> toRacingRecordDto(List<RacingRecord> racingRecords) {
        return racingRecords.stream()
                .map(racingRecord -> new RacingGameResponseDto.RacingRecordDto(racingRecord.getCarPositions()))
                .toList();
    }

    /**
     * RacingRecordDto 리스트를 문자열 형태로 변환
     *
     * @param racingRecordDtos - RacingRecordDto 리스트
     */
    public static String toRawRaceRecord(List<RacingGameResponseDto.RacingRecordDto> racingRecordDtos) {
        StringBuilder rawRaceRecode = new StringBuilder();

        for (RacingGameResponseDto.RacingRecordDto racingRecordDto : racingRecordDtos) {
            Map<String, Integer> carPositions = racingRecordDto.carPositions();
            for (String carName : carPositions.keySet()) {
                rawRaceRecode.append(carName).append(" : ");
                rawRaceRecode.append("-".repeat(Math.max(0, carPositions.get(carName))));
                rawRaceRecode.append("\n");
            }
            rawRaceRecode.append("\n");
        }
        rawRaceRecode.deleteCharAt(rawRaceRecode.length() - 1); // 마지막에 들어가는 개행문자 제거
        return rawRaceRecode.toString();
    }

    /**
     * 우승자 리스트를 문자열 형태로 변환
     *
     * @param winners - 우승자 리스트
     */
    public static String toRawWinner(List<String> winners) {
        return String.join(", ", winners);
    }

    private static List<String> mapCarNameList(String carNameList) {
        String[] split = carNameList.split(",", -1);
        return Arrays.stream(split)
                .map(String::trim) // 이름 앞 뒤에 포함되어 있는 공백 제거
                .toList();
    }

    private static int mapRoundCount(String raceCount) {
        int mappedRoundCount;
        try {
            mappedRoundCount = Integer.parseInt(raceCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도할 횟수로 숫자만 가능합니다.");
        }
        return mappedRoundCount;
    }
}
