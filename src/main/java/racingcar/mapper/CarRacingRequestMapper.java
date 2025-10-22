package racingcar.mapper;

import racingcar.dto.CarRacingRequestDto;

import java.util.Arrays;


public class CarRacingRequestMapper {

    private static final String ROUND_COUNT_PARSE_ERROR_MESSAGE = "시도할 횟수로 숫자를 입력해주세요.";


    public static CarRacingRequestDto toDto(String carNameList, String raceCount) {

        String[] mappedCarNameList = mapCarNameList(carNameList);

        int mappedRoundCount = mapRoundCount(raceCount);
        return new CarRacingRequestDto(Arrays.asList(mappedCarNameList), mappedRoundCount);
    }

    private static int mapRoundCount(String raceCount) {
        int mappedRoundCount = 0;
        try {
            mappedRoundCount = Integer.parseInt(raceCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ROUND_COUNT_PARSE_ERROR_MESSAGE);
        }
        return mappedRoundCount;
    }

    private static String[] mapCarNameList(String carNameList) {
        return carNameList.split(",", -1);
    }
}
