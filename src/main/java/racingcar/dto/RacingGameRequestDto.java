package racingcar.dto;

import java.util.List;

public record RacingGameRequestDto(
        List<String> carNameList,
        int roundCount
) {
}