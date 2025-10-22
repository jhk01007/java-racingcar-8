package racingcar.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @Test
    @DisplayName("Car를 생성할 때 name이 다섯글자 이상이면 에러가 발생한다.")
    public void create_fail1() throws Exception {
        // given
        String name = "abcdef";

        // when // then
        assertThatThrownBy(() -> Car.create(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차의 이름은 최대 5글자까지 가능합니다.");
    }


    @Test
    @DisplayName("Car를 생성할 때 name이 1글자 미만이면 에러가 발생한다.")
    public void create_fail2() throws Exception {
        // given
        String name = "";

        // when // then
        assertThatThrownBy(() -> Car.create(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차의 이름은 최소 1글자 이상이어야 합니다.");
    }

}