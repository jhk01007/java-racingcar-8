package racingcar.view;

public class ConsoleOutputView implements OutputView {

    @Override
    public void writeResultByRound(String result) {
        System.out.println(result);
    }

    @Override
    public void writeFinalWinner(String winner) {
        System.out.println(winner);
    }
}
