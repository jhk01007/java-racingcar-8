package racingcar.dto;

import java.util.List;

public record CarRacingRequestDto(
        List<String> carNameList,
        int roundCount
) {
}