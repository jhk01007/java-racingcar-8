package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class RacingGameTest {


    @Test
    @DisplayName("자동차가 2대미만이면 오류가 발생한다.")
    public void create_error1() throws Exception {
        // given
        List<RacingCar> racingCars = List.of(createRacingCar("a"));
        int roundCount = 5;

        // when // then
        Assertions.assertThatThrownBy(() -> RacingGame.create(racingCars, roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차는 최소 2대 이상이어야 합니다.");
    }

    @Test
    @DisplayName("자동차 이름이 중복되면 오류가 발생한다.")
    public void create_error2() throws Exception {
        // given
        List<RacingCar> racingCars = List.of(
                createRacingCar("a"),
                createRacingCar("a")
        );
        int roundCount = 5;

        // when // then
        Assertions.assertThatThrownBy(() -> RacingGame.create(racingCars, roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("라운드 수가 1보다 작으면 오류가 발생한다.")
    public void create_error3() throws Exception {
        // given
        List<RacingCar> racingCars = List.of(
                createRacingCar("a"),
                createRacingCar("b")
        );
        int roundCount = 0;

        // when // then
        Assertions.assertThatThrownBy(() -> RacingGame.create(racingCars, roundCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("라운드는 최소 1개 이상이어야 합니다.");
    }

    private static RacingCar createRacingCar(String name) {
        return RacingCar.create(Car.create(name), 0);
    }

}