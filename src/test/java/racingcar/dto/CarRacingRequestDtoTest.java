package racingcar.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarRacingRequestDtoTest {

    @Test
    @DisplayName("CarRacingRequestDto를 생성한다.")
    public void createCarRacingRequestDto_success() throws Exception {
        // given
        List<String> carNameList = List.of("a", "b", "c");
        int roundCount = 5;

        // when
        CarRacingRequestDto requestDto = new CarRacingRequestDto(carNameList, roundCount);

        // then
        assertThat(requestDto.carNameList()).hasSize(carNameList.size())
                .containsExactly("a", "b", "c");
        assertThat(requestDto.roundCount()).isEqualTo(roundCount);
    }

    @Test
    @DisplayName("CarRacingRequestDto를 생성할 때 자동차가 2대미만이면 오류가 발생한다..")
    public void createCarRacingRequestDto_fail1() throws Exception {
        // given
        List<String> carNameList = List.of("a");
        int roundCount = 5;

        // when // then
        assertThatThrownBy(() -> new CarRacingRequestDto(carNameList, roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차는 최소 2대 이상이어야 합니다.");
    }

    @Test
    @DisplayName("CarRacingRequestDto를 생성할 때 자동차 이름이 중복되면 오류가 발생한다..")
    public void createCarRacingRequestDto_fail2() throws Exception {
        // given
        List<String> carNameList = List.of("a", "a", "b");
        int roundCount = 5;

        // when // then
        assertThatThrownBy(() -> new CarRacingRequestDto(carNameList, roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("CarRacingRequestDto를 생성할 때 라운드 수가 1보다 작으면 오류가 발생한다..")
    public void createCarRacingRequestDto_fail3() throws Exception {
        // given
        List<String> carNameList = List.of("a", "b", "c");
        int roundCount = 0;

        // when // then
        assertThatThrownBy(() -> new CarRacingRequestDto(carNameList, roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("라운드는 최소 1개 이상이어야 합니다.");
    }
}