package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    @DisplayName("자동차 2대, 라운드 1개")
    void success1() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("자동차 3대, 라운드 5개")
    void success2() {
        CharSequence[] results = {
                "pobi : -", "woni : ", "jun : -", // 1라운드
                "pobi : --", "woni : -", "jun : --", // 2라운드
                "pobi : ---", "woni : --", "jun : ---", // 3라운드
                "pobi : ----", "woni : ---", "jun : ----", // 4라운드
                "pobi : -----", "woni : ----", "jun : -----", // 5라운드
                "최종 우승자 : pobi, jun" // 최종 결과
        };
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "5");
                    assertThat(output()).contains(results);
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD, // 1라운드
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD, // 2라운드
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD, // 3라운드
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD, // 4라운드
                MOVING_FORWARD, MOVING_FORWARD, MOVING_FORWARD // 5라운드
        );
    }

    @Test
    @DisplayName("자동차의 이름이 5글자가 넘으면 에러가 발생한다.")
    void fail1() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("자동차의 이름은 최대 5글자까지 가능합니다.")
        );
    }

    @Test
    @DisplayName("자동차의 이름이 1글자도 안되면 에러가 발생한다.")
    void fail2() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("자동차의 이름은 최소 1글자 이상이어야 합니다.")
        );
    }

    @Test
    @DisplayName("자동차 수가 2대 미만이면 에러가 발생한다.")
    void fail3() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("자동차는 최소 2대 이상이어야 합니다.")
        );
    }

    @Test
    @DisplayName("자동차 이름이 중복되면 에러가 발생한다.")
    void fail4() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,pobi,tom", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("자동차 이름은 중복될 수 없습니다.")
        );
    }

    @Test
    @DisplayName("입력받은 라운드 수가 1보다 작은 경우 에러가 발생한다.")
    void fail5() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,tom", "0"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("라운드는 최소 1개 이상이어야 합니다.")
        );
    }

    @Test
    @DisplayName("입력받은 라운드 수가 1보다 작은 경우 에러가 발생한다.")
    void fail6() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,tom", "0"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("라운드는 최소 1개 이상이어야 합니다.")
        );
    }

    @Test
    @DisplayName("입력받은 라운드 수가 숫자가 아닌 경우 에러가 발생한다.")
    void fail7() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,tom", "a"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("시도할 횟수로 숫자만 가능합니다.")
        );
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
