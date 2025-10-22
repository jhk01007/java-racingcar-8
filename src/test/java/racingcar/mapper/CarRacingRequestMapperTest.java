package racingcar.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarRacingRequestDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarRacingRequestMapperTest {

    @Test
    @DisplayName("문자열 형태의 차 이름 목록과 라운드 수를 CarRacingRequestDto 형태에 맞게 변환한다.")
    public void toDto_success() throws Exception {
        // given
        String carNameList = "pobi,woni,jun";
        String roundCount = "5";

        // when
        CarRacingRequestDto dto = CarRacingRequestMapper.toDto(carNameList, roundCount);

        // then
        assertThat(dto).isNotNull();
        assertThat(dto.carNameList())
                .hasSize(3)
                .containsExactlyInAnyOrder("pobi", "woni", "jun");
        assertThat(dto.roundCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("라운드 수가 숫자 형태가 아니면 에러가 발생한다.")
    public void toDto_fail() throws Exception {
        // given
        String carNameList = "pobi,woni,jun";
        String roundCount = "a";

        // when // then
        assertThatThrownBy(() -> CarRacingRequestMapper.toDto(carNameList, roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도할 횟수로 숫자를 입력해주세요.");
    }

}