package racingcar.view;


import camp.nextstep.edu.missionutils.Console;

public class ConsoleRacingGameInputView implements RacingGameInputView {
    private static final String CAR_NAME_GUIDE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ROUND_COUNT_GUIDE = "시도할 횟수는 몇 회인가요?";

    @Override
    public String readCarNames() {
        System.out.println(CAR_NAME_GUIDE);
        return Console.readLine();
    }

    @Override
    public String readRoundCount() {
        System.out.println(ROUND_COUNT_GUIDE);
        return Console.readLine();
    }
}
