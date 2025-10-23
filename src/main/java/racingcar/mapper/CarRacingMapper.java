package racingcar.mapper;

import racingcar.dto.CarRacingRequestDto;
import racingcar.dto.CarRacingResponseDto;
import racingcar.model.Car;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CarRacingMapper {

    public static Car toDomain(String carName) {
        return Car.create(carName);
    }

    public static CarRacingRequestDto toRequestDto(String carNameList, String raceCount) {

        String[] mappedCarNameList = mapCarNameList(carNameList);

        int mappedRoundCount = mapRoundCount(raceCount);
        return new CarRacingRequestDto(Arrays.asList(mappedCarNameList), mappedRoundCount);
    }

    public static String toRawRaceRecord(List<CarRacingResponseDto.RacingRecord> racingRecords) {
        StringBuilder rawRaceRecode = new StringBuilder();

        for (CarRacingResponseDto.RacingRecord racingRecord : racingRecords) {
            Map<String, Integer> carPositions = racingRecord.carPositions();
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

    public static String toRawWinner(List<String> winners) {
        return String.join(", ", winners);
    }

    private static String[] mapCarNameList(String carNameList) {
        return carNameList.split(",", -1);
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
