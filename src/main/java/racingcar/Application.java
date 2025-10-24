package racingcar;

import racingcar.controller.RacingGameController;
import racingcar.service.CarRacingService;
import racingcar.view.ConsoleRacingGameInputView;
import racingcar.view.ConsoleRacingGameOutputView;

public class Application {
    public static void main(String[] args) {
        ConsoleRacingGameInputView carRacingInputView = new ConsoleRacingGameInputView();
        ConsoleRacingGameOutputView carRacingOutputView = new ConsoleRacingGameOutputView();
        CarRacingService carRacingService = new CarRacingService();

        RacingGameController racingGameController = new RacingGameController(carRacingInputView, carRacingOutputView, carRacingService);
        racingGameController.start();
    }
}
