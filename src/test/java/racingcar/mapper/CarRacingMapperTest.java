package racingcar.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarRacingRequestDto;
import racingcar.dto.CarRacingResponseDto;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarRacingMapperTest {

    @Test
    @DisplayName("문자열 형태의 차 이름 목록과 라운드 수를 CarRacingRequestDto 형태에 맞게 변환한다.")
    public void toRequestDto_success() throws Exception {
        // given
        String carNameList = "pobi,woni,jun";
        String roundCount = "5";

        // when
        CarRacingRequestDto dto = CarRacingMapper.toRequestDto(carNameList, roundCount);

        // then
        assertThat(dto).isNotNull();
        assertThat(dto.carNameList())
                .hasSize(3)
                .containsExactlyInAnyOrder("pobi", "woni", "jun");
        assertThat(dto.roundCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("라운드 수가 숫자 형태가 아니면 에러가 발생한다.")
    public void toRequestDto_fail() throws Exception {
        // given
        String carNameList = "pobi,woni,jun";
        String roundCount = "a";

        // when // then
        assertThatThrownBy(() -> CarRacingMapper.toRequestDto(carNameList, roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도할 횟수로 숫자만 가능합니다.");
    }

    @Test
    @DisplayName("RacingRecordDto 리스트를 문자열 형태로 변환한다.")
    public void toRawRaceRecord_success() throws Exception {
        // given
        List<CarRacingResponseDto.RacingRecordDto> racingRecordDtos = List.of(
                new CarRacingResponseDto.RacingRecordDto(linkedMap("a", 1, "b", 1, "c", 0)),
                new CarRacingResponseDto.RacingRecordDto(linkedMap("a", 1, "b", 2, "c", 1)),
                new CarRacingResponseDto.RacingRecordDto(linkedMap("a", 1, "b", 3, "c", 2)),
                new CarRacingResponseDto.RacingRecordDto(linkedMap("a", 1, "b", 3, "c", 3)),
                new CarRacingResponseDto.RacingRecordDto(linkedMap("a", 2, "b", 4, "c", 4))
        );

        // when
        String rawRaceRecord = CarRacingMapper.toRawRaceRecord(racingRecordDtos);

        // then
        Assertions.assertThat(rawRaceRecord)
                .isEqualTo("a : -\n" +
                        "b : -\n" +
                        "c : \n" +
                        "\n" +
                        "a : -\n" +
                        "b : --\n" +
                        "c : -\n" +
                        "\n" +
                        "a : -\n" +
                        "b : ---\n" +
                        "c : --\n" +
                        "\n" +
                        "a : -\n" +
                        "b : ---\n" +
                        "c : ---\n" +
                        "\n" +
                        "a : --\n" +
                        "b : ----\n" +
                        "c : ----\n");
    }

    private static LinkedHashMap<String, Integer> linkedMap(Object... kvs) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < kvs.length; i += 2) {
            map.put((String) kvs[i], (Integer) kvs[i + 1]);
        }
        return map;
    }

    @Test
    @DisplayName("리스트 형태의 우승자 목록을 콤마를 기준으로 문자열 형태로 변환한다.")
    public void toRawWinner() throws Exception {
        // given
        List<String> winners = List.of("b", "c");

        // when
        String rawWinner = CarRacingMapper.toRawWinner(winners);

        // then
        assertThat(rawWinner).isEqualTo("b, c");
    }

}