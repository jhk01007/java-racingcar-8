package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarRacingRequestDto;
import racingcar.dto.CarRacingResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CarRacingServiceTest {

    private final CarRacingService carRacingService = new CarRacingService();

    @Test
    @DisplayName("차의 이름 리스트와 라운드 수를 입력받아 자동차 레이싱을 진행한다.")
    public void start() throws Exception {
        // given
        List<String> carNameList = List.of("a", "b", "c");
        int roundCount = 5;
        CarRacingRequestDto requestDto = new CarRacingRequestDto(carNameList, roundCount);

        // when
        CarRacingResponseDto responseDto = carRacingService.start(requestDto);

        // then - 결과는 무작위로 결정되기 때문에 정확한 결과를 테스트 하기 힘듦
        List<CarRacingResponseDto.RacingRecordDto> racingRecordDtos = responseDto.racingRecordDtos();

        assertThat(racingRecordDtos)
                .hasSize(roundCount) // 라운드 수 = 기록 수
                .extracting(CarRacingResponseDto.RacingRecordDto::carPositions)
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

    @Test
    @DisplayName("자동차가 2대미만이면 오류가 발생한다.")
    public void start_fail1() throws Exception {
        // given
        List<String> carNameList = List.of("a");
        int roundCount = 5;
        CarRacingRequestDto requestDto = new CarRacingRequestDto(carNameList, roundCount);

        // when // then
        assertThatThrownBy(() -> carRacingService.start(requestDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차는 최소 2대 이상이어야 합니다.");
    }

    @Test
    @DisplayName("자동차 이름이 중복되면 오류가 발생한다.")
    public void start_fail2() throws Exception {
        // given
        List<String> carNameList = List.of("a", "a", "b");
        int roundCount = 5;
        CarRacingRequestDto requestDto = new CarRacingRequestDto(carNameList, roundCount);

        // when // then
        assertThatThrownBy(() -> carRacingService.start(requestDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("라운드 수가 1보다 작으면 오류가 발생한다.")
    public void start_fail3() throws Exception {
        // given
        List<String> carNameList = List.of("a", "b", "c");
        int roundCount = 0;
        CarRacingRequestDto requestDto = new CarRacingRequestDto(carNameList, roundCount);

        // when // then
        assertThatThrownBy(() -> carRacingService.start(requestDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("라운드는 최소 1개 이상이어야 합니다.");
    }

}