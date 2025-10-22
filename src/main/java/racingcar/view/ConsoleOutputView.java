package racingcar.view;

public class ConsoleOutputView implements OutputView {

    @Override
    public void writeRaceRecord(String result) {
        System.out.println("실행 결과");
        System.out.println(result);
    }

    @Override
    public void writeWinner(String winner) {
        System.out.printf("최종 우승자 : %s\n", winner);
    }
}
