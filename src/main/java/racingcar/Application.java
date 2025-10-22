package racingcar;

import racingcar.controller.CarRacingController;
import racingcar.service.CarRacingService;
import racingcar.view.ConsoleCarRacingInputView;
import racingcar.view.ConsoleCarRacingOutputView;

public class Application {
    public static void main(String[] args) {
        ConsoleCarRacingInputView carRacingInputView = new ConsoleCarRacingInputView();
        ConsoleCarRacingOutputView carRacingOutputView = new ConsoleCarRacingOutputView();
        CarRacingService carRacingService = new CarRacingService();

        CarRacingController carRacingController = new CarRacingController(carRacingInputView, carRacingOutputView, carRacingService);
        carRacingController.start();
    }
}
