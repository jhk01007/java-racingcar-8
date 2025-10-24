package racingcar.service;

import camp.nextstep.edu.missionutils.test.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarRacingRequestDto;
import racingcar.dto.CarRacingResponseDto;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class CarRacingServiceTest {

    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    private final CarRacingService carRacingService = new CarRacingService();

    @Test
    @DisplayName("차의 이름 리스트와 라운드 수를 입력받아 자동차 레이싱을 진행한다.")
    public void start() throws Exception {
        // given
        List<String> carNameList = List.of("a", "b", "c");
        int roundCount = 3;
        CarRacingRequestDto requestDto = new CarRacingRequestDto(carNameList, roundCount);

        // when // then
        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    CarRacingResponseDto responseDto = carRacingService.start(requestDto);
                    List<CarRacingResponseDto.RacingRecordDto> records = responseDto.racingRecordDtos();

                    // 라운드별로 carPositions 검증
                    assertThat(records).hasSize(roundCount);

                    assertThat(records.get(0).carPositions())
                            .containsExactlyInAnyOrderEntriesOf(Map.of("a", 1, "b", 0, "c", 1));

                    assertThat(records.get(1).carPositions())
                            .containsExactlyInAnyOrderEntriesOf(Map.of("a", 2, "b", 1, "c", 2));

                    assertThat(records.get(2).carPositions())
                            .containsExactlyInAnyOrderEntriesOf(Map.of("a", 3, "b", 2, "c", 3));

                    // 최종 우승자 검증
                    List<String> winners = responseDto.winners();
                    assertThat(winners)
                            .containsExactlyInAnyOrder(carNameList.get(0), carNameList.get(2));
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD,  // 1라운드
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD,  // 2라운드
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD   // 3라운드
        );
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