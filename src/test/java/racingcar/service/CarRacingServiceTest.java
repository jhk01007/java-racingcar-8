package racingcar.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarRacingRequestDto;
import racingcar.dto.CarRacingResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CarRacingServiceTest {

    private CarRacingService carRacingService = new CarRacingService();

    @Test
    @DisplayName("")
    public void start() throws Exception {
        // given
        List<String> carNameList = List.of("a", "b", "c");
        int roundCount = 5;
        CarRacingRequestDto requestDto = new CarRacingRequestDto(carNameList, roundCount);

        // when
        CarRacingResponseDto responseDto = carRacingService.start(requestDto);

        // then - 결과는 무작위로 결정되기 때문에 정확한 결과를 테스트 하기 힘듦
        List<CarRacingResponseDto.RacingRecord> racingRecords = responseDto.racingRecords();

        assertThat(racingRecords)
                .hasSize(roundCount) // 라운드 수 = 기록 수
                .extracting(CarRacingResponseDto.RacingRecord::carPositions)
                .allSatisfy(carPositions -> {
                    assertThat(carPositions)
                            .hasSize(carNameList.size()) // 각 라운드에 모든 차의 위치가 기록됨
                            .containsOnlyKeys(carNameList); // (key = 차 이름) 모든 차 이름이 일치하는지 검증
                });


        List<String> winners = responseDto.winners();
        assertThat(winners)
                .isNotEmpty() // 최소 한 명의 우승자가 존재
                .allSatisfy(winner -> assertThat(carNameList).contains(winner)); // 모든 우승자가 유효한 차 이름
    }

}