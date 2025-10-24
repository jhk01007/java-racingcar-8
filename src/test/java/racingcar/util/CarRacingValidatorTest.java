package racingcar.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.RacingCar;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CarRacingValidatorTest {


    @Test
    @DisplayName("자동차가 2대 미만이거나 null이면 예외가 발생한다")
    void validateCarListSize_fail() {
        // given
        List<RacingCar> oneCar = racingCars("only");
        List<RacingCar> nullList = null;

        // expect
        assertThatThrownBy(() -> CarRacingValidator.validateCarListSize(oneCar))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차는 최소 2대 이상이어야 합니다.");

        assertThatThrownBy(() -> CarRacingValidator.validateCarListSize(nullList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차는 최소 2대 이상이어야 합니다.");
    }

    @Test
    @DisplayName("자동차 이름이 중복되면 예외가 발생한다")
    void validateCarNameDuplicate_fail() {
        // given
        List<RacingCar> cars = racingCars("dup", "dup", "other");

        // expect
        assertThatThrownBy(() -> CarRacingValidator.validateCarNameDuplicate(cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 비어있으면 예외가 발생한다")
    void validateCarNameLength_empty_fail() {
        // given
        String empty = "";

        // expect
        assertThatThrownBy(() -> CarRacingValidator.validateCarNameLength(empty))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차의 이름은 최소 1글자 이상이어야 합니다.");
    }

    @Test
    @DisplayName("이름이 6자 이상이면 예외가 발생한다")
    void validateCarNameLength_tooLong_fail() {
        // given
        String longName = "abcdef"; // 6자

        // expect
        assertThatThrownBy(() -> CarRacingValidator.validateCarNameLength(longName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차의 이름은 최대 5글자까지 가능합니다.");
    }

    @Test
    @DisplayName("라운드가 0 이하이면 예외가 발생한다")
    void validateRoundCount_fail() {
        // given
        int zero = 0;
        int negative = -3;

        // expect
        assertThatThrownBy(() -> CarRacingValidator.validateRoundCount(zero))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("라운드는 최소 1개 이상이어야 합니다.");

        assertThatThrownBy(() -> CarRacingValidator.validateRoundCount(negative))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("라운드는 최소 1개 이상이어야 합니다.");
    }

    // --- helper ---
    private static List<RacingCar> racingCars(String... names) {
        return java.util.Arrays.stream(names)
                .map(name -> RacingCar.create(
                        Car.create(name),
                        0
                ))
                .toList();
    }

}